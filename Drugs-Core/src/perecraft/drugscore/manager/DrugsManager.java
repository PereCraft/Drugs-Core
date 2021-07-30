package perecraft.drugscore.manager;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DrugsManager {

    public static String cocainename = "§f§lCocaine";
    public static List<String> cocainelore = Arrays.asList("§7Ti da un po' di carica");
    public static String weedname = "§2§lWeed";
    public static List<String> weedlore = Arrays.asList("§7Ti rigerera ma ti fa venire fame");
    public static String cocaineseedname = "§7§lCocaine seeds";
    public static List<String> cocaineseedlore = Arrays.asList("§7Serve per piantare una pianta di coca", "Ricorda di zappare il terreno!");

    public static void GiveDrug(CommandSender sender, Player target, String drug, int amount) {

        if(drug.equalsIgnoreCase("cocaine")) {
            ItemStack cocaineitem = new ItemStack(Material.SUGAR, amount, (short) 0);
            ItemMeta cocainemeta = cocaineitem.getItemMeta();

            cocainemeta.setDisplayName(cocainename);
            cocainemeta.setLore(cocainelore);
            cocaineitem.setItemMeta(cocainemeta);

            target.getInventory().addItem(cocaineitem);
            target.updateInventory();
        } else if(drug.equalsIgnoreCase("weed")) {
            ItemStack weeditem = new ItemStack(Material.INK_SACK, amount, (short) 2);
            ItemMeta weedmeta = weeditem.getItemMeta();

            weedmeta.setDisplayName(weedname);
            weedmeta.setLore(weedlore);
            weeditem.setItemMeta(weedmeta);

            target.getInventory().addItem(weeditem);
            target.updateInventory();
        } else {
            sender.sendMessage("§4Errore: §cDroga non valida!");
            return;
        }

        sender.sendMessage("§aGivvato x" + amount + drug + "§7 a §e" + target.getName());

    }

    public static void GiveDrugSeed(CommandSender sender, Player target, String drug, int amount) {

        if(drug.equalsIgnoreCase("cocaine")) {
            ItemStack cocaineseedsitem = new ItemStack(Material.PUMPKIN_SEEDS, amount, (short) 0);
            ItemMeta cocaineseedsmeta = cocaineseedsitem.getItemMeta();

            cocaineseedsmeta.setDisplayName(cocaineseedname);
            cocaineseedsmeta.setLore(cocaineseedlore);
            cocaineseedsitem.setItemMeta(cocaineseedsmeta);

            target.getInventory().addItem(cocaineseedsitem);
            target.updateInventory();
        } else {
            sender.sendMessage("§4Errore: §cSeme non valido!");
            return;
        }

        sender.sendMessage("§aGivvato x" + amount + drug + "§7 a §e" + target.getName());

    }
	
}
