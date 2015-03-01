package com.example.finalprojectapp.codescreenlogic.optionmenu.option.vardec;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectapp.codescreenlogic.Scope;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;
import com.example.finalprojectapp.codescreenlogic.optionmenu.optionsDB.NodeType;
import com.example.finalprojectapp.codescreenlogic.optionmenu.optionsDB.OptionsDB;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.vardec.BoolVarDecNode;

public class BoolVarDecOption extends Option {

	private AlertDialog dialog;

	private String reg = "^[a-zA-Z]*$";

	@Override
	public boolean isType(Type type) {
		List<Type> types = OptionsDB.getByNodeType(NodeType.BoolVarDec);
		return types.contains(type);
	}

	@Override
	public void setButton(final Context context, Button optionButton, final Setter setter) {

		optionButton.setText("bool var dec");	//TODO

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Creating and Building the Dialog 
				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				builder.setTitle("Select The Identifier Name");

				final EditText input = new EditText(context);

				builder.setView(input);

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						String text = input.getText().toString();

						if(text.isEmpty() || !text.matches(reg))
							Toast.makeText(context, "Failed - not a valid identifier", Toast.LENGTH_LONG).show();
						else if(Scope.getIdentifiersRecursive(setter.getParent(), setter.getOrder()).contains(text))
							Toast.makeText(context, "Failed - identifier allready declared", Toast.LENGTH_LONG).show();
						else{
							setter.setChildNode(new BoolVarDecNode(text));
							setter.addToBooleanScope(text);
							refresh();
						}
					}
				});

				builder .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

				dialog = builder.create();
				dialog.show();
			}
		});

	}

}
