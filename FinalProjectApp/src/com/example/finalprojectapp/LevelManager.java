package com.example.finalprojectapp;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;
import com.example.finalprojectapp.coderunning.snapshot.Snapshot;
import com.example.finalprojectapp.coderunning.testcase.TestCase;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
import com.example.finalprojectapp.codewriting.managment.CodeWritingManager;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.concrete.block.InitialBlockNode;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.scenario.configuration.Configuration;

public class LevelManager {


	private Scenario scenario;
	private InitialBlockNode rootNode;

	private CodeWritingManager codeWritingManager;
	private CodeRunningManager codeRunningManager;

	private static LevelManager instance = new LevelManager();

	private LevelManager() {
		reset();
	}

	public static LevelManager getInstance() {
		return instance;
	}

	public void reset() {
		rootNode = new InitialBlockNode();
		scenario = null;
		codeWritingManager = null;
		codeRunningManager = null;
	}

	public void loadScenario(Scenario scenario){
		this.scenario = scenario;
	}

	/******************* Register managers *******************/
	public void registerCodeWritingManager(CodeWritingManager codeWritingManager){
		this.codeWritingManager = codeWritingManager;
	}

	public void registerCodeRunningManager( CodeRunningManager codeRunningManager){
		this.codeRunningManager = codeRunningManager;
	}	
	/******************* Register managers *******************/



	/******************* GET *******************/
	public Scenario getScenario() {
		return scenario;
	}

	public InitialBlockNode getRootNode() {
		return rootNode;
	}

	public CodeRunningManager getCodeRunningManager() {
		return codeRunningManager;
	}

	public CodeWritingManager getCodeWritingManager() {
		return codeWritingManager;
	}
	/******************* GET *******************/

	/******************* Utility *******************/
	public boolean isCodeLinesValid() {
		//return codeWritingManager.getLogics().isCodeLinesValid();

		for (CodeWritingPart codePart : rootNode.getCodeWritingParts())
			if(codePart.getSetter()!=null && codePart.getSetter().isMandatory())
				return false;

		return true;
	}

	public void takeSnapshot(Node n) {
		codeRunningManager.getLogics().takeSnapshot(n);
	}

	public void clearCode() {
		rootNode = new InitialBlockNode();
	}
	
	public int getNumOfSnapshots(){
		return getCodeRunningManager().getLogics().getSnapshots().size();
	}

	/********** identifier utilities **********/
	public boolean getBooleanValueFromIdentifier(String identifier){
		return codeRunningManager.getLogics().getBool(identifier);
	}

	public int getInegerValueFromIdentifier(String identifier){
		return codeRunningManager.getLogics().getInt(identifier);
	}

	public void putBooleanValueToIdentifier(String identifier , boolean value){
		codeRunningManager.getLogics().putBool(identifier, value);
	}

	public void putIntegerValueToIdentifier(String identifier , int value){
		codeRunningManager.getLogics().putInt(identifier, value);
	}
	/********** identifier utilities **********/

	/********** refresh utilities **********/

	public void refrashWritingScreen(){
		codeWritingManager.refresh();
	}

	public void refrashRunningScreen(Snapshot snapshot){
		codeRunningManager.refresh(snapshot);
	}

	/********** refresh utilities **********/

	/********** game utilities **********/

	public boolean checkWin(TestCase testCase){
		return scenario.checkWin(testCase.getConfig(), testCase.getSnapshots().get(testCase.getSnapshots().size() - 1).gameSnapshot);
	}
	
	/********** game utilities **********/
	
	public void SetterClick(Setter setter) {
		codeWritingManager.SetterClick(setter);
	}

	public List<TestCase> runCodeTests() {
		
		List<TestCase> tests = new ArrayList<TestCase>();
		
		for (Configuration config : scenario.getConfigs()) {
			getScenario().setCurrentConfig(config);
			getScenario().reset();
			getCodeRunningManager().getLogics().reset();
			
			List<Snapshot> snapshots = getCodeRunningManager().getLogics().getSnapshots();
			
			MyException exception = null;
			
			try {
				getRootNode().run();
			} catch (MyException e) {
				exception = e;
			}

			TestCase t = new TestCase(config, snapshots, exception);
					
			tests.add(t);
		}
		
		return tests;
	}

	public void setEditable(Node makerNode) {
		codeWritingManager.getLogics().setCurrentMakerNode(makerNode);
	}

	public boolean isEditMode() {
		return codeWritingManager.getLogics().isEditMode();
	}

	public void setEditMode(boolean b) {
		codeWritingManager.getLogics().setEditMode(b);
	}

	/******************* Utility *******************/

}
