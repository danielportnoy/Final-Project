package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype;

public class Totorial_Level_2 extends MazeScenarioArchetype {

	public static final String TUTORIAL_LEVEL2_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: now you can try more of the movment options...\n\n" +
			"Warning: be careful not to go out of the maze borders.";
	
	public static final BoardTilesTypesEnum[][] TUTORIAL_LEVEL2_BOARD_TILES = new BoardTilesTypesEnum[][]
			{
		{
			BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass
		},
		{
			BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass
		}
			};

	@Override
	public void initiateConfigurations() {
		
		setDefaultConfig(new MyConfiguration(2, 2, 0, 0, 1, 1, TUTORIAL_LEVEL2_BOARD_TILES));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());	
	}

	@Override
	public void initiateAvailableOptions() {
		
		addToAvailableOptions(new GoRightOption());
		addToAvailableOptions(new GoLeftOption());
		addToAvailableOptions(new GoUpOption());
		addToAvailableOptions(new GoDownOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(TUTORIAL_LEVEL2_TEXT);
	}

}