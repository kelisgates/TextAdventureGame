package edu.westga.cs3211.text_adventure_game.model;

/**Handles enemy NPC logic
 * @author kg00281
 * @version Fall 2024
 */
public class EnemyNPC extends NPC {

	private String item;
	
	private int health;
	
	private int attackDamage;
	
	/**Creates enemy NPC
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param name name of NPC
	 * @param description description of NPC
	 * @param dialogue dialogue of NPC
	 */
	public EnemyNPC(String name, String description, String dialogue) {
		super(name, description, dialogue);
		
		this.health = 10;
		this.attackDamage = 2;
		this.setItem(null);
	}

	/**gets item of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return item of npc
	 */
	public String getItem() {
		return this.item;
	}

	/**sets item of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param item item of npc
	 */
	public void setItem(String item) {
		this.item = item;
	}
	
	/**gets health of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return name of npc
	 */
	public int getHealth() {
		return this.health;
	}
	
	/**sets health of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param health health of npc
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**gets attack damage power of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return attack damage of npc
	 */
	public int getAttackDamage() {
		return this.attackDamage;
	}

}
