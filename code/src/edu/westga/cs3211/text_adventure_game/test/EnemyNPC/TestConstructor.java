package edu.westga.cs3211.text_adventure_game.test.EnemyNPC;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.EnemyNPC;
import edu.westga.cs3211.text_adventure_game.model.FriendlyNPC;
import edu.westga.cs3211.text_adventure_game.model.Item;

class TestConstructor {

	@Test
	void testEnemyNPC() {
		String name = "a";
		String description = "a";
		String dialogue = "a";
		Item item = new Item("sword", "description");
		int attackDamage = 6;
		
		EnemyNPC enemy = new EnemyNPC(name, description, dialogue, attackDamage, item);
		
		assertEquals(name, enemy.getName());
		assertEquals(description, enemy.getDescription());
		assertEquals(dialogue, enemy.getDialogue());
		assertEquals(attackDamage, enemy.getAttackDamage());
		assertEquals(item, enemy.getItemDrop());
		
	}
	
	@Test
	void testNameIsEmpty() {
		String name = "";
		String description = "a";
		String dialogue = "a";
		Item item = new Item("sword", "description");
		int attackDamage = 1;
		
		assertThrows(IllegalArgumentException.class,()->{ new EnemyNPC(name, description, dialogue, attackDamage, item); });
	}
	
	@Test
	void testDescriptionIsEmpty() {
		String name = "a";
		String description = "";
		String dialogue = "a";
		Item item = new Item("sword", "description");
		int attackDamage = 1;
		
		assertThrows(IllegalArgumentException.class,()->{ new EnemyNPC(name, description, dialogue, attackDamage, item); });
	}
	
	
	@Test
	void testDialogueIsEmpty() {
		String name = "a";
		String description = "a";
		String dialogue = "";
		Item item = new Item("sword", "description");
		int attackDamage = 1;
		
		assertThrows(IllegalArgumentException.class,()->{ new EnemyNPC(name, description, dialogue, attackDamage, item); });
	}
	
	
	@Test
	void testAttackDamageIsZero() {
		String name = "a";
		String description = "a";
		String dialogue = "a";
		Item item = new Item("sword", "description");
		int attackDamage = 0;
		
		assertThrows(IllegalArgumentException.class,()->{ new EnemyNPC(name, description, dialogue, attackDamage, item); });
	}
	
	@Test
	void testAttackDamageIsBelowZero() {
		String name = "a";
		String description = "a";
		String dialogue = "a";
		Item item = new Item("sword", "description");
		int attackDamage = -1;
		
		assertThrows(IllegalArgumentException.class,()->{ new EnemyNPC(name, description, dialogue, attackDamage, item); });
	}
	
	@Test
	void testItemIsNull() {
		String name = "a";
		String description = "a";
		String dialogue = "a";
		Item item = null;
		int attackDamage = 6;
		
		assertThrows(NullPointerException.class,()->{ new EnemyNPC(name, description, dialogue, attackDamage, item); });
	}
	
	

}
