package org.scorpion.api;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.scorpion.SurvivalGames;
import org.scorpion.state.GameState;
import org.scorpion.util.table.StringTable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SurvivalGamesAPI {

    public static HashMap<Player, Integer> survivalGamesPlayer = new HashMap<>();
    public static ArrayList<Player> alive = new ArrayList<>();
    public static ArrayList<Player> freeze = new ArrayList<>();
    public static HashMap<Location, Inventory> sgChest = new HashMap<>();
    public static GameState state = new GameState(0);
    public static boolean protection = true;
    public final static int WAIT_TIME = 30;
    public final static int MIN_PLAYERS = 2;
    private static int protection_time = 60;
    public static int taskProtection;
    
    public static String getPrefix(){
        return "§8"+ StringTable.RIGHT_BAR+" §eSurvival Games §8"+StringTable.RIGHT_DOUBLE_ARROW+" §f";
    }

    public static void kickAll(){
        for(Player all : Bukkit.getOnlinePlayers()){
            all.kickPlayer("§cKicked from Server");
        }
    }

    public static void count(){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        int total = getCurrentCount()+1;
        data.set("Count", total);
        try{data.save(file);}catch(IOException ignored){}
    }

    public static void finishArena(){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("finish", true);
        try{data.save(file);}catch(IOException ignored){}
    }

    public static boolean isArenaFinish(){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getBoolean("finish");
    }

    public static int getCurrentCount(){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getInt("Count");
    }

    public static void setSpawn(Location loc){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("startpoint-"+getCurrentCount(), loc);
        try{data.save(file);}catch(IOException ignored){}
    }

    public static Location getSpawn(int i){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getLocation("startpoint-"+i);
    }

    public static void setGameName(String name){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        data.set("Arena-Name", name);
        try{data.save(file);}catch(IOException ignored){}
    }

    public static void clearAllPlayers(){
        for(Player all : Bukkit.getOnlinePlayers()){
            all.getInventory().clear();
        }
    }

    private static int t;
    private static boolean m;
    private static boolean s;

    public static void startGame(){
        freeze.clear();
        startMatch();
        setupPlayers();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 8 600");
        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§cDie Worldboarder wird sich verkleinern, es ist 10 Minuten Zeit!");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SurvivalGames.getPlugin(), () -> {
            t++;
            if(t == 320){
                Random r = new Random();
                int b = r.nextInt(5);
                if(b == 0){
                    m = true;
                    Bukkit.broadcastMessage(getPrefix()+"§cEs wird Zeit, dass die Gestalten auferstehen...");
                }else if(b == 1){
                    s = true;
                    Bukkit.broadcastMessage(getPrefix()+"§aEine Geistige Stärke kommt zu euch...");
                }else{
                    Bukkit.broadcastMessage(getPrefix()+"§aEs wird nichts geschehen!");
                }
            }
            if(t == 330){
                if(m){
                    for(Player player : alive){
                        player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                    }
                }
                if(s){
                    for(Player player : alive){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*30, 2));
                    }
                }
            }
            if(t == 450){
                Bukkit.broadcastMessage(getPrefix()+"§cDie Worldboarder ist auf den Weg zur Mitte!");
            }
        }, 20, 20);
    }

    public static void setupPlayers(){
        for(Player all : Bukkit.getOnlinePlayers()){
            all.setGameMode(GameMode.SURVIVAL);
            all.setFlying(false);
            all.setAllowFlight(false);
            all.setHealth(20);
            all.setFoodLevel(20);
        }
        clearAllPlayers();
    }

    public static void startMatch(){
        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§aDas Spiel §6"+SurvivalGamesAPI.getGame()+" §astartet!");
        taskProtection = Bukkit.getScheduler().scheduleSyncRepeatingTask(SurvivalGames.getPlugin(), () -> {
            protection_time--;
            for(Player all : Bukkit.getOnlinePlayers()){
                ServerAPI.getAPI().sendActionBar(all, "§8[§aSchutzzeit: §3"+protection_time+" Sekunden§8]");
            }
            if(protection_time == 0){
                protection = false;
                for(Player player : alive){
                    player.playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK, 2, 1);
                }
                Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§aPVP ist nun aktiv, viel Glück!");
                Bukkit.getScheduler().cancelTask(taskProtection);
            }
        }, 20, 20);
    }

    public static String getGame(){
        File file = new File("plugins/SurvivalGames/Settings.yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
        return data.getString("Arena-Name");
    }

    public static void stopGame(){
        Bukkit.broadcastMessage(SurvivalGamesAPI.getPrefix()+"§aDer Server wird in §e20 Sekunden §azurückgesetzt!");
        clearAllPlayers();
        for(Entity entity : Bukkit.getWorld("world").getEntities()){
            if(entity instanceof Item){
                entity.remove();
            }
        }
        Bukkit.getScheduler().runTaskLater(SurvivalGames.getPlugin(), () -> {
            kickAll();
            state.setState(2);
            Bukkit.reload();
        }, 20*20);
    }

}
