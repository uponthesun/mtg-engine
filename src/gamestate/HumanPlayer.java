package gamestate;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import utils.IO;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class HumanPlayer extends Player {
	public HumanPlayer(final String name, final Library library) {
		super(name, library);
	}

	@Override
	public ReceivePriorityResult receivePriority(final Game game) {
		//TODO: implement
		IO.print("%s received priority", this.getName());

		final List<PayableEffect> legalMoves = Lists.newArrayList(Sets.newHashSet(this.findCurrentLegalMoves(game)));
		if(legalMoves.isEmpty()) {
			IO.print("No legal moves.");
			return ReceivePriorityResult.noAction();
		}

		IO.print("Legal moves for %s:", this.getName());

		for(int i = 0; i < legalMoves.size(); i++) {
			IO.print("[%d] %s", i, legalMoves.get(i));
		}

		final String input = IO.readLine();
		if(NumberUtils.isDigits(input)) {
			final int number = Integer.parseInt(input);
			if(number < legalMoves.size()) {
				return ReceivePriorityResult.action(legalMoves.get(number), false);
			}
		}

		return ReceivePriorityResult.noAction();
	}
}