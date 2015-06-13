package org.eggiecode.rummikub.models.core;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable{
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
