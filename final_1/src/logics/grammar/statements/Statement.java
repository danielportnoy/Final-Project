package logics.grammar.statements;

import logics.visitor.CodeVisitable;


/*import logics.grammar.types.Type;*/

public abstract class Statement implements CodeVisitable<Void> {

	public String name;

	public Statement(String name) {
		this.name = name;
	}

	/*public abstract Type Run();*/
}
