package com.example.finalprojectapp.coderunning.managment;

import com.example.finalprojectapp.DrawGameView;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;

public class CodeRunningGraphicUnit {

	private CodeRunningLinesAdapter codeLinesAdapter;
	private LevelManager levelManager = LevelManager.getInstance();

	public CodeRunningGraphicUnit(CodeRunningLinesAdapter codeLinesAdapter) {
		this.codeLinesAdapter = codeLinesAdapter;
	}

	public void updateCodeRunningLines() {
		codeLinesAdapter.notifyDataSetChanged();
	}

	public CodeRunningLinesAdapter getCodeLinesAdapter() {
		return codeLinesAdapter;
	}

	public void updateGame(GameSnapshot gameSnapshot, DrawGameView drawGameView) {
		levelManager.getScenario().drawSnapshot(gameSnapshot , drawGameView);
	}

}
