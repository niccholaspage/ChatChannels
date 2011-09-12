package com.niccholaspage.ChatChannels;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.niccholaspage.ChatChannels.user.User;

public class ChatChannelsPlayerListener extends PlayerListener {
	private final ChatChannels plugin;
	
	public ChatChannelsPlayerListener(ChatChannels plugin){
		this.plugin = plugin;
	}
	
	public void onPlayerChat(PlayerChatEvent event){
		User user = plugin.getUserManager().getUser(event.getPlayer());
		
		for (User otherUser : plugin.getUserManager().getUsers()){
			if (user.getChannel() != otherUser.getChannel()){
				event.getRecipients().remove(otherUser.getPlayer());
			}
		}
	}
	
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		
		plugin.getUserManager().loadUser(player);
	}
	
	public void onPlayerQuit(PlayerQuitEvent event){
		saveAndRemoveUser(event.getPlayer());
	}
	
	public void onPlayerKick(PlayerKickEvent event){
		saveAndRemoveUser(event.getPlayer());
	}
	
	private void saveAndRemoveUser(Player player){
		User user = plugin.getUserManager().getUser(player);
		
		plugin.getUserManager().saveUser(user);
		
		plugin.getUserManager().removeUser(user);
	}
}
