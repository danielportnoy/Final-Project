package com.example.finalprojectapp.codewriting.option;

import android.content.Context;
import android.widget.Button;

import com.example.finalprojectapp.LevelManager;

import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public abstract class Option {

	public abstract boolean isType(Type type);

	public abstract void setButton(Context context, Button optionButton, Setter setter);

	public void refresh(){
		LevelManager.getInstance().refrashWritingScreen();
	}
}
