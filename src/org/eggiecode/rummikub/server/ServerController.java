package org.eggiecode.rummikub.server;

import java.util.ArrayList;

import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.server.objects.ClientPlayer;

public class ServerController {
	private ArrayList<Stone> stones = new ArrayList();
	private ArrayList<ClientPlayer> clients = new ArrayList();
	private Table table;
	public ServerController() {
		
	}
}
