package cards;
import com.google.common.collect.Sets;

public class Cards {
	public static final Card GRIZZLY_BEARS = Card.creatureCard("Grizzly Bears", 
			ManaCost.manaCostFromSymbols("1G"), 
			Sets.newHashSet(SuperType.CREATURE), 
			Sets.newHashSet("Bear"), 
			"", 
			2, 
			2);
	
	public static final Card FOREST = Card.noncreatureCard("Forest",
			null,
			Sets.newHashSet(SuperType.LAND),
			Sets.newHashSet("Forest"),
			"");
}