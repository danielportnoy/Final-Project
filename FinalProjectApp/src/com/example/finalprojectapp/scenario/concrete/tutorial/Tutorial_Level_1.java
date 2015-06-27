package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

/**
 * Instance of the Print game scenario - Level 1.
 * @author daniel portnoy
 *
 */
public class Tutorial_Level_1 extends PrintScenarioArchetype {

	// hint text.
	public static final String TUTORIAL_LEVEL1_TEXT = "";
	
	public static final String TUTORIAL_LEVEL1_HEADER = "3 + 4 = ?";
	public static final String TUTORIAL_LEVEL1_SUB_HEADER= "lets see if you are smart as they say...";
	public static final String TUTORIAL_LEVEL1_ANSWEAR = 7+"";

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(TUTORIAL_LEVEL1_HEADER, TUTORIAL_LEVEL1_SUB_HEADER , TUTORIAL_LEVEL1_ANSWEAR));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());
	}

	@Override
	public void initiateAvailableOptions() {
		addToAvailableOptions(new PrintOption());
		
		addToAvailableOptions(new NumberOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(TUTORIAL_LEVEL1_TEXT);
	}

}