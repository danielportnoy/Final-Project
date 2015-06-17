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
import com.example.finalprojectapp.node.concrete.literal.IntLiteralNode;

public class NumberOption extends Option{

	private AlertDialog dialog;

	@Override
	public boolean isType(Type type) {
		return type == Type.Int;
	}

	@Override
	public void setButton(final Context CONTEXT, Button optionButton, final Setter SETTER) {

		optionButton.setText("Number");

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { 

				AlertDialog.Builder builder = new AlertDialog.Builder(CONTEXT);

				builder.setTitle("Select The Number");
				LayoutInflater inflater = (LayoutInflater) CONTEXT.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
				View view = inflater.inflate(R.layout.pick_number_dialog, null);

				final NumberPicker NUMBER_PICKER = (NumberPicker)view.findViewById(R.id.numberPicker_intLiteral);
				NUMBER_PICKER.setMinValue(0);
				NUMBER_PICKER.setMaxValue(9999);
				NUMBER_PICKER.setValue(0);

				RadioButton radioButtonPositive =(RadioButton) view.findViewById(R.id.radioButton_positive);  
				radioButtonPositive.setChecked(true);
				final RadioButton RADIO_BUTTON_NEGATIVE =(RadioButton) view.findViewById(R.id.radioButton_negative); 

				builder.setView(view);

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {

						if(RADIO_BUTTON_NEGATIVE.isChecked())
							SETTER.setChildNode(new IntLiteralNode(- NUMBER_PICKER.getValue()));
						else
							SETTER.setChildNode(new IntLiteralNode(NUMBER_PICKER.getValue()));
						
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
			}
		});



	}

}
