package com.example.finalprojectapp.test;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.activities.InventoryWingActivity;
import com.example.finalprojectapp.activities.MazeWingActivity;
import com.example.finalprojectapp.activities.TutorialActivity;
import com.example.finalprojectapp.activities.WingPickingActivity;

public class WingPickingActivityTest extends ActivityInstrumentationTestCase2<WingPickingActivity>{

	private static final long TIMEOUT_IN_MS = 10;

	private WingPickingActivity wingPickingActivity;
	private Button tutorialButton;
	private Button mazeWingButton;
	private Button inventoryWingButton;

	public WingPickingActivityTest() {
		super(WingPickingActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(true);

		wingPickingActivity = getActivity();
		tutorialButton = (Button)wingPickingActivity.findViewById(R.id.button_Tutorial);
		mazeWingButton = (Button)wingPickingActivity.findViewById(R.id.button_Maze_Wing);
		inventoryWingButton = (Button)wingPickingActivity.findViewById(R.id.button_Inventory_Wing);
	}

	/*
	 * Tests the preconditions of this test fixture.
	 */
	@LargeTest
	public void testPreconditions() {
		assertNotNull("wingPickingActivity is null", wingPickingActivity);
		assertNotNull("tutorialButton is null", tutorialButton);
		assertNotNull("mazeWingButton is null", mazeWingButton);
		assertNotNull("inventoryWingButton is null", inventoryWingButton);
	}

	@MediumTest
	public void testTutorialButton_click() {	

		// Set up an ActivityMonitor
		ActivityMonitor tutorialActivityMonitor =
				getInstrumentation().addMonitor(TutorialActivity.class.getName(),
						null, false);

		// Validate that ReceiverActivity is started
		TouchUtils.clickView(this, tutorialButton);

		TutorialActivity tutorialActivity = (TutorialActivity) 
				tutorialActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);

		assertNotNull("TutorialActivity is null", tutorialActivity);

		assertEquals("Monitor for TutorialActivity has not been called",1, tutorialActivityMonitor.getHits());

		assertEquals("Activity is of wrong type", TutorialActivity.class, tutorialActivity.getClass());

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(tutorialActivityMonitor);

		// Return to MainMenuActivity
		tutorialActivity.finish();
	}

	@MediumTest
	public void testMazeWingButton_click() {	

		// Set up an ActivityMonitor
		ActivityMonitor mazeWingActivityMonitor =
				getInstrumentation().addMonitor(MazeWingActivity.class.getName(),
						null, false);

		// Validate that ReceiverActivity is started
		TouchUtils.clickView(this, mazeWingButton);

		MazeWingActivity mazeWingActivity = (MazeWingActivity) 
				mazeWingActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);

		assertNotNull("MazeWingActivity is null", mazeWingActivity);

		assertEquals("Monitor for MazeWingActivity has not been called",1, mazeWingActivityMonitor.getHits());

		assertEquals("Activity is of wrong type", MazeWingActivity.class, mazeWingActivity.getClass());

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(mazeWingActivityMonitor);

		// Return to MainMenuActivity
		mazeWingActivity.finish();
	}

	@MediumTest
	public void testInventoryWingButton_click() {	

		// Set up an ActivityMonitor
		ActivityMonitor inventoryWingActivityMonitor =
				getInstrumentation().addMonitor(InventoryWingActivity.class.getName(),
						null, false);

		// Validate that ReceiverActivity is started
		TouchUtils.clickView(this, inventoryWingButton);

		InventoryWingActivity inventoryWingActivity = (InventoryWingActivity) 
				inventoryWingActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);

		assertNotNull("InventoryWingActivity is null", inventoryWingActivity);

		assertEquals("Monitor for InventoryWingActivity has not been called",1, inventoryWingActivityMonitor.getHits());

		assertEquals("Activity is of wrong type", InventoryWingActivity.class, inventoryWingActivity.getClass());

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(inventoryWingActivityMonitor);

		// Return to MainMenuActivity
		inventoryWingActivity.finish();
	}
}