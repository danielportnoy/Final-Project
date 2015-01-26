package com.example.finalproject;

import final_2.logics.game.scenario.Scenario;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class PagerAdapter extends FragmentPagerAdapter {

	private int levelNumber;
	private Scenario level;
	private FragmentManager fm;

	public PagerAdapter(FragmentManager fm , Scenario level , int levelNumber) {
		super(fm);
		this.fm = fm;
		this.level = level;
		this.levelNumber = levelNumber;
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return newGameFragmentInstance(level , levelNumber);
		case 1:
			return newCodeFragmentInstance(level , levelNumber);
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

	private static GameFragment newGameFragmentInstance(Scenario level , int levelNumber) {
		GameFragment gameFragment = new GameFragment();

		gameFragment.setLevel(level);
	
		Bundle args = new Bundle();
		args.putSerializable(Constants.LEVEL_NUMBER_EXTRA, levelNumber);
		gameFragment.setArguments(args);
		 
		return gameFragment;
	}

	private static CodeFragment newCodeFragmentInstance(Scenario level , int levelNumber) {
		CodeFragment codeFragment = new CodeFragment();

		codeFragment.setLevel(level);
		
		Bundle args = new Bundle();
		args.putInt(Constants.LEVEL_NUMBER_EXTRA, levelNumber);
		codeFragment.setArguments(args);

		return codeFragment;
	}

	private static String getFragmentName(int viewId, int index) {
		return "android:switcher:" + viewId + ":" + index;
	}

	public Fragment getExisting(View container , int position){
		String name = getFragmentName(container.getId(), position);
		Fragment fragment = fm.findFragmentByTag(name);
		return fragment;
	}

}
