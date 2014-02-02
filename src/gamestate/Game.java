package gamestate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import utils.Logger;
import utils.PrintUtils;

public class Game {
	private int turnNumber;
	private final Player[] players;
	private int activePlayerIndex;
	
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
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			Logger.print("*** TURN %d ***", this.turnNumber);
			this.takeTurn();
			this.advanceTurn();
			scanner.nextLine();
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
			Logger.print("-- %s step --", currentStep.getClass().getSimpleName());
			GivePriority givePriority = currentStep.beginningAction(this);
			
			this.printGame();
			
			if(givePriority == GivePriority.TRUE) {
				Logger.print("Priority\n");
			}
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
			builder.append(String.format("Hand: %s\n", PrintUtils.cardsToString(player.getHand())));
			builder.append(String.format("Permanents: %s\n", player.getPermanents()));
			
			playerNumber++;
		}
		Logger.print(builder.toString());
	}
}