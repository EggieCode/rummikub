package org.eggiecode.rummikub.controllers;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Client;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.models.game.StoneModel;
import org.eggiecode.rummikub.models.game.StoneSetModel;
import org.eggiecode.rummikub.models.networking.ServerBroadcastReply;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.SlickException;

public class GameModelController {
	private RunnikubController rummikubController;

	private ArrayList<StoneModel> stoneModels = new ArrayList<>();
	private ArrayList<StoneSetModel> stoneSetModels = new ArrayList<>();
	private ArrayList<StoneSetModel> tableStoneSetModels = new ArrayList<>();
	private ArrayList<StoneModel> playerStoneModels = new ArrayList<>();

	private int updateDelay = 0;
	private boolean stonesUpdated = false;

	public GameModelController(RunnikubController rummikubController) {
		this.rummikubController = rummikubController;
	}

	public ArrayList<StoneModel> getPlayerStoneModels() throws SlickException {
		if (rummikubController.getPlayer() == null)
			return playerStoneModels;
		ArrayList<StoneModel> tmpList = new ArrayList<>();

		for (Stone s : rummikubController.getPlayer().getStones()) {
			StoneModel stoneModel = getStoneModel(s);
			if (s.isOnPlayerBoard()) {
				if (!playerStoneModels.contains(stoneModel)) {
					playerStoneModels.add(stoneModel);
					stonesUpdated = true;
				}
			} else {
				if (playerStoneModels.contains(stoneModel)) {
					playerStoneModels.remove(stoneModel);
					stonesUpdated = true;

				}
			}
			for (StoneModel model : playerStoneModels) {
				if (model.getStone() == s)
					tmpList.add(model);
			}
		}
		playerStoneModels.clear();
		playerStoneModels.addAll(tmpList);

		return playerStoneModels;
	}

	public ArrayList<StoneSetModel> getTableStoneSets() throws SlickException {
		if (rummikubController.getTable() == null)
			return tableStoneSetModels;
		ArrayList<StoneSetModel> tmpList = new ArrayList<>();
		for (StoneSet s : rummikubController.getTable().getStoneSets()) {
			StoneSetModel stoneSetModel = getStoneSetModel(s);

			if (!tableStoneSetModels.contains(stoneSetModel)) {
				tableStoneSetModels.add(stoneSetModel);
				stonesUpdated = true;
			}

			for (StoneSetModel model : tableStoneSetModels) {
				if (model.getStoneSet() == s)
					tmpList.add(model);
			}

		}

		tableStoneSetModels.clear();
		tableStoneSetModels.addAll(tmpList);

		return tableStoneSetModels;
	}

	public StoneModel getStoneModel(Stone s) throws SlickException {
		for (StoneModel stoneModel : stoneModels) {
			if (stoneModel.getStone() == s)
				return stoneModel;
		}

		StoneModel stoneModel = new StoneModel(s, null);
		stoneModel.init(Client.appgc, Client.game);
		stoneModels.add(stoneModel);
		return stoneModel;
	}

	private StoneSetModel getStoneSetModel(StoneSet s) throws SlickException {
		for (StoneSetModel stoneSetModel : stoneSetModels) {
			if (stoneSetModel.getStoneSet() == s)
				return stoneSetModel;
		}

		StoneSetModel stoneSetModel = new StoneSetModel(s);

		stoneSetModels.add(stoneSetModel);
		for (Stone stone : s.getStones()) {
			stoneSetModel.add(this.getStoneModel(stone));
		}
		stoneSetModel.init(Client.appgc, Client.game);
		return stoneSetModel;

	}

	public void preUpdateState(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		getTableStoneSets();
		getPlayerStoneModels();
		updateDelay = 0;
		stonesUpdated = true;
	}

	public boolean isStonesUpdated() {
		return stonesUpdated;
	}

}
