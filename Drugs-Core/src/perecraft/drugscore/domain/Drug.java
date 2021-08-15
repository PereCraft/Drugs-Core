package perecraft.drugscore.domain;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Drug {
    private String id;
    private String displayName;
    private Material material;
    private short shortnum;
    private List<String> lore;
    private List<PotionEffect> goodEffects;
    // TODO: Aggiungere badEffects
    private List<Material> dependencies;
    private Sound sound;
    private int amplifier;
    private int sellPrice;
    private int buyPrice;
    private String message;

    public Drug() {}
    
    public Drug(String id, String displayName, String material, short shortnum, List<String> lore, List<String> effects, List<String> dependencies, String sound, int sellPrice, int buyPrice, String message) {
        this.id = id;
        this.displayName = displayName;        
        this.material = Material.matchMaterial(material);
        this.shortnum = shortnum;
        this.lore = lore;
                
        // Caricamento effetti
        this.goodEffects = new ArrayList<>();
        effects.forEach((String effect) -> {
            this.goodEffects.add(new PotionEffect(PotionEffectType.getByName(effect), 600, 1));
        });
        
        // TODO: Fare gli effetti cattivi

        this.dependencies = new ArrayList<>();
        dependencies.forEach((String dep) -> {
            this.dependencies.add(Material.matchMaterial(dep));
        });
        
        this.sound = Sound.valueOf(sound);
        this.amplifier = 1;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public short getShortnum() {
        return shortnum;
    }

    public void setShortnum(short shortnum) {
        this.shortnum = shortnum;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public List<PotionEffect> getGoodEffects() {
        return goodEffects;
    }

    public void setGoodEffects(List<PotionEffect> effects) {
        this.goodEffects = effects;
    }
    
    public List<Material> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Material> dependencies) {
        this.dependencies = dependencies;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }    
    
    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void giveItem(Player target, int amount) {
        ItemStack item = new ItemStack(material, amount, shortnum);
        ItemMeta meta = item.getItemMeta();
        
        meta.setLocalizedName(id);
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        
        target.getInventory().addItem(item);
        target.updateInventory();
    }
    
    @Override
    public String toString() {
        
        return "Id: " + id + "\n " +
                "Display name: " + displayName + "\n " +
                "Material: " + material.toString() + "\n " +
                "Short number: " + shortnum + "\n " +
                "Lore: " + String.join(" - ", lore) + "\n " +
                "Effects: " + goodEffects + "\n " +
                "Dependencies: " + dependencies + "\n " +
                "Amplifier: " + amplifier + "\n " +
                "Sell price: " + sellPrice + "\n " +
                "Buy price: " + buyPrice;
    }
    
}
