package com.example.finalprojecttestapp;

import com.example.finalprojecttestapp.R;

import final_2.logics.game.level1.Level1;
import final_2.logics.game.level1.infrastructure.Board;
import final_2.logics.game.level1.infrastructure.Coordinate;
import final_2.logics.game.scenario.Scenario;
import final_2.logics.grammar.statement.Block_Stmt;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameFragment extends Fragment implements OnClickListener {

	private TextView levelTextView;
	
	private LinearLayout gameLayout;
	
	private Block_Stmt codeBlock;
	
	private FragmentCommunicationInterface com;
	
	private Level1 level1;
	
	private TableLayout boardTableLayout;
	
	private Button runButton;
	private Button resetButton;
	
	private int levelNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View myFragmentView = inflater.inflate(R.layout.game_fragment_layout,container,false);

		gameLayout = (LinearLayout) myFragmentView.findViewById(R.id.linearLayout_GameLayout);

		levelTextView = (TextView) myFragmentView.findViewById(R.id.TextView_LevelNumber);

		levelNumber = getArguments().getInt(Constants.LEVEL_NUMBER_EXTRA);

		buildLevel(levelNumber);

		com = (GameScreenActivity)getActivity();

		runButton = (Button) myFragmentView.findViewById(R.id.buttonRUN);
		runButton.setOnClickListener(this);
		
		resetButton = (Button) myFragmentView.findViewById(R.id.buttonRESET);
		resetButton.setOnClickListener(this);

		return myFragmentView;
	}

	private void buildLevel(int levelNumber) {
		switch (levelNumber) {
		case 1:	

			levelTextView.setText("");

			boardTableLayout = createBoardTableLayout(level1.getBoard());
			updateTable();

			gameLayout.addView(boardTableLayout);

			break;
		case 2:
			levelTextView.setText(levelNumber+"");
			break;
		case 3:
			levelTextView.setText(levelNumber+"");
			break;
		default:
			break;
		}
	}

	private TableLayout createBoardTableLayout(Board board) {

		// 1) Create a tableLayout and its params
		TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
		TableLayout tableLayout = new TableLayout(getActivity());
		tableLayout.setBackgroundColor(Color.BLACK);

		// 2) create tableRow params
		TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
		tableRowParams.setMargins(1, 1, 1, 1);
		tableRowParams.weight = 1;

		for (int i = 0; i < board.getBoardHeight(); i++) {
			// 3) create tableRow
			TableRow tableRow = new TableRow(getActivity());
			tableRow.setBackgroundColor(Color.BLACK);

			for (int j= 0; j < board.getBoardLength(); j++) {
				// 4) create textView
				TextView textView = new TextView(getActivity());
				textView.setBackgroundColor(Color.WHITE);
				textView.setGravity(Gravity.CENTER);

				// 5) add textView to tableRow
				tableRow.addView(textView, tableRowParams);
			}

			// 6) add tableRow to tableLayout
			tableLayout.addView(tableRow, tableLayoutParams);
		}
		
		return tableLayout;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.buttonRUN:
			handleRun();
			break;
		case R.id.buttonRESET:
			handleReset();
			break;
		default:
			break;
		}
	}

	private void handleReset() {
		levelTextView.setText("");
		
		level1.reset();

		updateTable();
	}

	private void handleRun() {
		
		this.codeBlock = com.getCode();

		level1.RunCode(this.codeBlock);

		updateTable();

		if(level1.checkWin())
			levelTextView.setText("YOU WON!");
		else
			levelTextView.setText("YOU LOST!");
	}

	private void updateTable() {

		for (int i = 0; i < level1.getBoard().getBoardHeight(); i++) {

			TableRow tr = (TableRow) boardTableLayout.getChildAt(i);

			for (int j = 0; j < level1.getBoard().getBoardLength(); j++) {

				TextView textView = (TextView) tr.getChildAt(j);

				updateCell(i, j, textView);
			}
		}
	}

	private void updateCell(int i, int j, TextView textView) {

		textView.setText(level1.getBoard().getTileAtLoaction(new Coordinate(i, j)).getMark());

		if(level1.getHero().getLocation().equals(new Coordinate(i, j)))
			textView.setText(level1.getHero().getLook());
		else if(level1.getGoalTile().getLocation().equals(new Coordinate(i,j)))
			textView.setText(level1.getGoalTile().getMark());
	}

	public void setLevel(Scenario level) {
		level1 = (Level1) level;	// TODO
	}
}
