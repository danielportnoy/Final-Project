package logics.visitor;

public interface CodeVisitable<R> {
	
	public R accept(CodeVisitor v);

}
