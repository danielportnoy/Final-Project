package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
	}

	public static class MyPreferenceFragment extends PreferenceFragment
	{
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}

	/*@Override
	public void onBackPressed() {
		super.onBackPressed();

		Intent intent = new Intent(this, MainManuActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}*/

}
