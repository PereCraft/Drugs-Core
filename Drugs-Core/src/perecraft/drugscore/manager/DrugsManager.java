package perecraft.drugscore.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import perecraft.drugscore.domain.CustomDrop;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;

public class DrugsManager {

    private static DrugsManager dm = null;
    
    private HashMap<String, Drug> drugsList = new HashMap<>();
    private HashMap<String, Seed> seedsList = new HashMap<>();
    private HashMap<EntityType, CustomDrop> cdList = new HashMap<>();
    private String warningMessage;

    private DrugsManager() {
        drugsList = new HashMap<>();
        seedsList = new HashMap<>();
        cdList = new HashMap<>();
    }
    
    public static DrugsManager getManager() {
        if(dm == null)
            dm = new DrugsManager();
        
        return dm;
    }
    
    /**
     * Metodo che elimina il DrugManager
     */
    public void destroy() {
        this.drugsList = null;
        this.seedsList = null;
        this.cdList = null;
        this.warningMessage = null;
        
        this.dm = null;
    }
    
    public void giveDrug(CommandSender sender, Player target, String drugArg, int amount) {

        if(!drugsList.containsKey(drugArg.toLowerCase())) {
            sender.sendMessage("§4Errore: §cDroga non valida!");
            return;
        }
        
        drugsList.get(drugArg.toLowerCase()).giveItem(target, amount);
        sender.sendMessage("§aGivvato x" + amount + drugArg + "§7 a §e" + target.getName());

    }

    public void giveDrugSeed(CommandSender sender, Player target, String seedArg, int amount) {

        if(!seedsList.containsKey(seedArg.toLowerCase())) {
            sender.sendMessage("§4Errore: §cDroga non valida!");
            return;
        }
        
        seedsList.get(seedArg.toLowerCase()).giveItem(target, amount);
        sender.sendMessage("§aGivvato x" + amount + seedArg + "§7 a §e" + target.getName());

    }
    
    public List<Drug> getDrugsList() {
        return new ArrayList<>(drugsList.values());
    }
    
    public void setDrugsList(List<Drug> lists) {
        lists.forEach((Drug d) -> {
            if(!drugsList.containsKey(d.getId()))
                drugsList.put(d.getId(), d);
        });
    }
    
    public List<Seed> getSeedsList() {
        return new ArrayList<>(seedsList.values());
    }
    
    public void setSeedsList(List<Seed> lists) {
        lists.forEach((Seed s) -> {
            if(!seedsList.containsKey(s.getId()))
                seedsList.put(s.getId(), s);
        });
    }
    
    public List<CustomDrop> getCustomDropList() {
        return new ArrayList<>(cdList.values());
    }
    
    public void setCustomDropList(List<CustomDrop> lists) {
        lists.forEach((CustomDrop cd) -> {
            if(!cdList.containsKey(cd.getId()))
                cdList.put(cd.getId(), cd);
        });
    }
    
    public String getWarningMessage() {
        return warningMessage;
    }
    
    public void setWarningMessage(String message) {
        warningMessage = message;
    }
        
    /**
     * Metodo che ritorna la droga, nel caso non esista ritorna null.
     * @param name della droga
     * @return droga
     */
    public Drug getDrug(String name) {
        if(!drugsList.containsKey(name))
            return null;
        
        return drugsList.get(name);
    }
    
    /**
     * Metodo che ritorna il drop personalizzato, nel caso non esista l'entità 
     * ritorna null.
     * @param e entità da cercare.
     * @return drop personalizzato del entità uccisa.
     */
    public CustomDrop getCustomDrop(EntityType e) {
        if(!cdList.containsKey(e)) 
            return null;
        
        return cdList.get(e);
    }
	
}
