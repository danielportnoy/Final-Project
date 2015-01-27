package final_2.logics.grammar.type;

public class Primitive_Type extends Type{
	
	public enum Primitive {
        Boolean, Char, Byte, Short, Int, Long, Float, Double;
        
        @Override
        public String toString() {
        	return name();
        }
    }

    private Primitive type;

    public Primitive_Type(Primitive type) {
        this.type = type;
    }

    public Primitive getType() {
        return type;
    }

    public void setType(Primitive type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return getType().toString();
	}

}
