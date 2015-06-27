package com.example.finalprojectapp.scenario.concrete.maze_wing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype;

/**
 * Instance of the Maze game scenario - Level 2.
 * @author daniel portnoy
 *
 */
public class Maze_Wing_Level_2 extends MazeScenarioArchetype {

	// hint text.
	public static final String MAZE_LEVEL2_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: now you can try more of the movment options...\n\n" +
			"Warning: be careful not to go out of the maze borders.";
	
	// Specific definitions.
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL2_BOARD_TILES = new ArrayList<List<BoardTilesTypesEnum>>(){
		{
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
		}
	};

	public static final int MAZE_LEVEL2_ROWS = 2;
	public static final int MAZE_LEVEL2_COLS = 2;
	public static final int MAZE_LEVEL2_HERO_X_POS = 0;
	public static final int MAZE_LEVEL2_HERO_Y_POS = 0;
	public static final int MAZE_LEVEL2_GOAL_X_POS = 1;
	public static final int MAZE_LEVEL2_GOAL_Y_POS = 1;

	@Override
	public void initiateConfigurations() {
		
		setDefaultConfig(new MyConfiguration(
				MAZE_LEVEL2_ROWS, MAZE_LEVEL2_COLS,
				MAZE_LEVEL2_HERO_X_POS, MAZE_LEVEL2_HERO_Y_POS,
				MAZE_LEVEL2_GOAL_X_POS, MAZE_LEVEL2_GOAL_Y_POS,
				MAZE_LEVEL2_BOARD_TILES, null));
		
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