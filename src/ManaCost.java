import java.util.Map;

import com.google.common.collect.Maps;

public class ManaCost {
	private final Map<Mana, Integer> manaCost;

	public ManaCost() {
		this.manaCost = Maps.newHashMap();
	}
}