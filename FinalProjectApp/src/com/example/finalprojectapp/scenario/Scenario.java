package com.example.finalprojectapp.scenario;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;

public abstract class Scenario {

	private List<Configuration> configs = new ArrayList<Configuration>();
	private Configuration currentConfig;

	private List<Option> availableOptions = new ArrayList<Option>();

	private String levelText;

	public Scenario() {
		initiateTests();
		initiateAvailableOptions();
		initiateLevelText();
	}

	public abstract void initiateTests();
	public abstract void initiateAvailableOptions();
	public abstract void initiateLevelText();

	public List<Option> getAvailableOptions() {
		return availableOptions;
	}

	public void addToAvailableOptions(Option op){
		availableOptions.add(op);
	}

	public String getLevelText() {
		return levelText;
	}

	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}

	public List<Configuration> getConfigs() {
		return configs;
	}

	public void addToConfigs(Configuration cf){
		configs.add(cf);
	}

	public Configuration getCurrentConfig() {
		return currentConfig;
	}

	public void setCurrentConfig(Configuration currentConfig) {
		this.currentConfig = currentConfig;
	}

	public abstract void reset();

	public abstract GameSnapshot takeSnapshot();

	public abstract MySurfaceView generateGameView(Context context, int fps, boolean isAnimation);

	public abstract boolean checkWin(Configuration config, GameSnapshot gameSnapshot);

}
