package utils;

import java.util.List;

import cards.Card;

public class PrintUtils {

	public static String cardsToString(List<Card> cards) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("[");

		int i = 0;
		for(Card card : cards) {
			builder.append(card.getName());
			
			if(i < cards.size() - 1) {
				builder.append(", ");
			}
			i++;
		}
		builder.append("]");
		
		return builder.toString();
	}
}