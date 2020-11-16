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
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("sg.admin")){
                if(args.length == 2){
                    p.sendMessage("§c"+args[0]+" : "+args[1]);
                    String data = args[0];
                    String input = args[1];
                    if(data.equalsIgnoreCase("name")){
                        SurvivalGamesAPI.setGameName(input);
                        p.sendMessage(SurvivalGamesAPI.getPrefix()+"§7Du hast die Arena §e"+input+" §7genannt!");
                    }
                }else if(args.length == 1){
                    String data = args[0];
                    if(data.equalsIgnoreCase("nextspawn")){
                        p.sendMessage(SurvivalGamesAPI.getPrefix()+"§aDu hast Spawn '"+SurvivalGamesAPI.getCurrentCount()+"' gesetzt!");
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
                    p.sendMessage("§aSG-Admin");
                }
            }else{
                p.sendMessage(SurvivalGamesAPI.getPrefix()+"§7Version: §6"+SurvivalGames.getPlugin().getDescription().getVersion());
            }
        }
        return false;
    }

}
