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

public class CodeRunningLinesAdapter extends ArrayAdapter<CodeRunningLine>{

	private Context context = null;

	public CodeRunningLinesAdapter(Context context, int resource, List<CodeRunningLine> codeRunningLines) {
		super(context, resource, codeRunningLines);
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

		CodeRunningLine item = getItem(position);

		if(item!=null)
			handleCodeRunningLine(item , codeLineLinearLayout);

		return view;

	}

	private void handleCodeRunningLine(CodeRunningLine item, LinearLayout codeLineLinearLayout) {

		List<CodeRunningPart> codeRunningParts = item.getCodeRunningParts();

		for (final CodeRunningPart codeRunningPart : codeRunningParts) {

			if(codeRunningPart.getText()!=null)
				handleText(codeLineLinearLayout, codeRunningPart.getText() , codeRunningPart.isHighlighted());
			else if(codeRunningPart.isTab())
				handleTab(codeLineLinearLayout);
		}

	}

	private void handleText(LinearLayout codeLineLinearLayout, final String textString, boolean isHighlighted) {
		TextView text = new TextView(context);

		Android_Utils.setLayoutParams(text , context);

		text.setText(textString);
		text.setTextAppearance(context, android.R.style.TextAppearance_Small);
		
		if(isHighlighted)	//TODO
			text.setBackgroundColor(Color.YELLOW);

		codeLineLinearLayout.addView(text);
	}

	private void handleTab(LinearLayout codeLineLinearLayout) {
		TextView tab = new TextView(context);

		Android_Utils.setLayoutParams(tab , context);

		tab.setText("      ");
		tab.setTextAppearance(context, android.R.style.TextAppearance_Small);

		codeLineLinearLayout.addView(tab);
	}
}