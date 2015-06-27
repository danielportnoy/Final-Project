package com.example.finalprojectapp.scenario.concrete.maze_wing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*import com.example.finalprojectapp.codewriting.option.concrete.block.*;
import com.example.finalprojectapp.codewriting.option.concrete.statement.*;
import com.example.finalprojectapp.codewriting.option.concrete.literal.*;
import com.example.finalprojectapp.codewriting.option.concrete.operators.arithmetic.*;
import com.example.finalprojectapp.codewriting.option.concrete.operators.assignment.*;
import com.example.finalprojectapp.codewriting.option.concrete.operators.relational.*;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.*;*/
import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype;

/**
 * Instance of the Maze game scenario - Level 3.
 * @author daniel portnoy
 *
 */
public class Maze_Wing_Level_3 extends MazeScenarioArchetype {

	// hint text.
	public static final String MAZE_LEVEL3_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: try ALL of the movment options...\n\n" +
			"Warning: be careful not to go out of the maze borders.";
	
	// Specific definitions.
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL3_BOARD_TILES = new ArrayList<List<BoardTilesTypesEnum>>(){
		{
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));

		}
	};
	
	public static final int MAZE_LEVEL3_ROWS = 4;
	public static final int MAZE_LEVEL3_COLS = 4;
	public static final int MAZE_LEVEL3_HERO_X_POS = 1;
	public static final int MAZE_LEVEL3_HERO_Y_POS = 1;
	public static final int MAZE_LEVEL3_GOAL_X_POS = 3;
	public static final int MAZE_LEVEL3_GOAL_Y_POS = 2;

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(
				MAZE_LEVEL3_ROWS, MAZE_LEVEL3_COLS,
				MAZE_LEVEL3_HERO_X_POS, MAZE_LEVEL3_HERO_Y_POS,
				MAZE_LEVEL3_GOAL_X_POS, MAZE_LEVEL3_GOAL_Y_POS,
				MAZE_LEVEL3_BOARD_TILES, null));

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
		setLevelText(MAZE_LEVEL3_TEXT);
	}

}