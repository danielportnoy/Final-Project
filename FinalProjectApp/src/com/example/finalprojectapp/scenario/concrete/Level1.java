package com.example.finalprojectapp.scenario.concrete;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.DrawGameView;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.codewriting.option.concrete.block.BlockOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.LiteralOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.PlusOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.IfThenOption;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.BoolVarDecOption;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.IntVarDecOption;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.scenario.Scenario;

public class Level1 extends Scenario {

	final int SIZE = 6;	// TODO

	int heroX=0,heroY=0,targetX=5,targetY=5;

	@Override
	public void initiateAvailableOptions() {
		availableOptions.add(new BlockOption());
		availableOptions.add(new IfThenOption());
		availableOptions.add(new LiteralOption());
		availableOptions.add(new BoolVarDecOption());
		availableOptions.add(new IntVarDecOption());
		//availableOptions.add(new ForOption());		// TODO

		availableOptions.add(new PlusOption());


		//custom options	// TODO
		availableOptions.add(new GoRightOption());

	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot(heroX, heroY);
	}

	@Override
	public void drawSnapshot(GameSnapshot gameSnapshot , DrawGameView drawGameView) {


		MyGameSnapshot myGameSnapshot = (MyGameSnapshot)gameSnapshot;
		Level1_DrawGameView level1_DrawGameView = (Level1_DrawGameView) drawGameView;
		
		level1_DrawGameView.a(myGameSnapshot.getHeroX(), myGameSnapshot.getHeroY());
		level1_DrawGameView.invalidate();

	}
	
	@Override
	public DrawGameView getDrawGameViewInstance(Context context , AttributeSet attrs) {
		return new Level1_DrawGameView(context, attrs);
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

	/******************** Nested classes ********************/

}
