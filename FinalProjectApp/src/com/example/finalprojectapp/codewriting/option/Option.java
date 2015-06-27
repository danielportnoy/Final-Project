package com.example.finalprojectapp.codewriting.option;

import android.content.Context;
import android.widget.Button;

import com.example.finalprojectapp.LevelManager;

import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

/**
 * Hold the logical and graphical data of the option.
 * @author daniel portnoy
 *
 */
public abstract class Option {

	/**
	 * Comparing the option Type to another Type object. 
	 * @param type - Type for comparison.
	 * @return if they are equal.
	 */
	public abstract boolean isType(Type type);

	/**
	 * Preparing the Option button for being displayed.
	 * @param context
	 * @param optionButton - the Button.
	 * @param setter - the parent Node setter object.
	 */
	public abstract void setButton(Context context, Button optionButton, Setter setter);

	/**
	 * Updates the code writing screen.
	 */
	public void refresh(){
		LevelManager.getInstance().refrashWritingScreen();
	}
}
