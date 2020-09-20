package me.leafs.fakename.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.ChatComponentText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtils {
    // stolen straight from EnumChatFormatting (why'd they make it fucking private?)
    private static final Pattern COLOR_FORMAT = Pattern.compile("(?i)" + '\u00a7' + "[0-9A-FK-OR]");

    public static void printChat(String message) {
        GuiNewChat chatGUI = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        Matcher matcher = COLOR_FORMAT.matcher(message);

        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            message = message.replace(fullMatch, ChatFormatting.getByChar(fullMatch.charAt(1)).toString());
        }

        chatGUI.printChatMessage(new ChatComponentText(message));
    }
}
