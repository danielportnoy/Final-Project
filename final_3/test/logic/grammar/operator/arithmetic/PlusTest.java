package logic.grammar.operator.arithmetic;

import static org.junit.Assert.*;
import logic.grammar.operator.arithmetic.concrete.Plus;

import org.junit.Test;

public class PlusTest {

	@Test
	public void testRunIntegerInteger() {		
		Plus p = new Plus();
		
		Integer a = 2;
		Integer b = 3;
		
		Integer expected = 5;
		Integer actual = p.run(a, b);
		
		assertEquals(expected, actual);
		
	}

}
