package org.scorpion.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.scorpion.SurvivalGames;
import org.scorpion.api.SurvivalGamesAPI;

public class CommandStart implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("sg.start")){
                int current = SurvivalGamesAPI.survivalGamesPlayer.size();
                int find = SurvivalGamesAPI.MIN_PLAYERS-SurvivalGamesAPI.survivalGamesPlayer.size();
                if(SurvivalGames.countdown > 15
                        && SurvivalGamesAPI.state.getState() == 0
                        && SurvivalGamesAPI.survivalGamesPlayer.size() >= SurvivalGamesAPI.MIN_PLAYERS){
                    SurvivalGames.countdown = 11;
                    p.sendMessage(SurvivalGamesAPI.getPrefix()+"§aDu hast das Spiel gestartet!");
                }else{
                    p.sendMessage(SurvivalGamesAPI.getPrefix()+"§cDu kannst das Spiel nicht starten!");
                }
            }
        }
        return false;
    }
}
