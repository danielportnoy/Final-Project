package com.example.finalprojectapp.coderunning.managment;

import com.example.finalprojectapp.DrawGameView;

public class CodeRunningManager {

	private CodeRunningLogicUnit logics;
	private CodeRunningGraphicUnit graphics;

	public CodeRunningManager(CodeRunningLogicUnit logics, CodeRunningGraphicUnit graphics) { 
		this.logics = logics;
		this.graphics = graphics;	
	}
	
	public void refresh(int snapshotNum, DrawGameView drawGameView){
		
		logics.updateCodeRunningLines(logics.getSnapshots().get(snapshotNum).currentNode);
		graphics.updateCodeRunningLines();
		
		graphics.updateGame(logics.getSnapshots().get(snapshotNum).gameSnapshot , drawGameView);
	}

	public CodeRunningLogicUnit getLogics() {
		return logics;
	}

	public CodeRunningGraphicUnit getGraphics() {
		return graphics;
	}
	
	
}
