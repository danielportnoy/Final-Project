package com.example.finalprojectapp;

import com.example.finalprojectapp.coderunning.CodeRunningManager;
import com.example.finalprojectapp.codewriting.managment.CodeWritingManager;
import com.example.finalprojectapp.node.concrete.block.InitialBlockNode;
import com.example.finalprojectapp.scenario.Scenario;

public class LevelManager {

	private Scenario scenario;
	private InitialBlockNode rootNode;

	private CodeWritingManager codeWritingManager;
	
	private CodeRunningManager codeRunningManager;

	private static LevelManager instance = new LevelManager();

	private LevelManager() {
		rootNode = new InitialBlockNode();
		scenario = null;
		codeWritingManager = null;
		codeRunningManager = null;
	}

	public static LevelManager getInstance() {
		return instance;
	}

	public void reset() {
		instance = new LevelManager();
	}

	public void loadScenario(Scenario scenario){
		this.scenario = scenario;
	}

	public void registerCodeWritingManager(CodeWritingManager codeWritingManager){
		this.codeWritingManager = codeWritingManager;
	}
	
	public void registerCodeRunningManager( CodeRunningManager codeRunningManager){
		this.codeRunningManager = codeRunningManager;
	}

	public Scenario getScenario() {
		return scenario;
	}

	public InitialBlockNode getRootNode() {
		return rootNode;
	}

	public CodeWritingManager getCodeWritingManager() {
		return codeWritingManager;
	}
	
	public CodeRunningManager getCodeRunningManager() {
		return codeRunningManager;
	}

}
