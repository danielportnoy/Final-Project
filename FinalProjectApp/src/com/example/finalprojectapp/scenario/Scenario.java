package com.example.finalprojectapp.scenario;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;

public abstract class Scenario {

	private List<Option> availableOptions = new ArrayList<Option>();
	private String levelText;

	public Scenario() {
		initiateAvailableOptions();
		initiateLevelText();
	}

	public abstract void initiateAvailableOptions();
	public abstract void initiateLevelText();

	public void addToAvailableOptions(Option op){
		availableOptions.add(op);
	}

	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}

	public String getLevelText() {
		return levelText;
	}

	public List<Option> getAvailableOptions() {
		return availableOptions;
	}

	public abstract void reset();

	public abstract GameSnapshot takeSnapshot();

	public abstract MySurfaceView generateGameView(Context context, int fps, boolean isAnimation);

}
