package com.example.homelander;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HomelanderMod implements ModInitializer {
    public static final Identifier LASER_PACKET = new Identifier("homelander", "laser_fire");

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (!player.getAbilities().allowFlying) {
                    player.getAbilities().allowFlying = true;
                    player.sendAbilities();
                }
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(LASER_PACKET, (server, player, handler, buf, responseSender) ->
            server.execute(() -> fireLaser(player))
        );

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient) {
                entity.damage(DamageSource.player(player), 8.0F);
            }
            return ActionResult.PASS;
        });
    }

    private static void fireLaser(ServerPlayerEntity player) {
        World world = player.world;
        HitResult hitResult = player.raycast(30.0, 0.0F, false);

        if (hitResult.getType() == HitResult.Type.BLOCK && hitResult instanceof BlockHitResult blockHit) {
            BlockPos pos = blockHit.getBlockPos();
            world.breakBlock(pos, true, player);
        } else if (hitResult.getType() == HitResult.Type.ENTITY && hitResult instanceof EntityHitResult entityHit) {
            entityHit.getEntity().damage(DamageSource.player(player), 8.0F);
        }
    }
}
