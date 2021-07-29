/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perecraft.drugscore;

import perecraft.drugscore.listeners.DrugConsumeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import perecraft.drugscore.commands.MainCommand;
import perecraft.drugscore.listeners.*;

/**
 *
 * @author antonio
 */
public class DrugsCore extends JavaPlugin {

    private static DrugsCore plugin;
    private Double version;

    public void onEnable() {
            plugin=this;
            version=0.1;

            saveDefaultConfig();
            getCommand("drugs").setExecutor(new MainCommand());
            Bukkit.getPluginManager().registerEvents(new MobListener(), this);
            Bukkit.getPluginManager().registerEvents(new PlantListener(), this);
            Bukkit.getPluginManager().registerEvents(new DrugConsumeListener(), this);
            Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
            checkEconomy();
    }

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

    public void checkEconomy() {
            if(Bukkit.getServer().getPluginManager().getPlugin("Vault")==null) {
                    Bukkit.getLogger().severe("Plugin Vault non trovato! Drugs-Core verrà disabilitato.");
                    Bukkit.getServer().getPluginManager().disablePlugin(plugin);
                    return;
            }
            if(Bukkit.getServer().getPluginManager().getPlugin("Vault").isEnabled()) {
                    Bukkit.getLogger().info("Plugin Vault trovato. Drugs-Core abilitato!");
            }else {
                    Bukkit.getLogger().severe("Vault non è abilitato! Drugs-Core verrà disabilitato.");
                    Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            }

    }
}
