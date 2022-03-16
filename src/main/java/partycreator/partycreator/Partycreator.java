package partycreator.partycreator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Partycreator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getLogger().info("Using PAF to Make Battle WAZUP92 Parties on join");
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("BattleRoyaleX"))
            Bukkit.getPluginManager().registerEvents(new PartyMakerBattleRoyale(), this);

        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PartyGamesX"));
            Bukkit.getPluginManager().registerEvents(new PartyMakerPartyGames(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
