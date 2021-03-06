package com.example.finalprojectapp.scenario.archetype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.graphic_utils.archetype.maze.BoardSpriteSheet;
import com.example.finalprojectapp.graphic_utils.archetype.maze.FireSprite;
import com.example.finalprojectapp.graphic_utils.archetype.maze.GoalSprite;
import com.example.finalprojectapp.graphic_utils.archetype.maze.HeroSprite;
import com.example.finalprojectapp.graphic_utils.archetype.maze.HeroSprite.heroDirection;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.scenario.configuration.Configuration;
import com.example.finalprojectapp.utilities.Android_Utils;
import com.example.finalprojectapp.utilities.Logic_Utils;

/**
 * Logical and Graphical data and functionality of the Maze game scenario.
 * @author daniel portnoy
 *
 */
public abstract class  MazeScenarioArchetype extends Scenario {

	// Scenario specific definitions.
	public static final String MOVE_OUT_OF_LIMITS_EXCEPTION_TEXT = "Exception : The character moved out of the maze limits.";
	public static final String STEP_ON_FIRE_EXCEPTION_TEXT = "Exception : The character steped on fire.";

	public static enum BoardTilesTypesEnum{
		Rockys_stones,
		Grass,
		Round_bricks_gray	
	}

	// Scenario dynamic parts.
	private int heroCurrentX,heroCurrentY;

	/**
	 * Randomizes a board.
	 * @param rows
	 * @param columns
	 * @return 2D logical representation of the board.
	 */
	public List<List<BoardTilesTypesEnum>> randomizeBoardTiles(int rows , int columns){

		// Create a new 2D logical board.
		List<List<BoardTilesTypesEnum>> randomMap = new ArrayList<List<BoardTilesTypesEnum>>();

		for (int i = 0; i < rows; i++) {

			// Create a new logical row.
			List<BoardTilesTypesEnum> randomMapRow = new ArrayList<MazeScenarioArchetype.BoardTilesTypesEnum>();

			for (int j = 0; j < columns; j++) {

				// Randomize a tile.
				int randomNum = Logic_Utils.randInt(1 , BoardTilesTypesEnum.values().length);

				// Add the tile to the row.
				switch (randomNum) {
				case 1:
					randomMapRow.add(j, BoardTilesTypesEnum.Rockys_stones);
					break;
				case 2:
					randomMapRow.add(j, BoardTilesTypesEnum.Grass);
					break;
				case 3:
					randomMapRow.add(j, BoardTilesTypesEnum.Round_bricks_gray);
					break;

				default:
					break;
				}
			}

			// Add the row to the board. 
			randomMap.add(i, randomMapRow);
		}

		return randomMap;
	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot(heroCurrentX, heroCurrentY);
	}

	@Override
	public void reset() {
		MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();
		heroCurrentX = currentConfig.heroStartX;
		heroCurrentY = currentConfig.heroStartY;
	}

	@Override
	public MySurfaceView generateGameView(Context context, int fps, boolean isAnimation) {
		return new SurfaceView_Maze(context, fps, isAnimation);
	}

	@Override
	public boolean checkWin(Configuration config, GameSnapshot gameSnapshot) {

		MyConfiguration currentConfig = (MyConfiguration) config;
		MyGameSnapshot mgs = (MyGameSnapshot)gameSnapshot;

		return mgs.heroX == currentConfig.targetX && mgs.heroY == currentConfig.targetY;
	}

	/******************** Nested classes ********************/


	/********** exception class **********/
	@SuppressWarnings("serial")
	protected class MoveOutOfLimitsException extends MyException{

		public MoveOutOfLimitsException() {
			super(MOVE_OUT_OF_LIMITS_EXCEPTION_TEXT);
		}

	}

	@SuppressWarnings("serial")
	protected class StepOnFireException extends MyException{

		public StepOnFireException() {
			super(STEP_ON_FIRE_EXCEPTION_TEXT);
		}

	}
	/********** exception class **********/


	/********** config class **********/
	/**
	 * Maze scenario specific Configuration object.
	 * @author daniel portnoy
	 *
	 */
	protected class MyConfiguration extends Configuration{

		public int rows;
		public int cols;

		public int heroStartX, heroStartY;

		public int targetX,targetY;

		public List<List<BoardTilesTypesEnum>> boardTiles;

		public List<List<Boolean>> fireTiles;

		public MyConfiguration(int rows, int cols, int heroStartX,int heroStartY,int targetX, int targetY,
				List<List<BoardTilesTypesEnum>> boardTiles, List<List<Boolean>> fireTiles) {
			this.rows = rows;
			this.cols = cols;
			this.heroStartX = heroStartX;
			this.heroStartY = heroStartY;
			this.targetX = targetX;
			this.targetY = targetY;

			this.boardTiles = boardTiles;
			this.fireTiles = fireTiles;

		}

		@Override
		public Configuration copy() {
			return new MyConfiguration(rows, cols, heroStartX, heroStartY, targetX, targetY, boardTiles, fireTiles);
		}

	}
	/********** config class **********/


	/********** snapshot class **********/
	/**
	 * Maze scenario specific GameSnapshot object.
	 * @author daniel portnoy
	 *
	 */
	protected class MyGameSnapshot extends GameSnapshot{

		private int heroX,heroY;

		public MyGameSnapshot(MyGameSnapshot other) {
			this.heroX = other.heroX;
			this.heroY = other.heroY;
		}


		public MyGameSnapshot(int heroX, int heroY) {
			this.heroX = heroX;
			this.heroY = heroY;
		}

		@Override
		public GameSnapshot copy(GameSnapshot other) {
			return new MyGameSnapshot((MyGameSnapshot) other);
		}

		public int getHeroX() {
			return heroX;
		}

		public int getHeroY() {
			return heroY;
		}

		@Override
		public boolean equals(GameSnapshot other) {

			MyGameSnapshot other_mgs = (MyGameSnapshot)other;
			return getHeroX() == other_mgs.getHeroX() && getHeroY() == other_mgs.getHeroY();
		}
	}
	/********** snapshot class **********/


	/********** special nodes class's **********/
	/**
	 * Maze scenario specific Node object.
	 * @author daniel portnoy
	 *
	 */
	protected abstract class NumberOfNode extends Node{

		private String codeText;

		public NumberOfNode(String codeText) {
			this.codeText = codeText;
			setType(Type.Int);
		}

		public abstract NumberOfNode newInstance();

		@Override
		public List<CodeWritingPart> getCodeWritingParts() {

			List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

			res.add(new CodeWritingPart(false, false, codeText, null, this));

			return res;
		}

		@Override
		public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

			isHighlighted = target.equals(this) || isHighlighted;
			List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

			res.add(new CodeRunningPart(false, false,isHighlighted, codeText));

			return res;
		}

		@Override
		public Node getFirstNode() {
			return null;
		}

		@Override
		public Set<String> getDeclaredIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			return res;
		}

		@Override
		public Set<String> getUsedIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			return res;
		}

		@Override
		public boolean DeleteChildNode(Node childNode) {
			return true;
		}

		@Override
		public List<Node> getChildNodes() {
			return null;
		}

		@Override
		public boolean addChild(Node child, int order) {
			return false;
		}
	}

	protected class NumberOfRowsNode extends NumberOfNode{

		public static final String NUMBER_OF_ROWS_CODE_TEXT = "numberOfRows";

		public NumberOfRowsNode() {
			super(NUMBER_OF_ROWS_CODE_TEXT);
		}

		@Override
		public NumberOfNode newInstance() {
			return new NumberOfRowsNode();
		}

		@Override
		public ReturnObject run() throws MyException {

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();
			return new ReturnObject(currentConfig.rows);
		}	
	}

	protected class NumberOfColumnsNode extends NumberOfNode{

		public static final String NUMBER_OF_COLUMNS_CODE_TEXT = "numberOfColumns";

		public NumberOfColumnsNode() {
			super(NUMBER_OF_COLUMNS_CODE_TEXT);
		}

		@Override
		public NumberOfNode newInstance() {
			return new NumberOfColumnsNode();
		}

		@Override
		public ReturnObject run() throws MyException {

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();
			return new ReturnObject(currentConfig.cols);
		}	
	}

	/**
	 * Maze scenario specific Node object.
	 * @author daniel portnoy
	 *
	 */
	protected abstract class MovementNode extends Node{

		private String codeText;

		public MovementNode(String codeText) {
			this.codeText = codeText;
			setType(Type.Statement);
		}

		public abstract MovementNode newInstance();

		protected abstract void move(MyConfiguration config) throws MyException;

		@Override
		public List<CodeWritingPart> getCodeWritingParts() {

			List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

			res.add(new CodeWritingPart(false, false, codeText, null, this));

			return res;
		}

		@Override
		public boolean addChild(Node child, int order) {
			return false;
		}

		@Override
		public List<Node> getChildNodes() {
			return null;
		}

		@Override
		public boolean DeleteChildNode(Node childNode) {
			return true;
		}

		@Override
		public Set<String> getDeclaredIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			return res;
		}

		@Override
		public Set<String> getUsedIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			return res;
		}

		@Override
		public Node getFirstNode() {
			return null;
		}

		@Override
		public ReturnObject run() throws MyException {

			LevelManager.getInstance().takeSnapshot(this);

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			try {
				move(currentConfig);
			} 
			catch (MyException e) {
				throw e;
			}

			if(currentConfig.fireTiles != null){
				if(currentConfig.fireTiles.get(heroCurrentY).get(heroCurrentX))
					throw new StepOnFireException();
			}

			return new ReturnObject();
		}

		@Override
		public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

			isHighlighted = target.equals(this) || isHighlighted;
			List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

			res.add(new CodeRunningPart(false, false,isHighlighted, codeText));

			return res;
		}
	}

	protected class GoRightNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new GoRightNode();
		}

		public static final String GO_RIGHT_CODE_TEXT = "goRight();";

		public GoRightNode() {
			super(GO_RIGHT_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentX < config.cols - 1 )
				heroCurrentX++;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}

	protected class GoLeftNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new GoLeftNode();
		}

		public static final String GO_LEFT_CODE_TEXT = "goLeft();";

		public GoLeftNode() {
			super(GO_LEFT_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentX > 0 )
				heroCurrentX--;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}

	protected class GoUpNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new GoUpNode();
		}

		public static final String GO_UP_CODE_TEXT = "goUp();";

		public GoUpNode() {
			super(GO_UP_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentY > 0 )
				heroCurrentY--;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}

	protected class GoDownNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new GoDownNode();
		}

		public static final String GO_DOWN_CODE_TEXT = "goDown();";

		public GoDownNode() {
			super(GO_DOWN_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentY < config.rows - 1 )
				heroCurrentY++;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}

	protected class JumpRightNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new JumpRightNode();
		}

		public static final String JUMP_RIGHT_CODE_TEXT = "jumpRight();";

		public JumpRightNode() {
			super(JUMP_RIGHT_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentX < config.cols - 2 )
				heroCurrentX += 2;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}

	protected class JumpLeftNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new JumpLeftNode();
		}

		public static final String JUMP_LEFT_CODE_TEXT = "jumpLeft();";

		public JumpLeftNode() {
			super(JUMP_LEFT_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentX > 0 )
				heroCurrentX -= 2;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}

	protected class JumpUpNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new JumpUpNode();
		}

		public static final String JUMP_UP_CODE_TEXT = "jumpUp();";

		public JumpUpNode() {
			super(JUMP_UP_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentY > 0 )
				heroCurrentY -= 2;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}

	protected class JumpDownNode extends MovementNode{

		@Override
		public MovementNode newInstance() {
			return new JumpDownNode();
		}

		public static final String JUMP_DOWN_CODE_TEXT = "jumpDown();";

		public JumpDownNode() {
			super(JUMP_DOWN_CODE_TEXT);
		}

		@Override
		protected void move(MyConfiguration config) throws MyException {
			if( heroCurrentY < config.rows - 2 )
				heroCurrentY += 2;
			else
				throw new MoveOutOfLimitsException(); 
		}
	}
	/********** special nodes class's **********/


	/********** special option class's **********/
	/**
	 * Maze scenario specific Option object.
	 * @author daniel portnoy
	 *
	 */
	protected abstract class NumberOfOption extends Option{

		private String optionText;

		private NumberOfNode node;

		public NumberOfOption(String optionText, NumberOfNode node) {
			this.optionText = optionText;
			this.node = node;
		}

		@Override
		public boolean isType(Type type) {
			return type == Type.Int;
		}

		@Override
		public void setButton(Context context, Button optionButton,final Setter SETTER) {

			optionButton.setText(optionText);

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SETTER.setChildNode(node.newInstance());
					SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
					refresh();
				}
			});
		}
	}

	protected class NumberOfRowsOption extends NumberOfOption{

		public static final String NUMBER_OF_ROWS_OPTION_TEXT = "numberOfRows";

		public NumberOfRowsOption() {
			super(NUMBER_OF_ROWS_OPTION_TEXT, new NumberOfRowsNode());
		}

	}

	protected class NumberOfColumnsOption extends NumberOfOption{

		public static final String NUMBER_OF_Columns_OPTION_TEXT = "numberOfColumns";

		public NumberOfColumnsOption() {
			super(NUMBER_OF_Columns_OPTION_TEXT, new NumberOfColumnsNode());
		}

	}

	/**
	 * Maze scenario specific Option object.
	 * @author daniel portnoy
	 *
	 */
	protected abstract class MovementOption extends Option{

		private String optionText;

		private MovementNode node;

		public MovementOption(String optionText, MovementNode node) {
			this.optionText = optionText;
			this.node = node;
		}

		@Override
		public boolean isType(Type type) {
			return type == Type.Statement;
		}

		@Override
		public void setButton(Context context, Button optionButton,final Setter SETTER) {

			optionButton.setText(optionText);

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SETTER.setChildNode(node.newInstance());
					SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
					refresh();
				}
			});
		}
	}

	protected class GoRightOption extends MovementOption{

		public static final String GO_RIGHT_OPTION_TEXT = "goRight";

		public GoRightOption() {
			super(GO_RIGHT_OPTION_TEXT, new GoRightNode());
		}
	}

	protected class GoLeftOption extends MovementOption{

		public static final String GO_LEFT_OPTION_TEXT = "goLeft";

		public GoLeftOption() {
			super(GO_LEFT_OPTION_TEXT, new GoLeftNode());
		}
	}

	protected class GoUpOption extends MovementOption{

		public static final String GO_UP_OPTION_TEXT = "goUp";

		public GoUpOption() {
			super(GO_UP_OPTION_TEXT, new GoUpNode());
		}
	}

	protected class GoDownOption extends MovementOption{

		public static final String GO_DOWN_OPTION_TEXT = "goDown";

		public GoDownOption() {
			super(GO_DOWN_OPTION_TEXT, new GoDownNode());
		}
	}

	protected class JumpRightOption extends MovementOption{

		public static final String JUMP_RIGHT_OPTION_TEXT = "jumpRight";

		public JumpRightOption() {
			super(JUMP_RIGHT_OPTION_TEXT, new JumpRightNode());
		}
	}

	protected class JumpLeftOption extends MovementOption{

		public static final String JUMP_LEFT_OPTION_TEXT = "jumpLeft";

		public JumpLeftOption() {
			super(JUMP_LEFT_OPTION_TEXT, new JumpLeftNode());
		}
	}

	protected class JumpUpOption extends MovementOption{

		public static final String JUMP_UP_OPTION_TEXT = "jumpUp";

		public JumpUpOption() {
			super(JUMP_UP_OPTION_TEXT, new JumpUpNode());
		}
	}

	protected class JumpDownOption extends MovementOption{

		public static final String JUMP_DOWN_OPTION_TEXT = "jumpDown";

		public JumpDownOption() {
			super(JUMP_DOWN_OPTION_TEXT, new JumpDownNode());
		}
	}
	/********** special option class's **********/


	/********** SurfaceView class **********/
	/**
	 * Maze scenario specific MySurfaceView object.
	 * @author daniel portnoy
	 *
	 */
	protected class SurfaceView_Maze extends MySurfaceView {

		private boolean isStillAnimating = false;

		// true - walk \ false - jump
		private boolean walkOrJump = false;

		// hero related
		private HeroSprite heroSprite;
		private int heroXpos, heroYpos;
		private int heroXprevLogic, heroYprevLogic;
		private int heroXcurrentLogic, heroYcurrentLogic;
		private final double HERO_SCALE_HEIGHT_PERCENT = 0.9, HERO_SCALE_WIDTH_PERCENT = 0.9;

		// goal related
		private GoalSprite goalSprite;
		private int goalXpos, goalYpos;
		private final double GOAL_SCALE_HEIGHT_PERCENT = 0.5, GOAL_SCALE_WIDTH_PERCENT = 0.5;

		// fire related
		private FireSprite fireSprite;
		private List<Pair<Integer, Integer>> fireXYpos;
		private final double FIRE_SCALE_HEIGHT_PERCENT = 1, FIRE_SCALE_WIDTH_PERCENT = 1;

		// board related
		private BoardSpriteSheet boardSpriteSheet;
		private int boardXpos, boardYpos;

		// Bitmaps
		private Bitmap heroCurrentBitmap;
		private Bitmap heroStandBitmap;
		private Bitmap goalCurrentBitmap;
		private Bitmap fireCurrentBitmap;
		private Bitmap boardBitmap;

		// positions and measurements
		private int boardScaleWidth;
		private int boardScaleHeight;
		private int goalScaleWidth;
		private int goalScaleHeight;
		private int fireScaleWidth;
		private int fireScaleHeight;
		private int heroScaleWidth;
		private int heroScaleHeight;
		private int tileWidth;
		private int tileHeight;
		private int shiftHeroX;
		private int shiftHeroY;
		private int tileHeightSpriteInterval;
		private int tileWidthSpriteInterval;

		public SurfaceView_Maze(Context context, int fps, boolean isAnimation) {
			super(context, fps, isAnimation);

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			// Initialize boardSpriteSheet.
			boardSpriteSheet = new BoardSpriteSheet(
					currentConfig.rows,
					currentConfig.cols, 

					BitmapFactory.decodeResource(
							getResources(),
							R.drawable.tile_set_two,
							Android_Utils.BitmapFactoryOptionsInScaled()
							),

							currentConfig.boardTiles
					);

			boardBitmap = boardSpriteSheet.getBitmap();

			fireXYpos = new ArrayList<Pair<Integer,Integer>>();

			// Initialize fireSprite.
			fireSprite = new FireSprite(

					BitmapFactory.decodeResource(
							getResources(),
							R.drawable.fire_sprite,
							Android_Utils.BitmapFactoryOptionsInScaled()
							),

							Constants.MAZE_ARCHETYPE_FIRE_SPRITE_ROWS,
							Constants.MAZE_ARCHETYPE_FIRE_SPRITE_COLS,
							0,
							0
					);

			// Initialize goalSprite.
			goalSprite = new GoalSprite(

					BitmapFactory.decodeResource(
							getResources(),
							R.drawable.coin_sprite,
							Android_Utils.BitmapFactoryOptionsInScaled()
							),

							Constants.MAZE_ARCHETYPE_GOAL_SPRITE_ROWS,
							Constants.MAZE_ARCHETYPE_GOAL_SPRITE_COLS,
							0,
							0
					);

			// Initialize heroSprite.
			heroSprite = new HeroSprite(

					BitmapFactory.decodeResource(
							getResources(),
							R.drawable.hero_sprite,
							Android_Utils.BitmapFactoryOptionsInScaled()
							),

							Constants.MAZE_ARCHETYPE_HERO_SPRITE_ROWS,
							Constants.MAZE_ARCHETYPE_HERO_SPRITE_COLS,
							Constants.MAZE_ARCHETYPE_HERO_SPRITE_DIRECTION_RIGHT_ROW,
							0
					);

			heroStandBitmap = heroSprite.getBitmapByCoords(0, 3);

			reset();

		}

		@Override
		public boolean isStillAnimating() {
			return isStillAnimating;
		}

		@Override
		public void preCalculation() {

			/***** Calculate screen and board positions and measurements *****/
			int screenWidth = getWidth();
			int screenHeight = getHeight();

			boardScaleWidth = boardBitmap.getWidth();
			boardScaleHeight = boardBitmap.getHeight();

			float imageRatio = (float) boardScaleWidth / boardScaleHeight;
			float screenRatio = (float) screenWidth / screenHeight;

			if(imageRatio >= screenRatio){
				// Image is wider than the display (ratio)
				boardScaleWidth = screenWidth;
				boardScaleHeight = (int) (boardScaleWidth / imageRatio);
			}
			else{
				// Image is taller than the display (ratio)
				boardScaleHeight = screenHeight;
				boardScaleWidth = (int) (boardScaleHeight * imageRatio);
			}

			int XShift = (screenWidth - boardScaleWidth)/2;
			int YShift = (screenHeight - boardScaleHeight)/2;

			boardXpos = XShift;
			boardYpos = YShift;
			/***** Calculate screen and board positions and measurements *****/

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			/***** Calculate tile positions and measurements *****/
			tileHeight = boardScaleHeight/currentConfig.rows;
			tileWidth = boardScaleWidth/currentConfig.cols;
			/***** Calculate tile positions and measurements *****/

			/***** Calculate Hero related positions and measurements *****/
			heroScaleWidth = (int) (tileWidth * HERO_SCALE_WIDTH_PERCENT);
			heroScaleHeight = (int) (tileHeight * HERO_SCALE_HEIGHT_PERCENT);

			shiftHeroX = (tileWidth - heroScaleWidth)/2;
			shiftHeroY = (tileHeight - heroScaleHeight)/2;

			tileWidthSpriteInterval = tileWidth/heroSprite.getNumOfFrames();
			tileHeightSpriteInterval = tileHeight/heroSprite.getNumOfFrames();
			/***** Calculate Hero related positions and measurements *****/

			/***** Calculate Goal related positions and measurements *****/
			goalScaleWidth = (int) (tileWidth * GOAL_SCALE_WIDTH_PERCENT);
			goalScaleHeight = (int) (tileHeight * GOAL_SCALE_HEIGHT_PERCENT);

			int shiftGoalX = (tileWidth - goalScaleWidth)/2;
			int shiftGoalY = (tileHeight - goalScaleHeight)/2;

			goalXpos = boardXpos + shiftGoalX + currentConfig.targetX*tileWidth; 
			goalYpos = boardYpos + shiftGoalY + currentConfig.targetY*tileHeight;
			/***** Calculate Goal related positions and measurements *****/

			/***** Calculate Fire related positions and measurements *****/
			fireScaleWidth = (int) (tileWidth * FIRE_SCALE_WIDTH_PERCENT);
			fireScaleHeight = (int) (tileHeight * FIRE_SCALE_HEIGHT_PERCENT);

			int shiftFireX = (tileWidth - fireScaleWidth)/2;
			int shiftFireY = (tileHeight - fireScaleHeight)/2;
			/***** Calculate Fire related positions and measurements *****/

			// Set fire tile XY positions.
			if(currentConfig.fireTiles != null){

				for (int i = 0; i < currentConfig.fireTiles.size(); i++) {
					for (int j = 0; j < currentConfig.fireTiles.get(i).size(); j++) {

						if(currentConfig.fireTiles.get(i).get(j)){

							int fireXpos = boardXpos + shiftFireX + tileWidth*j;
							int fireYpos = boardYpos + shiftFireY + tileHeight*i;

							fireXYpos.add(new Pair<Integer, Integer>(fireXpos, fireYpos));
						}
					}
				}
			}
		}

		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);

			render();

			// fill background.
			canvas.drawColor(Color.WHITE);

			// draw board.
			canvas.drawBitmap(boardBitmap, boardXpos, boardYpos, null);

			// draw fire tiles.
			for (Pair<Integer, Integer> coord : fireXYpos) {
				canvas.drawBitmap(fireCurrentBitmap, coord.first, coord.second, null);
			}

			// draw goal
			canvas.drawBitmap(goalCurrentBitmap, goalXpos, goalYpos, null);
			// draw hero
			canvas.drawBitmap(heroCurrentBitmap, heroXpos, heroYpos, null);
		}

		@Override
		public void reset() {

			isStillAnimating = false;

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			// reset all data to the configuration.
			heroXprevLogic = heroXcurrentLogic = currentConfig.heroStartX;
			heroYprevLogic = heroYcurrentLogic = currentConfig.heroStartY;

			// reset all sprites.
			heroSprite.reset();
			goalSprite.reset();
			fireSprite.reset();
		}

		@Override
		public void updateAnimated() {
			goalSprite.update();
			fireSprite.update();
			goalCurrentBitmap = goalSprite.getCurrentBitmap();
			fireCurrentBitmap = fireSprite.getCurrentBitmap();

			int distanceMultiplier;

			if (isStillAnimating){

				int heroCurrentFrame = heroSprite.getFrameNumber();

				heroSprite.update();

				heroXpos = boardXpos + heroXprevLogic*tileWidth + shiftHeroX;
				heroYpos = boardYpos + heroYprevLogic*tileHeight + shiftHeroY;

				distanceMultiplier = walkOrJump ? 1 : 2;  

				switch (heroSprite.getDirection()) {
				case Up:
					heroYpos -= heroCurrentFrame*tileHeightSpriteInterval*distanceMultiplier;
					break;
				case Left:
					heroXpos -= heroCurrentFrame*tileWidthSpriteInterval*distanceMultiplier;
					break;
				case Down:
					heroYpos += heroCurrentFrame*tileHeightSpriteInterval*distanceMultiplier;
					break;
				case Right:
					heroXpos += heroCurrentFrame*tileWidthSpriteInterval*distanceMultiplier;
					break;

				default:
					break;
				}

				if(walkOrJump)
					heroCurrentBitmap = heroSprite.getCurrentBitmap();
				else{

					if (heroSprite.getDirection() == heroDirection.Left || heroSprite.getDirection() == heroDirection.Right )
						heroYpos -= (tileHeight/2)*HeroSprite.ARC_ARRAY_9_FRAMES[heroCurrentFrame-1];

					heroCurrentBitmap = heroSprite.getFirstFrameBitmap();

				}

				if(heroSprite.isLooped())
					isStillAnimating = false;
			}
			else{
				heroSprite.reset();

				heroXpos = boardXpos + heroXcurrentLogic*tileWidth + shiftHeroX;
				heroYpos = boardYpos + heroYcurrentLogic*tileHeight + shiftHeroY;

				heroXprevLogic = heroXcurrentLogic;
				heroYprevLogic = heroYcurrentLogic;

				heroCurrentBitmap = heroStandBitmap;
			}

		}

		@Override
		public void updateNonAnimated() {

			isStillAnimating = false;

			goalCurrentBitmap = goalSprite.getBitmapByCoords(0, 4);
			fireCurrentBitmap = fireSprite.getBitmapByCoords(0, 0);
			heroCurrentBitmap = heroStandBitmap;

			heroXpos = boardXpos + heroXcurrentLogic*tileWidth + shiftHeroX;
			heroYpos = boardYpos + heroYcurrentLogic*tileHeight + shiftHeroY;
		}

		@Override
		public void render() {
			/* scaling bitmaps */	
			boardBitmap = Bitmap.createScaledBitmap(boardBitmap, boardScaleWidth, boardScaleHeight, true);
			goalCurrentBitmap = Bitmap.createScaledBitmap(goalCurrentBitmap, goalScaleWidth, goalScaleHeight, true);
			fireCurrentBitmap = Bitmap.createScaledBitmap(fireCurrentBitmap, fireScaleWidth, fireScaleHeight, true);
			heroCurrentBitmap = Bitmap.createScaledBitmap(heroCurrentBitmap, heroScaleWidth, heroScaleHeight, true);		
			/* scaling bitmaps */
		}

		@Override
		public void loadSnapshot(GameSnapshot gameSnapshot) {

			MyGameSnapshot mgs = (MyGameSnapshot)gameSnapshot;

			heroSprite.reset();

			int xDifferential = mgs.getHeroX() - heroXprevLogic;
			int yDifferential = mgs.getHeroY() - heroYprevLogic;

			if(xDifferential > 0){
				heroSprite.setDirection(heroDirection.Right);
				isStillAnimating = true;
			}
			else if(xDifferential < 0){
				heroSprite.setDirection(heroDirection.Left);
				isStillAnimating = true;
			}
			else if(yDifferential > 0){
				heroSprite.setDirection(heroDirection.Down);
				isStillAnimating = true;
			}
			else if(yDifferential < 0){
				heroSprite.setDirection(heroDirection.Up);
				isStillAnimating = true;
			}
			else{
				heroSprite.setDirection(heroDirection.Stand);
				isStillAnimating = false;
			}

			if(Math.abs(xDifferential) == 2 || Math.abs(yDifferential) == 2)
				walkOrJump = false;
			else
				walkOrJump = true;

			heroXprevLogic = heroXcurrentLogic;
			heroYprevLogic = heroYcurrentLogic;

			heroXcurrentLogic = mgs.getHeroX();
			heroYcurrentLogic = mgs.getHeroY();
		}
		/********** SurfaceView class **********/


		/******************** Nested classes ********************/
	}
}
