package com.example.finalprojectapp.scenario.archetype;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.scenario.configuration.Configuration;

public abstract class PrintScenarioArchetype extends Scenario {

	private String currentTextToPrint = null;

	@Override
	public void reset() {
		currentTextToPrint = null;
	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot();
	}

	@Override
	public MySurfaceView generateGameView(Context context, int fps,boolean isAnimation) {
		return new SurfaceView_Board(context, fps, isAnimation);
	}

	@Override
	public boolean checkWin(Configuration config, GameSnapshot gameSnapshot) {
		MyConfiguration currentConfig = (MyConfiguration) config;
		MyGameSnapshot mgs = (MyGameSnapshot)gameSnapshot;

		if(mgs.textToPrint == null)
			return false;

		return mgs.textToPrint.equals(currentConfig.winText);
	}


	/******************** Nested classes ********************/

	/********** exception class **********/

	/********** exception class **********/


	/********** config class **********/
	protected class MyConfiguration extends Configuration{

		private String headerText;
		private String winText;

		public MyConfiguration(String headerText, String winText) {
			this.headerText = headerText;
			this.winText = winText;
		}

		public String getHeaderText() {
			return headerText;
		}
		
		public void setHeaderText(String headerText) {
			this.headerText = headerText;
		}

		public String getWinText() {
			return winText;
		}
		
		public void setWinText(String winText) {
			this.winText = winText;
		}

		@Override
		public Configuration copy() {
			return new MyConfiguration(headerText, winText);
		}

	}
	/********** config class **********/

	/********** snapshot class **********/
	protected class MyGameSnapshot extends GameSnapshot{

		private String textToPrint;

		public MyGameSnapshot(MyGameSnapshot other) {
			this.textToPrint = other.textToPrint;
		}

		public MyGameSnapshot() {
			this.textToPrint = currentTextToPrint;
		}

		@Override
		public GameSnapshot copy(GameSnapshot other) {
			return new MyGameSnapshot((MyGameSnapshot) other);
		}

		public String getTextToPrint() {
			return textToPrint;
		}

		@Override
		public boolean equals(GameSnapshot other) {

			MyGameSnapshot other_mgs = (MyGameSnapshot)other;
			return textToPrint.equals(other_mgs.getTextToPrint());
		}
	}
	/********** snapshot class **********/


	/********** special nodes class's **********/
	protected class PrintNode extends Node{

		private Node toPrint;

		public PrintNode() {
			setType(Type.Statement);
		}

		@Override
		public List<CodeWritingPart> getCodeWritingParts() {

			List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

			res.add(new CodeWritingPart(false, false, "print( ", null, this));

			if(toPrint == null)
				res.add(new CodeWritingPart(false, false, null, new ToPrintSetter(this), this));
			else
				res.addAll(toPrint.getCodeWritingParts());

			res.add(new CodeWritingPart(false, false, " );", null, this));

			return res;
		}

		@Override
		public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

			isHighlighted = target.equals(this) || isHighlighted;
			List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

			res.add(new CodeRunningPart(false, false,isHighlighted, "print( "));
			res.addAll(toPrint.getCodeRunningParts(target, isHighlighted));
			res.add(new CodeRunningPart(false, false,isHighlighted, " );"));

			return res;
		}

		@Override
		public ReturnObject run() throws MyException {

			LevelManager.getInstance().takeSnapshot(this);

			try {
				if(toPrint.getType() == Type.Bool)
					currentTextToPrint = toPrint.run().getBoolValue().toString();
				else if(toPrint.getType() == Type.Int)
					currentTextToPrint = toPrint.run().getIntValue().toString();
			} 
			catch (MyException e) {
				throw e;
			}

			return new ReturnObject();
		}

		@Override
		public Node getFirstNode() {
			return toPrint;
		}

		@Override
		public Set<String> getDeclaredIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			if(toPrint!=null)
				res.addAll(toPrint.getDeclaredIdentifiers());
			return res;
		}

		@Override
		public Set<String> getUsedIdentifiers() {

			HashSet<String> res = new HashSet<String>();
			if(toPrint!=null)
				res.addAll(toPrint.getUsedIdentifiers());
			return res;
		}

		@Override
		public boolean DeleteChildNode(Node childNode) {

			Set<String> used = new HashSet<String>();

			if(childNode.equals(toPrint) && toPrint != null)
				used.addAll(toPrint.getUsedIdentifiers());

			Set<String> intersection = new HashSet<String>(used);
			intersection.retainAll(childNode.getDeclaredIdentifiers());

			if(intersection.isEmpty()){
				if(childNode.equals(toPrint)){
					removeFromScope(toPrint);
					toPrint = null;
				}

				return true;
			}
			else
				return false;
		}

		@Override
		public List<Node> getChildNodes() {

			List<Node> res = new ArrayList<Node>();

			res.add(toPrint);

			return res;
		}

		class ToPrintSetter extends Setter{

			final static int order = 0;

			public ToPrintSetter(Node parent) {
				super(/*"< expr >", */true, parent, order);	//TODO
			}

			@Override
			public void setChildNode(Node toSet) {
				toPrint = toSet;
				toSet.setOrder(order);
				toSet.setParent(getParent());
			}

			@Override
			public List<Type> possibleTypes() {

				List<Type> possibilities = new ArrayList<Type>();
				possibilities.add(Type.Bool);
				possibilities.add(Type.Int);

				return possibilities;
			}	
		}
	}
	/********** special nodes class's **********/


	/********** special option class's **********/
	protected class PrintOption extends Option {

		public PrintOption() {}

		@Override
		public boolean isType(Type type) {
			return type == Type.Statement;
		}

		@Override
		public void setButton(Context context, Button optionButton,final Setter setter) {

			optionButton.setText("print");	//TODO

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setter.setChildNode(new PrintNode());
					refresh();
				}
			});
		}
	}
	/********** special option class's **********/


	/********** SurfaceView class **********/
	protected class SurfaceView_Board extends MySurfaceView {

		private int textXpos, textYpos;
		private String currentTextToPrint = null;
		private Paint textPaint;

		private int headerTextXpos, headerTextYpos;
		private String currentHeaderToPrint = ((MyConfiguration)getCurrentConfig()).getHeaderText();
		private Paint headerTextPaint;

		private int screenWidth ,screenHeight;

		public SurfaceView_Board(Context context, int fps, boolean isAnimation) {
			super(context, fps, isAnimation);

			reset();
		}

		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);

			canvas.drawColor(Color.WHITE);		// TODO

			canvas.drawText(currentHeaderToPrint, headerTextXpos, headerTextYpos, headerTextPaint);

			if(currentTextToPrint != null)
				canvas.drawText(currentTextToPrint, textXpos, textYpos, textPaint);
		}

		@Override
		public void reset() {
			textXpos = 0;
			textYpos = 0;

			currentTextToPrint = null;
		}

		@Override
		public void preCalculation() {

			screenWidth = getWidth();
			screenHeight = getHeight();

			textPaint = new Paint();
			textPaint.setColor(Color.RED);

			headerTextPaint = new Paint();
			headerTextPaint.setTextSize(determineMaxTextSize(currentHeaderToPrint, screenWidth, (screenHeight/4) ));

			Rect bounds = new Rect();
			headerTextPaint.getTextBounds(currentHeaderToPrint, 0, currentHeaderToPrint.length(), bounds);

			headerTextXpos = (screenWidth / 2) - (bounds.width() / 2);
			headerTextYpos = (screenHeight / 4) /*- (bounds.height() / 2)*/;
		}

		@Override
		public void updateAnimated() {

			if(currentTextToPrint == null){
				textXpos = (screenWidth / 2);
				textYpos = (screenHeight / 2);
			}
			else{

				Rect bounds = new Rect();
				textPaint.getTextBounds(currentTextToPrint, 0, currentTextToPrint.length(), bounds);
				textPaint.setTextSize(determineMaxTextSize(currentTextToPrint, screenWidth, (screenHeight/4) ));

				textXpos = (screenWidth / 2) - (bounds.width() / 2);
				textYpos = 3*(screenHeight / 4)/* - (bounds.height() / 2)*/;
			}
		}

		@Override
		public void updateNonAnimated() {

			if(currentTextToPrint == null){
				textXpos = (screenWidth / 2);
				textYpos = (screenHeight / 2);
			}
			else{

				Rect bounds = new Rect();
				textPaint.getTextBounds(currentTextToPrint, 0, currentTextToPrint.length(), bounds);
				textPaint.setTextSize(determineMaxTextSize(currentTextToPrint, screenWidth, (screenHeight/4) ));

				textXpos = (screenWidth / 2) - (bounds.width() / 2);
				textYpos = 3*(screenHeight / 4)/* - (bounds.height() / 2)*/;
			}

		}

		@Override
		public void render() {
			// TODO Auto-generated method stub

		}

		@Override
		public void loadSnapshot(GameSnapshot gameSnapshot) {
			MyGameSnapshot mgs = (MyGameSnapshot)gameSnapshot;

			currentTextToPrint = mgs.getTextToPrint();
		}

		@Override
		public boolean isStillAnimating() {
			// TODO Auto-generated method stub
			return false;
		}

		private int determineMaxTextSize(String str, float maxWidth, float maxHeight)
		{
			int size = 0; 

			Paint paint = new Paint();
			paint.setTextSize(size);

			Rect bounds = new Rect();

			paint.getTextBounds(str, 0, str.length(), bounds);		        

			while(bounds.width() < maxWidth && bounds.height() < maxHeight){
				paint.setTextSize(++ size);
				paint.getTextBounds(str, 0, str.length(), bounds);
			} 

			return size - 5;
		} //End getMaxTextSize()
	}
	/********** SurfaceView class **********/

	/******************** Nested classes ********************/

}
