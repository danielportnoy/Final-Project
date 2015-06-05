package com.example.finalprojectapp.coderunning.managment;

import android.view.View;

import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.adapter.VarValuesAdapter;
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;

public class CodeRunningGraphicUnit {

	private CodeRunningLinesAdapter codeLinesAdapter;
	private VarValuesAdapter varValuesLinesAdapter;

	private MySurfaceView gameView;

	public CodeRunningGraphicUnit(CodeRunningLinesAdapter codeLinesAdapter, VarValuesAdapter varValuesLinesAdapter, MySurfaceView gameView) {
		this.codeLinesAdapter = codeLinesAdapter;
		this.varValuesLinesAdapter = varValuesLinesAdapter;
		this.gameView = gameView;
	}

	public void updateCodeRunningLines() {
		codeLinesAdapter.notifyDataSetChanged();
	}

	public CodeRunningLinesAdapter getCodeLinesAdapter() {
		return codeLinesAdapter;
	}

	public void updateVarValues() {
		varValuesLinesAdapter.notifyDataSetChanged();
	}

	public VarValuesAdapter getVarValuesAdapter() {
		return varValuesLinesAdapter;
	}

	public View getGameView() {
		return gameView;
	}

	public void setGameView(MySurfaceView gameView) {
		this.gameView = gameView;
	}

	public void updateGame(GameSnapshot gameSnapshot) {
		gameView.loadSnapshot(gameSnapshot);
	}

}
