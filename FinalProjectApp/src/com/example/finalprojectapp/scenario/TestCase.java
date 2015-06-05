package com.example.finalprojectapp.scenario;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.coderunning.snapshot.Snapshot;

public class TestCase {

	private Configuration config;
	private List<Snapshot> snapshots;
	
	private MyException exception;

	public TestCase(Configuration config, List<Snapshot> snapshots, MyException exception) {
		this.config = config.copy();
		this.snapshots = new ArrayList<Snapshot>();
		this.exception = exception;

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

	public List<Snapshot> getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(List<Snapshot> snapshots) {
		this.snapshots = snapshots;
	}
	
	public MyException getException() {
		return exception;
	}
	
	public void setException(MyException exception) {
		this.exception = exception;
	}
}
