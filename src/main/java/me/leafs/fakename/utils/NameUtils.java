package me.leafs.fakename.utils;

import com.mojang.authlib.GameProfile;
import me.leafs.fakename.FakeName;
import net.minecraft.client.Minecraft;

public class NameUtils {
    @SuppressWarnings("unused") // it is used :(
    public static String apply(String input) {
        FakeName instance = FakeName.instance;
        if (instance == null) {
            return input;
        }

        // stupid bitch
        ConfigHandler config = instance.getConfig();
        if (config == null) {
            return input;
        }

        String fakeName = config.getFakeName();
        if (fakeName == null || input == null) {
            return input; // don't proceed if the fakeName or input isn't set
        }

        GameProfile profile = Minecraft.getMinecraft().getSession().getProfile();
        // idfk how but we can never be too sure
        if (profile == null) {
            return input; // do nothing to the input
        }

        String realName = profile.getName();
        // return the input with the real IGN replaced
        return input.replace(realName, fakeName);
    }
}
