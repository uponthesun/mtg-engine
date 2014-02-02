package cards;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

public class ManaCost {
	private final Map<Mana, Integer> manaCost;

	private ManaCost(Map<Mana, Integer> manaCost) {
		this.manaCost = manaCost;
	}
	
	public static ManaCost manaCostFromSymbols(String manaCostString) {
		Map<Mana, Integer> manaCost = Maps.newHashMap();
		for(Mana mana : Mana.values()) {
			manaCost.put(mana, 0);
		}

		for(int i = 0; i < manaCostString.length(); i++) {
			char symbol = manaCostString.charAt(i);
			Mana mana = Mana.getManaForSymbol(symbol);
			if(mana != null) {
				manaCost.put(mana, manaCost.get(mana) + 1);
			} else {
				if(Character.isDigit(symbol)) {
					manaCost.put(Mana.COLORLESS, manaCost.get(Mana.COLORLESS) + Integer.valueOf(symbol + ""));
				} else {
					throw new RuntimeException("Unknown symbol: " + symbol);
				}
			}
		}

		return new ManaCost(manaCost);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("ManaCost", this.manaCost)
				.toString();
	}
}