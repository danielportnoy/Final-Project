package com.example.finalprojectapp.codewriting.option.concrete.operation.logical;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.operators.logical.NotNode;

public class NotOption extends Option{

	@Override
	public boolean isType(Type type) {
		return type == Type.Bool;
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter SETTER) {
		
		optionButton.setText("!");	//TODO

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SETTER.setChildNode(new NotNode());
				SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
				refresh();
			}
		});
	}

}