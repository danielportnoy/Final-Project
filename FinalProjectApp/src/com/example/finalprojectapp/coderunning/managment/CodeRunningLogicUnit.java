package com.example.finalprojectapp.coderunning.managment;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningLine;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.snapshot.Snapshot;
import com.example.finalprojectapp.coderunning.varvalues.VarValues;
import com.example.finalprojectapp.node.Node;

public class CodeRunningLogicUnit {

	private VarValues values;
	private List<Snapshot> snapshots;

	private List<CodeRunningLine> codeRunningLines;

	public CodeRunningLogicUnit() {
		values = new VarValues();
		snapshots = new ArrayList<Snapshot>();

		codeRunningLines = new ArrayList<CodeRunningLine>();
	}

	public void takeSnapshot(Node n){
		snapshots.add(new Snapshot(n,values, LevelManager.getInstance().getScenario().takeSnapshot()));
	}

	public void putInt(String id, int value){
		values.getIntValues().put(id, value);
	}
	public void putBool(String id, boolean value){
		values.getBoolValues().put(id, value);
	}

	public int getInt(String id){
		return values.getIntValues().get(id);
	}
	public boolean getBool(String id){
		return values.getBoolValues().get(id);
	}

	public List<Snapshot> getSnapshots() {
		return snapshots;
	}

	public List<CodeRunningLine> getRunningCodeLines() {
		return codeRunningLines;
	}

	public void updateCodeRunningLines(Node target) {

		codeRunningLines.clear();

		CodeRunningLine temp = new CodeRunningLine();

		for (CodeRunningPart codePart : LevelManager.getInstance().getRootNode().getCodeRunningParts(target,false)) {
			if(!codePart.isNewline())
				temp.getCodeRunningParts().add(codePart);
			else{
				codeRunningLines.add(temp);
				temp = new CodeRunningLine();
			}
		}	

		codeRunningLines.add(temp);
	}
	
	public boolean checkWin(int gameSnapShotNum){
		return snapshots.get(gameSnapShotNum).gameSnapshot.checkWin();
	}
	
	public boolean checkLoss(int gameSnapShotNum){
		return snapshots.get(gameSnapShotNum).gameSnapshot.checkLoss();
	}
}
