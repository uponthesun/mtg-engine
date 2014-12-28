package gamestate;
import gamestate.Player.ReceivePriorityResult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import turnstructure.Step;
import turnstructure.Step.GivePriority;
import turnstructure.steps.BeginCombat;
import turnstructure.steps.Cleanup;
import turnstructure.steps.CombatDamage;
import turnstructure.steps.DeclareAttackers;
import turnstructure.steps.DeclareBlockers;
import turnstructure.steps.Draw;
import turnstructure.steps.End;
import turnstructure.steps.EndCombat;
import turnstructure.steps.MainPhase;
import turnstructure.steps.Untap;
import turnstructure.steps.Upkeep;
import utils.IO;
import utils.PrintUtils;

public class Game {
	private int turnNumber;
	private final Player[] players;
	private int activePlayerIndex;
	
	private final Deque<Effect> stack = new ArrayDeque<>();
	
	public Game(Player ...players) {
		this.players = players;
		this.turnNumber = 1;
		this.activePlayerIndex = 0;
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public int getTurnNumber() {
		return turnNumber;
	}
	
	public Player getActivePlayer() {
		return this.players[this.activePlayerIndex];
	}

	public List<Permanent> allPermanents() {
		return this.allPermanentsForPlayers(this.players);
	}
	
	public List<Permanent> allPermanentsForPlayers(Player... players) {
		List<Permanent> permanents = new ArrayList<Permanent>();
		for(Player player : players) {
			permanents.addAll(player.getPermanents());
		}
		return permanents;
	}
	
	private static final Step[] STANDARD_TURN_STEPS = {
		new Untap(),
		new Upkeep(),
		new Draw(),
		new MainPhase(),
		new BeginCombat(),
		new DeclareAttackers(),
		new DeclareBlockers(),
		new CombatDamage(),
		new EndCombat(),
		new MainPhase(),
		new End(),
		new Cleanup()
	};

	public void gameLoop() {
		this.initializeGame();
		
		while(true) {
			IO.print("*** TURN %d ***", this.turnNumber);
			this.takeTurn();
			this.advanceTurn();
			IO.readLine();
		}
	}

	private void initializeGame() {
		for(Player player :this.players) {
			player.getLibrary().shuffle();
			player.drawCards(Player.STARTING_HAND_SIZE);
		}
	}

	private void takeTurn() {
		for(Step currentStep : STANDARD_TURN_STEPS) {
			IO.print("-- %s step --", currentStep.getClass().getSimpleName());
			GivePriority givePriority = currentStep.beginningAction(this);
			
			this.printGame();

			if(givePriority == GivePriority.TRUE) {
				IO.print("Priority\n");

				/* After the turn-specific actions are completed, if the players receive priority, then
				 * all players receive priority in succession, starting with active player. When all players
				 * pass in succession, 
				 */
				while(true) {
					this.playersGetPriority();
					
					if(this.stack.isEmpty()) {
						break;
					}
					
					Effect effect = this.stack.removeFirst();
					//TODO: implement resolving effects
					IO.print("Resolving effect: " + effect);
				}
			}
		}
	}
	
	// All players get priority in order, starting with active player
	private void playersGetPriority() {
		int numPlayersPassedInSuccession = 0;
		int playerWithPriorityIndex = this.activePlayerIndex;

		/* All players must pass in succession before the game advances
		 * (i.e. resolves effects from the stack or continues to the next step.) */
		while(numPlayersPassedInSuccession < this.players.length) {
			final Player playerWithPriority = this.players[playerWithPriorityIndex];

			/* When one player receives priority, they may either perform an action or pass.
			 * If they perform an action, then they may choose to either retain priority
			 * (in which case they have the chance to perform another action), or not.
			 * TODO: cleanup?
			 */
			boolean playerTookAtLeastOneAction = false;
			while(true) {
				final ReceivePriorityResult result = playerWithPriority.receivePriority(this);
				playerTookAtLeastOneAction |= result.actionTaken();

				if(!result.actionTaken() || !result.retainPriority()) {
					break;
				}
			}

			IO.print("playerTookAtLeastOneAction: %s playerWithPriorityIndex: %d numPlayersPassedInSuccession: %d", 
					playerTookAtLeastOneAction, playerWithPriorityIndex, numPlayersPassedInSuccession);
			if(playerTookAtLeastOneAction) {
				numPlayersPassedInSuccession = 0;
			}
			
			numPlayersPassedInSuccession++;
			playerWithPriorityIndex = (playerWithPriorityIndex + 1) % this.players.length;
		}
	}

	private void advanceTurn() {
		this.turnNumber++;
		this.activePlayerIndex = (this.activePlayerIndex+1) % this.players.length;
	}
	
	public void printGame() {
		StringBuilder builder = new StringBuilder();
		//builder.append(String.format("** TURN %d **\n", this.turnNumber));
		
		int playerNumber = 1;
		for(Player player : this.players) {
			builder.append(String.format("- Player %d -\n", playerNumber));
			builder.append(String.format("Life: %d\n", player.getLifeTotal()));
			//builder.append(String.format("Library: %s\n", player.getLibrary()));
			builder.append(String.format("Hand: %s\n", PrintUtils.cardsToStringDeduped(player.getHand())));
			builder.append(String.format("Permanents: %s\n", player.getPermanents()));
			
			playerNumber++;
		}
		IO.print(builder.toString());
	}
}