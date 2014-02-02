package turnstructure.steps;

import gamestate.Game;
import gamestate.Permanent;
import turnstructure.Step;

public class Cleanup implements Step {

	@Override
	public GivePriority beginningAction(Game game) {
		for(Permanent perm : game.allPermanents()) {
			perm.clearMarkedDamage();
		}
		
		return GivePriority.FALSE;
	}

	@Override
	public boolean allowSlowEffects() {
		return false;
	}
}