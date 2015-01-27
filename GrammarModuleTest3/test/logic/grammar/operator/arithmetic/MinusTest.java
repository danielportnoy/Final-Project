package logic.grammar.operator.arithmetic;

import static org.junit.Assert.*;
import logic.grammar.operator.arithmetic.concrete.Minus;

import org.junit.Test;

public class MinusTest {

	@Test
	public void test() {
		Minus m = new Minus();

		Integer a = 2;
		Integer b = 3;

		Integer expected = -1;
		Integer actual = m.run(a, b);

		assertEquals(expected, actual);
	}

}
