package com.example.finalprojectapp.scenario.concrete;

import android.content.Context;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.scenario.configuration.Configuration;

public class emptyScenario extends Scenario {

	@Override
	public void initiateConfigurations() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initiateAvailableOptions() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void initiateLevelText() {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
	}

	@Override
	public GameSnapshot takeSnapshot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MySurfaceView generateGameView(Context context, int fps,boolean isAnimation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkWin(Configuration config, GameSnapshot gameSnapshot) {
		// TODO Auto-generated method stub
		return false;
	}

}
