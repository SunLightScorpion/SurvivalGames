package org.scorpion.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.scorpion.SurvivalGames;
import org.scorpion.api.SurvivalGamesAPI;

public class CommandSurvivalGamesAdmin implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player p){
            if(p.hasPermission("sg.admin")){
                if(args.length == 2){
                    p.sendMessage("§c"+args[0]+" : "+args[1]);
                    String data = args[0];
                    String input = args[1];
                    if(data.equalsIgnoreCase("name")){
                        SurvivalGamesAPI.setGameName(input);
                        p.sendMessage(SurvivalGamesAPI.getPrefix()+"§7You have named the arena §e"+input+" §7!");
                    }
                }else if(args.length == 1){
                    String data = args[0];
                    if(data.equalsIgnoreCase("nextspawn")){
                        p.sendMessage(SurvivalGamesAPI.getPrefix()+"§aYou have set spawn §b"+SurvivalGamesAPI.getCurrentCount()+"§a!");
                        SurvivalGamesAPI.setSpawn(p.getLocation());
                        SurvivalGamesAPI.count();
                    }
                    if(data.equalsIgnoreCase("finish")){
                        p.sendMessage(SurvivalGamesAPI.getPrefix()+"§aDu hast die Arena fertig eingestellt!");
                        SurvivalGamesAPI.finishArena();
                        SurvivalGamesAPI.kickAll();
                        Bukkit.reload();
                    }
                }else{
                    p.sendMessage(SurvivalGamesAPI.getPrefix()+"§aSG-Admin:");
                    p.sendMessage(SurvivalGamesAPI.getPrefix()+"§a/sga name <arena> §7| §6Set the name of the arena.");
                    p.sendMessage(SurvivalGamesAPI.getPrefix()+"§a/sga nextspawn §7| §6Set the spawns of the players.");
                    p.sendMessage(SurvivalGamesAPI.getPrefix()+"§a/sga finish §7| §6You are finish? Save the arena!");
                }
            }else{
                p.sendMessage(SurvivalGamesAPI.getPrefix()+"§7Version: §6"+SurvivalGames.getPlugin().getDescription().getVersion());
            }
        }
        return false;
    }

}
