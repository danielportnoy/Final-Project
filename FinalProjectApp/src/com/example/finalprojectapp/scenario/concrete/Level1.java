package com.example.finalprojectapp.scenario.concrete;

import java.util.ArrayList;
import java.util.List;

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
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.codewriting.option.concrete.block.BlockOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.FalseOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.TrueOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.PlusOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.assignment.SimpleAssignmentOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.EqualsOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.NotEqualsOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.ForOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.IfThenOption;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.BoolVarDecOption;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.IntVarDecOption;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.graphic_utils.maze.BoardSpriteSheet;
import com.example.finalprojectapp.graphic_utils.maze.GoalSprite;
import com.example.finalprojectapp.graphic_utils.maze.HeroSprite;
import com.example.finalprojectapp.graphic_utils.maze.HeroSprite.heroDirection;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.utilities.Android_Utils;

public class Level1 extends Scenario {

	private final int ROWS = 3;	// TODO
	private final int COLS = 4;	// TODO

	private final int heroStartX=0, heroStartY=1;
	private int heroCurrentX=heroStartX, heroCurrentY=heroStartY;	

	private final int targetX=3,targetY=1;

	private final String LEVEL1_TEXT = "Hello, travler! you'r goal is to reach the Coin. go ahead and try.... \n\n" +
			"Hint: try the \"GoRight();\" option...";

	@Override
	public void initiateAvailableOptions() {

		addToAvailableOptions(new BlockOption());
		addToAvailableOptions(new IfThenOption());
		addToAvailableOptions(new BoolVarDecOption());
		addToAvailableOptions(new IntVarDecOption());
		addToAvailableOptions(new ForOption());

		// literal
		//addToAvailableOptions(new LiteralOption());	// TODO
		addToAvailableOptions(new NumberOption());
		addToAvailableOptions(new TrueOption());
		addToAvailableOptions(new FalseOption());


		addToAvailableOptions(new PlusOption());

		addToAvailableOptions(new SimpleAssignmentOption());

		addToAvailableOptions(new EqualsOption());
		addToAvailableOptions(new NotEqualsOption());

		//custom options	// TODO
		addToAvailableOptions(new GoRightOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(LEVEL1_TEXT);
	}

	@Override
	public void reset() {
		heroCurrentX=heroStartX;
		heroCurrentY=heroStartY;
	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot();
	}

	@Override
	public MySurfaceView generateGameView(Context context, int fps, boolean isAnimation) {
		return new SurfaceView_Level1(context, fps, isAnimation);
	}

	/******************** Nested classes ********************/

	/********** snapshot class **********/
	class MyGameSnapshot extends GameSnapshot{

		private int heroX,heroY;

		public MyGameSnapshot() {
			this.heroX = heroCurrentX;
			this.heroY = heroCurrentY;
		}

		public int getHeroX() {
			return heroX;
		}

		public int getHeroY() {
			return heroY;
		}

		@Override
		public boolean checkWin() {
			return heroX == targetX && heroY == targetY;
		}

		@Override
		public boolean checkLoss() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean equals(GameSnapshot other) {
			
			MyGameSnapshot other_mgs = (MyGameSnapshot)other;
			return getHeroX() == other_mgs.getHeroX() && getHeroY() == other_mgs.getHeroY();
		}
	}
	/********** snapshot class **********/

	/********** special nodes class's **********/
	class GoRightNode extends Node{

		@Override
		public List<CodeWritingPart> getCodeWritingParts() {

			List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

			res.add(new CodeWritingPart(false, false, "goRight();", null));

			return res;
		}

		@Override
		public ReturnObject run() {

			LevelManager.getInstance().takeSnapshot(this);

			if( heroCurrentX < COLS - 1 )
				heroCurrentX++;

			return new ReturnObject();
		}

		@Override
		public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

			isHighlighted = target.equals(this) || isHighlighted;
			List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

			res.add(new CodeRunningPart(false, false,isHighlighted, "goRight();"));

			return res;
		}

	}
	/********** special nodes class's **********/

	/********** special option class's **********/
	class GoRightOption extends Option{

		@Override
		public boolean isType(Type type) {
			return type == Type.Statement;
		}

		@Override
		public void setButton(Context context, Button optionButton,final Setter setter) {

			optionButton.setText("goRight");	//TODO

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setter.setChildNode(new GoRightNode());
					refresh();
				}
			});
		}

	}
	/********** special option class's **********/

	/********** SurfaceView class **********/

	class SurfaceView_Level1 extends MySurfaceView {

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

		public SurfaceView_Level1(Context context, int fps, boolean isAnimation) {
			super(context, fps, isAnimation);

			boardSpriteSheet = new BoardSpriteSheet(ROWS,COLS,BitmapFactory.decodeResource(getResources(),R.drawable.tile_set_two,Android_Utils.BitmapFactoryOptionsInScaled()));
			boardBitmap = boardSpriteSheet.getBitmap();

			goalSprite = new GoalSprite(BitmapFactory.decodeResource(getResources(),R.drawable.coin,Android_Utils.BitmapFactoryOptionsInScaled()),
					Constants.GOAL_SPRITE_ROWS,Constants.GOAL_SPRITE_COLS,0,0);

			heroSprite = new HeroSprite(BitmapFactory.decodeResource(getResources(),R.drawable.hero_sprite,Android_Utils.BitmapFactoryOptionsInScaled()),
					Constants.HERO_SPRITE_ROWS,Constants.HERO_SPRITE_COLS,Constants.HERO_SPRITE_DIRECTION_RIGHT_ROW,0);
			
			heroStandBitmap = heroSprite.getBitmapByCoords(0, 3);

			reset();

		}

		@Override
		public void preCalculation() {

			int screenWidth = getWidth();
			int screenHeight = getHeight();

			boardScaleWidth = boardBitmap.getWidth();
			boardScaleHeight = boardBitmap.getHeight();

			if (boardScaleWidth > boardScaleHeight) {
				// landscape
				float ratio = (float) boardScaleWidth / screenWidth;
				boardScaleWidth = screenWidth;
				boardScaleHeight = (int)(boardScaleHeight / ratio);
			} else if (boardScaleHeight > boardScaleWidth) {
				// portrait
				float ratio = (float) boardScaleHeight / screenHeight;
				boardScaleHeight = screenHeight;
				boardScaleWidth = (int)(boardScaleWidth / ratio);
			} else {
				// square
				boardScaleHeight = screenHeight;
				boardScaleWidth = screenWidth;
			}

			tileHeight = boardScaleHeight/ROWS;
			tileWidth = boardScaleWidth/COLS;

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

			boardXpos = 0;
			boardYpos = screenHeight/2 - boardScaleHeight/2;

			goalXpos = boardXpos + shiftGoalX + targetX*tileWidth; goalYpos = boardYpos + shiftGoalY + targetY*tileHeight;
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

			heroXprevLogic = heroXcurrentLogic = heroStartX;
			heroYprevLogic = heroYcurrentLogic = heroStartY;

			heroSprite.reset();
			goalSprite.reset();
		}

		@Override
		public void updateAnimated() {
			goalSprite.update();
			goalCurrentBitmap = goalSprite.getCurrentBitmap();

			if (!heroSprite.isLooped()){
				
				int heroCurrentFrame = heroSprite.getFrameNumber();

				heroSprite.update();

				heroXpos = boardXpos + heroXprevLogic*tileWidth + shiftHeroX;
				heroYpos = boardYpos +heroYprevLogic*tileHeight + shiftHeroY;

				switch (heroSprite.getDirection()) {
				case Up:
					heroYpos -= heroCurrentFrame*tileHeightSpriteInterval;
					break;
				case Left:
					heroXpos -= heroCurrentFrame*tileWidthSpriteInterval;
					break;
				case Down:
					heroYpos += heroCurrentFrame*tileHeightSpriteInterval;
					break;
				case Right:
					heroXpos += heroCurrentFrame*tileWidthSpriteInterval;
					break;

				default:
					break;
				}

				heroCurrentBitmap = heroSprite.getCurrentBitmap();
			}
			else{

				heroSprite.reset();

				heroXpos = boardXpos + heroXcurrentLogic*tileWidth;
				heroYpos = boardYpos + heroYcurrentLogic*tileHeight;

				heroXprevLogic = heroXcurrentLogic;
				heroYprevLogic = heroYcurrentLogic;

				heroCurrentBitmap = heroStandBitmap;
			}
			
		}
		
		@Override
		public void updateNonAnimated() {
			
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

			if(mgs.getHeroX() - heroXprevLogic > 0)
				heroSprite.setDirection(heroDirection.Right);
			else if(mgs.getHeroX() - heroXprevLogic < 0)
				heroSprite.setDirection(heroDirection.Left);
			else if(mgs.getHeroY() - heroYprevLogic > 0)
				heroSprite.setDirection(heroDirection.Down);
			else if(mgs.getHeroY() - heroYprevLogic < 0)
				heroSprite.setDirection(heroDirection.Up);
			else
				heroSprite.setDirection(heroDirection.Stand);

			heroXprevLogic = heroXcurrentLogic;
			heroYprevLogic = heroYcurrentLogic;

			heroXcurrentLogic = mgs.getHeroX();
			heroYcurrentLogic = mgs.getHeroY();
		}

		/********** SurfaceView class **********/

		/******************** Nested classes ********************/

	}
}
