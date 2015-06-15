package com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.operators.arithmetic.DivisionNode;

public class DivisionOption extends Option{

	@Override
	public boolean isType(Type type) {
		return type == Type.Int;
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter setter) {
		
		optionButton.setText("/");	//TODO

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setter.setChildNode(new DivisionNode());
				setter.getParent().reOrderScope(setter.getOrder(), 1);
				refresh();
			}
		});
	}

}
