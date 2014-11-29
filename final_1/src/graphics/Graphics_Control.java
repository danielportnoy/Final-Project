package graphics;

import logics.game.Board;
import logics.game.Coordinate;
import logics.game.Tile;
import characters.Hero;

public class Graphics_Control {

	public static void drawBoard(Board board,Hero hero) {
		for (int i = 0; i < board.getBoardHeight(); i++) {
			for (int j = 0; j < board.getBoardLength(); j++) {
				
				Coordinate tempCoord = new Coordinate(i, j);

				if(hero.getLocation().equals(tempCoord))
					drawHero(hero);
				else
					drawTile(board.getTileAtLoaction(tempCoord));
			}

			System.out.println();
		}

		System.out.println();
	}

	private static void drawTile(Tile tile){
		System.out.print(tile.getMark()+"\t");
	}

	private static void drawHero(Hero hero){
		System.out.print(hero.getLook()+"\t");
	}
}