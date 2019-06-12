package mad.madster.itemfloat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mad.madster.itemfloats.ItemFloat;

public class CMDHelp implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command is for players only.");
			return true;
		}
		Player p = (Player) sender;

		if (p.hasPermission("if.help")) {
			p.sendMessage(ChatColor.AQUA + "------------" + ChatColor.LIGHT_PURPLE + "[IF]" + ChatColor.AQUA + "------------");
			p.sendMessage(ChatColor.GOLD + "Itemfloat: " + ChatColor.GREEN + "Use this command to set an itemFloat. After command, drop item." + ChatColor.GOLD
					+ "\nitemfloatpickup: " + ChatColor.GREEN
					+ "You can pick up the floating items by turning this command ON/OFF" + ChatColor.GOLD
					+ "\nitemfloathelp: " + ChatColor.GREEN + "Using this command brings up this menu.");
			p.sendMessage(ChatColor.AQUA + "------------------------");
		} else if (p.hasPermission("if.player")) {
			p.sendMessage(ChatColor.AQUA + "-----------------------");
			p.sendMessage(ChatColor.GOLD + "\nfsihelp: " + ChatColor.GREEN + "Using this command brings up this help menu..");
			p.sendMessage(ChatColor.AQUA + "------------------------");
		} else {
			p.sendMessage(ItemFloat.tag + ChatColor.RED + "You do not have permission to use this command");
		}

		return true;
	}

}