package gamestate;

//TODO: rename
public interface Payable {
	//TODO: should canPayCost be replaced by applying payCost without "committing" the changes to see if it's legal?
	//TODO: should control be inverted when applying costs or effects to the game?
	public abstract boolean canPayCost(Game game);
	public abstract void payCost(Game game);
}