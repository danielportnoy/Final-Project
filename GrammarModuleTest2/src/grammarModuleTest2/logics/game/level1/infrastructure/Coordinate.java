package grammarModuleTest2.logics.game.level1.infrastructure;

public class Coordinate {

	private int rowCoord;
	private int colCoord;

	public Coordinate( int rowCoord, int colCoord){
		this.rowCoord=rowCoord;
		this.colCoord=colCoord;
	}
	
	public Coordinate(Coordinate other) {
		this.rowCoord=other.rowCoord;
		this.colCoord=other.colCoord;
	}
	
	public Coordinate makeCopy() {
		return new Coordinate(this);
	}

	public int getRowCoord() {
		return rowCoord;
	}
	public void setRowCoord(int rowCoord) {
		this.rowCoord = rowCoord;
	}
	public int getColCoord() {
		return colCoord;
	}
	public void setColCoord(int colCoord) {
		this.colCoord = colCoord;
	}

	public void setCoordinate(int newRowCoord, int newColCoord) {
		setRowCoord(rowCoord);
		setColCoord(colCoord);
	}
	
	public boolean equals(Coordinate other){
		if(other!=null)
			if(this.getRowCoord() == other.getRowCoord() && this.getColCoord() == other.getColCoord())
				return true;
		return false;
	}

}
