package org.eggiecode.rummikub.controllers;

import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;

import org.eggiecode.rummikub.models.core.Player;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.server.objects.Command;

public class RunnikubController {

	private ArrayList<Stone> stones = new ArrayList();
	private ArrayList<StoneSet> stoneSets = new ArrayList<>();
	private Table table;
	private Player player;

	private boolean isConnected = false;

	private ClientController clientController;
	
	public RunnikubController() {
		clientController = new ClientController(this);
	}

	public void givePlayerNewStone() {
		
	}

	public Table getTable() {
		return table;
	}

	public Player getPlayer() {
		return player;
	}


	public boolean addToTable(StoneSet set) {
		return false;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public void connect(InetAddress address) {
		clientController.connect(address);
	}
	
	public void searchServer() {
		clientController.findServers();
	}
}
