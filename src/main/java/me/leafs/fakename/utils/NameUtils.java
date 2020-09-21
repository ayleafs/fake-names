package me.leafs.fakename.utils;

import com.mojang.authlib.GameProfile;
import me.leafs.fakename.FakeName;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

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
        GameProfile profile = Minecraft.getMinecraft().getSession().getProfile();
        if (fakeName == null || input == null || profile == null) {
            return input; // don't proceed if the fakeName or input isn't set
        }

        String realName = profile.getName();
        if (!input.contains(realName)) {
            return input;
        }

        String colored = ChatUtils.color(fakeName);

        // add reset code to prevent color fuck ups
        if (!colored.equals(fakeName)) {
            // TODO: 9/21/2020 make this better

            colored += EnumChatFormatting.RESET;
        }

        // return the input with the real IGN replaced
        return input.replace(realName, colored);
    }
}
