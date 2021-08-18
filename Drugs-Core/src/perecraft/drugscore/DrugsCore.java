package perecraft.drugscore;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import perecraft.drugscore.listeners.DrugConsumeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import perecraft.drugscore.commands.MainCommand;
import perecraft.drugscore.domain.CustomDrop;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;
import perecraft.drugscore.listeners.*;
import perecraft.drugscore.manager.DrugsManager;
import perecraft.drugscore.persistence.ConfigurationFile;

public class DrugsCore extends JavaPlugin {

    private static DrugsCore plugin;
    private Double version;

    @Override
    public void onEnable() {
        this.plugin = this;
        this.version = 0.1;

        List<Drug> drugsList = null;
        List<Seed> seedsList = null;
        List<CustomDrop> cdList = null;
        try {
            DrugsManager.getManager().setWarningMessage(ConfigurationFile.getConfigFile().getWarningMessage());
            
            // Leggere configurazione di semi e droghe
            drugsList = ConfigurationFile.getConfigFile().getDrugsElements();
            seedsList = ConfigurationFile.getConfigFile().getSeedElements();
            
            Bukkit.getLogger().info("Droghe caricate: ");
            drugsList.forEach((d) -> Bukkit.getLogger().info(d.toString()));
            DrugsManager.getManager().setDrugsList(drugsList);

            Bukkit.getLogger().info("Semi caricati: ");
            seedsList.forEach((s) -> Bukkit.getLogger().info(s.toString()));
            DrugsManager.getManager().setSeedsList(seedsList);

            // Leggere configurazione drop personalizzati
            cdList = ConfigurationFile.getConfigFile().getCustomDrops();

            Bukkit.getLogger().info("Drop personalizzati caricati: ");
            cdList.forEach((s) -> Bukkit.getLogger().info(s.toString()));
            DrugsManager.getManager().setCustomDropList(cdList);
        
        } catch(IOException ex) {
            Bukkit.getLogger().log(Level.WARNING, "Impossibile leggere il file di configurazione.{0}", ex.getMessage());
            Bukkit.getLogger().log(Level.SEVERE, ex.getMessage());
            onDisable();
        }
        
        getCommand("drugs").setExecutor(new MainCommand());

        Bukkit.getPluginManager().registerEvents(new MobListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlantListener(), this);
        Bukkit.getPluginManager().registerEvents(new DrugConsumeListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);

        checkEconomy();
        Bukkit.getLogger().info("Drugs-Core attivato");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Drugs-Core disattivato");
    }
    
    /**
     * Metodo che ricarica il file di config.
     */
    public void onRefresh() {
        List<Drug> drugsList = null;
        List<Seed> seedsList = null;
        List<CustomDrop> cdList = null;
        
        DrugsManager.getManager().destroy();
        
        try {
            ConfigurationFile.getConfigFile().refreshConfig();
            DrugsManager.getManager().setWarningMessage(ConfigurationFile.getConfigFile().getWarningMessage());
            
            // Leggere configurazione di semi e droghe
            drugsList = ConfigurationFile.getConfigFile().getDrugsElements();
            seedsList = ConfigurationFile.getConfigFile().getSeedElements();
            
            Bukkit.getLogger().info("Droghe caricate: ");
            drugsList.forEach((d) -> Bukkit.getLogger().info(d.toString()));
            DrugsManager.getManager().setDrugsList(drugsList);

            Bukkit.getLogger().info("Semi caricati: ");
            seedsList.forEach((s) -> Bukkit.getLogger().info(s.toString()));
            DrugsManager.getManager().setSeedsList(seedsList);

            // Leggere configurazione drop personalizzati
            cdList = ConfigurationFile.getConfigFile().getCustomDrops();

            Bukkit.getLogger().info("Drop personalizzati caricati: ");
            cdList.forEach((s) -> Bukkit.getLogger().info(s.toString()));
            DrugsManager.getManager().setCustomDropList(cdList);
        
        } catch(IOException ex) {
            Bukkit.getLogger().log(Level.WARNING, "Impossibile leggere il file di configurazione.{0}", ex.getMessage());
            Bukkit.getLogger().log(Level.SEVERE, ex.getMessage());
            onDisable();
        }
        
        Bukkit.getLogger().info("File config ricaricato");
    }
    
    /**
     * Metodo che ricarica il plugin (compreso il file di config).
     */
    public void onReload() {
        this.onRefresh();
        plugin.getServer().getPluginManager().disablePlugin(plugin);
        plugin.getServer().getPluginManager().enablePlugin(plugin);
        Bukkit.getLogger().info("Plugin ricaricato");
    }

    public static DrugsCore getInstance() {
        return plugin;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version=version;
    }
    
    /**
     * Metodo che assicura che la dipendenza Vault sia impostata, in caso contrario
     * disabilita il metodo.
     */
    public void checkEconomy() {
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getLogger().severe("Plugin Vault non trovato! Drugs-Core verrà disabilitato.");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        if(Bukkit.getServer().getPluginManager().getPlugin("Vault").isEnabled()) {
            Bukkit.getLogger().info("Plugin Vault trovato. Drugs-Core abilitato!");
        } else {
            Bukkit.getLogger().severe("Vault non è abilitato! Drugs-Core verrà disabilitato.");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        }

    }
}
