package com.example.finalprojectapp.codewriting.option.concrete.operation.assignment;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Scope;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.operators.assignment.SimpleAssignmentNode;

public class SimpleAssignmentOption extends Option{

	private AlertDialog dialog;

	private Type typeOfIdentifiver;
	private String identifierName;

	@Override
	public boolean isType(Type type) {
		return type == Type.Statement || type == Type.ForInit ||  type == Type.ForUpdate;
	}

	@Override
	public void setButton(final Context context, Button optionButton, final Setter setter) {

		optionButton.setText("=");	//TODO

		optionButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) { 

				// Creating and Building the Dialog 
				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				builder.setTitle("Select The Right Side");	// TODO

				// get all identifiers
				List<String> alllIds = Scope.getIdentifiersRecursive(setter.getParent(), setter.getOrder());

				// Strings to Show In Dialog with Radio Buttons
				final String[] items = alllIds.toArray(new String[alllIds.size()]);

				builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						
						identifierName = items[item];
						typeOfIdentifiver = Scope.getTypeByIdentifier(setter.getParent(), setter.getOrder(), identifierName); 
														
						((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
					}
				});

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						setter.setChildNode(new SimpleAssignmentNode(typeOfIdentifiver, identifierName));
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

