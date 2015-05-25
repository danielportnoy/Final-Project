package com.example.finalprojectapp.scenario;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.coderunning.snapshot.Snapshot;
import com.example.finalprojectapp.coderunning.varvalues.VarValues;

public class TestCase {

	private Configuration config;
	private VarValues values;
	private List<Snapshot> snapshots;

	public TestCase(Configuration config, VarValues values, List<Snapshot> snapshots) {
		this.config = config.copy();
		this.values = new VarValues(values);
		this.snapshots = new ArrayList<Snapshot>();

		for (Snapshot snapshot : snapshots) {
			this.snapshots.add(new Snapshot(snapshot));
		}
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public VarValues getValues() {
		return values;
	}

	public void setValues(VarValues values) {
		this.values = values;
	}

	public List<Snapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<Snapshot> snapshots) {
		this.snapshots = snapshots;
	}

	public boolean isWin(){
		return snapshots.get(snapshots.size() - 1).gameSnapshot.checkWin(config);
	}
}
