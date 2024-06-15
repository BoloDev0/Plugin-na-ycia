package bolodev.bolodevzycia.Listeners;

import bolodev.bolodevzycia.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
            player.sendMessage(ChatColor.RED + "Straciłeś wszystkie swoje życia! Zostajesz zbanowany na 24 godziny!");
            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tempban:tempban " + player.getName() + " 24h Brak żyć");
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    plugin.getLivesManager().setPlayerLives(player, 3);
                }, 24 * 60 * 60 * 20);
            });
        } else {
            player.sendMessage(ChatColor.RED + "Straciłeś jedno życie. Pozostało: " + ChatColor.AQUA + lives);
        }
    }
}