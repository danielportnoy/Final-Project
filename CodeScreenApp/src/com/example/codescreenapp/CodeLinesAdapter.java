package com.example.codescreenapp;

import java.util.List;

import com.example.codescreenapp.R;
import com.example.logic.Manager;
import com.example.logic.codescreen.InputMethod;
import com.example.logic.grammar.PlaceHolder;
import com.example.logic.optionmenu.OptionEnum;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class CodeLinesAdapter extends ArrayAdapter<PlaceHolder>{

	private Context context = null;

	public CodeLinesAdapter(Context context, int resource, List<PlaceHolder> objects) {
		super(context, resource, objects);
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

		PlaceHolder item = getItem(position);

		if(item.getPattern() == null)
			handleNewLine(codeLinearLayout, item);
		else
			handleInOrder(codeLinearLayout, item);

		return view;

	}

	/********** Handlers **********/
	private void handleNewLine(LinearLayout codeLinearLayout, PlaceHolder item) {

		Button newLineButton = new Button(context);

		if(!Manager.getInstance().getCodeScreen().isAllLinesOK())
			newLineButton.setEnabled(false);

		setButton(newLineButton, codeLinearLayout, item, "+");
	}

	private void handleInOrder(LinearLayout codeLinearLayout, PlaceHolder item) {

		if(item.getPattern() == null){

			if(item.getInputMethod().equals(InputMethod.Option)){
				Button b = new Button(context);
				setButton(b, codeLinearLayout, item, item.toString());
			}
			else if(item.getInputMethod().equals(InputMethod.Keyboard)){
				EditText et = new EditText(context);
				setEditText(et, codeLinearLayout, item, item.toString());
				
				/*if(item.getOptionFilter().getOption()==null)
					et.setEnabled(false);*/
				
				if(item.getOptionFilter().getOption().equals(OptionEnum.Idnt))
					et.setEnabled(false);
			}
			else if(item.getInputMethod().equals(InputMethod.Disabled)){
				TextView tv = new TextView(context);
				setTextView(tv, codeLinearLayout, item, item.toString());
			}
		}
		else {

			if(item.getPattern().getPlaceHolders().size() != 0){

				for (int i = 0; i < item.getPattern().getPlaceHolders().size() ; i++) {
					handleInOrder(codeLinearLayout, item.getPattern().getPlaceHolders().get(i));
				}
			}
			else{

				setTextView(new TextView(context), codeLinearLayout, item, item.toString());
			}
		}
	}
	/********** Handlers **********/


	/********** Checks **********/

	/********** Checks **********/

	/********** Listeners **********/
	private class MyOnClickListener implements OnClickListener
	{
		PlaceHolder ph;

		public MyOnClickListener(PlaceHolder item) {
			this.ph = item;
		}

		@Override
		public void onClick(View v)
		{
			Manager.getInstance().pickedCode(ph);
		}
	};

	private class MyOnEditorActionListener implements OnEditorActionListener{

		PlaceHolder ph;

		public MyOnEditorActionListener(PlaceHolder item) {
			this.ph = item;
		}

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {

				Manager.getInstance().setCode(v.getText().toString() , ph);

				v.clearFocus();

				return true; // consume.
			}      
			return false; // pass on to other listeners. 
		}

	}
	/********** Listeners **********/

	/********** helpers **********/
	private void setButton(Button button ,LinearLayout codeLinearLayout ,PlaceHolder item, String text){
		setLayoutParams(button);

		button.setText(text);

		button.setOnClickListener(new MyOnClickListener(item));

		codeLinearLayout.addView(button);
	}

	private void setTextView (TextView textView , LinearLayout codeLinearLayout ,PlaceHolder item, String text){

		setLayoutParams(textView);

		textView.setText(text);

		codeLinearLayout.addView(textView);
	}

	private void setEditText(EditText editText , LinearLayout codeLinearLayout, PlaceHolder item,String text) {

		setLayoutParams(editText);

		editText.setSingleLine(true);
		
		editText.setHint(text);
		
		//tv.setInputType(InputType.TYPE_CLASS_NUMBER);

		//InputFilter[] filterArray = new InputFilter[1];
		//filterArray[0] = new InputFilter.LengthFilter(3);
		//tv.setFilters(filterArray);

		//Integer literalValue = ((Int_Literal)(((Expr_Stmt)(Manager.getInstance().getCodeScreen().getStatements().get(row))).getExpression())).getValue();

		//if(literalValue != null)
		//tv.setText(literalValue.toString());
		//else
		//tv.setHint("< int literal >");

		//tv.setTag("intLiteralEditText");
		
		editText.setOnEditorActionListener(new MyOnEditorActionListener(item));

		editText.setImeOptions(EditorInfo.IME_ACTION_DONE);

		codeLinearLayout.addView(editText);
	}
	/********** helpers **********/

	/* utils */
	private int calcMargin(int dpValue){
		return (int)(dpValue * context.getResources().getDisplayMetrics().density); 
	}

	private void setLayoutParams(View v){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(calcMargin(5), 0, 0, 0);
		v.setLayoutParams(params);
	}
	/* utils */
}