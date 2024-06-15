package bolodev.bolodevzycia.Commands;

import bolodev.bolodevzycia.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LivesCommand implements CommandExecutor {

    private final Main plugin;

    public LivesCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "komenda dla graczy tylko (bo konsola nie ma zyc XD)");
            return true;
        }

        Player player = (Player) sender;
        int lives = plugin.getLivesManager().getPlayerLives(player);

        player.sendMessage(ChatColor.GREEN + "Ilość twoich żyć: " + ChatColor.AQUA + lives);
        return true;
    }
}