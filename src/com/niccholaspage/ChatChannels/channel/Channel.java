package com.niccholaspage.ChatChannels.channel;

public class Channel {
	private final String name;
	
	private final String nick;
	
	public Channel(String name, String nick){
		this.name = name;
		
		this.nick = nick;
	}
	
	public String getName(){
		return name;
	}
	
	public String getNick(){
		return nick;
	}
}
