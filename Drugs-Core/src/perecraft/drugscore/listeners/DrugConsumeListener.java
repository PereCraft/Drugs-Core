package perecraft.drugscore.listeners;

import java.util.Collection;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.ItemExtra;

import perecraft.drugscore.manager.DrugsManager;

/**
 *
 * @author PereCraft
 */
public class DrugConsumeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
                
        // e.getAction() == Action.RIGHT_CLICK_BLOCK genera un eccezione
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            
            if(p.isSneaking()) {
                /**
                 * Consumo
                 */
                ItemStack item = e.getItem();
                
                if(item.getItemMeta().hasLocalizedName()) {
                    String name = item.getItemMeta().getLocalizedName();

                    if(DrugsManager.getManager().getDrug(name) == null) return;
                    Drug d = DrugsManager.getManager().getDrug(name);
                    
                    // Controllo che l'utente abbia le dipendenze per il consumo
                    if(!d.getDependencies().isEmpty()) {
                        for(Material dep : d.getDependencies()) {
                            if(p.getInventory().first(dep) == -1)
                                return;
                        }
                    }
                    
                    // Controllo che l'utente abbia le dipendenze extra per il consumo
                    if(!d.getDependenciesExtra().isEmpty()) {
                        boolean exitStatus = true;
                        
                        for(ItemExtra ie : d.getDependenciesExtra()) {                            
                            for(ItemStack itemdep : p.getInventory().all(ie.getMaterial()).values()) {
                                if(itemdep.getItemMeta().hasLocalizedName() && itemdep.getItemMeta().getLocalizedName().equals(ie.getId())) {
                                    exitStatus = false;
                                } 
                            }
                        }
                        
                        if(exitStatus) return;
                    }
                    
                    // Controllo che non sia già sotto effetti
                    for(PotionEffect pe : d.getEffects()) {
                        if(p.hasPotionEffect(pe.getType())) {
                            p.sendMessage(DrugsManager.getManager().getWarningMessage());
                            return;
                        }
                    }
                    
                    // Consumo materiali
                    for(Material dep : d.getDependencies()) {
                        ItemStack itemdep = p.getInventory().getContents()[p.getInventory().first(dep)];

                        if((short)(itemdep.getDurability() + 1) > dep.getMaxDurability()) {
                            itemdep.setAmount(itemdep.getAmount() - 1);
                        } else {
                            itemdep.setDurability((short)(itemdep.getDurability() + 1));
                        }

                    }
                    
                    // Consumo ItemExtra
                    for(ItemExtra ie : d.getDependenciesExtra()) {                            
                        for(ItemStack itemdep : p.getInventory().all(ie.getMaterial()).values()) {
                            if(!itemdep.getItemMeta().hasLocalizedName() && !itemdep.getItemMeta().getLocalizedName().equals(ie.getId()))  {
                                System.out.println("sas");
                                return;
                            }

                            if(!itemdep.getItemMeta().hasLocalizedName()) return;
                            if(!itemdep.getItemMeta().getLocalizedName().equals(ie.getId())) return;

                            if((short)(itemdep.getDurability() + 1) > itemdep.getType().getMaxDurability()) {
                                itemdep.setAmount(itemdep.getAmount() - 1);
                            } else {
                                itemdep.setDurability((short)(itemdep.getDurability() + 1));
                            }
                        }
                    }


                    // Particelle
                    Bukkit.getServer().getWorld(p.getWorld().getName()).spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 40, item);
                    if(d.getParticle() != null) {
                        Particle part = d.getParticle();
                        Bukkit.getServer().getWorld(p.getWorld().getName()).spawnParticle(part, p.getLocation(), 40);
                    }
                    
                    // Rottura item, give effetti, avvio suono
                    Bukkit.getServer().getWorld(p.getWorld().getName()).spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 40, item);
                    item.setAmount(item.getAmount() - 1);
                    
                    p.addPotionEffects(d.getEffects());
                    p.playSound(p.getLocation(), d.getSound(), 10, 1);
//                    if(!d.getDependencies().isEmpty()) {
//                        
//                        // Controllo che l'utente abbia le dipendenze per il consumo
//                        for(Material dep : d.getDependencies()) {
//                            if(p.getInventory().first(dep) == -1)
//                                return;
//                        }
//                        
//                        // Controllo che l'utente abbia le dipendenze extra per il consumo
////                        boolean exitStatus = true;
////                        for(ItemExtra ie : d.getDependenciesExtra()) {                            
////                            for(ItemStack itemdep : p.getInventory().all(ie.getMaterial()).values()) {
////                                if(itemdep.getItemMeta().hasLocalizedName() && itemdep.getItemMeta().getLocalizedName().equals(ie.getId())) {
////                                    exitStatus = false;
////                                } 
////                            }
////                        }
////                        if(exitStatus) return;
//                                                
//                        // Controllo che non sia già sotto effetti
//                        for(PotionEffect pe : d.getEffects()) {
//                            if(p.hasPotionEffect(pe.getType())) {
//                                p.sendMessage(DrugsManager.getManager().getWarningMessage());
//                                return;
//                            }
//                        }
//                        
//                        // Consumo materiali
//                        for(Material dep : d.getDependencies()) {
//                            ItemStack itemdep = p.getInventory().getContents()[p.getInventory().first(dep)];
//                            
//                            if((short)(itemdep.getDurability() + 1) > dep.getMaxDurability()) {
//                                itemdep.setAmount(itemdep.getAmount() - 1);
//                            } else {
//                                itemdep.setDurability((short)(itemdep.getDurability() + 1));
//                            }
//                            
//                        }
//                        
//                        // Consumo ItemExtra
//                        for(ItemExtra ie : d.getDependenciesExtra()) {                            
//                            for(ItemStack itemdep : p.getInventory().all(ie.getMaterial()).values()) {
//                                if(!itemdep.getItemMeta().hasLocalizedName() && !itemdep.getItemMeta().getLocalizedName().equals(ie.getId()))  {
//                                    System.out.println("sas");
//                                    return;
//                                }
//
//                                //if(!itemdep.getItemMeta().hasLocalizedName()) return;
//                                //if(!itemdep.getItemMeta().getLocalizedName().equals(ie.getId())) return;
//                                
//                                if((short)(itemdep.getDurability() + 1) > itemdep.getType().getMaxDurability()) {
//                                    itemdep.setAmount(itemdep.getAmount() - 1);
//                                } else {
//                                    itemdep.setDurability((short)(itemdep.getDurability() + 1));
//                                }
//                            }
//                        }
//                        
//                        // Particelle
//                        Bukkit.getServer().getWorld(p.getWorld().getName()).spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 40, item);
//                        if(d.getParticle() != null) {
//                            Particle part = d.getParticle();
//                            Bukkit.getServer().getWorld(p.getWorld().getName()).spawnParticle(part, p.getLocation(), 40);
//                        }
//
//                        
//                        // Give degli effetti
//                        item.setAmount(item.getAmount() - 1);
//                        
//                        p.addPotionEffects(d.getEffects());
//                        p.playSound(p.getLocation(), d.getSound(), 10, 1);
//                        return;
//                                                
//                    }
//                    
//                    for(PotionEffect pe : d.getEffects()) {
//                        if(p.hasPotionEffect(pe.getType())) {
//                            p.sendMessage(DrugsManager.getManager().getWarningMessage());
//                            return;
//                        }
//                    }
//                    
//                    Bukkit.getServer().getWorld(p.getWorld().getName()).spawnParticle(Particle.ITEM_CRACK, p.getLocation(), 40, item);
//                    item.setAmount(item.getAmount() - 1);
//                    
//                    p.addPotionEffects(d.getEffects());
//                    p.playSound(p.getLocation(), d.getSound(), 10, 1);
                    
                }                

                /**
                 * Pianta
                 */
                // TODO
            }
        }

    }

    // TODO
//    private void checkGrowth(Player p, Block plant) {
//        if(plant.getMetadata("drug-plant").get(0).asString() == "cocaine-plant-1") {
//            p.sendMessage("§7Questa pianta di coca sta crescendo...");
//        } else if(plant.getMetadata("drug-plant").get(0).asString() == "cocaine-plant-2") {
//            p.sendMessage("§aPianta di coca cresciuta!");
//        }
//    }
	
}