package perecraft.drugscore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import perecraft.drugscore.DrugsCore;
import perecraft.drugscore.manager.DrugsManager;

/**
 *
 * @author PereCraft
 */
public class PlantListener implements Listener {
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
//            Player p = e.getPlayer();
//            ItemStack iteminhand = e.getItemInHand();
//            Block placedblock = e.getBlockPlaced();
//
//            if(p == null || iteminhand == null || placedblock == null) return;
//
//            if(iteminhand.getItemMeta().hasDisplayName()) {
//                if(iteminhand.getType() == Material.PUMPKIN_SEEDS && iteminhand.getItemMeta().getDisplayName().contains(DrugsManager.cocaineseedname)) {
//                    placedblock.setMetadata("drug-plant", new FixedMetadataValue(DrugsCore.getInstance(), "cocaine-plant-1"));
//                    p.sendMessage("Hai piantato la coca");
//                }
//            }
	}
	
	@EventHandler
	public void onPlantGrow(BlockGrowEvent e) {
//            Block plant = e.getBlock();
//
//            if(plant.hasMetadata("drug-plant")) {
//                if(plant.getMetadata("drug-plant").get(0).asString() == "cocaine-plant-1") {
//                    e.setCancelled(true);
//                    plant.setType(Material.SAPLING);
//                    plant.setMetadata("drug-plant", new FixedMetadataValue(DrugsCore.getInstance(), "cocaine-plant-2"));
//                }
//            }
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
//            Block block = e.getBlock();
//            Location loc = block.getLocation();
//
//            if(block.hasMetadata("drug-plant")) {
//                if(block.getMetadata("drug-plant").get(0).asString() == "cocaine-plant-1") {
//                    ItemStack cocaineseeditem = new ItemStack(Material.PUMPKIN_SEEDS, 1, (short) 0);
//                    ItemMeta cocaineseedmeta = cocaineseeditem.getItemMeta();
//
//                    cocaineseedmeta.setDisplayName(DrugsManager.cocaineseedname);
//                    cocaineseedmeta.setLore(DrugsManager.cocaineseedlore);
//                    cocaineseeditem.setItemMeta(cocaineseedmeta);
//
//                    e.setDropItems(false);
//                    Bukkit.getServer().getWorld(loc.getWorld().getName()).dropItem(loc, cocaineseeditem);
//                } else if(block.getMetadata("drug-plant").get(0).asString() == "cocaine-plant-2") {
//                    ItemStack cocainedrop = new ItemStack(Material.SUGAR, 1, (short) 0);
//                    ItemMeta cocainemeta = cocainedrop.getItemMeta();
//
//                    cocainemeta.setDisplayName(DrugsManager.cocainename);
//                    cocainemeta.setLore(DrugsManager.cocainelore);
//                    cocainedrop.setItemMeta(cocainemeta);
//
//                    e.setDropItems(false);
//                    Bukkit.getServer().getWorld(loc.getWorld().getName()).dropItem(loc, cocainedrop);
//                }
//            }
		
	}
	

}