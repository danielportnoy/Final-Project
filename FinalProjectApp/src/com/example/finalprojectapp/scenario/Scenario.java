package com.example.finalprojectapp.scenario;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.scenario.configuration.Configuration;

/**
 * Logical data and functionality of the game scenario.
 * Graphical functionality and screen visualization.
 * @author daniel portnoy
 *
 */
public abstract class Scenario {

	private List<Configuration> configs = new ArrayList<Configuration>();
	private Configuration currentConfig;
	private Configuration defaultConfig;

	private List<Option> availableOptions = new ArrayList<Option>();

	private String levelText;

	public Scenario() {
		initiateConfigurations();
		initiateAvailableOptions();
		initiateLevelText();
	}

	/**
	 * Initiates the scenario Configurations.
	 */
	public abstract void initiateConfigurations();
	
	/**
	 * Initiates the scenario Options.
	 */
	public abstract void initiateAvailableOptions();
	
	/**
	 * Initiates the scenario Hint text.
	 */
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
	
	public Configuration getDefaultConfig() {
		return defaultConfig;
	}
	
	public void setDefaultConfig(Configuration defaultConfig) {
		this.defaultConfig = defaultConfig;
	}

	/**
	 * Reset the scenario to its default values.
	 */
	public abstract void reset();

	/**
	 * Captures the game system, including the regular Snapshots.
	 * @return GameSnapshot object.
	 */
	public abstract GameSnapshot takeSnapshot();

	/**
	 * Generates a view that will hold the game, animation and refreshing.
	 * @param context - Application context.
	 * @param fps - Frame rate per second.
	 * @param isAnimation - flag that tells if the game is animating.
	 * @return A surfaceView for the game to show.
	 */
	public abstract MySurfaceView generateGameView(Context context, int fps, boolean isAnimation);

	/**
	 * Checks if the game was won with the current Configuration.
	 * @param config - Configuration of the current scenario.
	 * @param gameSnapshot - GameSnapshot object.
	 * @return boolean value.
	 */
	public abstract boolean checkWin(Configuration config, GameSnapshot gameSnapshot);

}
