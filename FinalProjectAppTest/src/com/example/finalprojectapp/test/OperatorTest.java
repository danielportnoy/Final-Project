package com.example.finalprojectapp.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.example.finalprojectapp.coderunning.exception.concrete.DivisionByZeroException;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.concrete.literal.BoolLiteralNode;
import com.example.finalprojectapp.node.concrete.literal.IntLiteralNode;
import com.example.finalprojectapp.node.concrete.operators.arithmetic.AdditionNode;
import com.example.finalprojectapp.node.concrete.operators.arithmetic.DivisionNode;
import com.example.finalprojectapp.node.concrete.operators.arithmetic.MultiplicationNode;
import com.example.finalprojectapp.node.concrete.operators.arithmetic.SubtractionNode;
import com.example.finalprojectapp.node.concrete.operators.logical.NotNode;
import com.example.finalprojectapp.node.concrete.operators.relational.EqualsNode;
import com.example.finalprojectapp.node.concrete.operators.relational.NotEqualsNode;

public class OperatorTest extends LogicTest {

	private final static int LEFT_NUMBER = 5;
	private final static int RIGHT_NUMBER = 3;
	private final static int ZERO = 0;

	private final static boolean FALSE = false, TRUE = true;

	private IntLiteralNode numberNodeLeft, numberNodeRight;
	private IntLiteralNode zeroNodeRight;

	private BoolLiteralNode falseBooleanNode, trueBooleanNode;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		numberNodeLeft = new IntLiteralNode(LEFT_NUMBER);
		numberNodeLeft.setOrder(0);

		numberNodeRight = new IntLiteralNode(RIGHT_NUMBER);
		numberNodeRight.setOrder(1);

		zeroNodeRight = new IntLiteralNode(ZERO);
		zeroNodeRight.setOrder(1);

		falseBooleanNode = new BoolLiteralNode(FALSE);
		falseBooleanNode.setOrder(0);

		trueBooleanNode = new BoolLiteralNode(TRUE);
		trueBooleanNode.setOrder(0);
	}

	private void initBinaryOperatorNode(Node n){
		n.setOrder(0);
		//n.setParent(root);
		//root.addChild(n, n.getOrder());

		n.addChild(numberNodeLeft, numberNodeLeft.getOrder());
		n.addChild(numberNodeRight, numberNodeRight.getOrder());
	}

	private void initUnaryOperatorNode(Node n, Node inner){
		n.setOrder(0);
		//n.setParent(root);
		//root.addChild(n, n.getOrder());

		n.addChild(inner, inner.getOrder());
	}

	@SmallTest
	public final void testAddition() {

		AdditionNode addisionNode = new AdditionNode(); 
		initBinaryOperatorNode(addisionNode);

		Integer expectedResult = LEFT_NUMBER + RIGHT_NUMBER;

		arithmeticTest(addisionNode, expectedResult);
	}

	@SmallTest
	public final void testSubtraction() {

		SubtractionNode subtractionNode = new SubtractionNode(); 
		initBinaryOperatorNode(subtractionNode);

		Integer expectedResult = LEFT_NUMBER - RIGHT_NUMBER;

		arithmeticTest(subtractionNode, expectedResult);
	}

	@SmallTest
	public final void testMultiplication() {

		MultiplicationNode multiplicationNode = new MultiplicationNode(); 
		initBinaryOperatorNode(multiplicationNode);

		Integer expectedResult = LEFT_NUMBER * RIGHT_NUMBER;

		arithmeticTest(multiplicationNode, expectedResult);
	}

	@SmallTest
	public final void testDivision() {

		DivisionNode divisionNode = new DivisionNode(); 
		initBinaryOperatorNode(divisionNode);

		Integer expectedResult = LEFT_NUMBER / RIGHT_NUMBER;

		arithmeticTest(divisionNode, expectedResult);
	}

	@SmallTest
	public final void testDivisionByZeroThroesException() {

		DivisionNode divisionNode = new DivisionNode(); 

		initBinaryOperatorNode(divisionNode);

		divisionNode.addChild(zeroNodeRight, zeroNodeRight.getOrder());

		Exception exception = null;

		try {
			divisionNode.run().getIntValue();
		} catch (Exception e) {
			e.printStackTrace();
			exception = e;
		}

		assertNotNull(exception);
		assertTrue("DivisionByZeroException was not thrown.", exception.getClass().equals(DivisionByZeroException.class));
	}

	@SmallTest
	public final void testEquals(){

		EqualsNode equalsNode = new EqualsNode(); 

		initBinaryOperatorNode(equalsNode);

		equalsNode.left_type = numberNodeLeft.getType();
		equalsNode.right_type = numberNodeRight.getType();

		Boolean expectedResult = LEFT_NUMBER == RIGHT_NUMBER;

		rationalTest(equalsNode, expectedResult);

	}

	@SmallTest
	public final void testNotEquals(){

		NotEqualsNode notEqualsNode = new NotEqualsNode(); 

		initBinaryOperatorNode(notEqualsNode);

		notEqualsNode.left_type = numberNodeLeft.getType();
		notEqualsNode.right_type = numberNodeRight.getType();

		Boolean expectedResult = LEFT_NUMBER != RIGHT_NUMBER;

		rationalTest(notEqualsNode, expectedResult);

	}

	@SmallTest
	public final void testNot_false(){

		NotNode notNode = new NotNode(); 

		initUnaryOperatorNode(notNode, falseBooleanNode);

		Boolean expectedResult = !FALSE;

		rationalTest(notNode, expectedResult);
	}

	@SmallTest
	public final void testNot_true(){

		NotNode notNode = new NotNode(); 

		initUnaryOperatorNode(notNode, trueBooleanNode);

		Boolean expectedResult = !TRUE;

		rationalTest(notNode, expectedResult);
	}
}