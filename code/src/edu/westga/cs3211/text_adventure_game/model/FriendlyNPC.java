package edu.westga.cs3211.text_adventure_game.model;

/**Handles Friendly NPC logic
 * @author kg00281
 * @version Fall 2024
 */
public class FriendlyNPC extends NPC {
	
	/**Creates friendly NPC
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param name name of NPC
	 * @param description description of NPC
	 * @param dialogue dialogue of NPC
	 */
	public FriendlyNPC(String name, String description, String dialogue) {
		super(name, description, dialogue);
		
	}

}
