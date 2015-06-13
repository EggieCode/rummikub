package org.eggiecode.rummikub.controllers;

import java.util.ArrayList;

import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.StoneSet;
import org.eggiecode.rummikub.models.core.Table;
import org.eggiecode.rummikub.models.game.StoneModel;
import org.eggiecode.rummikub.models.game.StoneSetModel;

public class GameModelController {
	private RunnikubController rummikubController;

	private ArrayList<StoneModel> stoneModels = new ArrayList();
	private ArrayList<StoneSetModel> stoneSetModels = new ArrayList();
	private ArrayList<StoneSetModel> tableStoneSetModels = new ArrayList();
	private ArrayList<StoneModel> playerStoneModels = new ArrayList<>();

	public GameModelController(RunnikubController rummikubController) {
		this.rummikubController = rummikubController;
	}

	public ArrayList<StoneModel> getPlayerStoneModels() {
		
		for(Stone s: rummikubController.getPlayer().getStones()) {
			StoneModel stoneModel = getStoneModel(s);
			if(s.isOnPlayerBoard() ) {
				if(!playerStoneModels.contains(stoneModel)) {
					playerStoneModels.add(stoneModel);
				}
			} else {
				if(playerStoneModels.contains(stoneModel)) {
					playerStoneModels.remove(stoneModel);
					
				}
			}
		}
		
		return playerStoneModels;
	}

	public ArrayList<StoneSetModel> getTableStoneSets() {
		for(StoneSet s: rummikubController.getTable().getStoneSets()) {
			StoneSetModel stoneSetModel = getStoneSetModel(s);
			
			if(!tableStoneSetModels.contains(stoneSetModel)) {
				tableStoneSetModels.add(stoneSetModel);
			}
		}
		
		return tableStoneSetModels;
	}


	private StoneModel getStoneModel(Stone s) {
		for (StoneModel stoneModel : stoneModels) {
			if (stoneModel.getStone() == s)
				return stoneModel;
		}

		StoneModel stoneModel = new StoneModel(s, null);
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
}
