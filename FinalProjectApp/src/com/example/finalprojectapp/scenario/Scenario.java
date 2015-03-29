package com.example.finalprojectapp.scenario;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.option.Option;

public abstract class Scenario {

	protected List<Option> availableOptions;

	public Scenario() {
		availableOptions = new ArrayList<Option>();
		initiateAvailableOptions();
	}

	public abstract void initiateAvailableOptions();

	public List<Option> getAvailableOptions() {
		return availableOptions;
	}
	
	public abstract void reset();
	
	public abstract GameSnapshot takeSnapshot();
	
	public abstract MySurfaceView generateGameView(Context context);

}
