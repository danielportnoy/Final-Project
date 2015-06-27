package com.example.finalprojectapp.scenario.concrete.maze_wing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.finalprojectapp.codewriting.option.concrete.block.BlockOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.AdditionOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.IncrementOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.SubtractionOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.GreaterThanOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.LessThanOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.ForOption;
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
 * Instance of the Maze game scenario - Level 5.
 * @author daniel portnoy
 *
 */
public class Maze_Wing_Level_5 extends MazeScenarioArchetype {

	// hint text.
	public static final String MAZE_LEVEL5_TEXT = "";

	// Specific definitions.
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL5_BOARD_TILES = new ArrayList<List<BoardTilesTypesEnum>>(){
		{	
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
		}
	};

	@SuppressWarnings("serial")
	public static final List<List<Boolean>> MAZE_LEVEL5_FIRE_TILES = new ArrayList<List<Boolean>>(){
		{
			add(Arrays.asList(new Boolean[]{false, false, true, true}));
			add(Arrays.asList(new Boolean[]{true, false, false, true}));
			add(Arrays.asList(new Boolean[]{true, true, false, false}));
			add(Arrays.asList(new Boolean[]{true, true, true, false}));
		}
	};

	public static final int MAZE_LEVEL5_ROWS = 4;
	public static final int MAZE_LEVEL5_COLS = 4;
	public static final int MAZE_LEVEL5_HERO_X_POS = 0;
	public static final int MAZE_LEVEL5_HERO_Y_POS = 0;
	public static final int MAZE_LEVEL5_GOAL_X_POS = 3;
	public static final int MAZE_LEVEL5_GOAL_Y_POS = 3;

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(
				MAZE_LEVEL5_ROWS, MAZE_LEVEL5_COLS,
				MAZE_LEVEL5_HERO_X_POS, MAZE_LEVEL5_HERO_Y_POS,
				MAZE_LEVEL5_GOAL_X_POS, MAZE_LEVEL5_GOAL_Y_POS,
				MAZE_LEVEL5_BOARD_TILES, MAZE_LEVEL5_FIRE_TILES));

		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());	
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
		addToAvailableOptions(new IntVarDecOption());
		addToAvailableOptions(new IncrementOption());

		addToAvailableOptions(new GreaterThanOption());
		addToAvailableOptions(new LessThanOption());

		addToAvailableOptions(new NumberOption());
		addToAvailableOptions(new BlockOption());

		addToAvailableOptions(new AdditionOption());
		addToAvailableOptions(new SubtractionOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(MAZE_LEVEL5_TEXT);
	}

}