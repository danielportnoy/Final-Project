package com.example.finalprojectapp;

/**
 * Constant data of the system. 
 * @author daniel portnoy
 *
 */
public class Constants {

	public static final String ANDROIDNS="http://schemas.android.com/apk/res/android";
	public static final String APPLICATIONNS="http://schemas.android.com/apk/res/com.example.finalprojectapp";

	//FPS
	public static final int DEFAULT_FPS = 20;
	public static final int MAX_FPS = 60;
	public static final int MIN_FPS = 10;
	public static final int FPS_INTERVAL = 5;
	public static final String FPS_KEY = "FPS";

	//CPS
	public static final int DEFAULT_CPS = 2;
	public static final int MAX_CPS = 4;
	public static final int MIN_CPS = 1;
	public static final int CPS_INTERVAL = 1;
	public static final String CPS_KEY = "CPS";

	//NUMBER OPTION
	public static final int NUMBER_PICKER_MIN_VALUE = 0;
	public static final int NUMBER_PICKER_MAX_VALUE = 999;
	public static final int NUMBER_PICKER_DEFAULT_VALUE = 0;

	//LI
	public static final boolean DEFAULT_LI = true;
	public static final String LI_KEY = "LI";

	//ANIMATION
	public static final boolean DEFAULT_ANIMATION = false;
	public static final String ANIMATION_KEY = "ANIMATION";

	//	SWIPE
	public static final boolean DEFAULT_SWIPE = true;
	public static final String 	SWIPE_KEY = "SWIPE";

	// MAZE ARCHETYPE HERO SPRITE
	public static final int MAZE_ARCHETYPE_HERO_SPRITE_ROWS = 4;
	public static final int MAZE_ARCHETYPE_HERO_SPRITE_COLS = 9;
	public static final int MAZE_ARCHETYPE_HERO_SPRITE_DIRECTION_UP_ROW = 0;
	public static final int MAZE_ARCHETYPE_HERO_SPRITE_DIRECTION_LEFT_ROW = 1;
	public static final int MAZE_ARCHETYPE_HERO_SPRITE_DIRECTION_DOWN_ROW = 2;
	public static final int MAZE_ARCHETYPE_HERO_SPRITE_DIRECTION_RIGHT_ROW = 3;

	// MAZE ARCHETYPE GOAL SPRITE
	public static final int MAZE_ARCHETYPE_GOAL_SPRITE_ROWS = 8;
	public static final int MAZE_ARCHETYPE_GOAL_SPRITE_COLS = 8;

	// MAZE ARCHETYPE FIRE SPRITE
	public static final int MAZE_ARCHETYPE_FIRE_SPRITE_ROWS = 8;
	public static final int MAZE_ARCHETYPE_FIRE_SPRITE_COLS = 8;

	// GAME TEXTS
	public static final String LEVEL_START_TEXT = "Before you jump in...";

	public static final String LEVEL_END_WIN_HEADER_TEXT = "You Won!";
	public static final String LEVEL_END_WIN_TEXT = "great!";

	public static final String LEVEL_END_LOSS_HEADER_TEXT = "You Lost!";
	public static final String LEVEL_END_LOSS_TEXT = "maybe next time...";

	public static final String LEVEL_END_WIN_TITLE_TEXT = "Well played...";
	public static final String LEVEL_END_LOSS_TITLE_TEXT = "Too bad...";

	public static final String LEVEL_END_WIN_POSITIVE_TEXT = "Continue";
	public static final String LEVEL_END_LOSS_POSITIVE_TEXT = "Retry";

	//EXCEPTIONS TEXTS
	public static final String DIVISION_BY_ZERO_EXCEPTION_TEXT = "Exception : Can not devide by zero.";

	// OPTIONS TEXTS
	public static final String BLOCK_OPTION_TEXT = "Block";
	public static final String FALSE_OPTION_TEXT = "False";
	public static final String NUMBER_OPTION_TEXT = "Number";
	public static final String TRUE_OPTION_TEXT = "True";
	public static final String ADDITION_OPTION_TEXT = "+";
	public static final String DECREMENT_OPTION_TEXT = "--";
	public static final String DIVISION_OPTION_TEXT = "/";
	public static final String INCREMENT_OPTION_TEXT = "++";
	public static final String MULTIPLICATION_OPTION_TEXT = "*";
	public static final String SUBTRACTION_OPTION_TEXT = "-";
	public static final String ASSIGNMENT_OPTION_TEXT = "=";
	public static final String NOT_OPTION_TEXT = "!";
	public static final String EQUALS_OPTION_TEXT = "==";
	public static final String GREATER_THAN_OPTION_TEXT = ">";
	public static final String LESS_THAN_OPTION_TEXT = "<";
	public static final String NOT_EQUALS_OPTION_TEXT = "!=";
	public static final String FOR_OPTION_TEXT = "For";
	public static final String IF_THAN_OPTION_TEXT = "If than";
	public static final String WHILE_OPTION_TEXT = "While";
	public static final String BOOL_VAR_DEC_OPTION_TEXT = "Bool var dec";
	public static final String INT_VAR_DEC_OPTION_TEXT = "Int var dec";

	// CODE TEXTS
	public static final String ADDITION_CODE_TEXT = "+";
	public static final String DECREMENT_CODE_TEXT = "--";
	public static final String DIVISION_CODE_TEXT = "/";
	public static final String INCREMENT_CODE_TEXT = "++";
	public static final String MULTIPLICATION_CODE_TEXT = "*";
	public static final String SUBTRACTION_CODE_TEXT = "-";
	public static final String ASSIGNMENT_CODE_TEXT = "=";
	public static final String NOT_CODE_TEXT = "!";
	public static final String EQUALS_CODE_TEXT = "==";
	public static final String GREATER_THAN_CODE_TEXT = ">";
	public static final String LESS_THAN_CODE_TEXT = "<";
	public static final String NOT_EQUALS_CODE_TEXT = "!=";
	public static final String BOOLEAN_CODE_TEXT = "boolean";
	public static final String INTEGER_CODE_TEXT = "int";

	// TABS TEXTS
	public static final String LEVEL_TAB_TEXT = "Level";
	public static final String CODE_TAB_TEXT = "Code";
	public static final String RUN_TAB_TEXT = "Run";
	
	// DIALOG TEXTS
	public static final String END_GAME_POSITIVE_TEXT = "OK";
	public static final String END_GAME_NEGATIVE_TEXT= "Stay";

}
