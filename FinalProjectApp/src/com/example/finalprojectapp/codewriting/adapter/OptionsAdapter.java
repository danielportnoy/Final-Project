package com.example.finalprojectapp.codewriting.adapter;

import java.util.List;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

/**
 * Bridges between the Options data and the display.
 * @author daniel portnoy
 *
 */
public class OptionsAdapter extends ArrayAdapter<Option> {

	private Context context = null;

	private Setter currentSetter = null;

	/**
	 * 
	 * @param context
	 * @param resource
	 * @param objects
	 */
	public OptionsAdapter(Context context, int resource, List<Option> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Button optionButton = null;
		View view = null;
		
		// Inflating the VarValue item.

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.option_item, null);
		}
		else
			view = convertView;

		optionButton = (Button) view.findViewById(R.id.button_option);
		
		//Set the option button.

		Option item = getItem(position);

		if(item != null)
			item.setButton(context, optionButton , currentSetter);

		return view;
	}

	public void setCurrentSetter(Setter currentSetter) {
		this.currentSetter = currentSetter;
	}

}
