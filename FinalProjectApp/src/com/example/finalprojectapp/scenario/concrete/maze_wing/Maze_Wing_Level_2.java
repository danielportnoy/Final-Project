package com.example.finalprojectapp.scenario.concrete.maze_wing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype;

public class Maze_Wing_Level_2 extends MazeScenarioArchetype {

	public static final String MAZE_LEVEL2_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: now you can try more of the movment options...\n\n" +
			"Warning: be careful not to go out of the maze borders.";
	
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL2_BOARD_TILES = new ArrayList<List<BoardTilesTypesEnum>>(){
		{
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
		}
	};


	@Override
	public void initiateConfigurations() {
		
		setDefaultConfig(new MyConfiguration(2, 2, 0, 0, 1, 1, MAZE_LEVEL2_BOARD_TILES, null));
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
		setLevelText(MAZE_LEVEL2_TEXT);
	}

}