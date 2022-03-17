package org.scorpion.version;

import net.minecraft.network.protocol.game.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.scorpion.SurvivalGames;

/**
 * @author Lukas on 3/17/2022
 */
public class Version1182 {

    public static void respawn(Player p){
        Bukkit.getScheduler().runTaskLaterAsynchronously(SurvivalGames.getPlugin(),
                () -> ((CraftPlayer) p).getHandle().b.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.a)), 3);
    }

}
