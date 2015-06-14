package org.eggiecode.rummikub.models.networking;

import java.io.Serializable;

public class ClientBroadcastReply implements Serializable {

	private static final long serialVersionUID = -3139712780307064971L;

	private String name;
	
	public ClientBroadcastReply(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public String getName() {
		return name;
	}

@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cleint name: " + name;
	}	
	
}
