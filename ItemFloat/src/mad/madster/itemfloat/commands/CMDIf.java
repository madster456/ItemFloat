package mad.madster.itemfloat.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import mad.madster.itemfloats.ItemFloat;

public class CMDIf implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command is for players only.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("if.float")) {
			p.sendMessage(ItemFloat.tag + ChatColor.RED + "You do not have permission to do this.");
			return true;
		}

		if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
			p.sendMessage(p.getInventory().getHeldItemSlot() + "");
			p.sendMessage(ItemFloat.tag + ChatColor.RED + "You can not place air!");
			return true;
		}

		if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()
				&& p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("FLOATING")) {

			p.sendMessage("This Item is already a floating item. Go ahead and place!");
		} else {
			ItemStack item = p.getInventory().getItemInMainHand();
			ItemMeta itemmeta = item.getItemMeta();
			ArrayList<String> lore = new ArrayList<>();
			lore.add("FLOATING");
			itemmeta.setDisplayName(ChatColor.GOLD + "[ItemFloat] " + ChatColor.WHITE + getItemName(item));
			itemmeta.setLore(lore);
			item.setItemMeta(itemmeta);
			p.sendMessage(ItemFloat.tag + ChatColor.GREEN + "Drop the item now!");

		}

		return true;
	}

	private String getItemName(ItemStack item) {
		return item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName()
				: capitalize(item.getType().toString().toLowerCase().replaceAll("_", " "), null);
	}

	public static String capitalize(final String str, final char... delimiters) {
		final int delimLen = delimiters == null ? -1 : delimiters.length;
		if ((str == null || str.equals("")) || delimLen == 0) {
			return str;
		}
		final char[] buffer = str.toCharArray();
		boolean capitalizeNext = true;
		for (int i = 0; i < buffer.length; i++) {
			final char ch = buffer[i];
			if (isDelimiter(ch, delimiters)) {
				capitalizeNext = true;
			} else if (capitalizeNext) {
				buffer[i] = Character.toTitleCase(ch);
				capitalizeNext = false;
			}
		}
		return new String(buffer);
	}

	private static boolean isDelimiter(final char ch, final char[] delimiters) {
		if (delimiters == null) {
			return Character.isWhitespace(ch);
		}
		for (final char delimiter : delimiters) {
			if (ch == delimiter) {
				return true;
			}
		}
		return false;
	}
}
