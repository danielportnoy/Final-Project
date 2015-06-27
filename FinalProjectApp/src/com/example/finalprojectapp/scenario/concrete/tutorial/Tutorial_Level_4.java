package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

/**
 * Instance of the Print game scenario - Level 4.
 * @author daniel portnoy
 *
 */
public class Tutorial_Level_4 extends PrintScenarioArchetype {

	// hint text.
	public static final String TUTORIAL_LEVEL4_TEXT = "";

	public static final String TUTORIAL_LEVEL4_HEADER = "";
	public static final String TUTORIAL_LEVEL4_SUB_HEADER= "";
	public static final String TUTORIAL_LEVEL4_ANSWEAR = "";
	
	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(TUTORIAL_LEVEL4_HEADER, TUTORIAL_LEVEL4_SUB_HEADER, TUTORIAL_LEVEL4_ANSWEAR));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());
	}

	@Override
	public void initiateAvailableOptions() {
		addToAvailableOptions(new PrintOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(TUTORIAL_LEVEL4_TEXT);
	}

}