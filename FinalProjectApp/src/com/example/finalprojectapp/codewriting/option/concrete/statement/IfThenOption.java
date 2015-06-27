package com.example.finalprojectapp.codewriting.option.concrete.statement;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.statements.IfThenNode;
/**
 * Option that represents a IfThan Node.
 * @author daniel portnoy
 *
 */
public class IfThenOption extends Option {

	@Override
	public boolean isType(Type type) {
		return type == Type.Statement;
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter SETTER) {

		// Set the button text.
		optionButton.setText(Constants.IF_THAN_OPTION_TEXT);

		// Set the button listener.
		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SETTER.setChildNode(new IfThenNode());
				SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
				refresh();
			}
		});
	}

}
