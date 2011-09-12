package com.niccholaspage.ChatChannels.channel;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.util.config.Configuration;

import com.niccholaspage.ChatChannels.ChatChannels;

public class ChannelManager {
	private final Configuration config;
	
	private Channel defaultChannel = null;
	
	private final Set<Channel> channels;
	
	public ChannelManager(ChatChannels plugin){
		File channelFile = new File(plugin.getDataFolder(), "players");
		
		try {
			channelFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		config = new Configuration(channelFile);
		
		channels = new HashSet<Channel>();
		
		load();
	}
	
	private void load(){
		config.load();
		
		//Write default everything
		
		List<String> keys = config.getKeys("channels");
		
		for (String key : keys){
			Channel channel = new Channel(key);
			
			channels.add(channel);
		}
		
		String defaultChannelName = config.getString("default");
		
		defaultChannel = getChannel(defaultChannelName);
	}
	
	public Channel getChannel(String name){
		for (Channel channel : channels){
			if (channel.getName().equalsIgnoreCase(name)){
				return channel;
			}
		}
		
		return null;
	}
	
	public Channel getDefaultChannel(){
		return defaultChannel;
	}
}
