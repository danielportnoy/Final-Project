package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

public class Tutorial_Level_4 extends PrintScenarioArchetype {

	public static final String TUTORIAL_LEVEL4_TEXT = "";

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration("< header >", "< sub header >", ""));
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