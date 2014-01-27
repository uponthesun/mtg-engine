import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


public class Player {
	public static final int STARTING_LIFE_TOTAL = 20;
	
	private int lifeTotal;
	private final List<Card> hand;	
	private final Library library;
	private final List<Permanent> permanents;
	
	public Player(Library library) {
		this.lifeTotal = STARTING_LIFE_TOTAL;
		this.hand = Lists.newArrayList();
		this.library = Preconditions.checkNotNull(library);
		this.permanents = Lists.newArrayList();
	}

	public int getLifeTotal() {
		return lifeTotal;
	}

	public void setLifeTotal(int lifeTotal) {
		this.lifeTotal = lifeTotal;
	}

	public List<Card> getHand() {
		return hand;
	}

	public Library getLibrary() {
		return library;
	}

	public List<Permanent> getPermanents() {
		return permanents;
	}
	
	@Override
	public String toString() {
		return Utils.toStringFromGetters(this);
	}
}