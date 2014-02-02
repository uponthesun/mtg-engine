package turnstructure;

import gamestate.Game;

public interface Step {
	/*public enum StepType {
		UNTAP,
		UPKEEP,
		DRAW,
		MAIN,
		BEGINNING_OF_COMBAT,
		DECLARE_ATTACKERS,
		DECLARE_BLOCKERS,
		COMBAT_DAMAGE,
		END_OF_COMBAT,
		END,
		CLEANUP
	}
	
	private final StepType stepType;

	public Step(StepType stepType) {
		this.stepType = Preconditions.checkNotNull(stepType);
	}
	
	public StepType getStepType() {
		return stepType;
	}*/
	
	public enum GivePriority {
		TRUE,
		FALSE
	}
	
	public GivePriority beginningAction(Game game);
	public boolean allowSlowEffects();
}