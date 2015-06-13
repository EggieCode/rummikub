package org.eggiecode.rummikub.controllers;

import java.util.ArrayList;

import org.eggiecode.rummikub.client.Client;
import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.models.game.StoneModel;
import org.eggiecode.rummikub.models.game.StoneSetModel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.AppletGameContainer.Container;
import org.newdawn.slick.SlickException;

public class GameModelController {
	private RunnikubController rummikubController;

	private ArrayList<StoneModel> stoneModels = new ArrayList();
	private ArrayList<StoneSetModel> stoneSetModels = new ArrayList();
	private ArrayList<StoneSetModel> tableStoneSetModels = new ArrayList();
	private ArrayList<StoneModel> playerStoneModels = new ArrayList<>();
	private int updateDelay = 0;
	private boolean stonesUpdated = false;

	public GameModelController(RunnikubController rummikubController) {
		this.rummikubController = rummikubController;
	}

	public ArrayList<StoneModel> getPlayerStoneModels() throws SlickException {

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
		}

		return playerStoneModels;
	}

	public ArrayList<StoneSetModel> getTableStoneSets() {
		for (StoneSet s : rummikubController.getTable().getStoneSets()) {
			StoneSetModel stoneSetModel = getStoneSetModel(s);

			if (!tableStoneSetModels.contains(stoneSetModel)) {
				tableStoneSetModels.add(stoneSetModel);
				stonesUpdated = true;
			}
		}

		return tableStoneSetModels;
	}

	private StoneModel getStoneModel(Stone s) throws SlickException {
		for (StoneModel stoneModel : stoneModels) {
			if (stoneModel.getStone() == s)
				return stoneModel;
		}

		StoneModel stoneModel = new StoneModel(s, null);
		stoneModel.init(Client.appgc, Client.game);
		stoneModels.add(stoneModel);
		return stoneModel;
	}

	private StoneSetModel getStoneSetModel(StoneSet s) {
		for (StoneSetModel stoneSetModel : stoneSetModels) {
			if (stoneSetModel.getStoneSet() == s)
				return stoneSetModel;
		}

		StoneSetModel stoneSetModel = new StoneSetModel(s);

		stoneSetModels.add(stoneSetModel);
		return stoneSetModel;

	}

	public void preUpdateState(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		if (updateDelay >= 1) {
			getTableStoneSets();
			getPlayerStoneModels();
			updateDelay = 0;
			stonesUpdated = true;
		} else {
			stonesUpdated = false;
			updateDelay += 0.2f * delta;
		}
	}

	public boolean isStonesUpdated() {
		return stonesUpdated;
	}

}
