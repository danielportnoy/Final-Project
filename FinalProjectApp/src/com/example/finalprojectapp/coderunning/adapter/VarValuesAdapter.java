package com.example.finalprojectapp.coderunning.adapter;

import java.util.List;

import com.example.finalprojectapp.R;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class VarValuesAdapter extends ArrayAdapter<Pair<String, String>>{

	private Context context = null;

	public VarValuesAdapter(Context context, int resource, List<Pair<String, String>> VarValuesList) {
		super(context, resource, VarValuesList);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView textView_Var_Name = null;
		TextView textView_Var_Value = null;
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.var_value_item, null);
		}
		else
			view = convertView;

		textView_Var_Name = (TextView) view.findViewById(R.id.textView_Var_Name);
		textView_Var_Value = (TextView) view.findViewById(R.id.textView_Var_Value);

		Pair<String, String> item = getItem(position);

		if(item!=null){
			textView_Var_Name.setText(item.first + " : ");
			textView_Var_Value.setText(item.second);
		}

		return view;

	}
}