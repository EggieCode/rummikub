package org.eggiecode.rummikub.models.networking;

import java.awt.Image;
import java.io.Serializable;
import java.net.InetAddress;

public class ServerBroadcastReply implements Serializable {
	private static final long serialVersionUID = 7921293567993513268L;
	private String name;
	private int players;
	
	private InetAddress address;
	private int port;
	
	public ServerBroadcastReply(String name, int players, int port) {
		super();
		this.name = name;
		this.players = players;
		this.port  = port;
	}

	public String getName() {
		return name;
	}


	public int getPlayers() {
		return players;
	}

	
	
	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ServerName: " + name;
	}
	
	public int  getPort() {
		return port;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this)
			return true;
		if(!(obj instanceof ServerBroadcastReply))
			return false;
		ServerBroadcastReply replay = (ServerBroadcastReply) obj;
		
		if(replay.getAddress().equals(this.getAddress()) && replay.getPort() == this.getPort())
			return true;
		return false;
	}

}
