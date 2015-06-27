package com.example.finalprojectapp.codewriting.option.concrete.operation.assignment;

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
import com.example.finalprojectapp.node.concrete.operators.assignment.SimpleAssignmentNode;

/**
 * Option that represents a Simple Assignment Node.
 * @author daniel portnoy
 *
 */
public class SimpleAssignmentOption extends Option{

	private AlertDialog dialog;

	private Type typeOfIdentifiver;
	private String identifierName;

	@Override
	public boolean isType(Type type) {
		return type == Type.Statement || type == Type.ForInit ||  type == Type.ForUpdate;
	}

	@Override
	public void setButton(final Context CONTEXT, Button optionButton, final Setter SETTER) {

		// Set the button text.
		optionButton.setText(Constants.ASSIGNMENT_OPTION_TEXT);

		// Set the button listener.
		optionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 

				// Creating and Building the Dialog 
				AlertDialog.Builder builder = new AlertDialog.Builder(CONTEXT);

				// Set the dialog view.
				builder.setTitle("Select The Left Side");

				// get all identifiers
				List<String> alllIds = Scope.getPrevIdentifiers(SETTER.getParent(), SETTER.getOrder());

				// Strings to Show In Dialog with Radio Buttons
				final String[] ITEMS = alllIds.toArray(new String[alllIds.size()]);

				builder.setSingleChoiceItems(ITEMS, -1, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {

						// get the picked identifier name.
						identifierName = ITEMS[item];
						typeOfIdentifiver = Scope.getTypeByIdentifier(SETTER.getParent(), SETTER.getOrder(), identifierName); 

						((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
					}
				});

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					/*
					 * Create the simple assignment node.		
					 * use the identifierName to know what identifier to pick.
					 */
					public void onClick(DialogInterface dialog, int id) {
						SETTER.setChildNode(new SimpleAssignmentNode(typeOfIdentifiver, identifierName));
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

