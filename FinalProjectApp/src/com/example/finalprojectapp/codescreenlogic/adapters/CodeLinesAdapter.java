package com.example.finalprojectapp.codescreenlogic.adapters;

import java.util.List;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codescreenlogic.CodeScreenManager;
import com.example.finalprojectapp.codescreenlogic.codescreen.InputMethod;
import com.example.finalprojectapp.codescreenlogic.pattern.NewLinePattern;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolder;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolderType;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
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

		if(item.getPattern() instanceof NewLinePattern)
			handleNewLine(codeLinearLayout, item);
		else
			handleInOrder(codeLinearLayout, item);

		return view;

	}

	/********** Handlers **********/
	private void handleNewLine(LinearLayout codeLinearLayout, PlaceHolder item) {

		Button newLineButton = new Button(context);

		if(!CodeScreenManager.getInstance().getCodeScreen().validateAllLines())
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
			CodeScreenManager.getInstance().pickedCode(ph);
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

				/*if(ph.getPlaceholderType().equals(PlaceHolderType.Idnt)){	//TODO	

					String text = v.getText().toString();
					boolean isValidIdentifier = true;

					if(text == null || text.length() == 0)
						isValidIdentifier = false;
					else{
						if(!Character.isJavaIdentifierStart((text.charAt(0))))
							isValidIdentifier = false;

						for (int i = 1; i < text.length() ; i++) {
							if(!Character.isJavaIdentifierPart((text.charAt(i))))
								isValidIdentifier = false;
						}
					}

					if(isValidIdentifier)
						GameScreenManager.getInstance().setCode(v.getText().toString() , ph);
					else
						Toast.makeText(context, "Bad identifier format", Toast.LENGTH_SHORT).show();

				}*/

				CodeScreenManager.getInstance().setCode(v.getText().toString() , ph);

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
		button.setTextAppearance(context, android.R.style.TextAppearance_Small);

		button.setOnClickListener(new MyOnClickListener(item));

		codeLinearLayout.addView(button);
	}

	private void setTextView (TextView textView , LinearLayout codeLinearLayout ,PlaceHolder item, String text){

		setLayoutParams(textView);

		textView.setText(text);
		textView.setTextAppearance(context, android.R.style.TextAppearance_Small);

		codeLinearLayout.addView(textView);
	}

	private void setEditText(EditText editText , LinearLayout codeLinearLayout, PlaceHolder item,String text) {

		setLayoutParams(editText);

		editText.setSingleLine(true);

		editText.setHint(text);
		editText.setTextAppearance(context, android.R.style.TextAppearance_Small);

		if(item.getPlaceholderType().equals(PlaceHolderType.IntLiteral)){
			editText.setInputType(InputType.TYPE_CLASS_NUMBER);

			InputFilter[] filterArray = new InputFilter[1];
			filterArray[0] = new InputFilter.LengthFilter(3);
			editText.setFilters(filterArray);
		}

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