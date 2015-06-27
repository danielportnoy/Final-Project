package com.example.finalprojectapp.codewriting.option.concrete.operation.relational;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.operators.relational.LessThanNode;

/**
 * Option that represents a LessThan Node.
 * @author daniel portnoy
 *
 */
public class LessThanOption extends Option{

	@Override
	public boolean isType(Type type) {
		return type == Type.Bool;
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter SETTER) {

		// Set the button text.
		optionButton.setText(Constants.LESS_THAN_OPTION_TEXT);

		// Set the button listener.
		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SETTER.setChildNode(new LessThanNode());
				SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
				refresh();
			}
		});
	}

}
