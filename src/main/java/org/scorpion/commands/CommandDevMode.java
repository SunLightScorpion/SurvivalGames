package org.scorpion.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.scorpion.api.SurvivalGamesAPI;

public class CommandDevMode implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player p){
            if(p.hasPermission("sg.admin")){
                SurvivalGamesAPI.startGame();
            }
        }
        return false;
    }
}
