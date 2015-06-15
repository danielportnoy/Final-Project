package com.example.finalprojectapp.codewriting.option.concrete.statement;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.statements.IfThenNode;

public class IfThenOption extends Option {
	
	@Override
	public boolean isType(Type type) {
		return type == Type.Statement;
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter setter) {

		optionButton.setText("if then");	//TODO

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setter.setChildNode(new IfThenNode());
				setter.getParent().reOrderScope(setter.getOrder(), 1);
				refresh();
			}
		});
	}

}
