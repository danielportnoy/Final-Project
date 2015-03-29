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
	public void setButton(final Context context, Button optionButton, final Setter setter) {

		optionButton.setText("Number");

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) { 

				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				builder.setTitle("Select The Number");
				LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
				View view = inflater.inflate(R.layout.pick_number_dialog, null);

				final NumberPicker numberPicker = (NumberPicker)view.findViewById(R.id.numberPicker_intLiteral);
				numberPicker.setMinValue(0);
				numberPicker.setMaxValue(9999);
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
