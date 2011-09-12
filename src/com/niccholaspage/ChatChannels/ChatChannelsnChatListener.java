package com.niccholaspage.ChatChannels;

import com.niccholaspage.ChatChannels.user.User;
import com.niccholaspage.nChat.api.ChatFormatEvent;
import com.niccholaspage.nChat.api.Node;
import com.niccholaspage.nChat.api.nChatEventListener;

public class ChatChannelsnChatListener extends nChatEventListener {
	private final ChatChannels plugin;
	
	public ChatChannelsnChatListener(ChatChannels plugin){
		this.plugin = plugin;
	}
	
	public void onChatFormat(ChatFormatEvent event){
		User user = plugin.getUserManager().getUser(event.getPlayer());
		
		Node node = event.getNode("channel");
		
		node.setValue(user.getChannel().getName());
	}
}
