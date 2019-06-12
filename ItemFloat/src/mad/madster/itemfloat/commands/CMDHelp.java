package mad.madster.itemfloat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDHelp implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command is for players only.");
			return true;
		}
		Player p = (Player) sender;

		if (p.hasPermission("if.staff")) {
			p.sendMessage(ChatColor.AQUA + "-----------------------");
			p.sendMessage(ChatColor.GOLD + "Itemfloat: " + ChatColor.GREEN + "TestTestTest" + ChatColor.GOLD
					+ "\nitemfloatpickup: " + ChatColor.GREEN
					+ "You can pick up the floating items by turning this command ON/OFF" + ChatColor.GOLD
					+ "\nitemfloathelp: " + ChatColor.GREEN + "Using this command brings up this menu.");
			p.sendMessage(ChatColor.AQUA + "-----------------------");
		} else if (p.hasPermission("fsi.player")) {
			p.sendMessage(ChatColor.AQUA + "-----------------------");
			p.sendMessage(ChatColor.GOLD + "\nfsihelp: " + ChatColor.GREEN + "Using this command brings up this menu.");
			p.sendMessage(ChatColor.AQUA + "-----------------------");
		} else {
			p.sendMessage(ChatColor.GOLD + "[FI] " + ChatColor.RED + "You do not have permission to use this command");
		}

		return true;
	}

}