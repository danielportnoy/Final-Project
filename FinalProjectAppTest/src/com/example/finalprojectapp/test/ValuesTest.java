package com.example.finalprojectapp.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.literal.IntLiteralNode;
import com.example.finalprojectapp.node.concrete.operators.assignment.SimpleAssignmentNode;
import com.example.finalprojectapp.node.concrete.vardec.IntVarDecNode;

public class ValuesTest extends LogicTest {

	private final static String I_NAME = "i";
	private final static int I_INITIAL_VALUE = 7;
	private final static int I_SET_VALUE = -4;
	private final static Type I_TYPE = Type.Int;

	private IntVarDecNode IntINode;	
	private IntLiteralNode iInitialValue;

	private IntLiteralNode iSetValues;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		IntINode = new IntVarDecNode(I_NAME);

		IntINode.setOrder(0);
		IntINode.setParent(root);
		root.addChild(IntINode, IntINode.getOrder());

		iInitialValue = new IntLiteralNode(I_INITIAL_VALUE);

		iInitialValue.setOrder(0);
		iInitialValue.setParent(IntINode);
		IntINode.addChild(iInitialValue, iInitialValue.getOrder());

		IntINode.getParent().getScope().getIntegerIdentifiers().put(I_NAME, IntINode.getOrder());

		iSetValues = new IntLiteralNode(I_SET_VALUE);
		iSetValues.setOrder(0);
	}

	@SmallTest
	public final void testPreConditions(){

		try {
			root.run();
		} catch (MyException e) {
			e.printStackTrace();
			fail("Run failed.");
		}		

		Integer expectedResult = I_INITIAL_VALUE;

		Integer actualResult = levelManager.getCodeRunningManager().getLogics().getInt(I_NAME);

		assertEquals(expectedResult, actualResult);

	}

	@SmallTest
	public final void testSimpleAssignment_number(){

		SimpleAssignmentNode simpleAssignmentNode = new SimpleAssignmentNode(I_TYPE , I_NAME);
		simpleAssignmentNode.setOrder(1);
		simpleAssignmentNode.setParent(root);
		root.addChild(simpleAssignmentNode, simpleAssignmentNode.getOrder());

		iSetValues.setParent(simpleAssignmentNode);
		simpleAssignmentNode.addChild(iSetValues, iSetValues.getOrder());

		try {
			root.run();
		} catch (MyException e) {
			e.printStackTrace();
			fail("Run failed.");
		}		

		Integer expectedResult = I_SET_VALUE;

		Integer actualResult = levelManager.getCodeRunningManager().getLogics().getInt(I_NAME);

		assertEquals(expectedResult, actualResult);

	}

}
