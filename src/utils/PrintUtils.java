package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static String cardsToStringDeduped(List<Card> cards) {		
		HashMap<Card, Integer> cardsWithCount = new HashMap<Card, Integer>();
		for(Card card : cards) {
			int prevCount = cardsWithCount.containsKey(card) ? cardsWithCount.get(card) : 0;
			cardsWithCount.put(card, prevCount+1);
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for(Map.Entry<Card, Integer> entry : cardsWithCount.entrySet()) {
			Card card = entry.getKey();
			int count = entry.getValue();
			if(count > 1) {
				builder.append(String.format("%dx ", count));
			}
			builder.append(card.getName());
			
			if(i < cardsWithCount.keySet().size() - 1) {
				builder.append(", ");
			}
			i++;
		}
		builder.append("]");
		return builder.toString();
	}
}