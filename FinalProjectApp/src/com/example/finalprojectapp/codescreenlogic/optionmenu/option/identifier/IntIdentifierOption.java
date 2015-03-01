package com.example.finalprojectapp.codescreenlogic.optionmenu.option.identifier;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.identifier.IntIdentifier;

public class IntIdentifierOption extends Option {

	private String name;

	public IntIdentifierOption(String name) {
		this.name = name;
	}

	@Override
	public boolean isType(Type type) {
		return type == Type.Int;
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter setter) {

		optionButton.setText(name);	//TODO
		
		optionButton.setBackgroundColor(Color.LTGRAY); //TODO

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				setter.setChildNode(new IntIdentifier(name));
				refresh();
			}
		});
	}
}
