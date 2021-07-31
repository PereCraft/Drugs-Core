package perecraft.drugscore.domain;

import java.util.List;
import org.bukkit.Material;


public class Seed {
    private String id;
    private String displayName;
    private Material material;
    private short shortnum;
    private List<String> lore;

    public Seed() {}

    public Seed(String id, String material, short shortnum, String displayName, List<String> lore) {
        this.id = id;
        this.material = Material.matchMaterial(material);
        this.shortnum = shortnum;
        this.displayName = displayName;
        this.lore = lore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Display name: " + displayName + "\n" +
                "Material: " + material.toString() + "\n" +
                "Short number: " + shortnum + "\n" +
                "Lore: " + String.join(" - ", lore);
    }
    
}
