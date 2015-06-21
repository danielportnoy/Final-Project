package com.example.finalprojectapp.test;

import com.example.finalprojectapp.activities.MainManuActivity;
import com.example.finalprojectapp.activities.SettingsActivity;
import com.example.finalprojectapp.activities.WingPickingActivity;
import com.example.finalprojectapp.R;

import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

public class MainManuActivityTest extends ActivityInstrumentationTestCase2<MainManuActivity>{

	private static final long TIMEOUT_IN_MS = 10;

	private MainManuActivity mainManuActivity;
	private Button playButton;
	private Button settingsButton;

	public MainManuActivityTest() {
		super(MainManuActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(true);

		mainManuActivity = getActivity();
		playButton = (Button)mainManuActivity.findViewById(R.id.buttonPLAY);
		settingsButton = (Button)mainManuActivity.findViewById(R.id.buttonSETTINGS);

	}

	/*
	 * Tests the preconditions of this test fixture.
	 */
	@LargeTest
	public void testPreconditions() {
		assertNotNull("mainManuActivity is null", mainManuActivity);
		assertNotNull("playButton is null", playButton);
		assertNotNull("settingsButton is null", settingsButton);
	}

	/*
	public void testPlayButton_labelText() {
		final String expected =
				mainManuActivity.getString(R.string.play);
		final String actual = playButton.getText().toString();
		assertEquals(expected, actual);
	}
	 */

	/*
	@SmallTest
	public void testPlayButton_layout() {

		final View decorView = mainManuActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, playButton);

		final ViewGroup.LayoutParams layoutParams =
				playButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}

	public void testSettingsButton_layout() {

		final View decorView = mainManuActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, settingsButton);

		final ViewGroup.LayoutParams layoutParams =
				settingsButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}

	public void testExitButton_layout() {

		final View decorView = mainManuActivity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, exitButton);

		final ViewGroup.LayoutParams layoutParams =
				exitButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}
	 */
	@MediumTest
	public void testPlayButton_click() {	

		// Set up an ActivityMonitor
		ActivityMonitor wingPickingActivityMonitor =
				getInstrumentation().addMonitor(WingPickingActivity.class.getName(),
						null, false);

		// Validate that ReceiverActivity is started
		TouchUtils.clickView(this, playButton);

		WingPickingActivity wingPickingActivity = (WingPickingActivity) 
				wingPickingActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);

		assertNotNull("WingPickingActivity is null", wingPickingActivity);

		assertEquals("Monitor for WingPickingActivity has not been called",1, wingPickingActivityMonitor.getHits());

		assertEquals("Activity is of wrong type", WingPickingActivity.class, wingPickingActivity.getClass());

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(wingPickingActivityMonitor);

		// Return to MainMenuActivity
		wingPickingActivity.finish();
	}

	@MediumTest
	public void testSettingsButton_click() {	

		// Set up an ActivityMonitor
		ActivityMonitor settingsActivityMonitor =
				getInstrumentation().addMonitor(SettingsActivity.class.getName(),
						null, false);

		// Validate that ReceiverActivity is started
		TouchUtils.clickView(this, settingsButton);

		SettingsActivity settingsActivity = (SettingsActivity) 
				settingsActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);

		assertNotNull("SettingsActivity is null", settingsActivity);

		assertEquals("Monitor for SettingsActivity has not been called",1, settingsActivityMonitor.getHits());

		assertEquals("Activity is of wrong type", SettingsActivity.class, settingsActivity.getClass());

		// Remove the ActivityMonitor
		getInstrumentation().removeMonitor(settingsActivityMonitor);

		// Return to MainMenuActivity
		settingsActivity.finish();
	}

}
