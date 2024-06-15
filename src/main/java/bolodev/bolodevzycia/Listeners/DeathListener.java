package bolodev.bolodevzycia.Listeners;

import bolodev.bolodevzycia.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final Main plugin;

    public DeathListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        plugin.getLivesManager().takePlayerLives(player, 1);
        int lives = plugin.getLivesManager().getPlayerLives(player);

        if (lives <= 0) {
            player.sendMessage(plugin.getMessage("player_lost_all_lives"));
            Bukkit.getScheduler().runTask(plugin, () -> {
                String banCommand = plugin.getMessage("player_ban_command").replace("{player}", player.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), banCommand);
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    plugin.getLivesManager().setPlayerLives(player, 3);
                    player.sendMessage(plugin.getMessage("reset_lives_after_ban"));
                }, 24 * 60 * 60 * 20);
            });
        } else {
            player.sendMessage(plugin.getMessage("player_lost_life").replace("{lives}", String.valueOf(lives)));
        }
    }
}