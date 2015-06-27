package com.example.finalprojectapp.coderunning.testcase;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.coderunning.snapshot.Snapshot;
import com.example.finalprojectapp.scenario.configuration.Configuration;

/**
 * Test results of running the current code with a specific configuration of the system. 
 * @author daniel portnoy
 *
 */
public class TestCase {

	private Configuration config;
	private List<Snapshot> snapshots;
	
	private MyException exception;

	/** 
	 * @param config - Current configuration of the system.
	 * @param snapshots - System Snapshots created after running.
	 * @param exception - Possible thrown exception. might be null.
	 */
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
