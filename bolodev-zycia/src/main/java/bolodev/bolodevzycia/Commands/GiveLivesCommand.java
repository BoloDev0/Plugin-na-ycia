package bolodev.bolodevzycia.Commands;

import bolodev.bolodevzycia.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveLivesCommand implements CommandExecutor {

    private final Main plugin;

    public GiveLivesCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("bolodev.zycia.dajzycie")) {
            sender.sendMessage(plugin.getMessage("no_permission"));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(plugin.getMessage("usage_give_lives"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(plugin.getMessage("player_not_online").replace("{player}", args[0]));
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(plugin.getMessage("amount_not_number"));
            return true;
        }

        plugin.getLivesManager().givePlayerLives(target, amount);
        sender.sendMessage(plugin.getMessage("lives_given_sender").replace("{amount}", String.valueOf(amount)).replace("{player}", target.getName()));
        target.sendMessage(plugin.getMessage("lives_given_target").replace("{amount}", String.valueOf(amount)));
        return true;
    }
}