package com.example.finalprojectapp.coderunning;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.node.Node;

public class CodeRunningManager {

	private VarValues values;
	private List<Snapshot> snapshots;
	
	public CodeRunningManager() {
		values = new VarValues();
		snapshots = new ArrayList<Snapshot>();
	}
	
	public void takeSnapshot(Node n){
		snapshots.add(new Snapshot(n,values, LevelManager.getInstance().getScenario().takeSnapshot())); // TODO
	}

	public void putInt(String id, int value){
		values.intValues.put(id, value);
	}
	public void putBool(String id, boolean value){
		values.boolValues.put(id, value);
	}
	
	public int getInt(String id){
		return values.intValues.get(id);
	}
	public boolean getBool(String id){
		return values.boolValues.get(id);
	}
	
	public List<Snapshot> getSnapshots() {
		return snapshots;
	}

}
