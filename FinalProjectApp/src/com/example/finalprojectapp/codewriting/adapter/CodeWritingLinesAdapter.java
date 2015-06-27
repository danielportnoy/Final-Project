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

/**
 * Bridges between the CodeLines data and the display.
 * @author daniel portnoy
 *
 */
public class CodeWritingLinesAdapter extends ArrayAdapter<CodeWritingLine>{

	private Context context = null;

	/**
	 * 
	 * @param context
	 * @param resource
	 * @param codeWritingLines
	 */
	public CodeWritingLinesAdapter(Context context, int resource, List<CodeWritingLine> codeWritingLines) {
		super(context, resource, codeWritingLines);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Inflating the codeWritingLine item.

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

	/**
	 * Handles the showing of a CodeWritingLine item.
	 * @param item - CodeWritingLine item to show.
	 * @param codeLineLinearLayout - the item layout.
	 */
	private void handleCodeWritingLine(CodeWritingLine item, LinearLayout codeLineLinearLayout) {

		List<CodeWritingPart> codeWritingParts = item.getCodeWritingParts();

		for (final CodeWritingPart CODE_WRITING_PART : codeWritingParts) {

			if(CODE_WRITING_PART.getSetter()!=null)
				handleSetter(codeLineLinearLayout, CODE_WRITING_PART.getSetter(), CODE_WRITING_PART);
			else if(CODE_WRITING_PART.getText()!=null)
				handleText(codeLineLinearLayout, CODE_WRITING_PART.getText(), CODE_WRITING_PART);
			else if(CODE_WRITING_PART.isTab())
				handleTab(codeLineLinearLayout);
		}

	}

	/**
	 * Handles the showing of a CodeRunningLine text item.
	 * @param codeLineLinearLayout - item layout. 
	 * @param TEXT_STRING - text to show.
	 * @param CODE_WRITING_PART - item itself.
	 */
	private void handleText(LinearLayout codeLineLinearLayout, final String TEXT_STRING, final CodeWritingPart CODE_WRITING_PART) {
		TextView text = new TextView(context);

		Android_Utils.setLayoutParams(text , context);

		//Set the text and its appearance.

		text.setText(TEXT_STRING);
		text.setTextAppearance(context, android.R.style.TextAppearance_Small);

		//Set the erasable appearance.
		if(!CODE_WRITING_PART.getMakerNode().isErasable())
			text.setTextColor(0xff6E6E6E);
		else
			text.setTextColor(Color.BLACK);

		//Set the text highlight.
		if(CODE_WRITING_PART.isEditable() && LevelManager.getInstance().isEditMode())
			text.setBackgroundColor(Color.YELLOW);

		//Set the long click listener.
		text.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				if(CODE_WRITING_PART.getMakerNode().isErasable()){

					LevelManager.getInstance().setEditMode(true);
					LevelManager.getInstance().setEditable(CODE_WRITING_PART.getMakerNode());
					LevelManager.getInstance().refrashWritingScreen();
				}
				return false;
			}
		});

		codeLineLinearLayout.addView(text);
	}

	/**
	 * Handles the showing of a CodeWritingLine tab item.
	 * @param codeLineLinearLayout - item layout.
	 */
	private void handleTab(LinearLayout codeLineLinearLayout) {
		TextView tab = new TextView(context);

		//Set the text and its appearance.

		Android_Utils.setLayoutParams(tab , context);

		tab.setText("      ");
		tab.setTextAppearance(context, android.R.style.TextAppearance_Small);

		codeLineLinearLayout.addView(tab);
	}

	/**
	 * Handles the showing of a CodeWritingLine item setter button.
	 * @param codeLineLinearLayout - item layout.
	 * @param SETTER - the code setter object.
	 * @param codeWritingPart - item itself.
	 */
	private void handleSetter(LinearLayout codeLineLinearLayout,final Setter SETTER, CodeWritingPart codeWritingPart) {
		Button button = new Button(context);

		//Set the setter button and its appearance.

		Android_Utils.setSetterLayoutParams(button , context);

		if(SETTER.isMandatory()){
			if(codeWritingPart.isEditable() && LevelManager.getInstance().isEditMode())
				button.setBackgroundResource(R.drawable.mandatory_button_highlighted);
			else
				button.setBackgroundResource(R.drawable.mandatory_button);
		}
		else if(!SETTER.isMandatory()){
			if(codeWritingPart.isEditable() && LevelManager.getInstance().isEditMode())
				button.setBackgroundResource(R.drawable.not_mandatory_button_highlighted);
			else
				button.setBackgroundResource(R.drawable.not_mandatory_button);
		}

		button.setText("+");
		button.setTextAppearance(context, android.R.style.TextAppearance_Small);

		//Set the click listener.

		button.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				LevelManager.getInstance().setEditMode(false);
				LevelManager.getInstance().setEditable(null);
				LevelManager.getInstance().refrashWritingScreen();

				LevelManager.getInstance().SetterClick(SETTER);
			}
		});

		codeLineLinearLayout.addView(button);
	}
}