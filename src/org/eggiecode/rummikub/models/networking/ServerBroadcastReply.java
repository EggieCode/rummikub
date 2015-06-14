package org.eggiecode.rummikub.models.networking;

import java.io.Serializable;
import java.net.InetAddress;

public class ServerBroadcastReply implements Serializable {
	private static final long serialVersionUID = 7921293567993513268L;
	private String name;
	private int players;

	public ServerBroadcastReply(String name, int players) {
		super();
		this.name = name;
		this.players = players;
	}

	public String getName() {
		return name;
	}


	public int getPlayers() {
		return players;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ServerName: " + name;
	}
}
