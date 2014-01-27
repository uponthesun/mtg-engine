import java.util.List;

import com.google.common.collect.Lists;


public class Player {
	
	public static final int STARTING_LIFE_TOTAL = 20;
	
	private final int lifeTotal;
	private final List<Card> hand;
	
	public Player() {
		this.lifeTotal = STARTING_LIFE_TOTAL;
		this.hand = Lists.newArrayList();
	}
	
	
}