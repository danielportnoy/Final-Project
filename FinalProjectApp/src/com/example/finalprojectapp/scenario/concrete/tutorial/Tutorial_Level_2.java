package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.codewriting.option.concrete.literal.FalseOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.TrueOption;
import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

public class Tutorial_Level_2 extends PrintScenarioArchetype {

	public static final String TUTORIAL_LEVEL2_TEXT = "";

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration("Are you AMAZING ?", "we both know the answear =]", true+""));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());
	}

	@Override
	public void initiateAvailableOptions() {
		addToAvailableOptions(new PrintOption());
		
		addToAvailableOptions(new TrueOption());
		addToAvailableOptions(new FalseOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(TUTORIAL_LEVEL2_TEXT);
	}

}