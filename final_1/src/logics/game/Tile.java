package logics.game;

public abstract class Tile {

	private int rowCoord;
	private int colCoord;
	private String mark;

	public Tile(int rowCoord,int colCoord) {
		this.rowCoord=rowCoord;
		this.colCoord=colCoord;
	}

	public int getRowCoord() {
		return rowCoord;
	}

	public int getColCoord() {
		return colCoord;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}

}
