package edu.westga.cs3211.text_adventure_game.model;

/**Handles NPC logic
 * @author kg00281
 * @version Fall 2024
 */
public abstract class NPC {

	private String name;
	
	private String description;
	
	private String dialogue;
	
	/**Creates NPC
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param name name of NPC
	 * @param description description of NPC
	 * @param dialogue dialogue of NPC
	 */
	public NPC(String name, String description, String dialogue) {
		this.setName(name);
		this.setDescription(description);
		this.setDialogue(dialogue);
	}

	/**gets name of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return name of npc
	 */
	public String getName() {
		return this.name;
	}

	/**sets name of NPC
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param name name of NPC
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**gets description of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return description of npc
	 */
	public String getDescription() {
		return this.description;
	}

	/**sets description of NPC
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param description description of NPC
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**gets dialogue of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return dialogue of npc
	 */
	public String getDialogue() {
		return this.dialogue;
	}

	/**sets dialogue of NPC
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param dialogue dialogue of NPC
	 */
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}
}
