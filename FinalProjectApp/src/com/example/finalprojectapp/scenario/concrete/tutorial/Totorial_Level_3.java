package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype;

public class Totorial_Level_3 extends MazeScenarioArchetype {

	public static final String TUTORIAL_LEVEL3_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: try ALL of the movment options...\n\n" +
			"Warning: be careful not to go out of the maze borders.";

	public static final BoardTilesTypesEnum[][] TUTORIAL_LEVEL3_BOARD_TILES = new BoardTilesTypesEnum[][]
			{
		{
			BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass
		},
		{
			BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass
		},
		{
			BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass
		},
		{
			BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass,BoardTilesTypesEnum.Grass
		}
			};

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(4, 4, 1, 1, 3, 2, TUTORIAL_LEVEL3_BOARD_TILES));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());	
	}

	@Override
	public void initiateAvailableOptions() {

		addToAvailableOptions(new GoRightOption());
		addToAvailableOptions(new GoLeftOption());
		addToAvailableOptions(new GoUpOption());
		addToAvailableOptions(new GoDownOption());

		addToAvailableOptions(new JumpRightOption());
		addToAvailableOptions(new JumpLeftOption());
		addToAvailableOptions(new JumpUpOption());
		addToAvailableOptions(new JumpDownOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(TUTORIAL_LEVEL3_TEXT);
	}

}