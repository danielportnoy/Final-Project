package com.example.finalprojectapp.scenario.concrete.maze_wing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.finalprojectapp.codewriting.option.concrete.block.BlockOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.AdditionOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.DecrementOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.IncrementOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.SubtractionOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.GreaterThanOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.LessThanOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.ForOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.WhileOption;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.IntVarDecOption;
/*import com.example.finalprojectapp.codewriting.option.concrete.block.*;
import com.example.finalprojectapp.codewriting.option.concrete.statement.*;
import com.example.finalprojectapp.codewriting.option.concrete.literal.*;
import com.example.finalprojectapp.codewriting.option.concrete.operators.arithmetic.*;
import com.example.finalprojectapp.codewriting.option.concrete.operators.assignment.*;
import com.example.finalprojectapp.codewriting.option.concrete.operators.relational.*;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.*;*/
import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype;

/**
 * Instance of the Maze game scenario - Level 4.
 * @author daniel portnoy
 *
 */
public class Maze_Wing_Level_4 extends MazeScenarioArchetype {

	// hint text.
	public static final String MAZE_LEVEL4_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"this time write a GENERAL solution (one that solves all cases)..\n\n"+
			"Hint: try ALL of the movment options...\n\n" +
			"Warning: be careful not to go out of the maze borders.\n\n"+
			"Warning: be careful not to step on the fire.";

	// Specific definitions.
	
	/***** Configuration 1 *****/
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL4_BOARD_TILES_2 = new ArrayList<List<BoardTilesTypesEnum>>(){
		{	
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
		}
	};

	@SuppressWarnings("serial")
	public static final List<List<Boolean>> MAZE_LEVEL4_FIRE_TILES_2 = new ArrayList<List<Boolean>>(){
		{
			add(Arrays.asList(new Boolean[]{false, true, true, false}));
			add(Arrays.asList(new Boolean[]{false, true, true, false}));
			add(Arrays.asList(new Boolean[]{false, false, false, false}));	
		}
	};

	public static final int MAZE_LEVEL4_ROWS_1 = 2;
	public static final int MAZE_LEVEL4_COLS_1 = 3;
	public static final int MAZE_LEVEL4_HERO_X_POS_1 = 0;
	public static final int MAZE_LEVEL4_HERO_Y_POS_1 = 0;
	public static final int MAZE_LEVEL4_GOAL_X_POS_1 = 2;
	public static final int MAZE_LEVEL4_GOAL_Y_POS_1 = 0;
	/***** Configuration 1 *****/

	/***** Configuration 2 *****/
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL4_BOARD_TILES_1 = new ArrayList<List<BoardTilesTypesEnum>>(){
		{	
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
		}
	};

	@SuppressWarnings("serial")
	public static final List<List<Boolean>> MAZE_LEVEL4_FIRE_TILES_1 = new ArrayList<List<Boolean>>(){
		{
			add(Arrays.asList(new Boolean[]{false, true, false}));
			add(Arrays.asList(new Boolean[]{false, false, false}));	
		}
	};

	public static final int MAZE_LEVEL4_ROWS_2 = 3;
	public static final int MAZE_LEVEL4_COLS_2 = 4;
	public static final int MAZE_LEVEL4_HERO_X_POS_2 = 0;
	public static final int MAZE_LEVEL4_HERO_Y_POS_2 = 0;
	public static final int MAZE_LEVEL4_GOAL_X_POS_2 = 3;
	public static final int MAZE_LEVEL4_GOAL_Y_POS_2 = 0;
	/***** Configuration 2 *****/

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(
				MAZE_LEVEL4_ROWS_1, MAZE_LEVEL4_COLS_1,
				MAZE_LEVEL4_HERO_X_POS_1, MAZE_LEVEL4_HERO_Y_POS_1,
				MAZE_LEVEL4_GOAL_X_POS_1, MAZE_LEVEL4_GOAL_Y_POS_1,
				MAZE_LEVEL4_BOARD_TILES_1, MAZE_LEVEL4_FIRE_TILES_1));

		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());

		addToConfigs(new MyConfiguration(
				MAZE_LEVEL4_ROWS_2, MAZE_LEVEL4_COLS_2,
				MAZE_LEVEL4_HERO_X_POS_2, MAZE_LEVEL4_HERO_Y_POS_2,
				MAZE_LEVEL4_GOAL_X_POS_2, MAZE_LEVEL4_GOAL_Y_POS_2,
				MAZE_LEVEL4_BOARD_TILES_2, MAZE_LEVEL4_FIRE_TILES_2));

	}

	@Override
	public void initiateAvailableOptions() {

		addToAvailableOptions(new GoRightOption());
		addToAvailableOptions(new GoLeftOption());
		addToAvailableOptions(new GoUpOption());
		addToAvailableOptions(new GoDownOption());

		addToAvailableOptions(new NumberOfRowsOption());
		addToAvailableOptions(new NumberOfColumnsOption());

		addToAvailableOptions(new ForOption());
		addToAvailableOptions(new WhileOption());

		addToAvailableOptions(new IntVarDecOption());
		addToAvailableOptions(new IncrementOption());
		addToAvailableOptions(new DecrementOption());

		addToAvailableOptions(new GreaterThanOption());
		addToAvailableOptions(new LessThanOption());

		addToAvailableOptions(new NumberOption());
		addToAvailableOptions(new BlockOption());

		addToAvailableOptions(new AdditionOption());
		addToAvailableOptions(new SubtractionOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(MAZE_LEVEL4_TEXT);
	}

}