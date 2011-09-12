package com.niccholaspage.ChatChannels.user;

import org.bukkit.entity.Player;

import com.niccholaspage.ChatChannels.channel.Channel;

public class User {
	private final Player player;
	
	private Channel channel = null;
	
	public User(Player player){
		this.player = player;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void setChannel(Channel channel){
		this.channel = channel;
	}
	
	public Channel getChannel(){
		return channel;
	}
}
