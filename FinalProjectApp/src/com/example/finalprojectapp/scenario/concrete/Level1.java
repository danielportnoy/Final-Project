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
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.scenario.MySurfaceView;
import com.example.finalprojectapp.scenario.Scenario;

public class Level1 extends Scenario {

	final int SIZE = 6;	// TODO

	private int heroX=0,heroY=0,targetX=5,targetY=5;

	@Override
	public void initiateAvailableOptions() {
		availableOptions.add(new BlockOption());
		availableOptions.add(new IfThenOption());
		availableOptions.add(new BoolVarDecOption());
		availableOptions.add(new IntVarDecOption());
		availableOptions.add(new ForOption());

		// literal
		//availableOptions.add(new LiteralOption());	// TODO
		availableOptions.add(new NumberOption());
		availableOptions.add(new TrueOption());
		availableOptions.add(new FalseOption());


		availableOptions.add(new PlusOption());

		availableOptions.add(new SimpleAssignmentOption());

		availableOptions.add(new EqualsOption());
		availableOptions.add(new NotEqualsOption());

		//custom options	// TODO
		availableOptions.add(new GoRightOption());
	}

	@Override
	public void reset() {
		heroX=0;
		heroY=0;
	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot(heroX, heroY);
	}

	@Override
	public MySurfaceView generateGameView(Context context) {
		return new SurfaceView_Level1(context);
	}

	/******************** Nested classes ********************/

	/********** snapshot class **********/
	class MyGameSnapshot extends GameSnapshot{

		private int heroX,heroY;

		public MyGameSnapshot(int heroX,int heroY) {
			this.heroX = heroX;
			this.heroY = heroY;
		}

		public int getHeroX() {
			return heroX;
		}

		public int getHeroY() {
			return heroY;
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

			if( heroX < SIZE - 1 )
				heroX++;

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

	class SurfaceView_Level1 extends MySurfaceView {

		private Bitmap heroCurrentBitmap;
		private Bitmap[] heroGoRightSprite;
		private Bitmap heroStandBitmap;
		private final int heroNumOfSprites = 8;
		private int heroXpos, heroYpos;
		private int heroXposLogic, heroYposLogic;
		private int heroCurrentFrame;

		private Bitmap boardBitmap;
		private final int boardNumOfRows = 6,boardNumOfCols = 6;
		private final int boardXstart = 0,boardYstart = 0;
		private int boardXpos, boardYpos;		

		public SurfaceView_Level1(Context context) {
			super(context);

			reset();

			boardBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.level1_background);
			
			heroStandBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_1);

			heroGoRightSprite = new Bitmap[] {
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_1),
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_2),
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_3),
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_4),
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_5),
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_6),
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_7),
					BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_8)
			};
		}

		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);

			canvas.drawColor(Color.WHITE);		// TODO
			canvas.drawBitmap(boardBitmap, boardXpos, boardYpos, null);
			canvas.drawBitmap(heroCurrentBitmap, heroXpos, heroYpos, null);
		}

		@Override
		public void reset() {

			heroXposLogic = 0;
			heroYposLogic = 0;

			heroCurrentFrame = 0;
		}

		@Override
		public void update() {

			int screenWidth = getWidth();
			int screenHeight = getHeight();

			int scaleBy =  (screenWidth > screenHeight) ? screenHeight : screenWidth;

			int XShift = (screenWidth - scaleBy)/2;
			int YShift = (screenHeight - scaleBy)/2;

			int tileHeight = scaleBy/boardNumOfRows;
			int tileWidth = scaleBy/boardNumOfCols;

			int tileWidthSpriteInterval = tileWidth/heroNumOfSprites;
			int tileHeightSpriteInterval = tileHeight/heroNumOfSprites;

			boardXpos = boardXstart + XShift; boardYpos = boardYstart + YShift;

			if (heroCurrentFrame + 1 < heroNumOfSprites){
				
				heroCurrentFrame++;

				heroXpos = (heroXposLogic-1)*tileWidth + heroCurrentFrame*tileWidthSpriteInterval + XShift;

				//(myGameSnapshot.getHeroY()-1)*tileHeight + heroCurrentFrame*tileHeightSpriteInterval;	
				heroYpos = heroYposLogic*tileHeight + YShift; 
				
				heroCurrentBitmap = heroGoRightSprite[heroCurrentFrame];

			}
			else{
				heroXpos = heroXposLogic*tileWidth + XShift;
				heroYpos = heroYposLogic*tileHeight + YShift;
				
				heroCurrentBitmap = heroStandBitmap;
			}
			
			/* scaling bitmaps */	
			boardBitmap = Bitmap.createScaledBitmap(boardBitmap, scaleBy, scaleBy, true);		
			heroCurrentBitmap = Bitmap.createScaledBitmap(heroCurrentBitmap, tileWidth, tileHeight, true);		
			/* scaling bitmaps */
		}

		@Override
		public void loadSnapshot(GameSnapshot gameSnapshot) {

			MyGameSnapshot mgs = (MyGameSnapshot)gameSnapshot;;

			if(mgs.getHeroX() != heroXposLogic || mgs.getHeroY() != heroYposLogic){
				heroXposLogic = mgs.getHeroX();
				heroYposLogic = mgs.getHeroY();
				heroCurrentFrame = 0;
			}
		}
	}

	/******************** Nested classes ********************/

}
