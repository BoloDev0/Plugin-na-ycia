package bolodev.bolodevzycia;

import bolodev.bolodevzycia.Commands.GiveLivesCommand;
import bolodev.bolodevzycia.Commands.LivesCommand;
import bolodev.bolodevzycia.Commands.TakeLivesCommand;
import bolodev.bolodevzycia.Listeners.DeathListener;
import bolodev.bolodevzycia.Managers.LivesManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private LivesManager livesManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        livesManager = new LivesManager();

        getCommand("dajzycie").setExecutor(new GiveLivesCommand(this));
        getCommand("usunzycie").setExecutor(new TakeLivesCommand(this));
        getCommand("zycia").setExecutor(new LivesCommand(this));

        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }

    public LivesManager getLivesManager() {
        return livesManager;
    }

    public String getMessage(String key) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages." + key));
    }
}