package com.example.finalprojectapp.codewriting.managment;

import android.support.v4.app.FragmentActivity;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.gamescreen.GameScreenActivity;
import com.example.finalprojectapp.node.Setter;

public class CodeWritingManager {

	private CodeWritingLogicUnit logics;
	private CodeWritingGraphicUnit graphics;

	private GameScreenActivity gameScreenActivity;

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

	public void refresh(){
		logics.getCurrentOptions().clear();
		logics.updateCodeWritingLines();

		if(logics.isEditMode())
			logics.loadEditingOptions();

		if(gameScreenActivity != null){
			if(!LevelManager.getInstance().isCodeLinesValid()){
				gameScreenActivity.getPagerAdapter().setDisableRunningFragment(true);
				gameScreenActivity.removeRunTab();
			}
			else{
				gameScreenActivity.getPagerAdapter().setDisableRunningFragment(false);
				gameScreenActivity.addRunTab();
			}
			gameScreenActivity.getPagerAdapter().notifyDataSetChanged();
		}

		graphics.updateOptions();
		graphics.updateCodeWritingLines();
	}

	public void setGameScreenActivity(FragmentActivity activity) {
		gameScreenActivity = (GameScreenActivity) activity;
	}
}
