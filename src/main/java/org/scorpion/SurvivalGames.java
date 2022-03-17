package org.scorpion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.scorpion.api.SurvivalGamesAPI;
import org.scorpion.commands.CommandDevMode;
import org.scorpion.commands.CommandStart;
import org.scorpion.commands.CommandSurvivalGamesAdmin;
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
        getServer().getPluginManager().registerEvents(new SurvivalGamesListener(), this);
        Objects.requireNonNull(getCommand("survivalgamesadmin")).setExecutor(new CommandSurvivalGamesAdmin());
        Objects.requireNonNull(getCommand("sga")).setExecutor(new CommandSurvivalGamesAdmin());
        Objects.requireNonNull(getCommand("start")).setExecutor(new CommandStart());
        Objects.requireNonNull(getCommand("devmode")).setExecutor(new CommandDevMode());

        taskTester = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if(state.getState() == 1){
                int current = SurvivalGamesAPI.alive.size();
                boolean winning = current == 1;
                //System.out.println(current+" : "+winning);
                if(winning){
                    Bukkit.getScheduler().cancelTask(taskTester);
                    Player winner = SurvivalGamesAPI.alive.get(0);
                    Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§aDer Spieler §3"+ winner.getName()+" §ahat das Spiel gewonnen!");
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
                    Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §630 §7Sekunden");
                }
                countdown--;
                switch (countdown){
                    case 20:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §620 §7Sekunden");
                        break;
                    case 15:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §615 §7Sekunden");
                        break;
                    case 10:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §610 §7Sekunden");
                        break;
                    case 5:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §65 §7Sekunden");
                        break;
                    case 4:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §64 §7Sekunden");
                        break;
                    case 3:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §63 §7Sekunden");
                        break;
                    case 2:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §62 §7Sekunden");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§7Das Spiel beginnt in §6einer §7Sekunde");
                        break;
                    case 0:
                        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§aDas Spiel beginnt!");
                        break;
                    default:
                        break;
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
                    Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§cWarte auf noch "+find+" Spieler!");
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
