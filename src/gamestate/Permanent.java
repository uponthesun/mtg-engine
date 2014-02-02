package gamestate;
import cards.Card;

public class Permanent {
	public enum TappedStatus {
		TAPPED,
		UNTAPPED;
	}

	public enum SummoningSickStatus {
		SUMMONING_SICK,
		NOT_SUMMONING_SICK;
	}
	
	private final Card card;
	private SummoningSickStatus summoningSickStatus;
	private TappedStatus tappedStatus;
	private int markedDamage;
	
	public Permanent(Card card) {
		this.card = card;
		this.summoningSickStatus = SummoningSickStatus.SUMMONING_SICK;
		this.tappedStatus = TappedStatus.UNTAPPED;
		this.markedDamage = 0;
	}

	public TappedStatus getTappedStatus() {
		return tappedStatus;
	}

	public Card getCard() {
		return card;
	}

	public int getMarkedDamage() {
		return markedDamage;
	}

	public SummoningSickStatus getSummoningSickStatus() {
		return summoningSickStatus;
	}

	/*********************************************************
	 * Mutators
	 *********************************************************/
	
	public void tap() {
		this.tappedStatus = TappedStatus.TAPPED;
	}
	
	public void untap() {
		this.tappedStatus = TappedStatus.UNTAPPED;
	}
	
	public void clearMarkedDamage() {
		this.markedDamage = 0;
	}
}