package perecraft.drugscore.manager;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Sound;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;
import perecraft.drugscore.manager.exception.DrugsManagerException;

public class DrugsManager {

    private static HashMap<String, Drug> drugsList = new HashMap<>();
    private static HashMap<String, Seed> seedsList = new HashMap<>();
    private static String warningMessage;

    public static void giveDrug(CommandSender sender, Player target, String drugArg, int amount) {

        if(!drugsList.containsKey(drugArg.toLowerCase())) {
            sender.sendMessage("§4Errore: §cDroga non valida!");
            return;
        }
        
        drugsList.get(drugArg.toLowerCase()).giveItem(target, amount);
        sender.sendMessage("§aGivvato x" + amount + drugArg + "§7 a §e" + target.getName());

    }

    public static void giveDrugSeed(CommandSender sender, Player target, String seedArg, int amount) {

        if(!seedsList.containsKey(seedArg.toLowerCase())) {
            sender.sendMessage("§4Errore: §cDroga non valida!");
            return;
        }
        
        seedsList.get(seedArg.toLowerCase()).giveItem(target, amount);
        sender.sendMessage("§aGivvato x" + amount + seedArg + "§7 a §e" + target.getName());

    }
    
    public static void setDrugsList(List<Drug> lists) {
        lists.forEach((d) -> {
            if(!drugsList.containsKey(d.getId()))
                drugsList.put(d.getId(), d);
        });
    }
    
    public static void setSeedsList(List<Seed> lists) {
        lists.forEach((d) -> {
            if(!seedsList.containsKey(d.getId()))
                seedsList.put(d.getId(), d);
        });
    }
    
    public static void setWarningMessage(String message) {
        warningMessage = message;
    }
    
    public static String getWarningMessage() {
        return warningMessage;
    }
    
    public static boolean checkDrug(String name) {
        return drugsList.containsKey(name);
    }
    
    public static List<PotionEffect> getEffects(String name) throws DrugsManagerException {
        if(!drugsList.containsKey(name)) 
            throw new DrugsManagerException();
        
        return drugsList.get(name).getGoodEffects();
    }
    
    public static Sound getSound(String name) throws DrugsManagerException {
        if(!drugsList.containsKey(name)) 
            throw new DrugsManagerException();
        
        return drugsList.get(name).getSound();
    }
	
}
