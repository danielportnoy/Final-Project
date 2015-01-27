package logics.grammar.operators;

import logics.grammar.Identifier;
import logics.grammar.expression.Expression;
import main.DB;

public class Integer_Unary_Operator extends Unary_Operator<Integer> {

	public Integer_Unary_Operator(UnaryOperatorEnum operator) {
		super(operator);
	}

	@Override
	public Integer operate(Integer operand , Expression exp) {

		Integer res = 0;
		UnaryOperatorEnum op = getOperator();

		switch(op){

		case preIncrement:
		case posIncrement:
			res = operand+1;
			if(exp instanceof Identifier){DB.db.put(((Identifier)exp).getName(), res);}
			break;	

		default:
			res=null;
			break;	
		}

		return res;
	}

	public Integer operate(Object operand , Expression exp) {

		if(operand instanceof Integer )
			return operate((Integer)operand , exp);
		else
			return null;
	}

}
