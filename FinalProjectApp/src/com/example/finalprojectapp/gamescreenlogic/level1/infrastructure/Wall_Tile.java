package com.example.finalprojectapp.gamescreenlogic.level1.infrastructure;

public class Wall_Tile extends Tile {

	public Wall_Tile(Coordinate location) {
		super("X",location,false);
	}
	
	public Wall_Tile(Wall_Tile other) {
		super(other);
	}

	@Override
	public Wall_Tile makeCopy() {
		return new Wall_Tile(this);
	}

}
