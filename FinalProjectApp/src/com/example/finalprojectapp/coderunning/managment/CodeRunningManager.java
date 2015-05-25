package com.example.finalprojectapp.coderunning.managment;

import com.example.finalprojectapp.coderunning.snapshot.Snapshot;

public class CodeRunningManager {

	private CodeRunningLogicUnit logics;
	private CodeRunningGraphicUnit graphics;

	public CodeRunningManager(CodeRunningLogicUnit logics, CodeRunningGraphicUnit graphics) { 
		this.logics = logics;
		this.graphics = graphics;	
	}
	
	public void refresh(Snapshot snapshot){
		
		logics.updateCodeRunningLines(snapshot.currentNode);
		graphics.updateCodeRunningLines();
		
		graphics.updateGame(snapshot.gameSnapshot);
	}

	public CodeRunningLogicUnit getLogics() {
		return logics;
	}

	public CodeRunningGraphicUnit getGraphics() {
		return graphics;
	}
}
