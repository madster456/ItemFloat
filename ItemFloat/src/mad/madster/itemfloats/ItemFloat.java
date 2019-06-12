package mad.madster.itemfloats;

import org.bukkit.ChatColor;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;

import mad.madster.itemfloat.commands.CMDHelp;
import mad.madster.itemfloat.commands.CMDIf;
import mad.madster.itemfloat.commands.CMDPickup;

public class ItemFloat extends JavaPlugin implements Listener {
	public ArrayList<Player> canpickup = new ArrayList();
	public static String tag = ChatColor.GOLD + "[ItemFloat] ";

	public void onEnable() {
		// this.saveResource("config.yml", false);

		this.getLogger().info("sucesfully enabled!");
		this.getLogger().info("Created by https://github.com/madster456");

		getServer().getPluginManager().registerEvents(this, this);

		this.getCommand("itemfloat").setExecutor(new CMDIf());
		this.getCommand("itemfloathelp").setExecutor(new CMDHelp());
		this.getCommand("itemfloatpickup").setExecutor(new CMDPickup(this));
	}

	public void onDisable() {
		this.getLogger().info("sucesfully disabled.");

	}

	@EventHandler
	public void onPlayerPickupItem(EntityPickupItemEvent event) {
		if (event.getItem().getItemStack().getItemMeta().hasLore()
				&& event.getItem().getItemStack().getItemMeta().getLore().contains("FLOATING")
				&& !this.canpickup.contains(event.getEntity())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getItemMeta().hasLore()
				&& event.getItemDrop().getItemStack().getItemMeta().getLore().contains("FLOATING")) {
			// event.getItemDrop().setVelocity(new Vector(0, 0, 0));
			event.getPlayer().sendMessage(tag + ChatColor.GREEN + "Floating Item Placed!");
			event.getPlayer()
					.sendMessage(ChatColor.AQUA + "X: " + ChatColor.GREEN
							+ event.getItemDrop().getLocation().getBlockX() + ChatColor.AQUA + "\nY: " + ChatColor.GREEN
							+ event.getItemDrop().getLocation().getBlockY() + ChatColor.AQUA + "\nZ: " + ChatColor.GREEN
							+ event.getItemDrop().getLocation().getBlockZ());
			event.getPlayer()
					.sendMessage(ChatColor.DARK_PURPLE + "ID: " + ChatColor.GREEN
							+ event.getItemDrop().getItemStack().getType() + ChatColor.RED + "(" + ChatColor.GREEN
							+ event.getItemDrop().getItemStack().getType().toString() + ChatColor.RED + ")");
		} else {
			return;
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void itemMerg(ItemMergeEvent event) {
		if (event.getEntity().getType() == EntityType.DROPPED_ITEM) {
			ItemStack item = event.getEntity().getItemStack();

			if (item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains("FLOATING")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void itemVanish(ItemDespawnEvent event) {
		if (event.getEntity().getType() == EntityType.DROPPED_ITEM) {
			ItemStack item = event.getEntity().getItemStack();

			if (item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains("FLOATING")) {
				// event.getEntity().getWorld().dropItem(event.getEntity().getLocation(),
				// item);
				// event.getEntity().getWorld().dropItem(event.getEntity().getLocation(),
				// item).setVelocity(new Vector(0, 0, 0).multiply(0));
				// event.getEntity().setVelocity(new Vector(0, 0,
				// 0).multiply(0));
				event.setCancelled(true);
			}

		} else {
			return;
		}
	}

	// @EventHandler
	// public void alreadyFloating() {}

	// pick up only the item you are looking at.
	public void itemPickupLook(RayTraceResult event) {

	}

}
