package com.example.finalprojectapp.codewriting.adapter;

import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codewriting.codeline.CodeLine;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.utilities.Android_Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CodeLinesAdapter extends ArrayAdapter<CodeLine>{

	private Context context = null;

	public CodeLinesAdapter(Context context, int resource, List<CodeLine> codeLines) {
		super(context, resource, codeLines);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout codeLinearLayout = null;
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.code_item, null);
		}
		else
			view = convertView;

		codeLinearLayout = (LinearLayout) view.findViewById(R.id.LinearLayout_CodeItem);
		codeLinearLayout.removeAllViews();

		CodeLine item = getItem(position);

		if(item!=null)
			handleCodeLine(item , codeLinearLayout);

		return view;

	}

	private void handleCodeLine(CodeLine item, LinearLayout codeLinearLayout) {

		List<CodePart> codeParts = item.getCodeScreenParts();

		for (final CodePart codePart : codeParts) {

			if(codePart.getSetter()!=null)
				handleSetter(codeLinearLayout, codePart.getSetter());
			else if(codePart.getText()!=null)
				handleText(codeLinearLayout, codePart.getText());
			else if(codePart.isTab())
				handleTab(codeLinearLayout);
		}

	}

	private void handleText(LinearLayout codeLinearLayout, final String textString) {
		TextView text = new TextView(context);

		Android_Utils.setLayoutParams(text , context);

		text.setText(textString);
		text.setTextAppearance(context, android.R.style.TextAppearance_Small);

		codeLinearLayout.addView(text);
	}

	private void handleTab(LinearLayout codeLinearLayout) {
		TextView tab = new TextView(context);

		Android_Utils.setLayoutParams(tab , context);

		tab.setText("      ");
		tab.setTextAppearance(context, android.R.style.TextAppearance_Small);

		codeLinearLayout.addView(tab);
	}

	private void handleSetter(LinearLayout codeLinearLayout,final Setter setter) {
		Button button = new Button(context);

		Android_Utils.setLayoutParams(button , context);

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

		if(setter.isMandatory())
			button.setBackgroundResource(R.drawable.mandatory_button);
		else if(!setter.isMandatory())
			button.setBackgroundResource(R.drawable.not_mandatory_button);



		button.setText(setter.getText());
		button.setTextAppearance(context, android.R.style.TextAppearance_Small);

		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				LevelManager.getInstance().getCodeWritingManager().SetterClick(setter);
			}
		});

		codeLinearLayout.addView(button);
	}
}