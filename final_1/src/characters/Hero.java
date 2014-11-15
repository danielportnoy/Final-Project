package characters;

public class Hero {

	private String look;

	private int rowCoord;
	private int colCoord;

	public Hero(String look , int rowCoord , int colCoord) {
		setLook(look);
		setRowCoord(rowCoord);
		setColCoord(colCoord);
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

	public String getLook() {
		return look;
	}

	public void setLook(String look) {
		this.look = look;
	}

}
