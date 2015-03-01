package com.example.finalprojectapp.codescreenlogic.optionmenu.option;

import android.content.Context;
import android.widget.Button;

import com.example.finalprojectapp.codescreenlogic.CodeScreenGraphicsUnit;
import com.example.finalprojectapp.codescreenlogic.CodeScreenManager;

import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.Setter;

public abstract class Option {
	
	public abstract boolean isType(Type type);
	
	public abstract void setButton(Context context, Button optionButton, Setter setter);
	
	public void refresh(){
		CodeScreenManager.getInstance().getOptionMenu().clearMenu();
		CodeScreenManager.getInstance().getCodeScreen().updateCodeLines();
		CodeScreenGraphicsUnit.getInstance().updateOptionsMenu();
		CodeScreenGraphicsUnit.getInstance().updateCodeLines();
	}
}
