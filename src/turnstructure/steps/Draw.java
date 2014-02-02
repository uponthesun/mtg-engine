package turnstructure.steps;

import gamestate.Game;
import turnstructure.Step;

public class Draw implements Step {

	@Override
	public GivePriority beginningAction(Game game) {
		if(game.getTurnNumber() > 1) {
			game.getActivePlayer().drawCards(1);
		}
		return GivePriority.TRUE;
	}

	@Override
	public boolean allowSlowEffects() {
		return false;
	}
}
