package com.example.finalprojectapp.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.example.finalprojectapp.coderunning.exception.MyException;
import com.example.finalprojectapp.node.concrete.block.BlockNode;
import com.example.finalprojectapp.node.concrete.identifier.IntIdentifier;
import com.example.finalprojectapp.node.concrete.literal.IntLiteralNode;
import com.example.finalprojectapp.node.concrete.operators.arithmetic.IncrementNode;
import com.example.finalprojectapp.node.concrete.operators.relational.LessThanNode;
import com.example.finalprojectapp.node.concrete.statements.ForNode;
import com.example.finalprojectapp.node.concrete.vardec.IntVarDecNode;

public class StatementTest extends LogicTest {

	private final static String I_NAME = "i";
	private final static int I_INITIAL_VALUE = 0;
	private final static int I_END_VALUE = 5;

	private IntVarDecNode IntINode;	
	private IntLiteralNode iInitialValue;
	private IntLiteralNode iEndValue;
	
	private IntIdentifier iIdentifier;

	private LessThanNode ltNode;

	private IncrementNode inc;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		IntINode = new IntVarDecNode(I_NAME);	
		
		iIdentifier = new IntIdentifier(I_NAME);

		iInitialValue = new IntLiteralNode(I_INITIAL_VALUE);

		ltNode = new LessThanNode();
		
		inc = new IncrementNode(I_NAME);

		iEndValue = new IntLiteralNode(I_END_VALUE);
	}

	@SmallTest
	public final void testPreConditions(){

		assertNotNull("IntINode is null", IntINode);
		assertNotNull("iIdentifier is null", iIdentifier);
		assertNotNull("iInitialValue is null", iInitialValue);
		assertNotNull("ltNode is null", ltNode);
		assertNotNull("inc is null", inc);
		assertNotNull("iEndValue is null", iEndValue);

	}

	@SmallTest
	public final void testFor(){

		ForNode forNode = new ForNode();
		forNode.setOrder(0);
		forNode.setParent(root);
		root.addChild(forNode, 0);

		IntINode.setOrder(0);
		IntINode.setParent(forNode);
		forNode.addChild(IntINode, IntINode.getOrder());
	
		iInitialValue.setOrder(0);
		iInitialValue.setParent(IntINode);
		IntINode.addChild(iInitialValue, iInitialValue.getOrder());

		IntINode.getParent().getScope().getIntegerIdentifiers().put(I_NAME, IntINode.getOrder());

		ltNode.setOrder(1);
		ltNode.setParent(forNode);
		forNode.addChild(ltNode, ltNode.getOrder());

		iIdentifier.setOrder(0);
		iIdentifier.setParent(ltNode);
		ltNode.addChild(iIdentifier, iIdentifier.getOrder());

		iEndValue.setOrder(1);
		iEndValue.setParent(ltNode);
		ltNode.addChild(iEndValue, iEndValue.getOrder());

		inc.setOrder(2);
		inc.setParent(forNode);

		forNode.addChild(inc, inc.getOrder());
		
		BlockNode block = new BlockNode();
		block.setOrder(3);
		block.setParent(forNode);
		
		forNode.addChild(block, block.getOrder());

		try {
			root.run();
			
			Integer expectedResult = I_END_VALUE;
			
			Integer actualResult = levelManager.getCodeRunningManager().getLogics().getInt(I_NAME);
			
			assertEquals(expectedResult, actualResult);

		} catch (MyException e) {
			e.printStackTrace();
			fail("Run failed.");
		}		
	}

}
