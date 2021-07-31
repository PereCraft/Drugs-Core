package perecraft.drugscore.domain;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

public class Drug {
    private String id;
    private String displayName;
    private Material material;
    private short shortnum;
    private List<String> lore;
    private List<PotionEffectType> goodEffects;
    // TODO: Aggiungere badEffects
    private Sound sound;
    private int amplifier;
    private int sellPrice;
    private int buyPrice;

    public Drug() {}
    
    public Drug(String id, String displayName, String material, short shortnum, List<String> lore, List<String> effects, String sound, int sellPrice, int buyPrice) {
        this.id = id;
        this.displayName = displayName;        
        this.material = Material.matchMaterial(material);
        this.shortnum = shortnum;
        this.lore = lore;
        
        // Caricamento effetti
        this.goodEffects = new ArrayList<>();
        effects.forEach((String effect) -> {
            this.goodEffects.add(PotionEffectType.getByName(effect));
        });
        
        this.sound = Sound.valueOf(sound);
        this.amplifier = 1;
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

    public List<PotionEffectType> getGoodEffects() {
        return goodEffects;
    }

    public void setGoodEffects(List<PotionEffectType> effects) {
        this.goodEffects = effects;
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
    
    public void giveItem(Player target, int amount) {
        ItemStack item = new ItemStack(material, amount, shortnum);
        ItemMeta meta = item.getItemMeta();
        
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        
        target.getInventory().addItem(item);
        target.updateInventory();
    }

    @Override
    public String toString() {
        String goodEffect = "";
        // TODO: Rendere più figa la visione della lista elementi
        goodEffect = goodEffects.stream().map(p -> p.getName()).reduce(goodEffect, String::concat);
        
        return "Id: " + id + "\n " +
                "Display name: " + displayName + "\n " +
                "Material: " + material.toString() + "\n " +
                "Short number: " + shortnum + "\n " +
                "Lore: " + String.join(" - ", lore) + "\n " +
                "Effects: " + goodEffect + "\n " +
                "Amplifier: " + amplifier + "\n " +
                "Sell price: " + sellPrice + "\n " +
                "Buy price: " + buyPrice;
    }
    
}
