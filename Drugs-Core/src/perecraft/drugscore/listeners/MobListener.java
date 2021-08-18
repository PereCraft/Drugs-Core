package perecraft.drugscore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import perecraft.drugscore.domain.CustomDrop;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;

import perecraft.drugscore.manager.DrugsManager;

public class MobListener implements Listener {

    /**
     * Drop random delle droghe in base al mob ucciso.
     * @param e entità del mob.
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        
        // FIX: Crasha quando override-drop è true
        if(DrugsManager.getManager().getCustomDrop(e.getEntity().getType()) != null) {
            CustomDrop cd = DrugsManager.getManager().getCustomDrop(e.getEntity().getType());
            
            // FIX
            if(cd.getOverrideDrop()) {
                System.out.println("Drops: " + e.getDrops());
                e.getDrops().clear();
            }
            
            // Drugs chance
            if(!cd.getDrugsList().isEmpty() && cd.getDrugsChance() != 0) {
                if(Math.round(Math.random()*100)%(100/cd.getDrugsChance()) == 0) {
                    cd.getDrugsList().forEach((Drug d) -> {
                        e.getDrops().add(1, d.getItem(1));
                    });
                }
            }            
            
            // Seeds chance
            if(!cd.getSeedsList().isEmpty() && cd.getSeedsChance() != 0) {
                if(Math.round(Math.random()*100)%(100/cd.getSeedsChance()) == 0) {
                    cd.getSeedsList().forEach((Seed s) -> {
                        e.getDrops().add(1, s.getItem(1));
                    });
                }
            }
            
        }

    }
	
}