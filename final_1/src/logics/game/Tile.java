package logics.game;

public class Tile {

	private int rowCoord;
	private int colCoord;
	private String mark;

	public Tile(int rowCoord,int colCoord, String mark) {
		this.rowCoord=rowCoord;
		this.colCoord=colCoord;
		setMark(mark);
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
