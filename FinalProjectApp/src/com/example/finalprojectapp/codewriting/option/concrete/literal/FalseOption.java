package com.example.finalprojectapp.codewriting.option.concrete.literal;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.literal.BoolLiteralNode;

/**
 * Option that represents a False Node.
 * @author daniel portnoy
 *
 */
public class FalseOption extends Option{

	@Override
	public boolean isType(Type type) {
		return type == Type.Bool;
	}

	@Override
	public void setButton(final Context CONTEXT, Button optionButton, final Setter SETTER) {

		// Set the button text.
		optionButton.setText(Constants.FALSE_OPTION_TEXT);

		// Set the button listener.
		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SETTER.setChildNode(new BoolLiteralNode(false));
				SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
				refresh();
			}
		});
	}
}