package com.example.homelander;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class HomelanderClientMod implements ClientModInitializer {
    public static KeyBinding LASER_KEY;

    @Override
    public void onInitializeClient() {
        LASER_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.homelander.laser",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                "category.homelander.powers"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && LASER_KEY.wasPressed()) {
                ClientPlayNetworking.send(HomelanderMod.LASER_PACKET, PacketByteBufs.create());
            }
        });
    }
}
