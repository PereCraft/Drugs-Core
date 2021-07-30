package perecraft.drugscore.domain;

import java.util.List;
import org.bukkit.Material;
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
    private List<PotionEffectType> effects;
    private int amplifier;
    private int sellPrice;
    private int buyPrice;

    public Drug() {}
    
    public Drug(String id, String displayName, String material, short shortnum, List<String> lore, List<String> effects, int sellPrice, int buyPrice) {
        this.id = id;
        this.displayName = displayName;        
        this.material = Material.matchMaterial(material);
        this.shortnum = shortnum;
        this.lore = lore;
        
        // Caricamento effetti
//        effects.forEach((String effect) -> { 
//            this.effects.add(PotionEffectType.getByName(effect));
//        });
        
        this.amplifier = 1;
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

    public List<PotionEffectType> getEffects() {
        return effects;
    }

    public void setEffects(List<PotionEffectType> effects) {
        this.effects = effects;
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
//        String effect = "";
//        //effect = effects.stream().map(p -> p.getName()).reduce(effect, String::concat);
//        
        return "Id: " + id + "\n " +
                "Display name: " + displayName + "\n " +
                "Material: " + material.toString() + "\n " +
                "Short number: " + shortnum + "\n " +
                "Lore: " + String.join(" - ", lore) + "\n " +
//                //"Effects: " + effect + "\n " +
                "Amplifier: " + amplifier + "\n " +
                "Sell price: " + sellPrice + "\n " +
                "Buy price: " + buyPrice;
    }
    
}
