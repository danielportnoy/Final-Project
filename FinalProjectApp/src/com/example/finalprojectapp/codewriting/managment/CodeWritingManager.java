package com.example.finalprojectapp.codewriting.managment;

import android.support.v4.app.FragmentActivity;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.gamescreen.GameScreenActivity;
import com.example.finalprojectapp.node.Setter;

/**
 * Manages the code writing logics and graphics.
 * @author daniel portnoy
 *
 */
public class CodeWritingManager {

	private CodeWritingLogicUnit logics;
	private CodeWritingGraphicUnit graphics;

	private GameScreenActivity gameScreenActivity;

	/**
	 * @param logics - CodeWritingLogicUnit object.
	 * @param graphics - CodeWritingGraphicUnit object.
	 */
	public CodeWritingManager(CodeWritingLogicUnit logics, CodeWritingGraphicUnit graphics) { 
		this.logics = logics;
		this.graphics = graphics;	
	}

	public CodeWritingLogicUnit getLogics() {
		return logics;
	}

	public CodeWritingGraphicUnit getGraphics() {
		return graphics;
	}

	public void SetterClick(Setter setter) {

		logics.loadOptionsForSetter(setter);

		graphics.getOptionsAdapter().setCurrentSetter(setter);
		graphics.updateOptions();
	}

	/**
	 * Displays a code lines on the screen.
	 * @param snapshot
	 */
	public void refresh(){


		//Update the current core lines displayed.
		logics.getCurrentOptions().clear();
		logics.updateCodeWritingLines();

		//Set to display the editing options
		if(logics.isEditMode())
			logics.loadEditingOptions();

		// Handle the SwipeMode
		if(gameScreenActivity != null){
			if(!LevelManager.getInstance().isCodeLinesValid()){

				// remove the run tab if code lines aren't valid.
				gameScreenActivity.getPagerAdapter().setDisableRunningFragment(true);
				gameScreenActivity.removeRunTab();
			}
			else{
				// add the run tab if code lines are valid.
				gameScreenActivity.getPagerAdapter().setDisableRunningFragment(false);
				gameScreenActivity.addRunTab();
			}
			gameScreenActivity.getPagerAdapter().notifyDataSetChanged();
		}

		/*
		 * Update the current options displayed.
		 * Update screen representation.
		 */
		graphics.updateOptions();
		graphics.updateCodeWritingLines();
	}

	public void setGameScreenActivity(FragmentActivity activity) {
		gameScreenActivity = (GameScreenActivity) activity;
	}
}
