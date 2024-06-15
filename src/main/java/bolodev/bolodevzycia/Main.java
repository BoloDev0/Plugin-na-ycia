package bolodev.bolodevzycia;

import bolodev.bolodevzycia.Managers.LivesManager;
import org.bukkit.plugin.java.JavaPlugin;
import bolodev.bolodevzycia.Commands.GiveLivesCommand;
import bolodev.bolodevzycia.Commands.LivesCommand;
import bolodev.bolodevzycia.Commands.TakeLivesCommand;
import bolodev.bolodevzycia.Listeners.DeathListener;

public class Main extends JavaPlugin {

    private LivesManager livesManager;

    @Override
    public void onEnable() {
        this.livesManager = new LivesManager();

        getCommand("zycia").setExecutor(new LivesCommand(this));
        getCommand("dajzycie").setExecutor(new GiveLivesCommand(this));
        getCommand("usunzycie").setExecutor(new TakeLivesCommand(this));

        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
    }

    public LivesManager getLivesManager() {
        return livesManager;
    }
}