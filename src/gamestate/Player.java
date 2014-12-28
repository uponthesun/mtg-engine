package gamestate;
import java.util.List;

import utils.Utils;
import cards.Card;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


public abstract class Player {
	public static final int STARTING_LIFE_TOTAL = 20;
	public static final int STARTING_HAND_SIZE = 7;
	
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

	public static class ReceivePriorityResult {
		private final Effect effect;
		private final Boolean retainPriority;
		
		private ReceivePriorityResult(Effect effect, Boolean retainPriority) {
			this.effect = effect;
			this.retainPriority = retainPriority;
		}
		
		public static ReceivePriorityResult noAction() {
			return new ReceivePriorityResult(null, null);
		}
		
		public static ReceivePriorityResult action(Effect effect, boolean retainPriority) {
			Preconditions.checkNotNull(effect);
			return new ReceivePriorityResult(effect, retainPriority);
		}

		public Effect getEffect() {
			return effect;
		}

		public Boolean retainPriority() {
			return retainPriority;
		}
		
		public boolean actionTaken() {
			return this.effect != null;
		}
	}
	
	public abstract ReceivePriorityResult receivePriority(Game game);
	
	public int getLifeTotal() {
		return lifeTotal;
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

	public void drawCards(int numCards) {
		List<Card> newCards = this.library.removeCardsFromTop(numCards);
		this.hand.addAll(newCards);
	}
	
	@Override
	public String toString() {
		return Utils.toStringFromGetters(this);
	}
}