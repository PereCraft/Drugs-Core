package perecraft.drugscore.commands;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import perecraft.drugscore.DrugsCore;
import perecraft.drugscore.manager.DrugsManager;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {

        if(sender instanceof Player || sender instanceof ConsoleCommandSender) {

            if(args.length == 0) {
                getHelp(sender);
                return false;
            }

            String noperm = "§cNon hai accesso a quel comando";

            if(args[0].equalsIgnoreCase("give")) {

                if(!sender.hasPermission("drugs.give")) {
                    sender.sendMessage(noperm);
                    return false;
                }

                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) {
                    sender.sendMessage("§4Errore: §cPlayer non trovato!");
                    return false;
                }

                if(args.length != 4 || !NumberUtils.isNumber(args[3])) {
                    sender.sendMessage("§4Uso: §c/drugs give [Player] [drugid] [amount]");
                    return false;
                }

                int amount = Integer.parseInt(args[3]);
                if(amount < 1 || amount > 64) {
                    sender.sendMessage("§4Errore: §cMin 1 - Max 64");
                    return false;
                }

                DrugsManager.giveDrug(sender, target, args[2], amount);

            } else if(args[0].equalsIgnoreCase("giveseed")) {

                if(!sender.hasPermission("drugs.giveseed")) {
                    sender.sendMessage(noperm);
                    return false;
                }

                Player target = Bukkit.getPlayer(args[1]);
                if(target == null) {
                    sender.sendMessage("§4Errore: §cPlayer non trovato!");
                    return false;
                }

                if(args.length != 4 || !NumberUtils.isNumber(args[3])) {
                    sender.sendMessage("§4Uso: §c/drugs seedgive [Player] [drugid] [amount]");
                    return false;
                }

                int amount = Integer.parseInt(args[3]);
                if(amount<1 || amount>64) {
                    sender.sendMessage("§4Errore: §cMin 1 - Max 64");
                    return false;
                }

                DrugsManager.giveDrugSeed(sender, target, args[2], amount);

            } else if(args[0].equalsIgnoreCase("reload")) {

                if(!sender.hasPermission("drugs.reload")) {
                    sender.sendMessage(noperm);
                    return false;
                }

                DrugsCore.getInstance().onReload();
                sender.sendMessage("[Drugs-Core] §aReloaded!");

            } else if(args[0].equalsIgnoreCase("refresh")) {

                if(!sender.hasPermission("drugs.refresh")) {
                    sender.sendMessage(noperm);
                    return false;
                }

                if(args[1].equalsIgnoreCase("config")) {
                    DrugsCore.getInstance().reloadConfig();
                }

                else if(args[1].equalsIgnoreCase("drugs")) {
                    //TODO: Metodo reload drugs esempio:
                    //DrugsManager.reloadDrugs();
                }

            } else {
                sender.sendMessage("§cArgomento sconosciuto. Digita §7/dr §cper aiuto.");
            }
        }
        
        return false;	
    }

    private void getHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "------ " + ChatColor.GREEN + "PereCraft Drugs" + ChatColor.GRAY + " ------");
        sender.sendMessage("§aDrugs-Core §7version: §e" + DrugsCore.getInstance().getVersion() + "§7 by §aPereCraft");

        // TODO: Ci starebbe riorganizzare meglio i comandi, prendendoli in modo
        // dinamici da un file di config
        
        if(sender.hasPermission("drugs.help")) {

            sender.spigot().sendMessage(new ComponentBuilder("/dr give [player] [drugname] [amount]")
                .color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dr give "))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Givva una droga ad un giocatore")))
                .create()
            );

            sender.spigot().sendMessage(new ComponentBuilder("/dr giveseed [player] [drugname] [amount]")
                .color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dr giveseed "))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Givva un seme ad un giocatore")))
                .create()
            );

            sender.spigot().sendMessage(new ComponentBuilder("/dr reload")
                .color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dr reload"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Reload plugin")))
                .create()
            );

            sender.spigot().sendMessage(new ComponentBuilder("/dr refresh")
                .color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/dr reload"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("Refresh plugin configuration")))
                .create()
            );

        }else {
            sender.sendMessage(ChatColor.RED + "Non hai i permessi necessari.");
        }

        sender.sendMessage(ChatColor.RED + "Find more: " + ChatColor.GRAY + "https://github.com/PereCraft");
        sender.sendMessage(ChatColor.GRAY + "------" + ChatColor.GREEN + "PereCraft Drugs" + ChatColor.GRAY + "------");
    }
	
}
