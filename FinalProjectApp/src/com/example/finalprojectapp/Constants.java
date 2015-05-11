package com.example.finalprojectapp;

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

	//LI
	public static final boolean DEFAULT_LI = true;
	public static final String LI_KEY = "LI";

	//ANIMATION
	public static final boolean DEFAULT_ANIMATION = false;
	public static final String ANIMATION_KEY = "ANIMATION";

	//	SWIPE
	public static final boolean DEFAULT_SWIPE = true;
	public static final String 	SWIPE_KEY = "SWIPE";



	// LEVEL1 HERO SPRITE
	public static final int HERO_SPRITE_ROWS = 4;
	public static final int HERO_SPRITE_COLS = 9;
	public static final int HERO_SPRITE_DIRECTION_UP_ROW = 0;
	public static final int HERO_SPRITE_DIRECTION_LEFT_ROW = 1;
	public static final int HERO_SPRITE_DIRECTION_DOWN_ROW = 2;
	public static final int HERO_SPRITE_DIRECTION_RIGHT_ROW = 3;

	// LEVEL1 GOAL SPRITE
	public static final int GOAL_SPRITE_ROWS = 8;
	public static final int GOAL_SPRITE_COLS = 8;

	//TEXTS
	public static final String LEVEL_START_TEXT = "Before you jump in...";
	public static final String LEVEL_END_WIN_TEXT = "You Won!";
	public static final String LEVEL_END_LOSS_TEXT = "You Lost!";
	public static final String LEVEL_END_WIN_TITLE_TEXT = "Well played...";
	public static final String LEVEL_END_LOSS_TITLE_TEXT = "Too bad...";

	public static final String LEVEL_END_WIN_POSITIVE_TEXT = "Continue";
	public static final String LEVEL_END_LOSS_POSITIVE_TEXT = "Retry";


}
