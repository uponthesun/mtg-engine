package turnstructure.steps;

import turnstructure.Step;
import gamestate.Game;

public class MainPhase implements Step {
	@Override
	public GivePriority beginningAction(Game game) {
		return GivePriority.TRUE;
	}

	@Override
	public boolean allowSlowEffects() {
		return true;
	}
}