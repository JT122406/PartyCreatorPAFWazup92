package partycreator.partycreator;

import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.spigot.api.party.PartyManager;
import de.simonsator.partyandfriends.spigot.api.party.PlayerParty;
import me.wazup.battleroyalex.BattleRoyaleX;
import me.wazup.battleroyalex.Party;
import me.wazup.battleroyalex.utils.Enums;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PartyMakerBattleRoyale implements Listener {

    private PlayerParty getPAFParty(Player p) {
        PAFPlayer pafPlayer = PAFPlayerManager.getInstance().getPlayer(p.getUniqueId());
        return PartyManager.getInstance().getParty(pafPlayer);
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (getPAFParty(player) != null){  //player is in party
            for (Party party: BattleRoyaleX.getInstance().parties) {
                    if (party.players.contains(player.getName())) return;
                }

                    if(getPAFParty(player).isLeader(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId()))){ //player is leader
                        Party party = new Party(player, 10);
                        BattleRoyaleX.getInstance().parties.add(party);
                        BattleRoyaleX.getInstance().updatePartiesInventory();
                        party.setPrivacy(Enums.PartyPrivacy.PUBLIC);
                        for (PAFPlayer partyplayer : getPAFParty(player).getAllPlayers()) {
                            if(Bukkit.getPlayer(partyplayer.getPAFPlayer().getUniqueId()) != null){
                                Player player1 = Bukkit.getPlayer(partyplayer.getPAFPlayer().getUniqueId());
                                if (!party.players.contains(player1.getName())) party.join(player1);
                            }
                        }
                        party.setPrivacy(Enums.PartyPrivacy.INVITE);

                    }
                    else
                    {
                        if (Bukkit.getPlayer(getPAFParty(player).getLeader().getUniqueId()) != null){
                            Player leader = Bukkit.getPlayer(getPAFParty(player).getLeader().getUniqueId());
                            for (Party party: BattleRoyaleX.getInstance().parties) {
                                if (party.leaderName == leader.getName()){
                                    party.setPrivacy(Enums.PartyPrivacy.PUBLIC);
                                    party.join(player);
                                    party.setPrivacy(Enums.PartyPrivacy.INVITE);
                                }
                            }
                            BattleRoyaleX.getInstance().updatePartiesInventory();
                        }
                        //leader isn't online already then it'll be handled by leader class
                    }
            }
        return;
    }
}
