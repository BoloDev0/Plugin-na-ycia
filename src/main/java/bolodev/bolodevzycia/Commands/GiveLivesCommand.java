package bolodev.bolodevzycia.Commands;

import bolodev.bolodevzycia.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        if (!sender.hasPermission("mcszlamek.dajzycie")) {
            sender.sendMessage(ChatColor.RED + "Nie masz uprawnien do tej komendy!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.YELLOW + "Poprawne użycie: " + ChatColor.GOLD + "/dajzycie <nick> <ilość>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Gracz o nicku " + args[0] + " nie jest online!");
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Ilość musi być liczbą!");
            return true;
        }

        plugin.getLivesManager().givePlayerLives(target, amount);
        sender.sendMessage(ChatColor.GREEN + "Dodano " + amount + " żyć graczowi " + target.getName() + ".");
        target.sendMessage(ChatColor.GREEN + "Otrzymałeś " + amount + " żyć.");
        return true;
    }
}