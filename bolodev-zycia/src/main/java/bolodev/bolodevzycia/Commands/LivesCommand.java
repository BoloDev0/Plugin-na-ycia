package bolodev.bolodevzycia.Commands;

import bolodev.bolodevzycia.Main;
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
            sender.sendMessage(plugin.getMessage("lives_command_console"));
            return true;
        }

        Player player = (Player) sender;
        int lives = plugin.getLivesManager().getPlayerLives(player);

        player.sendMessage(plugin.getMessage("lives_command_player").replace("{lives}", String.valueOf(lives)));
        return true;
    }
}