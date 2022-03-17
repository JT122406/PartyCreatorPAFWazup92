package partycreator.partycreator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Partycreator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("BattleRoyaleX"))
        {
            Bukkit.getLogger().info("Loaded BattleRoyaleX PAF integration (Bungee)");
            Bukkit.getPluginManager().registerEvents(new PartyMakerBattleRoyale(), this);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
