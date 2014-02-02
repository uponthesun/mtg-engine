package gamestate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utils.PrintUtils;
import cards.Card;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class Library {
	private final LinkedList<Card> cardsInLibrary;

	private Library(List<Card> library) {
		this.cardsInLibrary = Lists.newLinkedList(Preconditions.checkNotNull(library));
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

	public List<Card> removeCardsFromTop(int numCards) {
		List<Card> cards = Lists.newArrayList();
		
		for(int n = 0; n < numCards; n++) {
			cards.add(this.cardsInLibrary.pop());
		}
		
		return cards;
	}
	
	public void shuffle() {
		Collections.shuffle(this.cardsInLibrary);
	}
	
	@Override
	public String toString() {
		return PrintUtils.cardsToString(this.cardsInLibrary);
	}
}