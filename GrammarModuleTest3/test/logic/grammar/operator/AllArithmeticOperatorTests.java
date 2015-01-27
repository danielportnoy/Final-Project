package logic.grammar.operator;

import logic.grammar.operator.arithmetic.MinusTest;
import logic.grammar.operator.arithmetic.PlusTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PlusTest.class,MinusTest.class })
public class AllArithmeticOperatorTests {

}
