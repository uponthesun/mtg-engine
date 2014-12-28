package gamestate;

import utils.IO;

public class HumanPlayer extends Player {
	public HumanPlayer(Library library) {
		super(library);
	}

	@Override
	public ReceivePriorityResult receivePriority(Game game) {
		//TODO: implement
		IO.print("Received priority");
		return ReceivePriorityResult.noAction();
	}
}