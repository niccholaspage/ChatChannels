package com.niccholaspage.ChatChannels;

import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.niccholaspage.ChatChannels.channel.ChannelManager;
import com.niccholaspage.ChatChannels.user.UserManager;

public class ChatChannels extends JavaPlugin {
	private ChatChannelsnChatListener nChatListener;
	
	private ChatChannelsPlayerListener playerListener;
	
	private ChannelManager channelManager;
	
	private UserManager userManager;
	
	private Logger log;
	
	public void onEnable(){
		log = Logger.getLogger("Minecraft");
		
		nChatListener = new ChatChannelsnChatListener(this);
		
		playerListener = new ChatChannelsPlayerListener(this);
		
		registerEvent(Type.PLAYER_JOIN, playerListener);
		
		registerEvent(Type.PLAYER_QUIT, playerListener);
		
		registerEvent(Type.PLAYER_KICK, playerListener);
		
		registerEvent(Type.CUSTOM_EVENT, nChatListener);
		
		channelManager = new ChannelManager(this);
		
		userManager = new UserManager(this);
		
		log("I am enabled!");
	}
	
	public ChannelManager getChannelManager(){
		return channelManager;
	}
	
	public UserManager getUserManager(){
		return userManager;
	}
	
	private void registerEvent(Type type, Listener listener){
		registerEvent(type, listener, Priority.Normal);
	}
	
	private void registerEvent(Type type, Listener listener, Priority priority){
		getServer().getPluginManager().registerEvent(type, listener, priority, this);
	}
	
	public void onDisable(){
		log("Goodbye!");
	}
	
	private void log(String message){
		log.info("[ChatChannels] " + message);
	}
}
