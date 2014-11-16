package graphics;

import logics.game.Board;
import logics.game.Tile;
import characters.Hero;

public class Graphics_Control {

	public static void drawBoard(Board board,Hero hero) {
		for (int i = 0; i < board.getTiles().length; i++) {
			for (int j = 0; j < board.getTiles()[i].length; j++) {

				if(hero.getRowCoord()==i & hero.getColCoord()==j)
					drawHero(hero);
				else
					drawTile(board.getTiles()[i][j]);
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