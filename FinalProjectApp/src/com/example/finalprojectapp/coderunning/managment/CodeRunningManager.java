package com.example.finalprojectapp.coderunning.managment;

import com.example.finalprojectapp.coderunning.snapshot.Snapshot;

/**
 * Manages the code running logics and graphics.
 * @author daniel portnoy
 *
 */
public class CodeRunningManager {

	private CodeRunningLogicUnit logics;
	private CodeRunningGraphicUnit graphics;

	/**
	 * @param logics - CodeRunningLogicUnit object.
	 * @param graphics - CodeRunningGraphicUnit object.
	 */
	public CodeRunningManager(CodeRunningLogicUnit logics, CodeRunningGraphicUnit graphics) { 
		this.logics = logics;
		this.graphics = graphics;	
	}
	
	/**
	 * Displays a Snapshot on the screen.  
	 * @param snapshot
	 */
	public void refresh(Snapshot snapshot){
		
		// update current data by the snapshot.
		logics.updateCodeRunningLines(snapshot.currentNode);
		logics.updateVarValues(snapshot.values.toList());
		
		// update screen representation.
		graphics.updateCodeRunningLines();
		graphics.updateVarValues();
		
		// update the game.
		graphics.updateGame(snapshot.gameSnapshot);
	}

	public CodeRunningLogicUnit getLogics() {
		return logics;
	}

	public CodeRunningGraphicUnit getGraphics() {
		return graphics;
	}
}
