
package com.noahswitzer;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.io.File;
import java.util.*;

import org.bukkit.block.Block;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import javax.swing.event.DocumentEvent.EventType;

import com.avaje.ebeaninternal.server.persist.dml.InsertMeta;
import com.noahswitzer.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.StringUtil;



@SuppressWarnings("unused")
public class TruBlud extends JavaPlugin implements Listener, CommandExecutor {
	public static TruBlud plugin;	

	public void onEnable() {
try{
	FileConfiguration config;
    			config = getConfig();
			File config2 = new File("plugins" + File.separator + "<variable>" + File.separator + "config.yml");
			config2.mkdir();
			if(!config.contains("general.TruBlud.item")){
				config.set("general.TruBlud.item", "373");}
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getMainScoreboard();
			if (board.equals(null)){
				manager.getNewScoreboard();
				Team team = board.registerNewTeam("Human");
			}
					

}catch(Exception e1){e1.printStackTrace();}
	
saveConfig();
this.saveDefaultConfig();

	getLogger().info("TruBlud enabled!");
	getServer().getPluginManager().registerEvents(this, this);
	  
	}
	
public void onDisable() {getLogger().info("TruBlud disabled! :D");}




@EventHandler
public void onPlayerJoin(PlayerJoinEvent evt) {
    Player player = evt.getPlayer();
  
 player.getPlayer().sendMessage(this.getConfig().getString("message"));
     List<String> rules = this.getConfig().getStringList("rules");
for (String s : rules)
 player.getPlayer().sendMessage(s); 



FileConfiguration config;
config = getConfig(); 
if(!config.contains("players." + player.getDisplayName())){
	config.set("players." + player.getDisplayName()+ ".Race", "Human");
	ScoreboardManager manager = Bukkit.getScoreboardManager();
	Scoreboard board = manager.getMainScoreboard();
	Team teama = board.getTeam("Human");
	teama.addPlayer(player);
}
 
String isit = config.getString("players." + player.getDisplayName()+ ".Race");
ScoreboardManager manager = Bukkit.getScoreboardManager();
Scoreboard board = manager.getMainScoreboard();
Team teama = board.getTeam(isit);
if (teama.getName() != null){

}else{
	board.registerNewTeam(isit);
	teama = board.registerNewTeam(isit);
}
Team team = board.getPlayerTeam(player);
if (teama.getName() != null){team.removePlayer(player);}
teama.addPlayer(player);
teama.setAllowFriendlyFire(false);


    saveConfig();
    
}


public ItemStack changeAuthor(Player author, ItemStack book){
	ItemMeta itemMeta = book.getItemMeta(); //Get the metadata
	BookMeta bookMeta = (BookMeta)itemMeta; //since its a book, cast it into book meta
	bookMeta.setAuthor(author.getName()); //sets the author. This is a BookMeta specific method.
	book.setItemMeta(bookMeta); //saves the metadata to the item stack
	return book; //returns the book object. You should give this to the player, to refresh their inventory
	}

public void givetrublud(Player player){
	 PlayerInventory inventory = player.getInventory(); 
	     Potion trublud = new Potion(PotionType.STRENGTH, 1); 
	     ItemStack stack = trublud.toItemStack(1);
	     PotionMeta meta = (PotionMeta) stack.getItemMeta();
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 9000, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9000, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9000, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 9000, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9000, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9000, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 9000, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 900, 1), isEnabled());
	     meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 9000, 1), isEnabled());
	     meta.setDisplayName("TruBlud");
	     stack.setItemMeta(meta);
	     player.getInventory().addItem(stack);
}
public void givelight(Player player){
 PlayerInventory inventory = player.getInventory(); // The player's inventory
 ItemStack stack = new ItemStack(Material.FIREWORK, 1);
 FireworkMeta meta = (FireworkMeta) stack.getItemMeta();

 Builder effect1 = FireworkEffect.builder();
 effect1.with(Type.BALL_LARGE);
 effect1.withColor(Color.WHITE);
 effect1.withFlicker();
 meta.addEffect(effect1.build());

 Builder effect2 = FireworkEffect.builder();
 effect2.with(Type.BURST);
 effect2.withColor(Color.SILVER);
 effect2.withFlicker();
 meta.addEffect(effect2.build());
 meta.setDisplayName("Fairy Light");
 meta.setPower(0);

 stack.setItemMeta(meta);
 player.getInventory().addItem(stack);
}




private ItemStack setname(ItemStack is, String name, List<String> lore){
	ItemMeta im = is.getItemMeta();
	if(name != null)
		im.setDisplayName(name);
	if(lore != null)
		im.setLore(lore);
	is.setItemMeta(im);
	return is;
	}


	@EventHandler
	public void onPlayerInteractBlock(PlayerInteractEvent evt){
		if(evt.getPlayer().getItemInHand().getTypeId() == Material.FISHING_ROD.getId()){
			evt.getPlayer().getWorld().strikeLightning(evt.getPlayer().getTargetBlock(null, 200).getLocation());
		Player player = evt.getPlayer();
			evt.getPlayer().setFireTicks(1000);
		player.sendMessage(ChatColor.RED+ "[TruBlud]" + ChatColor.GREEN + " GET OUT OF THE SUN!");
	}}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent evt) {
		Player player = evt.getPlayer();
		evt.setCancelled(true);
		player.sendMessage(ChatColor.RED + "[TruBlud]" + ChatColor.GREEN + " No Dropping Stuff!");
		 sun(player);
			
			  }
	
	 public  void sun (Player player) {
		 FileConfiguration config;
		 config = getConfig(); 
		 String isit = config.getString("players." + player.getDisplayName()+ ".Race");
		 if(config.contains("players." + player.getDisplayName())){
			if (isit == "Vampire" ){}else{
					 player.sendMessage(isit);
			        Location plocation = player.getLocation();
			        int topX =plocation.getBlockX();
			        int topZ = plocation.getBlockZ();
			        int topy1 = plocation.getBlockY();
			        int topY = plocation.getWorld().getHighestBlockYAt(topX, topZ);
			        player.sendMessage(ChatColor.RED + "[TruBlud]" + ChatColor.GREEN + topy1);
			        player.sendMessage(ChatColor.RED + "[TruBlud]" + ChatColor.GREEN + topY);
			       	if (Bukkit.getWorld(player.getWorld().getName()).getTime() >= 14000) { } else{  
			    		if(topy1 >= topY){player.setFireTicks(100);
			       	 player.sendMessage(ChatColor.RED + "[TruBlud]" + ChatColor.GREEN + " Get out of the sun!");
						}else{} 			
			    		}
			}
		 }
}
	      
	        
	        
	       
	 
	public void LivingHurtEvent(EventType event)
	{
	
	}

	public void setMetadata(Player player, String key, Object value, Plugin plugin){
		  player.setMetadata(key,new FixedMetadataValue(plugin,value));
		}
		public Object getMetadata(Player player, String key, Plugin plugin){
		  List<MetadataValue> values = player.getMetadata(key);  
		  for(MetadataValue value : values){
		     if(value.getOwningPlugin().getDescription().getName().equals(plugin.getDescription().getName())){
		        return value.value();
		     }
		  }
		return values;
		}
	
	@Override 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		FileConfiguration config;
		config = getConfig(); 
		String isit = config.getString("players." + player.getDisplayName()+ ".Race");

	if (cmd.getName().equalsIgnoreCase("Hybrid")) {
			player.setMaxHealth(40);
			config.set("players." + player.getDisplayName()+ ".Race", "Hybrid");
			player.sendMessage("Your a Special!!");

			}	
	
	if (cmd.getName().equalsIgnoreCase("Shifter")) {
			player.setMaxHealth(15);
			config.set("players." + player.getDisplayName()+ ".Race", "Shifter");
			player.sendMessage("Your a ShapeShifter!");
			}

	 if (cmd.getName().equalsIgnoreCase("FangBanger")) {
			player.setMaxHealth(12);
		config.set("players." + player.getDisplayName()+ ".Race", "FangBanger");
			player.sendMessage("Your a Vamp lover!");
			givetrublud(player);

			    	 }
	 if (cmd.getName().equalsIgnoreCase("Fairy")) {
			player.setMaxHealth(8);
			givelight(player);
		config.set("players." + player.getDisplayName()+ ".Race", "Fairy");
			player.sendMessage("Your a sweetblooded Fairy!");
		;
	 }
	 if (cmd.getName().equalsIgnoreCase("Police")) {
			player.setMaxHealth(11);
		config.set("players." + player.getDisplayName()+ ".Race", "Police");
			player.sendMessage("Your a Vamp Police agent!");
		
	 }
	if (cmd.getName().equalsIgnoreCase("Fanger")) {
		player.setMaxHealth(5);
	config.set("players." + player.getDisplayName()+ ".Race", "Vampire");
		player.sendMessage("Your a Vamp!");
		givetrublud(player);
;
	}
	  
	    saveConfig();		
 if (cmd.getName().equalsIgnoreCase("Trublud")) {
					
			  if (args.length == 1) {
			         if (args[0].equalsIgnoreCase("reload")) {
			        								
							PluginManager plg = Bukkit.getPluginManager();  
				            Plugin plgname = plg.getPlugin("TruBlud");   
				            plg.disablePlugin(plgname);  
				             plg.enablePlugin(plgname);   
				            player.sendMessage(ChatColor.YELLOW + "[TruBlud]" + ChatColor.GREEN + ChatColor.BOLD + " Plugin Reloaded!");
		}				     	 	        
			 }
				
			 }
		return true; 
		 
}
	
	
}
