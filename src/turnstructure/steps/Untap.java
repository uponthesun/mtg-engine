package turnstructure.steps;

import gamestate.Game;
import gamestate.Permanent;
import turnstructure.Step;

public class Untap implements Step {

	@Override
	public GivePriority beginningAction(Game game) {
		for(Permanent perm : game.getActivePlayer().getPermanents()) {
			perm.untap();
		}
		return GivePriority.FALSE;
	}

	@Override
	public boolean allowSlowEffects() {
		return false;
	}
}