package com.example.finalprojectapp.scenario.archetype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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

public abstract class  MazeScenarioArchetype extends Scenario {
	
	public static final String MOVE_OUT_OF_LIMITS_EXCEPTION_TEXT = "Exception : The character moved out of the maze limits.";

	public static enum BoardTilesTypesEnum{
		Rockys_stones,
		Grass,
		Round_bricks_gray	
	}

	private int heroCurrentX,heroCurrentY;

	public BoardTilesTypesEnum[][] randomizeBoardTiles(int rows , int columns){

		BoardTilesTypesEnum[][] randomMap = new BoardTilesTypesEnum[rows][columns];
		Random rand = new Random();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int randomNum = rand.nextInt(BoardTilesTypesEnum.values().length) + 1;

				switch (randomNum) {
				case 1:
					randomMap[i][j] = BoardTilesTypesEnum.Rockys_stones;
					break;
				case 2:
					randomMap[i][j] = BoardTilesTypesEnum.Grass;
					break;
				case 3:
					randomMap[i][j] = BoardTilesTypesEnum.Round_bricks_gray;
					break;

				default:
					break;
				}
			}
		}

		return randomMap;
	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot();
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
	/********** exception class **********/


	/********** config class **********/
	protected class MyConfiguration extends Configuration{

		public int rows;
		public int cols;

		public int heroStartX, heroStartY;

		public int targetX,targetY;

		public BoardTilesTypesEnum[][] boardTiles;

		public MyConfiguration(int rows, int cols, int heroStartX,int heroStartY,int targetX, int targetY, BoardTilesTypesEnum[][] boardTiles) {
			this.rows = rows;
			this.cols = cols;
			this.heroStartX = heroStartX;
			this.heroStartY = heroStartY;
			this.targetX = targetX;
			this.targetY = targetY;

			this.boardTiles = boardTiles;

		}

		@Override
		public Configuration copy() {
			return new MyConfiguration(rows, cols, heroStartX, heroStartY, targetX, targetY, boardTiles);
		}

	}
	/********** config class **********/


	/********** snapshot class **********/
	protected class MyGameSnapshot extends GameSnapshot{

		private int heroX,heroY;

		public MyGameSnapshot(MyGameSnapshot other) {
			this.heroX = other.heroX;
			this.heroY = other.heroY;
		}


		public MyGameSnapshot() {
			this.heroX = heroCurrentX;
			this.heroY = heroCurrentY;
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
		public void setButton(Context context, Button optionButton,final Setter setter) {

			optionButton.setText(optionText);	//TODO

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setter.setChildNode(node.newInstance());
					setter.getParent().reOrderScope(setter.getOrder(), 1);
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

	protected class SurfaceView_Maze extends MySurfaceView {

		private boolean isStillAnimating = false;

		private boolean walkOrJump = false;	// true - walk \ false - jump

		private HeroSprite heroSprite;
		private int heroXpos, heroYpos;
		private int heroXprevLogic, heroYprevLogic;
		private int heroXcurrentLogic, heroYcurrentLogic;
		private final double heroScaleHeightPercent = 0.9, heroScaleWidthPercent = 0.9;

		private GoalSprite goalSprite;
		private int goalXpos, goalYpos;
		private final double goalScaleHeightPercent = 0.5, goalScaleWidthPercent = 0.5;

		private BoardSpriteSheet boardSpriteSheet;
		private int boardXpos, boardYpos;

		private Bitmap heroCurrentBitmap;
		private Bitmap heroStandBitmap;

		private Bitmap goalCurrentBitmap;

		private Bitmap boardBitmap;

		// positions and measurements
		private int boardScaleWidth;
		private int boardScaleHeight;
		private int goalScaleWidth;
		private int goalScaleHeight;
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

			goalSprite = new GoalSprite(

					BitmapFactory.decodeResource(
							getResources(),
							R.drawable.coin,
							Android_Utils.BitmapFactoryOptionsInScaled()
							),

							Constants.MAZE_ARCHETYPE_GOAL_SPRITE_ROWS,
							Constants.MAZE_ARCHETYPE_GOAL_SPRITE_COLS,
							0,
							0
					);

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

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			tileHeight = boardScaleHeight/currentConfig.rows;
			tileWidth = boardScaleWidth/currentConfig.cols;

			heroScaleWidth = (int) (tileWidth*heroScaleWidthPercent);
			heroScaleHeight = (int) (tileHeight*heroScaleHeightPercent);

			shiftHeroX = (tileWidth - heroScaleWidth)/2;
			shiftHeroY = (tileHeight - heroScaleHeight)/2;

			goalScaleWidth = (int) (tileWidth*goalScaleWidthPercent);
			goalScaleHeight = (int) (tileHeight*goalScaleHeightPercent);

			int shiftGoalX = (tileWidth - goalScaleWidth)/2;
			int shiftGoalY = (tileHeight - goalScaleHeight)/2;

			tileWidthSpriteInterval = tileWidth/heroSprite.getNumOfFrames();
			tileHeightSpriteInterval = tileHeight/heroSprite.getNumOfFrames();

			boardXpos = XShift;
			boardYpos = YShift;

			goalXpos = boardXpos + shiftGoalX + currentConfig.targetX*tileWidth; 
			goalYpos = boardYpos + shiftGoalY + currentConfig.targetY*tileHeight;
		}

		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);

			render();

			canvas.drawColor(Color.WHITE);		// TODO

			canvas.drawBitmap(boardBitmap, boardXpos, boardYpos, null);
			canvas.drawBitmap(goalCurrentBitmap, goalXpos, goalYpos, null);
			canvas.drawBitmap(heroCurrentBitmap, heroXpos, heroYpos, null);
		}

		@Override
		public void reset() {

			isStillAnimating = false;

			MyConfiguration currentConfig = (MyConfiguration) getCurrentConfig();

			heroXprevLogic = heroXcurrentLogic = currentConfig.heroStartX;
			heroYprevLogic = heroYcurrentLogic = currentConfig.heroStartY;

			heroSprite.reset();
			goalSprite.reset();
		}

		@Override
		public void updateAnimated() {
			goalSprite.update();
			goalCurrentBitmap = goalSprite.getCurrentBitmap();

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

				heroXpos = boardXpos + heroXcurrentLogic*tileWidth +shiftHeroX;
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
			heroCurrentBitmap = heroStandBitmap;

			heroXpos = boardXpos + heroXcurrentLogic*tileWidth;
			heroYpos = boardYpos + heroYcurrentLogic*tileHeight;
		}

		@Override
		public void render() {
			/* scaling bitmaps */	
			boardBitmap = Bitmap.createScaledBitmap(boardBitmap, boardScaleWidth, boardScaleHeight, true);
			goalCurrentBitmap = Bitmap.createScaledBitmap(goalCurrentBitmap, goalScaleWidth, goalScaleHeight, true);
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
