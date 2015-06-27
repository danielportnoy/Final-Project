package com.example.finalprojectapp.scenario.concrete;

import android.content.Context;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.scenario.configuration.Configuration;

public class emptyScenario extends Scenario {

	@Override
	public void initiateConfigurations() {
	}

	@Override
	public void initiateAvailableOptions() {
	}

	@Override
	public void initiateLevelText() {
	}

	@Override
	public void reset() {
	}

	@Override
	public GameSnapshot takeSnapshot() {
		return null;
	}

	@Override
	public MySurfaceView generateGameView(Context context, int fps,boolean isAnimation) {
		return null;
	}

	@Override
	public boolean checkWin(Configuration config, GameSnapshot gameSnapshot) {
		return false;
	}

}
