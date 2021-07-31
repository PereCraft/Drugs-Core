package perecraft.drugscore;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import perecraft.drugscore.listeners.DrugConsumeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import perecraft.drugscore.commands.MainCommand;
import perecraft.drugscore.domain.Drug;
import perecraft.drugscore.domain.Seed;
import perecraft.drugscore.listeners.*;
import perecraft.drugscore.persistence.ConfigurationFile;

public class DrugsCore extends JavaPlugin {

    private static DrugsCore plugin;
    private Double version;

    @Override
    public void onEnable() {
        plugin=this;
        version=0.1;

        //saveDefaultConfig();
        List<Drug> drugsList = null;
        List<Seed> seedsList = null;
        try {
            drugsList = ConfigurationFile.getConfigFile().getDrugsElements();
            seedsList = ConfigurationFile.getConfigFile().getSeedElements();
        } catch(IOException ex) {
            Bukkit.getLogger().log(Level.WARNING, "Impossibile leggere il file di configurazione.{0}", ex.getMessage());
            Bukkit.getLogger().log(Level.SEVERE, ex.getMessage());
            onDisable();
        }

        Bukkit.getLogger().info("Droghe caricate: ");
        drugsList.forEach((d) -> Bukkit.getLogger().info(d.toString()));
        
        Bukkit.getLogger().info("Semi caricati: ");
        seedsList.forEach((s) -> Bukkit.getLogger().info(s.toString()));
        
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

    public void onReload() {
        reloadConfig();
        plugin.getServer().getPluginManager().disablePlugin(plugin);
        plugin.getServer().getPluginManager().enablePlugin(plugin);
        Bukkit.getLogger().info("Plugin reloaded");
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
