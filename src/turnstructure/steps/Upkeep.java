package turnstructure.steps;

import gamestate.Game;
import turnstructure.Step;

public class Upkeep implements Step {

	@Override
	public GivePriority beginningAction(Game game) {
		return GivePriority.TRUE;
	}

	@Override
	public boolean allowSlowEffects() {
		return false;
	}
}