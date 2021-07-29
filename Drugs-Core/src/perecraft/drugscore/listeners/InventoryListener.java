/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perecraft.drugscore.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

//import net.milkbowl.vault.Vault;
//import net.milkbowl.vault.VaultEco;
import perecraft.drugscore.manager.DrugsManager;

public class InventoryListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		if((e.getCurrentItem() == null) || (e.getCurrentItem().getType()==Material.AIR))
            return;
		
		if(e.getClickedInventory().getName()=="§2Drug shop") {
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains(DrugsManager.cocainename)) {
				
			}
			
			else if(e.getCurrentItem().getItemMeta().getDisplayName().contains(DrugsManager.weedname)) {
				
			}
		}
		
	}
	
}