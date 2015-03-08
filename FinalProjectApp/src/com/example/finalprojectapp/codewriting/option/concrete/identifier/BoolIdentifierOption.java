package com.example.finalprojectapp.codewriting.option.concrete.identifier;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.identifier.BoolIdentifier;

public class BoolIdentifierOption extends Option {

	private String name;

	public BoolIdentifierOption(String name) {
		this.name = name;
	}

	@Override
	public boolean isType(Type type) {
		return type == Type.Bool;
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter setter) {

		optionButton.setText(name);	//TODO

		//optionButton.setBackgroundColor(Color.LTGRAY); //TODO

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				setter.setChildNode(new BoolIdentifier(name));
				refresh();
			}
		});
	}
}
