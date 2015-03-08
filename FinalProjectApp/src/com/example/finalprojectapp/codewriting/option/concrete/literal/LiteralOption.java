package com.example.finalprojectapp.codewriting.option.concrete.literal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.literal.BoolLiteralNode;
import com.example.finalprojectapp.node.concrete.literal.IntLiteralNode;

public class LiteralOption extends Option{

	private AlertDialog dialog;

	private boolean booleanRes;

	@Override
	public boolean isType(Type type) {
		return type == Type.Int || type == Type.Bool;
	}

	@Override
	public void setButton(final Context context, Button optionButton, final Setter setter) {

		optionButton.setText("literal");

		if(setter.possibleTypes().contains(Type.Bool))
			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					// Strings to Show In Dialog with Radio Buttons
					final CharSequence[] items = {"True","False"};

					// Creating and Building the Dialog 
					AlertDialog.Builder builder = new AlertDialog.Builder(context);

					builder.setTitle("Select The Boolean Literal");

					builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {

							switch(item){
							case 0:
								booleanRes = true;
								break;
							case 1:
								booleanRes = false;
							default:
								break;
							}

							((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
						}
					});

					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

							setter.setChildNode(new BoolLiteralNode(booleanRes));
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

		else if(setter.possibleTypes().contains(Type.Int))
			optionButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) { 

					AlertDialog.Builder builder = new AlertDialog.Builder(context);

					builder.setTitle("Select The Integer Literal");
					LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
					View view = inflater.inflate(R.layout.int_literal_dialog, null);

					final NumberPicker numberPicker = (NumberPicker)view.findViewById(R.id.numberPicker_intLiteral);
					numberPicker.setMinValue(0);
					numberPicker.setMaxValue(999);
					numberPicker.setValue(0);

					RadioButton radioButtonPositive =(RadioButton) view.findViewById(R.id.radioButton_positive);  
					radioButtonPositive.setChecked(true);
					final RadioButton radioButtonNegative =(RadioButton) view.findViewById(R.id.radioButton_negative); 

					builder.setView(view);

					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int id) {

							if(radioButtonNegative.isChecked())
								setter.setChildNode(new IntLiteralNode(-numberPicker.getValue()));
							else
								setter.setChildNode(new IntLiteralNode(numberPicker.getValue()));
							
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
				}
			});

	}

}
