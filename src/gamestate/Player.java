package gamestate;
import java.util.ArrayList;
import java.util.List;

import utils.Utils;
import cards.Card;
import cards.ManaAmount;
import cards.SuperType;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public abstract class Player {
	public static final int STARTING_LIFE_TOTAL = 20;
	public static final int STARTING_HAND_SIZE = 7;

	//TODO: enforce uniqueness or add ID?
	private final String name;

	private final int lifeTotal;
	private final List<Card> hand;
	private final Library library;
	private final List<Permanent> permanents;

	private final List<ManaSource> manaSources;
	private final ManaAmount manaPool;

	public Player(final String name, final Library library) {
		this.name = Preconditions.checkNotNull(name);
		this.lifeTotal = STARTING_LIFE_TOTAL;
		this.hand = new ArrayList<>();
		this.library = Preconditions.checkNotNull(library);
		this.permanents = new ArrayList<>();
		this.manaSources = new ArrayList<>();
		this.manaPool = ManaAmount.emptyManaPool();
	}

	public static class ReceivePriorityResult {
		private final Effect effect;
		private final boolean retainPriority;

		private ReceivePriorityResult(final Effect effect, final boolean retainPriority) {
			this.effect = effect;
			this.retainPriority = retainPriority;
		}

		public static ReceivePriorityResult noAction() {
			return new ReceivePriorityResult(null, false);
		}

		public static ReceivePriorityResult action(final Effect effect, final boolean retainPriority) {
			Preconditions.checkNotNull(effect);
			return new ReceivePriorityResult(effect, retainPriority);
		}

		public Effect getEffect() {
			return effect;
		}

		public boolean retainPriority() {
			return retainPriority;
		}

		public boolean actionTaken() {
			return this.effect != null;
		}
	}

	public abstract ReceivePriorityResult receivePriority(Game game);

	public String getName() {
		return name;
	}

	public int getLifeTotal() {
		return lifeTotal;
	}

	public List<Card> getHand() {
		return Lists.newArrayList(hand);
	}

	public Library getLibrary() {
		return library;
	}

	public List<Permanent> getPermanents() {
		return Lists.newArrayList(permanents);
	}

	public void drawCards(final int numCards) {
		final List<Card> newCards = this.library.removeCardsFromTop(numCards);
		this.hand.addAll(newCards);
	}

	/* Determines if this player is able to pay for the given mana cost from the current game state.
	 * This includes mana currently in the player's mana pool, and all activated abilities of permanents
	 * the player controls, but not spells that the player could cast which would add mana to the mana pool.
	 */
	private boolean canPayManaCost(final ManaAmount manaCost) {



		return false;
	}

	// TODO: should this be moved into Game?
	private boolean canCast(final Card card, final Game game) {
		if(!card.canPayCost(game)) {
			return false;
		}

		if(card.getSuperType().contains(SuperType.INSTANT)) {
			return true;
		}

		// Non-instant spells can only be cast on your own main phase when the stack is empty
		return game.getCurrentStep().allowSlowEffects() && game.getStack().isEmpty() &&
				game.getActivePlayer().equals(this);
	}

	protected List<PayableEffect> findCurrentLegalMoves(final Game game) {
		final List<PayableEffect> legalMoves = Lists.newArrayList();

		for(final Card card : this.hand) {
			if(canCast(card, game)) {
				legalMoves.add(card);
			}
		}

		for(final Permanent permanent : this.permanents) {
			for(final PayableEffect ability : permanent.getActivatedAbilities()) {
				if(ability.canPayCost(game)) {
					legalMoves.add(ability);
				}
			}
		}

		return legalMoves;
	}

	@Override
	public String toString() {
		return Utils.toStringFromGetters(this);
	}
}