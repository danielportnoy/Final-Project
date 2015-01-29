package com.example.finalprojectapp.codescreenlogic.adapters;

import java.util.List;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codescreenlogic.CodeScreenManager;
import com.example.finalprojectapp.codescreenlogic.optionmenu.Option;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OptionsAdapter extends ArrayAdapter<Option> {

	private Context context = null;

	public OptionsAdapter(Context context, int resource, List<Option> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView optionTextView = null;
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.option_item, null);
		}
		else
			view = convertView;

		Option item = getItem(position);

		if(item != null){
			optionTextView = (TextView) view.findViewById(R.id.textView_oprtion);

			optionTextView.setOnClickListener(new MyOnClickListener(item));

			optionTextView.setText(item.toString());
		}
		else{

		}

		return view;
	}

	private class MyOnClickListener implements OnClickListener
	{

		Option option;
		public MyOnClickListener(Option option) {
			this.option = option;
		}

		@Override
		public void onClick(View v)
		{
			CodeScreenManager.getInstance().pickedOption(option);
		}

	};

}
