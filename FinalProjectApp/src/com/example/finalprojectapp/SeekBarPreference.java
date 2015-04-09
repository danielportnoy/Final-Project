package com.example.finalprojectapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBarPreference extends Preference implements OnSeekBarChangeListener {

	private int mDefaultValue;

	private int mMaxValue;
	private int mMinValue;

	private int mCurrentValue;

	private int mInterval;

	private SeekBar mSeekBar;

	private TextView mStatusText;

	public SeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPreference(context, attrs);
		setWidgetLayoutResource(R.layout.seek_bar_preference);
	}

	public SeekBarPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPreference(context, attrs);
		setWidgetLayoutResource(R.layout.seek_bar_preference);
	}

	private void initPreference(Context context, AttributeSet attrs) {
		getValuesFromXml(attrs);
		mSeekBar = new SeekBar(context, attrs);
		mSeekBar.setMax(mMaxValue - mMinValue);
		mSeekBar.setOnSeekBarChangeListener(this);
	}

	private void getValuesFromXml(AttributeSet attrs) {
		mMaxValue = attrs.getAttributeIntValue(Constants.ANDROIDNS, "max", 1);
		mMinValue = attrs.getAttributeIntValue(Constants.APPLICATIONNS, "min", 1);
		mDefaultValue = attrs.getAttributeIntValue(Constants.ANDROIDNS, "defaultValue", 1);
		mInterval = attrs.getAttributeIntValue(Constants.APPLICATIONNS, "interval", 1);
	}

	@Override
	protected View onCreateView(ViewGroup parent) {
		View view = super.onCreateView(parent);

		// The basic preference layout puts the widget frame to the right of the title and summary,
		// so we need to change it a bit - the seekbar should be under them.
		LinearLayout layout = (LinearLayout) view;
		layout.setOrientation(LinearLayout.VERTICAL);

		return view;
	}

	@Override
	public void onBindView(View view) {
		super.onBindView(view);

		// move our seekbar to the new view we've been given
		ViewParent oldContainer = mSeekBar.getParent();
		ViewGroup newContainer = (ViewGroup) view.findViewById(R.id.seekBarPrefBarContainer);

		if (oldContainer != newContainer) {
			// remove the seekbar from the old view
			if (oldContainer != null) {
				((ViewGroup) oldContainer).removeView(mSeekBar);
			}
			// remove the existing seekbar (there may not be one) and add ours
			newContainer.removeAllViews();
			newContainer.addView(mSeekBar, ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		//if dependency is false from the beginning, disable the seek bar
		if (view != null && !view.isEnabled())
			mSeekBar.setEnabled(false);

		updateView(view);
	}

	/**
	 * Update a SeekBarPreference view with our current state
	 * @param view
	 */
	protected void updateView(View view) {

		mStatusText = (TextView) view.findViewById(R.id.seekBarPrefValue);

		mStatusText.setText(String.valueOf(mCurrentValue));
		//mStatusText.setMinimumWidth(30);

		mSeekBar.setProgress(mCurrentValue - mMinValue);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		int newValue = progress + mMinValue;

		if(newValue > mMaxValue)
			newValue = mMaxValue;
		else if(newValue < mMinValue)
			newValue = mMinValue; 
		else if(mInterval != 1 && newValue % mInterval != 0)
			newValue = Math.round(((float)newValue)/mInterval)*mInterval;  

		// change rejected, revert to the previous value
		if(!callChangeListener(newValue)){
			seekBar.setProgress(mCurrentValue - mMinValue); 
			return; 
		}

		// change accepted, store it
		mCurrentValue = newValue;
		mStatusText.setText(String.valueOf(newValue));
		persistInt(newValue);

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		notifyChanged();
	}


	@Override 
	protected Object onGetDefaultValue(TypedArray ta, int index){

		int defaultValue = ta.getInt(index, mDefaultValue);
		return defaultValue;

	}

	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {

		if(restoreValue) 
			mCurrentValue = getPersistedInt(mCurrentValue);
		else {
			int temp = (Integer)defaultValue;
			persistInt(temp);
			mCurrentValue = temp;
		}
	}

	/**
	 * make sure that the seekbar is disabled if the preference is disabled
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		mSeekBar.setEnabled(enabled);
	}

	@Override
	public void onDependencyChanged(Preference dependency, boolean disableDependent) {
		super.onDependencyChanged(dependency, disableDependent);
		//Disable movement of seek bar when dependency is false
		if (mSeekBar != null)
			mSeekBar.setEnabled(!disableDependent);
	}
}