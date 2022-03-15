package partycreator.partycreator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Partycreator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getLogger().info("Using PAF to Make Battle Royale X Parties on join");
        Bukkit.getPluginManager().registerEvents(new PartyMaker(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
