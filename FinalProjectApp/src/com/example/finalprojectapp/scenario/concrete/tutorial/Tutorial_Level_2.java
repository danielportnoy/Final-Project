package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.codewriting.option.concrete.literal.FalseOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.TrueOption;
import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

/**
 * Instance of the Print game scenario - Level 2.
 * @author daniel portnoy
 *
 */
public class Tutorial_Level_2 extends PrintScenarioArchetype {

	// hint text.
	public static final String TUTORIAL_LEVEL2_TEXT = "";

	public static final String TUTORIAL_LEVEL2_HEADER = "Are you AMAZING ?";
	public static final String TUTORIAL_LEVEL2_SUB_HEADER= "we both know the answear =]";
	public static final String TUTORIAL_LEVEL2_ANSWEAR = true+"";

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(TUTORIAL_LEVEL2_HEADER, TUTORIAL_LEVEL2_SUB_HEADER , TUTORIAL_LEVEL2_ANSWEAR));
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