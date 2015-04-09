package com.example.finalprojectapp.codewriting.option.concrete.vardec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Scope;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.vardec.BoolVarDecNode;

public class BoolVarDecOption extends Option {

	private AlertDialog dialog;

	private String reg = "^[a-zA-Z]*$";

	@Override
	public boolean isType(Type type) {
		return type == Type.Statement || type == Type.ForInit;
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
				
				//TODO
				/*LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
				View view = inflater.inflate(R.layout.pick_identifier_dialog, null);

				final EditText input = (EditText)view.findViewById(R.id.editText_Identifier);

				builder.setView(view);*/

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
