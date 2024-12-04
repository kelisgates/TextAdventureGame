package edu.westga.cs3211.text_adventure_game.model;

/**Handles enemy NPC logic
 * @author kg00281 and MoriaEL
 * @version Fall 2024
 */
public class EnemyNPC extends NPC {

	private Item itemDrop;
	
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
	public EnemyNPC(String name, String description, String dialogue, int attackDamage, Item itemDrop) {
		super(name, description, dialogue);
		
		this.attackDamage = attackDamage;
        this.itemDrop = itemDrop;
	}

	/**gets item of npc
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return item of npc
	 */
	public Item getItemDrop() {
		return this.itemDrop;
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
