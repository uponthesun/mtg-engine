import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


public class Library {
	private final ArrayList<Card> cardsInLibrary;

	private Library(ArrayList<Card> library) {
		this.cardsInLibrary = Preconditions.checkNotNull(library);
	}
	
	public static Library fromDeckList(Map<Card, Integer> decklist) {
		ArrayList<Card> cards = Lists.newArrayList();

		for(Map.Entry<Card, Integer> entry : decklist.entrySet()) {
			for(int n = 0; n < entry.getValue(); n++) {
				cards.add(entry.getKey());
			}
		}
		
		return new Library(cards);
	}

	public ArrayList<Card> getCardsInLibrary() {
		return cardsInLibrary;
	}
	
	public void shuffle() {
		Collections.shuffle(this.cardsInLibrary);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("[");

		int i = 0;
		for(Card card : this.cardsInLibrary) {
			builder.append(card.getName());
			
			if(i < this.cardsInLibrary.size() - 1) {
				builder.append(", ");
			}
			i++;
		}
		builder.append("]");
		
		//return Utils.toStringFromGetters(this);
		return builder.toString();
	}
}