package com.example.finalprojectapp.gamescreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Bridges between the Fragments and the Pager.
 * @author daniel portnoy
 *
 */
public class PagerAdapter extends FragmentStatePagerAdapter{

	private DisplayFragment displayFragment;

	private CodingFragment codingFragment;

	private RunningFragment runningFragment;

	private boolean disableRunningFragment;

	public PagerAdapter(FragmentManager fm) {
		super(fm);
		displayFragment = new DisplayFragment();
		codingFragment = new CodingFragment();
		runningFragment = new RunningFragment();

		disableRunningFragment = false;		
	}

	public void setDisableRunningFragment(boolean disableRunningFragment) {
		this.disableRunningFragment = disableRunningFragment;
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
		case 0:
			return displayFragment;
		case 1:
			return codingFragment;
		case 2:
			if (!disableRunningFragment)
				return runningFragment;
			else
				return null;
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		if(!disableRunningFragment)
			return 3;
		else 
			return 2;
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
