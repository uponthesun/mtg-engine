package turnstructure.steps;

import gamestate.Game;
import turnstructure.Step;

public class CombatDamage implements Step {

	@Override
	public GivePriority beginningAction(Game game) {
		// TODO Auto-generated method stub
		return GivePriority.TRUE;
	}

	@Override
	public boolean allowSlowEffects() {
		return false;
	}

}
