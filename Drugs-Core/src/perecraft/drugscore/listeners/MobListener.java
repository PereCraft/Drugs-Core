package perecraft.drugscore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import perecraft.drugscore.domain.CustomDrop;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;

import perecraft.drugscore.manager.DrugsManager;

/**
 *
 * @author PereCraft
 */
public class MobListener implements Listener {

    /**
     * Drop random delle droghe in base al mob ucciso.
     * @param e entità del mob.
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        
        if(DrugsManager.getManager().getCustomDrop(e.getEntity().getType()) != null) {
            CustomDrop cd = DrugsManager.getManager().getCustomDrop(e.getEntity().getType());
            
            if(cd.getOverrideDrop()) {
                e.getDrops().clear();
            }
            
            // Drugs chance
            if(!cd.getDrugsList().isEmpty() && cd.getDrugsChance() != 0) {
                if(Math.round(Math.random()*100)%(100/cd.getDrugsChance()) == 0) {
                    cd.getDrugsList().forEach((Drug d) -> {
                        e.getDrops().add(d.getItem(1));
                    });
                }
            }            
            
            // Seeds chance
            if(!cd.getSeedsList().isEmpty() && cd.getSeedsChance() != 0) {
                if(Math.round(Math.random()*100)%(100/cd.getSeedsChance()) == 0) {
                    cd.getSeedsList().forEach((Seed s) -> {
                        e.getDrops().add(s.getItem(1));
                    });
                }
            }
            
        }

    }
	
}