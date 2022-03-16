package partycreator.partycreator;

import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.spigot.api.party.PartyManager;
import de.simonsator.partyandfriends.spigot.api.party.PlayerParty;
import me.wazup.partygames.Enums;
import me.wazup.partygames.Party;
import me.wazup.partygames.PartyGames;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PartyMakerPartyGames implements Listener {

    private PlayerParty getPAFParty(Player p) {
        PAFPlayer pafPlayer = PAFPlayerManager.getInstance().getPlayer(p.getUniqueId());
        return PartyManager.getInstance().getParty(pafPlayer);
    }

    @EventHandler
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event){
        if (event.getPlayer().getWorld().getName().equals("PartyGames")){
            Player eventplayer = event.getPlayer();
            if (getPAFParty(eventplayer) != null){
                if (getPAFParty(eventplayer).isLeader(PAFPlayerManager.getInstance().getPlayer(eventplayer.getUniqueId()))){
                    Party party = new Party(PartyGames.getInstance(), eventplayer, getPAFParty(eventplayer).getAllPlayers().size());
                    party.setPrivacy(Enums.PartyPrivacy.PUBLIC);
                    for (PAFPlayer p : getPAFParty(eventplayer).getAllPlayers()) {
                        if (p.getUniqueId().equals(eventplayer.getUniqueId()))
                            continue;
                        else
                        {
                               party.join(Bukkit.getPlayer(p.getUniqueId()));
                        }
                    }
                    party.setPrivacy(Enums.PartyPrivacy.INVITE);
                }


            }
        }
        else if (event.getFrom().getName().equals("PartyGames")){
            Player eventplayer = event.getPlayer();
            for (Party party : PartyGames.getInstance().parties) {

            }
        }

    }
}
