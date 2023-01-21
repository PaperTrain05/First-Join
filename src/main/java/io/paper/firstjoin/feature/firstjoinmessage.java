package io.paper.firstjoin.feature;

import com.earth2me.essentials.Essentials;
import com.golfing8.kore.config.annotation.ConfigValue;
import com.golfing8.kore.feature.Feature;
import io.paper.firstjoin.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class firstjoinmessage extends Feature {
    public firstjoinmessage(Plugin plugin, String name, String description, String basePermDesc, String adminPermDesc) {
        super(plugin, name, description, basePermDesc, adminPermDesc);
    }

    private BukkitTask task;

    public void onDisable() {
        this.cancelTask(this.task);
    }

    public void onEnable() {
        this.startTask();
    }
    private void startTask(){
        this.task = new BukkitRunnable(){
            @Override
            public void run(){
                Bukkit.getLogger().info("[FactionsKore] - FirstJoin feature is enabled!");
            }
        }.runTask(this.getPlugin());
    }

    @ConfigValue(name = "first-join", path = "feature.messages")
    private String firstjoin;

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e){
        Essentials ess = (Essentials)((Object)Bukkit.getPluginManager().getPlugin("Essentials"));
        String uniqueJoin = Integer.toString(ess.getUserMap().getUniqueUsers());
        Player p = e.getPlayer();
        if(!e.getPlayer().hasPlayedBefore()){
            Bukkit.broadcastMessage(CC.translate(firstjoin).replace("{player}", p.getDisplayName()).replace("{unique-number}", uniqueJoin));
       }
    }

    public void onPersonalJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.sendMessage(firstjoinmessage.this.getConfig().getStringList("feature.messages.personal-join-message").stream().toString());
    }
}
