package com.example.finalprojectapp.gamescreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter{

	//private FragmentManager fm;

	private DisplayFragment displayFragment;

	private CodingFragment codingFragment;

	private RunningFragment runningFragment;

	public PagerAdapter(FragmentManager fm) {
		super(fm);
		//this.fm = fm;
		displayFragment = new DisplayFragment();
		codingFragment = new CodingFragment();
		runningFragment = new RunningFragment();
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
		case 0:
			return displayFragment;
		case 1:
			return codingFragment;
		case 2:
			return runningFragment;
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

	public void refresh(int position){
		switch (position) {
		case 0:
			displayFragment.refresh();
			break;
		case 1:
			codingFragment.refresh();
			break;
		case 2:
			runningFragment.refresh();
			break;
		default:
			break;
		}
	}



}
