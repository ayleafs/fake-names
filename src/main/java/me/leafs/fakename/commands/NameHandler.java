package me.leafs.fakename.commands;

import me.leafs.fakename.FakeName;
import me.leafs.fakename.utils.ChatUtils;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.Arrays;
import java.util.List;

public class NameHandler implements ICommand {
    @Override
    public String getCommandName() {
        return "fakename";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/fakename <name: string>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("fakeign", "hidename", "revealname");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        FakeName instance = FakeName.instance;

        // if no arguments were provided then reset their name
        if (args.length == 0) {
            instance.setFakeName(null);
            ChatUtils.printChat("&7Your fake name has been&d reset&7. To use this command please type &b/fakename <some name>");
            return;
        }

        // allow spaces cuz we're cool like that
        String fakeName = String.join(" ", args);
        instance.setFakeName(fakeName);

        ChatUtils.printChat("&7Your fake name has been set to &d" + fakeName + "&7.");
        ChatUtils.printChat("&7Good choice... I think? I don't know this was hard-coded...\n&f- leafs &d<3");
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
