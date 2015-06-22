package com.example.finalprojectapp.scenario.concrete.maze_wing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype;

public class Maze_Wing_Level_1 extends MazeScenarioArchetype {

	// TODO
	/*public static final String TOTORIAL_LEVEL1_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: try the \"GoRight();\" option...\n\n" +
			"Warning: the displayed level is just an example of the general problem.\n"+
			"Try writing code which solves all of the cases.";*/

	public static final String MAZE_LEVEL1_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: try the \"GoRight();\" option...\n\n" +
			"Warning: be careful not to go out of the maze borders.";
	
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL1_BOARD_TILES = new ArrayList<List<BoardTilesTypesEnum>>(){
		{
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
		}
	};

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(1, 2, 0, 0, 1, 0 , MAZE_LEVEL1_BOARD_TILES, null));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());	
	}

	@Override
	public void initiateAvailableOptions() {

		addToAvailableOptions(new GoRightOption());

	}

	@Override
	public void initiateLevelText() {
		setLevelText(MAZE_LEVEL1_TEXT);
	}

}