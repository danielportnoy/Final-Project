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
import com.example.finalprojectapp.utilities.Logic_Utils;

public abstract class PrintScenarioArchetype extends Scenario {

	private String currentTextToPrint = null;

	@Override
	public void reset() {
		currentTextToPrint = null;
	}

	@Override
	public GameSnapshot takeSnapshot() {
		return new MyGameSnapshot(currentTextToPrint);
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
		private String subHeaderText;
		private String winText;

		public MyConfiguration(MyConfiguration other) {
			this.headerText = other.headerText;
			this.subHeaderText = other.subHeaderText;
			this.winText = other.winText;
		}

		public MyConfiguration(String headerText, String subHeaderText, String winText) {
			this.headerText = headerText;
			this.subHeaderText = subHeaderText;
			this.winText = winText;
		}

		public String getHeaderText() {
			return headerText;
		}

		public String getSubHeaderText() {
			return subHeaderText;
		}

		public String getWinText() {
			return winText;
		}

		@Override
		public Configuration copy() {
			return new MyConfiguration(headerText,subHeaderText, winText);
		}

	}
	/********** config class **********/


	/********** snapshot class **********/
	protected class MyGameSnapshot extends GameSnapshot{

		private String textToPrint;

		public MyGameSnapshot(MyGameSnapshot other) {
			this.textToPrint = other.textToPrint;
		}

		public MyGameSnapshot(String textToPrint) {
			this.textToPrint = textToPrint;
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
		public boolean addChild(Node child, int order) {
			return false;
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
			if(toPrint != null)
				return toPrint;
			else
				return null;
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

			final static int ORDER = 0;

			public ToPrintSetter(Node parent) {
				super(/*"< expr >", */true, parent, ORDER);	//TODO
			}

			@Override
			public void setChildNode(Node toSet) {
				toPrint = toSet;
				toSet.setOrder(ORDER);
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
		public void setButton(Context context, Button optionButton,final Setter SETTER) {

			optionButton.setText("print");	//TODO

			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SETTER.setChildNode(new PrintNode());
					SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
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

		private int subHeaderTextXpos, subHeaderTextYpos;
		private String currentSubHeaderToPrint = ((MyConfiguration)getCurrentConfig()).getSubHeaderText();
		private Paint subHeaderTextPaint;

		private static final double SCALE_WIDTH_COEFFICIENT = 0.9;
		private static final double SCALE_HEIGHT_COEFFICIENT = 0.8;

		private static final double HEADER_HEIGHT_RELATIVE = 1.0/4.0, SUB_HEADER_HEIGHT_RELATIVE = 1.0/4.0 , TEXT_HEIGHT_RELATIVE = 1.0/4.0;
		private static final double HEADER_HEIGHT_RELATIVE_POS = 1.0/4.0, SUB_HEADER_HEIGHT_RELATIVE_POS = 7.0/16.0 , TEXT_HEIGHT_RELATIVE_POS = 3.0/4.0;

		private int midWidth;
		private int screenRectWidth;

		private int textHeight;
		private int textRectHeight;

		public SurfaceView_Board(Context context, int fps, boolean isAnimation) {
			super(context, fps, isAnimation);

			reset();
		}

		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);

			render();

			canvas.drawColor(Color.WHITE);		// TODO

			canvas.drawText(currentHeaderToPrint, headerTextXpos, headerTextYpos, headerTextPaint);

			canvas.drawText(currentSubHeaderToPrint, subHeaderTextXpos, subHeaderTextYpos, subHeaderTextPaint);


			if(currentTextToPrint != null)
				canvas.drawText(currentTextToPrint, textXpos, textYpos, textPaint);
		}

		@Override
		public void reset() {
			currentTextToPrint = null;
		}

		@Override
		public void preCalculation() {

			int screenWidth = getWidth();
			int screenHeight = getHeight();

			midWidth = screenWidth / 2 ;
			screenRectWidth = (int)(screenWidth * SCALE_WIDTH_COEFFICIENT);

			int headerRectHeight = (int)((screenHeight * HEADER_HEIGHT_RELATIVE) * SCALE_HEIGHT_COEFFICIENT);

			int subHeaderRectHeight = (int)((screenHeight * SUB_HEADER_HEIGHT_RELATIVE) * SCALE_HEIGHT_COEFFICIENT);

			textRectHeight = (int)((screenHeight * TEXT_HEIGHT_RELATIVE) * SCALE_HEIGHT_COEFFICIENT);

			int headerHeight = (int) (screenHeight * HEADER_HEIGHT_RELATIVE_POS);
			int subHeaderHeight = (int) (screenHeight * SUB_HEADER_HEIGHT_RELATIVE_POS);

			textHeight = (int) (screenHeight * TEXT_HEIGHT_RELATIVE_POS);

			textPaint = new Paint();
			textPaint.setColor(Color.RED);

			headerTextPaint = new Paint();
			headerTextPaint.setTextSize(Logic_Utils.determineMaxTextSize(currentHeaderToPrint, screenRectWidth, headerRectHeight));

			Rect headerBounds = new Rect();

			headerTextPaint.getTextBounds(currentHeaderToPrint, 0, currentHeaderToPrint.length(), headerBounds);

			subHeaderTextPaint = new Paint();
			subHeaderTextPaint.setTextSize(Logic_Utils.determineMaxTextSize(currentSubHeaderToPrint, screenRectWidth, subHeaderRectHeight));

			Rect subHeaderBounds = new Rect();

			subHeaderTextPaint.getTextBounds(currentSubHeaderToPrint, 0, currentSubHeaderToPrint.length(), subHeaderBounds);

			headerTextXpos = midWidth - (headerBounds.width() / 2);
			headerTextYpos = headerHeight;

			subHeaderTextXpos = midWidth - (subHeaderBounds.width() / 2);
			subHeaderTextYpos = subHeaderHeight;
		}

		@Override
		public void updateAnimated() {

			if(currentTextToPrint != null){

				Rect bounds = new Rect();
				textPaint.getTextBounds(currentTextToPrint, 0, currentTextToPrint.length(), bounds);
				textPaint.setTextSize(Logic_Utils.determineMaxTextSize(currentTextToPrint, screenRectWidth, textRectHeight));

				textXpos = midWidth - (bounds.width() / 2);
				textYpos = textHeight /* - (bounds.height() / 2)*/;
			}
		}

		@Override
		public void updateNonAnimated() {

			if(currentTextToPrint != null){

				Rect bounds = new Rect();
				textPaint.getTextBounds(currentTextToPrint, 0, currentTextToPrint.length(), bounds);
				textPaint.setTextSize(Logic_Utils.determineMaxTextSize(currentTextToPrint, screenRectWidth, textRectHeight));

				textXpos = midWidth - (bounds.width() / 2);
				textYpos = textHeight /* - (bounds.height() / 2)*/;
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
	}
	/********** SurfaceView class **********/


	/******************** Nested classes ********************/

}
