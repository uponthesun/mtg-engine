import java.util.List;

import com.google.common.collect.ImmutableList;

public class Game {
	private final List<Player> players;
	
	public Game(Player ...players) {
		this.players = ImmutableList.copyOf(players);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void printGame() {
		int playerNumber = 1;
		for(Player player : this.players) {
			System.out.println("Player " + playerNumber);
			
			System.out.println(player.toString());
			
			playerNumber++;
		}
	}
}