package com.example.finalprojecttestapp;

import com.example.finalprojecttestapp.R;

import grammarModuleTest2.logics.game.level1.Level1;
import grammarModuleTest2.logics.game.level1.infrastructure.Coordinate;
import grammarModuleTest2.logics.game.scenario.Scenario;
import grammarModuleTest2.logics.grammar.statement.Block_Stmt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class GameScreenActivity extends FragmentActivity implements FragmentCommunicationInterface{

	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;
	private int levelNumber;

	//private TextView levelTextView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);

		viewPager = (ViewPager) findViewById(R.id.pager);

		levelNumber = getIntent().getIntExtra(Constants.LEVEL_NUMBER_EXTRA, 1);

		pagerAdapter = new PagerAdapter(getSupportFragmentManager(),getLevelInstance() , levelNumber);

		viewPager.setAdapter(pagerAdapter);

		//levelTextView = (TextView)findViewById(R.id.TextView_LevelNumber);
		//levelTextView.setText(getIntent().getIntExtra(Constants.LEVEL_NUMBER_EXTRA, 1)+"");
	}

	private Scenario getLevelInstance() {

		switch (levelNumber) {
		case 1:
			return new Level1(5, 5, "H", new Coordinate(1, 1), new Coordinate(1, 4));

		case 2:

			break;

		case 3:

			break;

		default:
			break;
		}

		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_screen, menu);
		return true;
	}

	@Override
	public Block_Stmt getCode() {
		CodeFragment codeFragment = (CodeFragment)pagerAdapter.getExisting(viewPager, 1);
		return codeFragment.getCode();
	}

}
