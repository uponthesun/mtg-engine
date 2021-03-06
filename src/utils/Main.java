package utils;
import gamestate.Game;
import gamestate.HumanPlayer;
import gamestate.Library;
import gamestate.Player;

import java.util.Map;

import cards.Card;
import cards.Cards;

import com.google.common.collect.Maps;


public class Main {

	public static void main(String[] args) {
		Map<Card, Integer> decklist = Maps.newHashMap();
		decklist.put(Cards.GRIZZLY_BEARS, 40);
		decklist.put(Cards.FOREST, 20);
		
		Player p1 = new HumanPlayer("Player1", Library.fromDeckList(decklist));
		Player p2 = new HumanPlayer("Player2", Library.fromDeckList(decklist));
		
		Game game = new Game(p1, p2);
		
		game.gameLoop();
	}
}