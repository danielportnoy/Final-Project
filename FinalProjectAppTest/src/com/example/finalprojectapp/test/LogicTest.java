package com.example.finalprojectapp.test;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.coderunning.managment.CodeRunningLogicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;
import com.example.finalprojectapp.codewriting.managment.CodeWritingLogicUnit;
import com.example.finalprojectapp.codewriting.managment.CodeWritingManager;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.concrete.block.InitialBlockNode;
import com.example.finalprojectapp.scenario.concrete.emptyScenario;

import junit.framework.TestCase;

public abstract class LogicTest extends TestCase {
	
	protected InitialBlockNode root;

	protected LevelManager levelManager = LevelManager.getInstance();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp(); 
		
		levelManager.reset(); 

		CodeWritingLogicUnit writingLogics = new CodeWritingLogicUnit();
		CodeWritingManager writingManager = new CodeWritingManager(writingLogics, null);

		levelManager.registerCodeWritingManager(writingManager);

		CodeRunningLogicUnit runningLogics = new CodeRunningLogicUnit();

		CodeRunningManager runningManager = new CodeRunningManager(runningLogics, null);
		levelManager.registerCodeRunningManager(runningManager);
		
		levelManager.loadScenario(new emptyScenario());

		root = levelManager.getRootNode();
	}
	
	public final void arithmeticTest(Node n, Integer expectedResult){
		
		Integer actualResult = null;

		try {
			actualResult = n.run().getIntValue();
		} catch (MyException e) {
			e.printStackTrace();
			fail();
		}
		
		assertEquals(expectedResult, actualResult);		
	}
	
	public final void rationalTest(Node n, Boolean expectedResult){
		
		Boolean actualResult = null;

		try {
			actualResult = n.run().getBoolValue();
		} catch (MyException e) {
			e.printStackTrace();
			fail();
		}
		
		assertEquals(expectedResult, actualResult);	
	}


}
