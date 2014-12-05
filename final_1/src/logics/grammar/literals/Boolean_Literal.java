package logics.grammar.literals;

import logics.grammar.expression.Literal_Expression;
import logics.visitor.CodeVisitor;

public class Boolean_Literal extends Literal_Expression  {

	private boolean value;

	public Boolean_Literal() {
		value=false;
	}

	public Boolean_Literal(boolean value) {
		setValue(value);
	}
	
	public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    
    @Override
	public Boolean accept(CodeVisitor v) {
		return v.visit(this);
	}

}
