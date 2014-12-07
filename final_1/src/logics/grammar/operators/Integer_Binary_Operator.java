package logics.grammar.operators;

public class Integer_Binary_Operator extends Binary_Operator<Integer> {


	public Integer_Binary_Operator(BinOperatorEnum operator) {
		super(operator);
	}

	public Integer operate(Integer right , Integer left) {

		Integer res = 0;
		BinOperatorEnum op =getOperator();

		switch(op){

		case plus:
			res = right + left;
			break;	

		default:
			res=null;
			break;	
		}

		return res;
	}

	public Integer operate(Object right , Object left) {
		
		if(right instanceof Integer && left instanceof Integer)
			return operate((Integer)right, (Integer)left);
		else
			return null;
	}
}
