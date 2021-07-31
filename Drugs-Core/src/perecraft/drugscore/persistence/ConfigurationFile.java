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
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;

public class ConfigurationFile {
    
    private static ConfigurationFile config = null;
    
    private File file;
    private YamlConfiguration configFile;
    
    private ConfigurationFile() throws IOException {
        file = new File(DrugsCore.getInstance().getDataFolder().getPath() + "/config.yml");
                
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            
            System.out.println("[PearRewards] Il file di configurazione non esiste, creazione del file config.yml");
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

    public List<Drug> getDrugsElements() {
        List<Drug> list = new ArrayList<>();
        
        configFile.getConfigurationSection("drugs").getKeys(false).forEach((String i) -> {
            String index = "drugs." + i;
            
            String displayName = configFile.getString(index+".displayname");            
            String material = configFile.getString(index+".material");
            short shortnum = Short.parseShort(configFile.getString(index+".short"));           

            List<String> lores = new ArrayList<>();
            configFile.getStringList(index+".lore").forEach((lore) -> lores.add(lore));
            
            // TODO: Aggiungere effetti
            List<String> effects = new ArrayList<>();
            configFile.getStringList(index+".effects").forEach((effect) -> {
                effects.add(effect);
            });
            
            String sound = configFile.getString(index+".sound");            
            int sellPrice = Integer.parseInt(configFile.getString(index+".sell-price"));
            int buyPrice = Integer.parseInt(configFile.getString(index+".buy-price"));
            
            list.add(new Drug(
                    i,
                    displayName,
                    material,
                    shortnum,
                    lores,
                    effects,
                    sound,
                    sellPrice,
                    buyPrice
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
            String displayName = configFile.getString(index+".displayname");            

            List<String> lores = new ArrayList<>();
            configFile.getStringList(index+".lore").forEach((lore) -> lores.add(lore));
            
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
}