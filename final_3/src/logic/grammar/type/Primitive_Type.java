package logic.grammar.type;

public class Primitive_Type extends Type{
	
	public enum Primitive {
		Int, Boolean,
		//Char, Byte, Short, Long, Float, Double
		;
        
        @Override
        public String toString() {
        	return name().toLowerCase();
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
