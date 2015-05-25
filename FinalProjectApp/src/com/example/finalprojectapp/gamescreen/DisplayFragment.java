package com.example.finalprojectapp.gamescreen;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.utilities.Android_Utils;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class DisplayFragment extends Fragment {

	private MySurfaceView gameView;

	private SharedPreferences SP;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.display_fragment_layout,container,false);

		SP = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());

		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		boolean levelInstructions = SP.getBoolean(Constants.LI_KEY, Constants.DEFAULT_LI);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		gameView = LevelManager.getInstance().getScenario().generateGameView(getActivity(), fps, animation);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		LinearLayout gameViewLayout = (LinearLayout)myFragmentView.findViewById(R.id.LinearLayout_GameScreen);
		gameViewLayout.addView(gameView);

		if(levelInstructions){
			AlertDialog.Builder builder = Android_Utils.getStartGameDialog(getActivity(),
					Constants.LEVEL_START_TEXT, LevelManager.getInstance().getScenario().getLevelText());

			builder.create().show();
		}

		return myFragmentView;
	}

	public void refresh() {

		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		boolean levelInstructions = SP.getBoolean(Constants.LI_KEY, Constants.DEFAULT_LI);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		gameView.setFps(fps);
		gameView.setAnimating(animation);

		if(levelInstructions){
			AlertDialog.Builder builder = Android_Utils.getStartGameDialog(getActivity(),
					Constants.LEVEL_START_TEXT, LevelManager.getInstance().getScenario().getLevelText());

			builder.create().show();
		}	
	}
}
