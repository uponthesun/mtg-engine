package cards;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.MoreObjects;

// TODO: Rename this class, or separate it into multiple? Since it's also used for representing the player's mana pool
public class ManaAmount {
	private final Map<Mana, Integer> manaAmount;

	private ManaAmount(final Map<Mana, Integer> manaAmount) {
		this.manaAmount = manaAmount;
	}

	public static ManaAmount emptyManaPool() {
		final Map<Mana, Integer> manaAmount = new HashMap<>();
		// Initialize the map
		for(final Mana mana : Mana.values()) {
			manaAmount.put(mana, 0);
		}

		return new ManaAmount(manaAmount);
	}

	/**
	 * Parses the mana cost from the input string using the standard MTG mana cost notation
	 * (i.e. WUBRG for colored mana symbols, numbers for colorless). Example input:
	 * 2WUU = 2 colorless, 1 white, and 2 blue mana.
	 * @param manaCostString
	 * @return A typed and validated representation of a mana cost.
	 */
	public static ManaAmount manaCostFromSymbols(final String manaCostString) {
		final Map<Mana, Integer> manaAmount = new HashMap<>();
		// Initialize the map
		for(final Mana mana : Mana.values()) {
			manaAmount.put(mana, 0);
		}

		for(int i = 0; i < manaCostString.length(); i++) {
			final char symbol = manaCostString.charAt(i);
			final Mana mana = Mana.getManaForSymbol(symbol);
			if(mana != null) {
				manaAmount.put(mana, manaAmount.get(mana) + 1);
			} else {
				if(Character.isDigit(symbol)) {
					manaAmount.put(Mana.COLORLESS,
							manaAmount.get(Mana.COLORLESS) + Integer.valueOf(Character.toString(symbol)));
				} else {
					throw new RuntimeException("Unknown symbol: " + symbol);
				}
			}
		}

		return new ManaAmount(manaAmount);
	}

	public void addMana(final Mana mana) {
		this.manaAmount.put(mana, this.manaAmount.get(mana) + 1);
	}

	public ManaAmount deepCopy() {
		final Map<Mana, Integer> newMap = new HashMap<>();
		for(final Map.Entry<Mana, Integer> entry : this.manaAmount.entrySet()) {
			newMap.put(entry.getKey(), entry.getValue());
		}

		return new ManaAmount(newMap);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("ManaCost", this.manaAmount)
				.toString();
	}
}