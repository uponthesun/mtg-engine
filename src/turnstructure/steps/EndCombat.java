package turnstructure.steps;

import gamestate.Game;
import turnstructure.Step;

public class EndCombat implements Step {

	@Override
	public GivePriority beginningAction(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean allowSlowEffects() {
		return false;
	}
}