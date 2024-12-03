package edu.westga.cs3211.text_adventure_game.model;

/**
 * Text Adventure Game - Action Enum
 * 
 * Lists all possible actions available in Text Adventure Game
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public enum Actions {
	NORTH("MOVE NORTH", 0),
	EAST("MOVE EAST", 1),
	SOUTH("MOVE SOUTH", 2),
	WEST("MOVE WEST", 3),
	ATTACK("Swing Sword", 4),
	DEFEND("Raise Shield", 5),
	INTERACT("Talk to: ", 6),
	TAKE("Pick Up: ", 7),
	DROP("Drop: ", 8);
	
	private String action;
	private int indexValue;
	
	Actions(String action, int index) {
		this.action = action;
		this.indexValue = index;
	}
	
	/**
	 * Returns a single string for the action available.
	 * 
	 * @return action the String value of the direction
	 */
	public String toString() {
		return this.action;
	}
	
	/**
	 * Returns the index value of the movement choice.
	 * 
	 * @return indexValue the value of the direction
	 */
	public int getIndexValue() {
		return this.indexValue;
	}
	
	/**
	 * Returns the proper String value to the index.
	 * 
	 * @param index of the available room.
	 * 
	 * @return action String
	 */
	public static Actions getActionByIndex(int index) {
		for (Actions action : Actions.values()) {
			if (action.getIndexValue() == index) {
				return action;
			}
		}
		return null;
	}
}
