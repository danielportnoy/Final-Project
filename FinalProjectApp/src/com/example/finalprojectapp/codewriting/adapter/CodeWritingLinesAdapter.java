package com.example.finalprojectapp.codewriting.adapter;

import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingLine;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.utilities.Android_Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CodeWritingLinesAdapter extends ArrayAdapter<CodeWritingLine>{

	private Context context = null;

	public CodeWritingLinesAdapter(Context context, int resource, List<CodeWritingLine> codeWritingLines) {
		super(context, resource, codeWritingLines);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout codeLineLinearLayout = null;
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.code_item, null);
		}
		else
			view = convertView;

		codeLineLinearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_CodeItem);
		codeLineLinearLayout.removeAllViews();

		CodeWritingLine item = getItem(position);

		if(item!=null)
			handleCodeWritingLine(item , codeLineLinearLayout);

		return view;

	}

	private void handleCodeWritingLine(CodeWritingLine item, LinearLayout codeLineLinearLayout) {

		List<CodeWritingPart> codeWritingParts = item.getCodeWritingParts();

		for (final CodeWritingPart codeWritingPart : codeWritingParts) {

			if(codeWritingPart.getSetter()!=null)
				handleSetter(codeLineLinearLayout, codeWritingPart.getSetter(), codeWritingPart);
			else if(codeWritingPart.getText()!=null)
				handleText(codeLineLinearLayout, codeWritingPart.getText(), codeWritingPart);
			else if(codeWritingPart.isTab())
				handleTab(codeLineLinearLayout);
		}

	}

	private void handleText(LinearLayout codeLineLinearLayout, final String textString, final CodeWritingPart codeWritingPart) {
		TextView text = new TextView(context);

		Android_Utils.setLayoutParams(text , context);

		text.setText(textString);
		text.setTextAppearance(context, android.R.style.TextAppearance_Small);

		if(codeWritingPart.isEditable() && LevelManager.getInstance().isEditMode())
			text.setBackgroundColor(Color.YELLOW);

		/***** Testing *****/

		text.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				LevelManager.getInstance().setEditMode(true);
				LevelManager.getInstance().setEditable(codeWritingPart.getMakerNode());
				LevelManager.getInstance().refrashWritingScreen();
				return false;
			}
		});

		/***** Testing *****/

		codeLineLinearLayout.addView(text);
	}

	private void handleTab(LinearLayout codeLineLinearLayout) {
		TextView tab = new TextView(context);

		Android_Utils.setLayoutParams(tab , context);

		tab.setText("      ");
		tab.setTextAppearance(context, android.R.style.TextAppearance_Small);

		codeLineLinearLayout.addView(tab);
	}

	private void handleSetter(LinearLayout codeLineLinearLayout,final Setter setter, CodeWritingPart codeWritingPart) {
		Button button = new Button(context);

		//Android_Utils.setLayoutParams(button , context);	// TODO

		Android_Utils.setSetterLayoutParams(button , context);

		/*
		android:text="Button"
		android:textColor="#FFFFFF"
		android:textSize="30sp"

		android:layout_width="270dp"
		android:layout_height="60dp"
		android:background="@drawable/buttonshape"
		android:shadowColor="#A8A8A8"
		android:shadowDx="0"
		android:shadowDy="0"
		android:shadowRadius="5"
		 */

		if(setter.isMandatory()){
			if(codeWritingPart.isEditable() && LevelManager.getInstance().isEditMode())
				button.setBackgroundResource(R.drawable.mandatory_button_highlighted);
			else
				button.setBackgroundResource(R.drawable.mandatory_button);
		}
		else if(!setter.isMandatory()){
			if(codeWritingPart.isEditable() && LevelManager.getInstance().isEditMode())
				button.setBackgroundResource(R.drawable.not_mandatory_button_highlighted);
			else
				button.setBackgroundResource(R.drawable.not_mandatory_button);
		}

		button.setText(setter.getText());
		button.setTextAppearance(context, android.R.style.TextAppearance_Small);


		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				LevelManager.getInstance().setEditMode(false);
				LevelManager.getInstance().setEditable(null);
				LevelManager.getInstance().refrashWritingScreen();

				LevelManager.getInstance().SetterClick(setter);
			}
		});

		codeLineLinearLayout.addView(button);

		/*
		Button button = new Button(context);

		Android_Utils.setSetterLayoutParams(button , context);

		if(setter.isMandatory())
			button.setBackgroundResource(R.drawable.mandatory);
		else if(!setter.isMandatory())
			button.setBackgroundResource(R.drawable.not_mandatory);

		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				LevelManager.getInstance().getCodeWritingManager().SetterClick(setter);
			}
		});

		codeLineLinearLayout.addView(button);
		 */
	}
}