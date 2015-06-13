package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

public class Tutorial_Level_1 extends PrintScenarioArchetype {

	public static final String TUTORIAL_LEVEL1_TEXT = "";

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration("3 + 4 = ?", 7+""));
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