package org.eggiecode.rummikub.controllers;

import java.util.ArrayList;

import org.eggiecode.rummikub.models.core.EndGame;
import org.eggiecode.rummikub.models.core.Player;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.models.networking.ServerBroadcastReply;
import org.eggiecode.rummikub.server.objects.Command;

public class RunnikubController {

	private ArrayList<Stone> stones = new ArrayList();
	private ArrayList<StoneSet> stoneSets = new ArrayList<>();
	private Table table;
	private Player player;
	private ClientController clientController;

	private boolean gameReady = false;
	private boolean playerTurn = false;
	private boolean playerSendedStones = false;
	private EndGame endGame;
	public RunnikubController() {
		clientController = new ClientController(this);
	}

	public void givePlayerNewStone() {
		
		clientController.sendCommand(Command.NEW_STONE);
		clientController.sendCommand(Command.END_TURN);
		playerTurn = false;
		playerSendedStones = false;
	}
	
	public boolean endTurn() {
		if(playerSendedStones != true)
			return false;
		clientController.sendCommand(Command.END_TURN);
		playerTurn = false;
		playerSendedStones = false;
		return true;
	}

	public Table getTable() {
		return table;
	}

	public Player getPlayer() {
		return player;
	}

	void setTable(Table table) {
		for(StoneSet set : table.getStoneSets()) {
			System.out.println("Table StoneSet: ");
			for(Stone s : set.getStones()) {
				System.out.println(s.getNumber() + " - " + s.getColor() + ", ");
			}
		}
		
		this.table = table;
	}

	void setPlayer(Player player) {
		this.player = player;
	}

	public boolean addToTable(StoneSet set) {
		
		if(!set.isValid())
			return false;
		clientController.sendStoneSet(set);
		playerSendedStones = true;
		return true;
	}

	public boolean isConnected() {
		return clientController.isConnected();
	}

	public boolean isConnecting() {
		return clientController.isConnecting();
	}

	public void searchServer() {
		clientController.findServers();
	}

	public ArrayList<ServerBroadcastReply> getServerFound() {
		return clientController.getServers();
	}

	public void connect(ServerBroadcastReply serverReply) {
		// TODO Auto-generated method stub
		clientController.connect(serverReply.getAddress(),
				serverReply.getPort());

	}

	public boolean isGameReady() {
		return gameReady;
	}

	public void processCommand(Command command) {
		// TODO Auto-generated method stub
		switch (command) {
		case GAME_START:
			gameReady = true;
			break;
		case PLAYER_TURN:
			playerTurn = true;
			break;
		default:
			break;
		}
	}

	public boolean isPlayerTurn() {
		// TODO Auto-generated method stub
		return playerTurn;
	}

	public boolean isPlayerSendedStones() {
		return playerSendedStones;
	}

	public void setEndGame(EndGame o) {
		// TODO Auto-generated method stub
		this.endGame = o;
	}

	public EndGame getEndGame() {
		// TODO Auto-generated method stub
		return endGame;
	}
	
}
