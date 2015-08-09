package cards;
import gamestate.Game;
import gamestate.PayableEffect;

import java.util.Set;

import utils.IO;
import utils.Utils;

import com.google.common.base.Preconditions;

public abstract class Card implements PayableEffect {
	private final String name;
	private final ManaAmount manaCost;
	private final Set<SuperType> superType;
	private final Set<String> subType;
	private final String rulesText;
	private final Integer power;
	private final Integer toughness;

	private Card(final String name, final ManaAmount manaCost, final Set<SuperType> superTypes,
			final Set<String> subType, final String rulesText, final Integer power, final Integer toughness) {
		this.name = Preconditions.checkNotNull(name);
		this.manaCost = manaCost;
		this.superType = Preconditions.checkNotNull(superTypes);
		this.subType = Preconditions.checkNotNull(subType);
		this.rulesText = Preconditions.checkNotNull(rulesText);
		this.power = power;
		this.toughness = toughness;
	}

	public static Card creatureCard(final String name, final ManaAmount manaCost, final Set<SuperType> superTypes,
			final Set<String> subType, final String rulesText, final int power, final int toughness) {
		Preconditions.checkArgument(superTypes.contains(SuperType.CREATURE));
		//TODO: add implementation
		return new Card(name, manaCost, superTypes, subType, rulesText, power, toughness) {
			@Override
			public void applyEffect(final Game game) {
				IO.print("<Effect of %s>", getName());
			}
		};
	}

	public static Card noncreatureCard(final String name, final ManaAmount manaCost, final Set<SuperType> superTypes,
			final Set<String> subType, final String rulesText) {
		Preconditions.checkArgument(!superTypes.contains(SuperType.CREATURE));
		// TODO: add implementation
		return new Card(name, manaCost, superTypes, subType, rulesText, null, null) {
			@Override
			public void applyEffect(final Game game) {
				IO.print("<Effect of %s>", getName());
			}
		};
	}

	//TODO: actual cost implementation, blocked on mana
	@Override
	public boolean canPayCost(final Game game) {
		return true;
	}

	@Override
	public void payCost(final Game game) {
	}

	public String getName() {
		return name;
	}

	public ManaAmount getManaCost() {
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

	@Override
	public boolean equals(final Object o) {
		if(o == null || !(o instanceof Card)) {
			return false;
		}

		final Card c = (Card) o;
		return this.getName().equals(c.getName());
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
}