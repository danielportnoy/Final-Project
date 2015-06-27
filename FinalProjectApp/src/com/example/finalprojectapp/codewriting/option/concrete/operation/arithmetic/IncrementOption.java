package com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Scope;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.operators.arithmetic.IncrementNode;

/**
 * Option that represents a Increment Node.
 * @author daniel portnoy
 *
 */
public class IncrementOption extends Option{

	private AlertDialog dialog;

	private String identifierName;

	@Override
	public boolean isType(Type type) {
		return type == Type.Int || type == Type.Statement || type == Type.ForUpdate;
	}

	@Override
	public void setButton(final Context CONTEXT, Button optionButton, final Setter SETTER) {

		// Set the button text.
		optionButton.setText(Constants.INCREMENT_OPTION_TEXT);

		optionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 

				// Creating and Building the Dialog 
				AlertDialog.Builder builder = new AlertDialog.Builder(CONTEXT);

				// Set the dialog view.
				builder.setTitle("Select The Left Side");

				// get all identifiers
				List<String> alllIds = Scope.getPrevIntegerIdentifiersRecursive(SETTER.getParent(), SETTER.getOrder());

				// Strings to Show In Dialog with Radio Buttons
				final String[] ITEMS = alllIds.toArray(new String[alllIds.size()]);

				builder.setSingleChoiceItems(ITEMS, -1, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						// get the picked identifier name.
						identifierName = ITEMS[item];

						((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
					}
				});

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						
						/*
						 * Create the decrement node.		
						 * use the identifierName to know what identifier to pick.
						 */
						SETTER.setChildNode(new IncrementNode(identifierName));
						SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
						refresh();
					}
				});

				builder .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

				dialog = builder.create();
				dialog.show();

				dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
			}
		});
	}

}
