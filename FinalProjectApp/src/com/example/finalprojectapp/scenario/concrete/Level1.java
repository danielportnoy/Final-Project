package com.example.finalprojectapp.scenario.concrete;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.coderunning.GameSnapshot;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
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

	final int SIZE = 7;	// TODO

	int heroX=0,heroY=0,targetX=6,targetY=6;

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

	class GoRightNode extends Node{

		@Override
		public List<CodePart> getCodeParts() {

			List<CodePart> res = new ArrayList<CodePart>();

			res.add(new CodePart(false, false, "goRight();", null));

			return res;
		}

		@Override
		public ReturnObject run() {

			heroX++;

			return new ReturnObject();
		}

	}

	class GoRightOption extends Option{

		@Override
		public boolean isType(Type type) {
			return type == Type.Statement;
		}

		@Override
		public void setButton(Context context, Button optionButton,final Setter setter) {

			optionButton.setText("goRight()");	//TODO

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setter.setChildNode(new GoRightNode());
					refresh();
				}
			});
		}

	}
}
