package com.example.finalprojectapp.gamescreen;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.activities.SettingsActivity;
import com.example.finalprojectapp.node.Setter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class GameScreenActivity extends FragmentActivity {

	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	
	private boolean firstResume = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);        

		setContentView(R.layout.activity_game_screen);

		viewPager = (ViewPager) findViewById(R.id.pager);

		pagerAdapter = new PagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(pagerAdapter);

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);             
		actionBar.setDisplayShowTitleEnabled(false);

		// Specify that tabs should be displayed in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create a tab listener that is called when the user changes tabs.
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// probably ignore this event
			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// When the tab is selected, switch to the
				// corresponding page in the ViewPager.
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// hide the given tab
			}
		};

		// Add 3 tabs, specifying the tab's text and TabListener
		actionBar.addTab(actionBar.newTab().setText("Level").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Code").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Run").setTabListener(tabListener));

		viewPager.setOnPageChangeListener(
				new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between pages, select the
						// corresponding tab.
						getActionBar().setSelectedNavigationItem(position);
						pagerAdapter.refresh(position);
					}
				});	
				
		viewPager.setOffscreenPageLimit(3);// TODO
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    case R.id.action_settings:
	        // Settings option clicked.
	    	Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(!firstResume)
			pagerAdapter.refresh(viewPager.getCurrentItem());
		
		firstResume = false;
	}
	
	public void setTab(int i){
		viewPager.setCurrentItem(i);
	}

}
