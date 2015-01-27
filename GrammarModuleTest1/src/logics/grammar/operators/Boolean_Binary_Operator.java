package logics.grammar.operators;

public class Boolean_Binary_Operator extends Binary_Operator<Boolean> {

	public Boolean_Binary_Operator(BinaryOperatorEnum operator) {
		super(operator);
	}

	@Override
	public Boolean operate(Boolean right, Boolean left) {

		Boolean res = false;
		BinaryOperatorEnum op =getOperator();

		switch(op){

		case equals:
			if(right=left){res = true;}
			break;

		default:
			res=null;
			break;	
		}

		return res;
	}
	
	//TODO
	private Boolean operate(Integer right, Integer left) {

		Boolean res = false;
		BinaryOperatorEnum op =getOperator();
		
		switch(op){

		case equals:
			if(right==left){res = true;}
			break;
			
		case less:
			if(left<right){res = true;}
			break;

		default:
			res=null;
			break;	
		}

		return res;
		
	}

	public Boolean operate(Object right , Object left) {

		if(right instanceof Boolean && left instanceof Boolean)
			return operate((Boolean)right, (Boolean)left);
		else if(right instanceof Integer && left instanceof Integer)
			return operate((Integer)right, (Integer)left);
		else
			return null;
	}

}
