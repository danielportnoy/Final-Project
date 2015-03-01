package com.example.finalprojectapp.codescreenlogic.adapters;

import java.util.List;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codescreenlogic.CodeScreenManager;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class OptionsAdapter extends ArrayAdapter<Option> {

	private Context context = null;

	public OptionsAdapter(Context context, int resource, List<Option> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Button optionButton = null;
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.option_item, null);
		}
		else
			view = convertView;
		
		optionButton = (Button) view.findViewById(R.id.button_option);

		Option item = getItem(position);
		
		if(item != null);
			item.setButton(context, optionButton , CodeScreenManager.getInstance().getCurrentSetter());

		return view;
	}

}
