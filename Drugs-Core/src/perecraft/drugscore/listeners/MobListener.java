package perecraft.drugscore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import perecraft.drugscore.manager.DrugsManager;

public class MobListener implements Listener {

    /**
     * Drop random delle droghe in base al mob ucciso.
     * @param e entità del mob.
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if(e.getEntity().getType()==EntityType.ZOMBIE) {
            ItemStack cocaineitem = new ItemStack(Material.PUMPKIN_SEEDS);
            ItemMeta cocainemeta = cocaineitem.getItemMeta();

            cocainemeta.setDisplayName(DrugsManager.cocaineseedname);
            cocainemeta.setLore(DrugsManager.cocaineseedlore);
            cocaineitem.setItemMeta(cocainemeta);

            e.getDrops().add(1, cocaineitem);
        }

    }
	
}