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

public class Maze_Wing_Level_4 extends MazeScenarioArchetype {

	public static final String MAZE_LEVEL4_TEXT = "";

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


	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(2, 3, 0, 0, 2, 0, MAZE_LEVEL4_BOARD_TILES_1, MAZE_LEVEL4_FIRE_TILES_1));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());
		addToConfigs(new MyConfiguration(3, 4, 0, 0, 3, 0, MAZE_LEVEL4_BOARD_TILES_2, MAZE_LEVEL4_FIRE_TILES_2));	

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