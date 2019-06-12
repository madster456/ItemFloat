package mad.madster.itemfloat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mad.madster.itemfloats.ItemFloat;

public class CMDPickup implements CommandExecutor {

	private ItemFloat main;

	public CMDPickup(ItemFloat main) {
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command is for players only.");
			return true;
		}
		Player p = (Player) sender;
		if (!p.hasPermission("if.staff")) {
			p.sendMessage(ChatColor.GOLD + "[ItemFloat] " + ChatColor.RED + "You do not have permission to do this.");
			return true;
		}

		if (main.canpickup.contains(p)) {
			main.canpickup.remove(p);
			p.sendMessage(ChatColor.GOLD + "[ItemFloat] " + ChatColor.GREEN + "You will no longer pickup floating items."
					+ ChatColor.GOLD + "\n[ItemFloat] " + ChatColor.GREEN + "To pick items up, use this command again!");
		} else {
			main.canpickup.add(p);
			p.sendMessage(
					ChatColor.GOLD + "[ItemFloat] " + ChatColor.GREEN + "You can now pick up floating items." + ChatColor.GOLD
							+ "\n[ItemFloat] " + ChatColor.GREEN + "To disable floating item pickup, use this command again!");
		}

		return true;
	}
}