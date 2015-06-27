package com.example.finalprojectapp.coderunning.adapter;

import java.util.List;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningLine;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.utilities.Android_Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Bridges between the CodeLines data and the display.
 * @author daniel portnoy
 *
 */
public class CodeRunningLinesAdapter extends ArrayAdapter<CodeRunningLine>{

	private Context context = null;

	/**
	 * 
	 * @param context
	 * @param resource
	 * @param codeRunningLines
	 */
	public CodeRunningLinesAdapter(Context context, int resource, List<CodeRunningLine> codeRunningLines) {
		super(context, resource, codeRunningLines);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Inflating the codeRunningLine item.

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

		CodeRunningLine item = getItem(position);

		if(item!=null)
			handleCodeRunningLine(item , codeLineLinearLayout);

		return view;

	}

	/**
	 * Handles the showing of a CodeRunningLine item.
	 * @param item - CodeRunningLine item to show.
	 * @param codeLineLinearLayout - the item layout.
	 */
	private void handleCodeRunningLine(CodeRunningLine item, LinearLayout codeLineLinearLayout) {

		List<CodeRunningPart> codeRunningParts = item.getCodeRunningParts();

		for (final CodeRunningPart CODE_RUNNING_PART : codeRunningParts) {

			if(CODE_RUNNING_PART.getText()!=null)
				handleText(codeLineLinearLayout, CODE_RUNNING_PART.getText() , CODE_RUNNING_PART.isHighlighted());
			else if(CODE_RUNNING_PART.isTab())
				handleTab(codeLineLinearLayout);
		}

	}

	/**
	 * Handles the showing of a CodeRunningLine text item.
	 * @param codeLineLinearLayout - the item layout.
	 * @param TEXT_STRING - text to show.
	 * @param isHighlighted - indicate if the show needs to be highlighted.
	 */
	private void handleText(LinearLayout codeLineLinearLayout, final String TEXT_STRING, boolean isHighlighted) {
		TextView text = new TextView(context);

		Android_Utils.setLayoutParams(text , context);

		//Set the text and its appearance.
		
		text.setText(TEXT_STRING);
		text.setTextAppearance(context, android.R.style.TextAppearance_Small);
		
		//Set the text highlight.
		if(isHighlighted)
			text.setBackgroundColor(Color.YELLOW);

		codeLineLinearLayout.addView(text);
	}

	/**
	 * Handles the showing of a CodeRunningLine tab item.
	 * @param codeLineLinearLayout - the item layout.
	 */
	private void handleTab(LinearLayout codeLineLinearLayout) {
		TextView tab = new TextView(context);

		//Set the text and its appearance.

		Android_Utils.setLayoutParams(tab , context);

		tab.setText("      ");
		tab.setTextAppearance(context, android.R.style.TextAppearance_Small);

		codeLineLinearLayout.addView(tab);
	}
}