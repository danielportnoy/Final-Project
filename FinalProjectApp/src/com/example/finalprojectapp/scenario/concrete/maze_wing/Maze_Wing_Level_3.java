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

public class Maze_Wing_Level_3 extends MazeScenarioArchetype {

	public static final String MAZE_LEVEL3_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: try ALL of the movment options...\n\n" +
			"Warning: be careful not to go out of the maze borders.";
	
	@SuppressWarnings("serial")
	public static final List<List<BoardTilesTypesEnum>> MAZE_LEVEL3_BOARD_TILES = new ArrayList<List<BoardTilesTypesEnum>>(){
		{
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));
			add(Arrays.asList(new BoardTilesTypesEnum[]{BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass, BoardTilesTypesEnum.Grass}));

		}
	};

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(4, 4, 1, 1, 3, 2, MAZE_LEVEL3_BOARD_TILES, null));
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