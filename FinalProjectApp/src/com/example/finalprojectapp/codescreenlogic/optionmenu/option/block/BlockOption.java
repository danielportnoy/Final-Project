package com.example.finalprojectapp.codescreenlogic.optionmenu.option.block;

import java.util.List;

import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;
import com.example.finalprojectapp.codescreenlogic.optionmenu.optionsDB.NodeType;
import com.example.finalprojectapp.codescreenlogic.optionmenu.optionsDB.OptionsDB;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.block.BlockNode;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BlockOption extends Option {
	

	@Override
	public boolean isType(Type type) {
		List<Type> types = OptionsDB.getByNodeType(NodeType.Block);
		return types.contains(type);
	}

	@Override
	public void setButton(Context context, Button optionButton , final Setter setter) {

		optionButton.setText("block");	//TODO
		
		optionButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setter.setChildNode(new BlockNode());
				refresh();
			}
		});

	}

}
