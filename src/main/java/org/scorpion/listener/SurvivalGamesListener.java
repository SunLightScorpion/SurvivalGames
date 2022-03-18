package org.scorpion.listener;

import net.minecraft.network.protocol.game.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.scorpion.SurvivalGames;
import org.scorpion.api.SurvivalGamesAPI;
import org.scorpion.state.GameState;
import org.scorpion.version.Version1182;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class SurvivalGamesListener implements Listener{

    GameState state = SurvivalGamesAPI.state;

    @EventHandler
    public void on(AsyncPlayerPreLoginEvent e){
        if(state.getState() == 2){
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "§cThe game is in the final stage!");
        }
    }

    @EventHandler
    public void on(PlayerInteractEvent e){
        Block b = e.getClickedBlock();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            assert b != null;
            if(b.getType() == Material.CHEST){
                e.setCancelled(true);
                Player p = e.getPlayer();
                if(p.getGameMode() == GameMode.SPECTATOR){
                    return;
                }
                Location loc = b.getLocation();
                if(!SurvivalGamesAPI.sgChest.containsKey(loc)) {
                    Random r = new Random();
                    int i = r.nextInt(17);

                    Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);
                    ArrayList<ItemStack> items = new ArrayList<>();

                    items.add(new ItemStack(Material.DIAMOND));
                    items.add(new ItemStack(Material.STICK));
                    items.add(new ItemStack(Material.STICK));
                    items.add(new ItemStack(Material.STICK));
                    items.add(new ItemStack(Material.STICK));
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.IRON_SWORD));
                    items.add(new ItemStack(Material.GOLDEN_SWORD));
                    items.add(new ItemStack(Material.GOLDEN_SWORD));
                    items.add(new ItemStack(Material.GOLDEN_SWORD));
                    items.add(new ItemStack(Material.GOLDEN_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.STONE_SWORD));
                    items.add(new ItemStack(Material.WOODEN_SWORD));
                    items.add(new ItemStack(Material.WOODEN_AXE));
                    items.add(new ItemStack(Material.GOLDEN_CHESTPLATE));
                    items.add(new ItemStack(Material.GOLDEN_CHESTPLATE));
                    items.add(new ItemStack(Material.GOLDEN_CHESTPLATE));
                    items.add(new ItemStack(Material.LEATHER_BOOTS));
                    items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
                    items.add(new ItemStack(Material.LEATHER_LEGGINGS));
                    items.add(new ItemStack(Material.LEATHER_LEGGINGS));
                    items.add(new ItemStack(Material.CHAINMAIL_BOOTS));
                    items.add(new ItemStack(Material.CHAINMAIL_BOOTS));
                    items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    items.add(new ItemStack(Material.CHAINMAIL_HELMET));
                    items.add(new ItemStack(Material.CHAINMAIL_HELMET));
                    items.add(new ItemStack(Material.CHAINMAIL_HELMET));
                    items.add(new ItemStack(Material.IRON_CHESTPLATE));
                    items.add(new ItemStack(Material.IRON_BOOTS));
                    items.add(new ItemStack(Material.IRON_BOOTS));
                    items.add(new ItemStack(Material.IRON_BOOTS));
                    items.add(new ItemStack(Material.IRON_BOOTS));
                    items.add(new ItemStack(Material.IRON_BOOTS));
                    items.add(new ItemStack(Material.IRON_LEGGINGS));
                    items.add(new ItemStack(Material.IRON_LEGGINGS));
                    items.add(new ItemStack(Material.IRON_LEGGINGS));
                    items.add(new ItemStack(Material.IRON_LEGGINGS));
                    items.add(new ItemStack(Material.IRON_LEGGINGS));
                    items.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
                    items.add(new ItemStack(Material.GOLDEN_HELMET));
                    items.add(new ItemStack(Material.GOLDEN_HELMET));
                    items.add(new ItemStack(Material.GOLDEN_HELMET));
                    items.add(new ItemStack(Material.GOLDEN_HELMET));
                    items.add(new ItemStack(Material.GOLDEN_HELMET));
                    items.add(new ItemStack(Material.BREAD, 5));
                    items.add(new ItemStack(Material.BREAD, 5));
                    items.add(new ItemStack(Material.BREAD, 5));
                    items.add(new ItemStack(Material.APPLE, 3));
                    items.add(new ItemStack(Material.APPLE, 3));
                    items.add(new ItemStack(Material.APPLE, 3));
                    items.add(new ItemStack(Material.CARROT, 3));
                    items.add(new ItemStack(Material.CARROT, 3));
                    items.add(new ItemStack(Material.CARROT, 3));
                    items.add(new ItemStack(Material.COOKED_COD, 3));
                    items.add(new ItemStack(Material.COOKED_COD, 3));
                    items.add(new ItemStack(Material.COOKED_COD, 3));
                    items.add(new ItemStack(Material.COOKED_COD, 3));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
                    items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
                    items.add(new ItemStack(Material.COOKED_BEEF, 3));
                    items.add(new ItemStack(Material.COOKED_BEEF, 3));
                    items.add(new ItemStack(Material.COOKED_BEEF, 3));
                    items.add(new ItemStack(Material.WHEAT, 3));
                    items.add(new ItemStack(Material.WHEAT));
                    items.add(new ItemStack(Material.ARROW, 32));
                    items.add(new ItemStack(Material.SPECTRAL_ARROW, 8));
                    items.add(new ItemStack(Material.GOLDEN_APPLE));
                    items.add(new ItemStack(Material.GOLDEN_APPLE));
                    items.add(new ItemStack(Material.GOLDEN_APPLE));
                    items.add(new ItemStack(Material.GOLDEN_LEGGINGS));
                    items.add(new ItemStack(Material.GOLDEN_LEGGINGS));
                    items.add(new ItemStack(Material.GOLDEN_LEGGINGS));
                    items.add(new ItemStack(Material.GOLDEN_BOOTS));
                    items.add(new ItemStack(Material.GOLDEN_BOOTS));
                    items.add(new ItemStack(Material.DIAMOND_BOOTS));
                    items.add(new ItemStack(Material.DIAMOND_LEGGINGS));
                    items.add(new ItemStack(Material.CROSSBOW));
                    items.add(new ItemStack(Material.SHIELD));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));
                    items.add(new ItemStack(Material.IRON_AXE));

                    while (i != 0) {
                        i--;

                        Random r2 = new Random();
                        Random r3 = new Random();

                        int n2 = r2.nextInt(27);
                        int n3 = r3.nextInt(items.size());

                        //System.out.println(n2+":"+n3+":"+items.get(n3).getType());

                        inv.setItem(n2, new ItemStack(items.get(n3)));
                    }
                    SurvivalGamesAPI.sgChest.put(loc, inv);
                }
                p.openInventory(SurvivalGamesAPI.sgChest.get(loc));
            }
        }
    }

    @EventHandler
    public void on(EntityCombustEvent e){
        if(e.getEntity() instanceof Zombie ||
                e.getEntity() instanceof Skeleton){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            if(SurvivalGamesAPI.protection){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(PlayerJoinEvent e){
        Player p = e.getPlayer();

        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(100);

        if(state.getState() != 0){
            p.setGameMode(GameMode.SPECTATOR);
            e.setJoinMessage(null);
            return;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 1000");
        e.setJoinMessage(SurvivalGamesAPI.getPrefix()+"§a"+p.getName()+" §7joined the game!");
        SurvivalGamesAPI.setupPlayers();
        if(!SurvivalGamesAPI.isArenaFinish()){
            p.sendMessage(SurvivalGamesAPI.getPrefix()+"§cThe arena must be set!");
            return;
        }
        int players = Bukkit.getOnlinePlayers().size()-1;
        p.teleport(SurvivalGamesAPI.getSpawn(players));
        if(!SurvivalGamesAPI.survivalGamesPlayer.containsKey(p)){
            SurvivalGamesAPI.survivalGamesPlayer.put(p, players);
        }
        if(!SurvivalGamesAPI.alive.contains(p)){
            SurvivalGamesAPI.alive.add(p);
        }
        if(!SurvivalGamesAPI.freeze.contains(p)){
            SurvivalGamesAPI.freeze.add(p);
        }
        //System.out.println("all: "+players+" - "+ SurvivalGamesAPI.survivalGamesPlayer.size());
    }

    @EventHandler
    public void on(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(SurvivalGamesAPI.alive.contains(p)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void on(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(SurvivalGamesAPI.alive.contains(p)){
            e.setCancelled(true);
        }
    }

    HashMap<Player, Location> death = new HashMap<>();

    @EventHandler
    public void on(PlayerDeathEvent e){
        Player p = e.getEntity();
        Version1182.respawn(p);
        p.setGameMode(GameMode.SPECTATOR);
        p.closeInventory();
        death.put(p, p.getLocation());
        SurvivalGamesAPI.alive.remove(p);
        int current = SurvivalGamesAPI.alive.size();
        boolean winning = current == 1;
    }

    @EventHandler
    public void on(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(SurvivalGames.getPlugin(), () -> p.teleport(death.get(p)), 3);
    }

    @EventHandler
    public void on(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location to = e.getTo();
        Location from = e.getFrom();
        Vector vec = new Vector(to.getX(), to.getY(), to.getZ());
        double speed = vec.distance(new Vector(from.getX(), from.getY(), from.getZ()));
        if(SurvivalGamesAPI.freeze.contains(p)){
            if(speed > 0){
                e.setTo(from);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            if(SurvivalGamesAPI.protection){
                Player p = (Player)e.getDamager();
                p.sendMessage(SurvivalGamesAPI.getPrefix()+"§cThe protection period is not over yet!");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage(SurvivalGamesAPI.getPrefix()+"§c"+p.getName()+" §7left the game!");
        if(SurvivalGamesAPI.survivalGamesPlayer.containsKey(p)){
            SurvivalGamesAPI.alive.remove(p);
            SurvivalGamesAPI.survivalGamesPlayer.remove(p);
        }
        if(SurvivalGamesAPI.alive.contains(p)){
            SurvivalGamesAPI.alive.remove(p);
        }
        if(SurvivalGamesAPI.freeze.contains(p)){
            SurvivalGamesAPI.freeze.remove(p);
        }
    }

}
