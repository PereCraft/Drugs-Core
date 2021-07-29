package perecraft.drugscore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import perecraft.drugscore.manager.DrugsManager;

public class DrugConsumeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = e.getPlayer();

            if(p.isSneaking()) {

                Block clickedblock = e.getClickedBlock();

                if(clickedblock != null && clickedblock.hasMetadata("drug-plant")) {
                    e.setCancelled(true);
                    checkGrowth(p, clickedblock);
                    return;
                }

                ItemStack item = e.getItem();
                String cooldown = "§cVacci piano bello!";

                if(item == null) return;

                // TODO: Automatizzare processo di controllo e consumo dell'oggetto
                if(item.getType() == Material.SUGAR && item.getItemMeta().getDisplayName().contains(DrugsManager.cocainename)) {
                    if(p.hasPotionEffect(PotionEffectType.SPEED)) {
                        p.sendMessage(cooldown);
                        return;
                    }

                    e.setCancelled(true);
                    
                    int amount = item.getAmount();
                    item.setAmount(amount-1);

                    p.updateInventory();
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
                    p.playSound(p.getLocation(), Sound.ENTITY_CAT_HISS, 10, 1);
                    p.sendMessage("§7Hai assunto la coca");
                } else if(item.getType() == Material.INK_SACK && item.getItemMeta().getDisplayName().contains(DrugsManager.weedname)) {
                    e.setCancelled(true);

                    if(p.hasPotionEffect(PotionEffectType.REGENERATION)) {
                        p.sendMessage(cooldown);
                        return;
                    }

                    if(p.getInventory().contains(Material.FLINT_AND_STEEL) && p.getInventory().contains(Material.PAPER)) {
                        for(ItemStack slot : p.getInventory().getStorageContents()) {
                            if(slot.getType()==Material.PAPER) {
                                int paperamount = slot.getAmount();
                                slot.setAmount(paperamount-1);

                                int weedamount = item.getAmount();
                                item.setAmount(weedamount-1);

                                p.updateInventory();
                                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 400, 1));
                                Bukkit.getServer().getWorld(p.getWorld().getName()).spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 40);
                                p.playSound(p.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 10, 1);
                                p.sendMessage("§7Hai fumato la weed");

                                break;
                            }
                        }

                    } else {
                        p.sendMessage("§7Devi avere un accendino e della carta per fumare la weed!");
                    }

                }

            }

            // TODO: Da aggiungere controllo dell'oggetto che si sta usando sulla 
            // mano, se è una droga → consuma, es:
            // if(!DrugsManager.isADrug(e.getPlayer.getItemInMainHand()) return;

        }

    }

    private void checkGrowth(Player p, Block plant) {
        if(plant.getMetadata("drug-plant").get(0).asString() == "cocaine-plant-1") {
            p.sendMessage("§7Questa pianta di coca sta crescendo...");
        } else if(plant.getMetadata("drug-plant").get(0).asString() == "cocaine-plant-2") {
            p.sendMessage("§aPianta di coca cresciuta!");
        }
    }
	
}