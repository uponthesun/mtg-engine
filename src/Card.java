import java.util.Set;

import com.google.common.base.Preconditions;

public abstract class Card {
	private final String name;
	private final ManaCost manaCost;
	private final Set<SuperTypes> superType;
	private final Set<String> subType;
	
	public Card(String name, ManaCost manaCost, Set<SuperTypes> superTypes, Set<String> subType) {
		this.name = Preconditions.checkNotNull(name);
		this.manaCost = Preconditions.checkNotNull(manaCost);
		this.superType = Preconditions.checkNotNull(superTypes);
		this.subType = Preconditions.checkNotNull(subType);
	}

	public String getName() {
		return name;
	}

	public ManaCost getManaCost() {
		return manaCost;
	}

	public Set<SuperTypes> getSuperType() {
		return superType;
	}

	public Set<String> getSubType() {
		return subType;
	}
}