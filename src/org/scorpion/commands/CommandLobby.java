package org.scorpion.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.scorpion.api.ServerAPI;

public class CommandLobby implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            ServerAPI.connect(p, "lobby");
        }
        return false;
    }
}
