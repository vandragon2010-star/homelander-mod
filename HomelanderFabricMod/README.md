# Homelander Fabric Mod

A simple Fabric mod that gives the player Homelander-style powers:
- Flight by enabling flying for all players
- Super strength via extra attack damage
- Laser vision bound to a key (`L` by default)
- Laser vision can break blocks and damage entities

## Build & run

1. Install Java 17 and Gradle or use the Gradle Wrapper.
2. From the mod folder, run:
   - `gradle build`
3. Place the generated JAR from `build/libs/` into your Fabric `mods/` folder.
4. Launch Minecraft with Fabric Loader and Fabric API.

## Controls

- Press `L` to fire laser vision in the direction you are looking.

## Notes

- The laser only breaks one block at a time and avoids heavy visual effects to keep performance simple.
