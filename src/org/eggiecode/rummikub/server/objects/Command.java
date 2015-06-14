package org.eggiecode.rummikub.server.objects;

import java.io.Serializable;

public class Command implements Serializable{
	private String command;

	public Command(String string) {
		// TODO Auto-generated constructor stub
		this.command = string;
	}

	public String getCommand() {
		// TODO Auto-generated method stub
		return command;
	}
	
	
}
