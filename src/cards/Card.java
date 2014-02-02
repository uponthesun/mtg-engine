package cards;
import java.util.Set;

import utils.Utils;

import com.google.common.base.Preconditions;

public class Card {
	private final String name;
	private final ManaCost manaCost;
	private final Set<SuperType> superType;
	private final Set<String> subType;
	private final String rulesText;
	private final Integer power;
	private final Integer toughness;
	
	private Card(String name, ManaCost manaCost, Set<SuperType> superTypes, Set<String> subType, String rulesText,
			Integer power, Integer toughness) {
		this.name = Preconditions.checkNotNull(name);
		this.manaCost = manaCost;
		this.superType = Preconditions.checkNotNull(superTypes);
		this.subType = Preconditions.checkNotNull(subType);
		this.rulesText = Preconditions.checkNotNull(rulesText);
		this.power = power;
		this.toughness = toughness;
	}

	public static Card creatureCard(String name, ManaCost manaCost, Set<SuperType> superTypes, Set<String> subType, 
			String rulesText, int power, int toughness) {
		Preconditions.checkArgument(superTypes.contains(SuperType.CREATURE));
		return new Card(name, manaCost, superTypes, subType, rulesText, power, toughness);
	}
	
	public static Card noncreatureCard(String name, ManaCost manaCost, Set<SuperType> superTypes, Set<String> subType, 
			String rulesText) {
		Preconditions.checkArgument(!superTypes.contains(SuperType.CREATURE));
		return new Card(name, manaCost, superTypes, subType, rulesText, null, null);
	}
	
	public String getName() {
		return name;
	}

	public ManaCost getManaCost() {
		return manaCost;
	}

	public Set<SuperType> getSuperType() {
		return superType;
	}

	public Set<String> getSubType() {
		return subType;
	}

	public String getRulesText() {
		return rulesText;
	}

	public Integer getPower() {
		return power;
	}

	public Integer getToughness() {
		return toughness;
	}
	
	@Override
	public String toString() {
		return Utils.toStringFromGetters(this);
		/*return Objects.toStringHelper(this)
				.omitNullValues()
				.add("name", this.name)
				.add("cost", this.manaCost)
				.add("power", this.power)
				.add("toughness", this.toughness)
				.toString();*/
	}
}