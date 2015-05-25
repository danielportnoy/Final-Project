package com.example.finalprojectapp.gamescreen;

import java.util.List;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.CodePlayer;
import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.managment.CodeRunningGraphicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningLogicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.scenario.TestCase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class RunningFragment extends Fragment implements OnClickListener{

	private Button playButton;
	
	private CodePlayer player;

	private SharedPreferences SP;

	private LinearLayout gameViewLayout;
	private MySurfaceView gameView;

	private CodeRunningGraphicUnit graphics;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.running_fragment_layout,container,false);

		CodeRunningLogicUnit logics = new CodeRunningLogicUnit();

		ListView codeLines = (ListView)myFragmentView.findViewById(R.id.listView_Running_Code);
		CodeRunningLinesAdapter codeRunningLinesAdapter = new CodeRunningLinesAdapter(getActivity(), android.R.layout.simple_list_item_1, logics.getRunningCodeLines());
		codeLines.setAdapter(codeRunningLinesAdapter);	

		SP = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

		graphics = new CodeRunningGraphicUnit(codeRunningLinesAdapter , gameView);
		LevelManager.getInstance().registerCodeRunningManager(new CodeRunningManager(logics, graphics));

		gameViewLayout = (LinearLayout)myFragmentView.findViewById(R.id.LinearLayout_Running_Game);

		playButton = (Button)myFragmentView.findViewById(R.id.button_PlayPause);

		myFragmentView.findViewById(R.id.button_endSnapshot).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_nextSnapshot).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_PlayPause).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_prevSnapshot).setOnClickListener(this);
		myFragmentView.findViewById(R.id.button_startSnapshot).setOnClickListener(this);

		return myFragmentView;
	}

	/*@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {

		super.setUserVisibleHint(isVisibleToUser);

		if(isVisibleToUser){
			player.start();
			player.display();
		}
	}*/

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_startSnapshot:
			player.startSnapshot();
			break;
		case R.id.button_prevSnapshot:
			player.prevSnapshot();
			break;
		case R.id.button_PlayPause:
			player.togglePlay();
			break;
		case R.id.button_nextSnapshot:
			player.nextSnapshot();
			break;
		case R.id.button_endSnapshot:
			player.endSnapshot();
			break;

		default:
			break;
		}

	}

	public void refresh() {

		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		int cps = SP.getInt(Constants.CPS_KEY,Constants.DEFAULT_CPS);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		TestCase testCaseToShow = null;

		List<TestCase> tests = LevelManager.getInstance().runCodeTests();

		for (TestCase testCase : tests) 
			if(!testCase.isWin()){
				testCaseToShow = testCase;
				break;
			}

		if(testCaseToShow == null)
			testCaseToShow = tests.get(0);

		LevelManager.getInstance().getScenario().setCurrentConfig(testCaseToShow.getConfig());

		gameView = LevelManager.getInstance().getScenario().generateGameView(getActivity(), fps, animation);;
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		gameViewLayout.removeAllViews();
		gameViewLayout.addView(gameView);

		graphics.setGameView(gameView);

		player = new CodePlayer(getActivity(), playButton, cps, testCaseToShow);

		player.start();
		player.display();
	}

}
