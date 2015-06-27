package com.example.finalprojectapp.codewriting.option.concrete.vardec;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Scope;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.vardec.IntVarDecNode;

/**
 * Option that represents a IntVarDec Node.
 * @author daniel portnoy
 *
 */
public class IntVarDecOption extends Option {

	private AlertDialog dialog;

	private String reg = "^[a-zA-Z]*$";

	@Override
	public boolean isType(Type type) {
		return type == Type.Statement || type == Type.ForInit;
	}

	@Override
	public void setButton(final Context CONTEXT, Button optionButton, final Setter SETTER) {

		// Set the button text.
		optionButton.setText(Constants.INT_VAR_DEC_OPTION_TEXT);

		// Set the button listener.
		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Creating and Building the Dialog 
				AlertDialog.Builder builder = new AlertDialog.Builder(CONTEXT);

				// Set the dialog view.
				builder.setTitle("Select The Identifier Name");

				final EditText INPUT = new EditText(CONTEXT);

				builder.setView(INPUT);

				//TODO
				/*LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
				View view = inflater.inflate(R.layout.pick_identifier_dialog, null);

				final EditText input = (EditText)view.findViewById(R.id.editText_Identifier);

				builder.setView(view);*/

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						String text = INPUT.getText().toString();

						if(text.isEmpty() || !text.matches(reg)){
							// Check if the text was entered and if the characters are valid.
							Toast.makeText(CONTEXT, "Failed - not a valid identifier.", Toast.LENGTH_LONG).show();
						}
						else if(Scope.getPrevIdentifiers(SETTER.getParent(), SETTER.getOrder()).contains(text)){
							// Check if there is already defined identifier.
							Toast.makeText(CONTEXT, "Failed - identifier allready declared.", Toast.LENGTH_LONG).show();
						}
						else if(Scope.getNextIdentifiers(SETTER.getParent(), SETTER.getOrder()).contains(text)){
							// Check if there is defined identifier after.
							Toast.makeText(CONTEXT, "Failed - identifier declared afterwards.", Toast.LENGTH_LONG).show();
						}
						else{
							
							// Create the BoolVarDecNode node.
							SETTER.setChildNode(new IntVarDecNode(text));
							SETTER.getParent().reOrderScope(SETTER.getOrder(), 1);
							SETTER.addToIntegerScope(text);
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
