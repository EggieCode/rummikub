package org.eggiecode.rummikub.models.core;

import java.util.ArrayList;

public class Table {
	private ArrayList<StoneSet> stoneSets = new ArrayList();

	
	public Table() {
		
	}


	public void add(StoneSet set) {
		// TODO Auto-generated method stub
		stoneSets.add(set);
	}
	
	public StoneSet[] getStoneSets() {
		return stoneSets.toArray(new StoneSet[0]);
	}
}
