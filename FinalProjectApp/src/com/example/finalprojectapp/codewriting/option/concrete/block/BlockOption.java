package com.example.finalprojectapp.codewriting.option.concrete.block;

import com.example.finalprojectapp.codewriting.option.Option;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.block.BlockNode;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BlockOption extends Option {
	

	@Override
	public boolean isType(Type type) {
		return type == Type.Statement;
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
