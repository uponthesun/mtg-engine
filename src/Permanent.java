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

	public void setTappedStatus(TappedStatus tappedStatus) {
		this.tappedStatus = tappedStatus;
	}

	public Card getCard() {
		return card;
	}

	public int getMarkedDamage() {
		return markedDamage;
	}

	public void setMarkedDamage(int markedDamage) {
		this.markedDamage = markedDamage;
	}

	public SummoningSickStatus getSummoningSickStatus() {
		return summoningSickStatus;
	}

	public void setSummoningSickStatus(SummoningSickStatus summoningSickStatus) {
		this.summoningSickStatus = summoningSickStatus;
	}
}