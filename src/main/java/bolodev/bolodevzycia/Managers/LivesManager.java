package bolodev.bolodevzycia.Managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class LivesManager {
    private final Map<String, Integer> playerLives = new HashMap<>();

    public int getPlayerLives(Player player) {
        return playerLives.getOrDefault(player.getName(), 0);
    }

    public void setPlayerLives(Player player, int lives) {
        playerLives.put(player.getName(), lives);
    }

    public void givePlayerLives(Player player, int amount) {
        int currentLives = getPlayerLives(player);
        setPlayerLives(player, currentLives + amount);
    }

    public void takePlayerLives(Player player, int amount) {
        int currentLives = getPlayerLives(player);
        setPlayerLives(player, Math.max(0, currentLives - amount));
    }
}