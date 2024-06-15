package bolodev.bolodevzycia.Managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LivesManager {

    private Map<UUID, Integer> playerLives;

    public LivesManager() {
        this.playerLives = new HashMap<>();
    }

    public int getPlayerLives(Player player) {
        return playerLives.getOrDefault(player.getUniqueId(), 3);
    }

    public void setPlayerLives(Player player, int lives) {
        playerLives.put(player.getUniqueId(), lives);
    }

    public void givePlayerLives(Player player, int amount) {
        int currentLives = getPlayerLives(player);
        setPlayerLives(player, currentLives + amount);
    }

    public void takePlayerLives(Player player, int amount) {
        int currentLives = getPlayerLives(player);
        setPlayerLives(player, Math.max(currentLives - amount, 0));
    }
}
