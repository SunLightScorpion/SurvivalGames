package org.scorpion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.scorpion.api.SurvivalGamesAPI;
import org.scorpion.commands.*;
import org.scorpion.listener.SurvivalGamesListener;
import org.scorpion.state.GameState;

import java.util.Objects;

public class SurvivalGames extends JavaPlugin{

    private static SurvivalGames plugin;
    public static int countdown;
    private int waiter;
    private int taskStart;
    private int taskTester;
    private final GameState state = SurvivalGamesAPI.state;

    @Override
    public void onEnable() {
        plugin = this;
        countdown = SurvivalGamesAPI.WAIT_TIME;

        SurvivalGamesAPI.init();

        getServer().getPluginManager().registerEvents(new SurvivalGamesListener(), this);
        Objects.requireNonNull(getCommand("survivalgamesadmin")).setExecutor(new CommandSurvivalGamesAdmin());
        Objects.requireNonNull(getCommand("sga")).setExecutor(new CommandSurvivalGamesAdmin());
        Objects.requireNonNull(getCommand("start")).setExecutor(new CommandStart());

        taskTester = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if(state.getState() == 1){
                int current = SurvivalGamesAPI.alive.size();
                boolean winning = current == 1;
                //System.out.println(current+" : "+winning);
                if(winning){
                    Bukkit.getScheduler().cancelTask(taskTester);
                    Player winner = SurvivalGamesAPI.alive.get(0);
                    Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§aThe player §3"+ winner.getName()+" §awin the game!");
                    SurvivalGamesAPI.stopGame();
                }
            }
        }, 20, 20);
        taskStart = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if(!SurvivalGamesAPI.isArenaFinish()){
                return;
            }
            int current = SurvivalGamesAPI.survivalGamesPlayer.size();
            int find = SurvivalGamesAPI.MIN_PLAYERS-SurvivalGamesAPI.survivalGamesPlayer.size();
            if(SurvivalGamesAPI.survivalGamesPlayer.size() >= SurvivalGamesAPI.MIN_PLAYERS){
                if(countdown == SurvivalGamesAPI.WAIT_TIME){
                    Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7The game will be start in §630 §7seconds");
                }
                countdown--;
                switch (countdown) {
                    case 20 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §620 §7seconds");
                    case 15 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §615 §7seconds");
                    case 10 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §610 §7seconds");
                    case 5 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §65 §7seconds");
                    case 4 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §64 §7seconds");
                    case 3 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §63 §7seconds");
                    case 2 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §62 §7seconds");
                    case 1 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§7The game will be start in §6einer §7seconds");
                    case 0 -> Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix() + "§aThe game has begun!");
                    default -> {
                    }
                }
                if(countdown == 0){
                    state.setState(1);
                    SurvivalGamesAPI.startGame();
                    Bukkit.getScheduler().cancelTask(taskStart);
                }
            }else{
                waiter++;
                if(current == 0){
                    waiter = 1;
                    return;
                }
                if(waiter >= 60){
                    Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§cStill waiting for "+find+" player!");
                    waiter = 1;
                }
                countdown = SurvivalGamesAPI.WAIT_TIME;
            }
        }, 20, 20);
    }

    public static SurvivalGames getPlugin() {
        return plugin;
    }

}
