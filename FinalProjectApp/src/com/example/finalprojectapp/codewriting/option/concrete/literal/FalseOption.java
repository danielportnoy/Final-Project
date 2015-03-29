package com.example.finalprojectapp.codewriting.option.concrete.literal;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.literal.BoolLiteralNode;

public class FalseOption extends Option{

	@Override
	public boolean isType(Type type) {
		return type == Type.Bool;
	}

	@Override
	public void setButton(final Context context, Button optionButton, final Setter setter) {

		optionButton.setText("False");

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setter.setChildNode(new BoolLiteralNode(false));
				refresh();
			}
		});
	}
}