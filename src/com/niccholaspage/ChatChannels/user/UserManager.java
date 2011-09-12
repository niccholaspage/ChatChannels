package com.niccholaspage.ChatChannels.user;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.util.config.Configuration;

import com.niccholaspage.ChatChannels.ChatChannels;
import com.niccholaspage.ChatChannels.channel.Channel;

public class UserManager {
	private final ChatChannels plugin;
	
	private final File playerFolder;
	
	private final Set<User> users;
	
	public UserManager(ChatChannels plugin){
		this.plugin = plugin;
		
		playerFolder = new File(plugin.getDataFolder(), "players");
		
		playerFolder.mkdir();
		
		users = new HashSet<User>();
	}
	
	public boolean addUser(User user){
		return users.add(user);
	}
	
	public boolean containsUser(Player player){
		return getUser(player) != null;
	}
	
	public boolean removeUser(User user){
		return users.remove(user);
	}
	
	public User getUser(Player player){
		for (User user : users){
			if (player.getName().equalsIgnoreCase(user.getPlayer().getName())){
				return user;
			}
		}
		
		return loadUser(player);
	}
	
	public User loadUser(Player player){
		File playersFile = new File(playerFolder, player.getName() + ".yml");
		
		User user;
		
		if (playersFile.exists()){
			
			Configuration config = new Configuration(playersFile);
			
			config.load();
			
			user = new User(player);
			
			String channelName = config.getString("channel");
			
			Channel channel = plugin.getChannelManager().getChannel(channelName);
			
			if (channelName == null || channel == null){
				user.setChannel(plugin.getChannelManager().getDefaultChannel());
			}
			
			user.setChannel(channel);
			
			addUser(user);
		}else {
			user = createNewUser(player, playersFile);
		}
		
		return user;
	}
	
	public void saveUser(User user){
		File playersFile = new File(playerFolder, user.getPlayer().getName() + ".yml");
		
		playersFile.delete();
		
		try {
			playersFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			
			System.out.println("Failed to save players file!");
			
			return;
		}
		
		Configuration config = new Configuration(playersFile);
		
		config.load();
		
		config.setProperty("channel", user.getChannel().getName());
		
		config.save();
	}
	
	public User createNewUser(Player player, File playersFile){
		try {
			playersFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Configuration config = new Configuration(playersFile);
		
		config.load();
		
		User user = new User(player);
		
		user.setChannel(plugin.getChannelManager().getDefaultChannel());
		
		addUser(user);
		
		return user;
	}
	
	public Set<User> getUsers(){
		return users;
	}
}
