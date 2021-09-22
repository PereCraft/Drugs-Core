/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perecraft.drugscore.domain;

import java.util.List;
import org.bukkit.entity.EntityType;

/**
 *
 * @author PereCraft
 */
public class CustomDrop {
    EntityType id;
    Boolean overrideDrop;
    List<ItemExtra> ieList;
    List<Drug> drugsList;
    List<Seed> seedsList;
    Short ieChance;
    Short drugsChance;
    Short seedsChance;

    public CustomDrop() {}
    
    public CustomDrop(String entity, Boolean overrideDrop, List<ItemExtra> ieList, List<Drug> drugsList, List<Seed> seedsList, Short ieChance, Short drugsChance, Short seedsChance) {
        this.id = EntityType.valueOf(entity);
        this.overrideDrop = overrideDrop;
        this.ieList = ieList;
        this.drugsList = drugsList;
        this.seedsList = seedsList;
        this.ieChance = ieChance;
        this.drugsChance = drugsChance;
        this.seedsChance = seedsChance;
    }

    public EntityType getId() {
        return id;
    }

    public void setId(EntityType entity) {
        this.id = entity;
    }

    public Boolean getOverrideDrop() {
        return overrideDrop;
    }

    public void setOverrideDrop(Boolean overrideDrop) {
        this.overrideDrop = overrideDrop;
    }

    public List<ItemExtra> getItemExtraList() {
        return ieList;
    }

    public void setItemExtraList(List<ItemExtra> ieList) {
        this.ieList = ieList;
    }
    
    public List<Drug> getDrugsList() {
        return drugsList;
    }

    public void setDrugsList(List<Drug> drugsList) {
        this.drugsList = drugsList;
    }

    public List<Seed> getSeedsList() {
        return seedsList;
    }

    public void setSeedsList(List<Seed> seedsList) {
        this.seedsList = seedsList;
    }
    
    public Short getItemExtraChance() {
        return ieChance;
    }

    public void setItemExtraChance(Short ieChance) {
        this.ieChance = ieChance;
    }

    public Short getDrugsChance() {
        return drugsChance;
    }

    public void setDrugsChance(Short drugsChance) {
        this.drugsChance = drugsChance;
    }

    public Short getSeedsChance() {
        return seedsChance;
    }

    public void setSeedsChance(Short seedsChance) {
        this.seedsChance = seedsChance;
    }
    
    @Override
    public String toString() {        
        return "Id: " + id + "\n " +
                "Override drop: " + overrideDrop + "\n " +
                "ItemExtras drop: " + ieList + "\n " +
                "Drugs drop: " + drugsList + "\n " +
                "Seeds drop: " + seedsList + "\n " +
                "ItemExtras chance: " + ieChance + "\n " +
                "Drugs chance: " + drugsChance + "\n " +
                "Seeds chance: " + seedsChance;
    }
    
    
    
}
