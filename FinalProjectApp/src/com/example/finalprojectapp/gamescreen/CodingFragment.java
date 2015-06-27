package com.example.finalprojectapp.gamescreen;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codewriting.adapter.CodeWritingLinesAdapter;
import com.example.finalprojectapp.codewriting.adapter.OptionsAdapter;
import com.example.finalprojectapp.codewriting.managment.CodeWritingGraphicUnit;
import com.example.finalprojectapp.codewriting.managment.CodeWritingLogicUnit;
import com.example.finalprojectapp.codewriting.managment.CodeWritingManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CodingFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		// Inflate the fragment.
		View myFragmentView = inflater.inflate(R.layout.coding_fragment_layout,container,false);

		/*
		 * Initialize the logicUnit.
		 * Adding the codeLine and VarValues adapters to the logicUnit.
		 */
		CodeWritingLogicUnit logics = new CodeWritingLogicUnit();

		ListView codeLines = (ListView)myFragmentView.findViewById(R.id.listView_Writing_Code);
		CodeWritingLinesAdapter codeLinesAdapter = new CodeWritingLinesAdapter(getActivity(), android.R.layout.simple_list_item_1, logics.getWritingCodeLines());
		codeLines.setAdapter(codeLinesAdapter);	

		ListView options = (ListView)myFragmentView.findViewById(R.id.listView_Options);
		OptionsAdapter optionsAdapter = new OptionsAdapter(getActivity(), android.R.layout.simple_list_item_1, logics.getCurrentOptions());
		options.setAdapter(optionsAdapter);		
		
		/*
		 * Initialize the graphicUnit.
		 * Adding the gameView to the graphicUnit.
		 */
		CodeWritingGraphicUnit graphics = new CodeWritingGraphicUnit(codeLinesAdapter, optionsAdapter);

		CodeWritingManager manager = new CodeWritingManager(logics, graphics);
		manager.setGameScreenActivity(getActivity());

		// registering the managers to the levelManager instance.
		LevelManager.getInstance().registerCodeWritingManager(manager);
		
		return myFragmentView;

	}

	/**
	 * Update the fragment screen.
	 */
	public void refresh() {
		LevelManager.getInstance().setEditable(null);
		LevelManager.getInstance().setEditMode(false);
		LevelManager.getInstance().refrashWritingScreen();
	}

}
