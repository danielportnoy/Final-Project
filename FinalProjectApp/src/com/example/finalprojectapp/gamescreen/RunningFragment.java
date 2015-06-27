package com.example.finalprojectapp.gamescreen;

import java.util.List;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.adapter.VarValuesAdapter;
import com.example.finalprojectapp.coderunning.codeplayer.CodePlayer;
import com.example.finalprojectapp.coderunning.managment.CodeRunningGraphicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningLogicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;
import com.example.finalprojectapp.coderunning.testcase.TestCase;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class RunningFragment extends Fragment implements OnClickListener{

	//private ImageButton playButton;
	private CodePlayer player;

	private SharedPreferences SP;

	private LinearLayout gameViewLayout;
	private MySurfaceView gameView;

	private CodeRunningGraphicUnit graphics;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// Inflate the fragment.
		View myFragmentView = inflater.inflate(R.layout.running_fragment_layout,container,false);

		/*
		 * Initialize the logicUnit.
		 * Adding the codeLine and VarValues adapters to the logicUnit.
		 */
		CodeRunningLogicUnit logics = new CodeRunningLogicUnit();

		ListView codeLines = (ListView)myFragmentView.findViewById(R.id.listView_Running_Code);
		CodeRunningLinesAdapter codeRunningLinesAdapter = new CodeRunningLinesAdapter(getActivity(), android.R.layout.simple_list_item_1, logics.getRunningCodeLines());
		codeLines.setAdapter(codeRunningLinesAdapter);	

		ListView listView_VarValues= (ListView)myFragmentView.findViewById(R.id.listView_Running_VarValues);
		VarValuesAdapter varValuesLinesAdapter = new VarValuesAdapter(getActivity(), android.R.layout.simple_list_item_1, logics.getValuesList());
		listView_VarValues.setAdapter(varValuesLinesAdapter);	

		//Retrieve all preferences.
		SP = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

		/*
		 * Initialize the graphicUnit.
		 * Adding the gameView to the graphicUnit.
		 */
		graphics = new CodeRunningGraphicUnit(codeRunningLinesAdapter, varValuesLinesAdapter, gameView);

		// registering the managers to the levelManager instance.
		LevelManager.getInstance().registerCodeRunningManager(new CodeRunningManager(logics, graphics));

		gameViewLayout = (LinearLayout)myFragmentView.findViewById(R.id.LinearLayout_Game);

		//playButton = (ImageButton)myFragmentView.findViewById(R.id.button_PlayPause);

		// Set the buttons listeners.
		myFragmentView.findViewById(R.id.button_endSnapshot).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_nextSnapshot).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_PlayPause).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_prevSnapshot).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_startSnapshot).setOnClickListener(this);

		return myFragmentView;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		// Start clicked.
		case R.id.button_startSnapshot:
			player.startSnapshot();
			break;

			// Previous clicked.
		case R.id.button_prevSnapshot:
			player.prevSnapshot();
			break;

			// PLay \ Pause clicked.
		case R.id.button_PlayPause:
			player.togglePlay();
			break;

			// Next clicked.
		case R.id.button_nextSnapshot:
			player.nextSnapshot();
			break;

			// End clicked.
		case R.id.button_endSnapshot:
			player.endSnapshot();
			break;

		default:
			break;
		}

	}

	/**
	 * Update the fragment screen.
	 */
	public void refresh() {

		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		int cps = SP.getInt(Constants.CPS_KEY,Constants.DEFAULT_CPS);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		/*
		 * Conduct the testing of the code for all of the Configurations.
		 * Choosing a proper TestCase to show in the CodeRunning display.
		 * If there is no Failing tests, the default (first) is chosen.
		 */
		
		TestCase testCaseToShow = null;

		List<TestCase> tests = LevelManager.getInstance().runCodeTests();

		for (TestCase testCase : tests) 
			if(!LevelManager.getInstance().checkWin(testCase)){
				
				// pick the failing test.
				testCaseToShow = testCase;
				break;
			}

		// default (first).
		if(testCaseToShow == null)
			testCaseToShow = tests.get(0);

		LevelManager.getInstance().getScenario().setCurrentConfig(testCaseToShow.getConfig());
		
		/*
		 * Creating the gameView and adding it to the activity.
		 * using FPS and CPS in the creation process. 
		 */

		gameView = LevelManager.getInstance().getScenario().generateGameView(getActivity(), fps, animation);;
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		gameViewLayout.removeAllViews();
		gameViewLayout.addView(gameView);

		graphics.setGameView(gameView);
		
		/*
		 * Creating the CodePLayer.
		 * using the chosen TestCase and the gameView.
		 */

		player = new CodePlayer(getActivity(), /*playButton*/ cps, testCaseToShow, gameView);

		player.display();
	}

}
