/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perecraft.drugscore.persistence;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import perecraft.drugscore.DrugsCore;
import perecraft.drugscore.domain.CustomDrop;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;
import perecraft.drugscore.manager.DrugsManager;

public class ConfigurationFile {
    
    private static ConfigurationFile config = null;
    
    private File file;
    private YamlConfiguration configFile;
    
    // FIX LETTURA FILE DI CONFIG
    private ConfigurationFile() throws IOException {
        file = new File(DrugsCore.getInstance().getDataFolder().getPath() + "/config.yml");
        
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            
            System.out.println("[DrugsCore] Il file di configurazione non esiste, creazione del file config.yml");
            InputStream in = DrugsCore.class.getResourceAsStream("/config.yml");
            OutputStream out = new FileOutputStream(file);

            try { 
                int n; 

                while ((n = in.read()) != -1) { 
                    out.write(n); 
                } 
            } finally { 
                if (in != null) in.close();
                if (out != null) out.close(); 
            } 
            
        }
        
        configFile = YamlConfiguration.loadConfiguration(file);
    }
    
    public static ConfigurationFile getConfigFile() throws IOException {
        
        if(config == null)
            config = new ConfigurationFile();
        
        return config;
    }
    
    public void refreshConfig() {
        configFile = YamlConfiguration.loadConfiguration(file);
    }
    
    public FileConfiguration getConfig() {
        return configFile;
    }

    public String getWarningMessage() {
        return configFile.getString("warning_message");
    }
    
    public List<Drug> getDrugsElements() {
        List<Drug> list = new ArrayList<>();
        
        configFile.getConfigurationSection("drugs").getKeys(false).forEach((String i) -> {
            String index = "drugs." + i;
            
            String displayName = configFile.getString(index+".displayname").replace("&", "§");            
            String material = configFile.getString(index+".material");
            short shortnum = Short.parseShort(configFile.getString(index+".short"));           

            List<String> lores = new ArrayList<>();
            configFile.getStringList(index+".lore").forEach((String lore) -> lores.add(lore.replace("&", "§")));
            List<String> effects = configFile.getStringList(index+".effects");
            List<String> dependencies = configFile.getStringList(index+".dependencies");

            String particle = configFile.getString(index+".particle");       
            String sound = configFile.getString(index+".sound");            
            int sellPrice = Integer.parseInt(configFile.getString(index+".sell-price"));
            int buyPrice = Integer.parseInt(configFile.getString(index+".buy-price"));
            String message = configFile.getString(index+".message");
            
            list.add(new Drug(
                    i,
                    displayName,
                    material,
                    shortnum,
                    lores,
                    effects,
                    particle,
                    dependencies,
                    sound,
                    sellPrice,
                    buyPrice,
                    message
            ));
        });
        
        return list;
    }
    
    public List<Seed> getSeedElements() {
        List<Seed> list = new ArrayList<>();
        
        configFile.getConfigurationSection("seeds").getKeys(false).forEach((String i) -> {
            String index = "seeds."+i;
            
            String material = configFile.getString(index+".material");
            short shortnum = Short.parseShort(configFile.getString(index+".short"));           
            String displayName = configFile.getString(index+".displayname").replace("&", "§");            

            List<String> lores = new ArrayList<>();
            configFile.getStringList(index+".lore").forEach((lore) -> lores.add(lore.replace("&", "§")));
            
            list.add(new Seed(
                    i, 
                    material,
                    shortnum,
                    displayName,
                    lores
            ));
        });
        
        return list;
    }
    
    public List<CustomDrop> getCustomDrops() {
        List<CustomDrop> list = new ArrayList<>();
        
        configFile.getConfigurationSection("custom-drops").getKeys(false).forEach((String i) -> {
            String index = "custom-drops."+i;
            
            Boolean overrideDrop = configFile.getBoolean(index+"override-drops");
            
            System.out.println("configFile override: " + configFile.getBoolean(index+"override-drops"));
            
            List<Drug> drugList = new ArrayList();
            configFile.getStringList(index+".drugs-drop").forEach((String nameDrug) -> {
                DrugsManager.getManager().getDrugsList().forEach((Drug d) -> {
                    if(nameDrug.equalsIgnoreCase(d.getId())) drugList.add(d);
                });
            });

            List<Seed> seedList = new ArrayList();
            configFile.getStringList(index+".seeds-drop").forEach((String nameSeed) -> {     
                DrugsManager.getManager().getSeedsList().forEach((Seed s) -> {
                    if(nameSeed.equalsIgnoreCase(s.getId())) seedList.add(s);
                });
            });

            Short drugsChance = Short.valueOf(configFile.getString(index+".drug-drop-chance"));
            Short seedsChance = Short.valueOf(configFile.getString(index+".seed-drop-chance"));
                    
            list.add(new CustomDrop(
                    i, 
                    overrideDrop,
                    drugList,
                    seedList,
                    drugsChance,
                    seedsChance
            ));
            
        });
        
        return list;        
    }
}