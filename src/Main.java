import java.util.Map;

import com.google.common.collect.Maps;


public class Main {

	public static void main(String[] args) {
		
		Map<Card, Integer> decklist = Maps.newHashMap();
		decklist.put(Cards.GRIZZLY_BEARS, 40);
		decklist.put(Cards.FOREST, 20);
		
		Player p1 = new Player(Library.fromDeckList(decklist));
		
		Game game = new Game(p1);
		
		game.printGame();
	}
}