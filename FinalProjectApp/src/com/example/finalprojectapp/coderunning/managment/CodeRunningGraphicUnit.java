package com.example.finalprojectapp.coderunning.managment;

import android.view.View;

import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.scenario.MySurfaceView;

public class CodeRunningGraphicUnit {

	private CodeRunningLinesAdapter codeLinesAdapter;
	private MySurfaceView gameView;

	public CodeRunningGraphicUnit(CodeRunningLinesAdapter codeLinesAdapter, MySurfaceView gameView) {
		this.codeLinesAdapter = codeLinesAdapter;
		this.gameView = gameView;
	}

	public void updateCodeRunningLines() {
		codeLinesAdapter.notifyDataSetChanged();
	}

	public CodeRunningLinesAdapter getCodeLinesAdapter() {
		return codeLinesAdapter;
	}
	
	public View getGameView() {
		return gameView;
	}

	public void updateGame(GameSnapshot gameSnapshot) {
		gameView.loadSnapshot(gameSnapshot);
	}

}
