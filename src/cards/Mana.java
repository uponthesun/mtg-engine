package cards;
import java.util.Map;

import com.google.common.collect.Maps;

public enum Mana {
	WHITE('W'),
	BLUE('U'),
	BLACK('B'),
	RED('R'),
	GREEN('G'),
	COLORLESS('C');
	
	private static final Map<Character, Mana> symbolToMana = Maps.newHashMap();
	
	static {
		for(Mana mana : Mana.values()) {
			symbolToMana.put(mana.getSymbol(), mana);
		}
	}
	
	private final char symbol;

	private Mana(char symbol) {
		this.symbol = symbol;
	}
	
	public static final Mana getManaForSymbol(char symbol) {
		return symbolToMana.get(symbol);
	}
	
	public char getSymbol() {
		return this.symbol;
	}
}