package logic.grammar.operator;

public abstract class Un_Op<R , T> extends Op{
	
	public Un_Op(String name) {
		super(name);
	}
	
	public abstract R run(T operand);
}
