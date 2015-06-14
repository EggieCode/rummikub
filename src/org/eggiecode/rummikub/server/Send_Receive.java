package org.eggiecode.rummikub.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.eggiecode.rummikub.models.core.Stone;
import org.eggiecode.rummikub.models.core.Table;

public class Send_Receive {
	public int receiveNum(ObjectInputStream in) {
		try {
			int Num = in.readInt(); 
			return Num;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public void sendBeginStones(ObjectOutputStream out,
			ArrayList<Stone> Beginstone) {
		try {
			out.writeObject(Beginstone);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void receivePutTable(ObjectInputStream in) {
		try {
			in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void sendPutTable(ObjectOutputStream out, Table table) {
		try {
			out.writeObject(table);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendTurn(ObjectOutputStream out, boolean a) {
		try {
			out.writeBoolean(a);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
