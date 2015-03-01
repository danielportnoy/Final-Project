package com.example.finalprojectapp.codescreenlogic.optionmenu.option.statement;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;
import com.example.finalprojectapp.codescreenlogic.optionmenu.optionsDB.NodeType;
import com.example.finalprojectapp.codescreenlogic.optionmenu.optionsDB.OptionsDB;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.statements.IfThenNode;

public class IfThenOption extends Option {
	
	@Override
	public boolean isType(Type type) {
		List<Type> types = OptionsDB.getByNodeType(NodeType.IfThen);
		return types.contains(type);
	}

	@Override
	public void setButton(Context context, Button optionButton, final Setter setter) {

		optionButton.setText("if then");	//TODO

		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setter.setChildNode(new IfThenNode());
				refresh();
			}
		});
	}

}
