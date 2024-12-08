package edu.westga.cs3211.text_adventure_game.test.FreindlyNPC;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.FriendlyNPC;

class TestConstructor {

	@Test
	void testFriendlyNPC() {
		String name = "a";
		String description = "a";
		String dialogue = "a";
		
		FriendlyNPC friendly = new FriendlyNPC(name, description, dialogue);
		
		assertEquals(name, friendly.getName());
		assertEquals(description, friendly.getDescription());
		assertEquals(dialogue, friendly.getDialogue());
		
	}
	
	
	@Test
	void testEmptyName() {
		String name = "";
		String description = "a";
		String dialogue = "a";
		
		assertThrows(IllegalArgumentException.class,()->{ new FriendlyNPC(name, description, dialogue); });
	}
	
	@Test
	void testEmptyDescription() {
		String name = "a";
		String description = "";
		String dialogue = "a";
		
		assertThrows(IllegalArgumentException.class,()->{ new FriendlyNPC(name, description, dialogue); });
	}
	
	@Test
	void testEmptyDialogue() {
		String name = "a";
		String description = "a";
		String dialogue = "";
		
		assertThrows(IllegalArgumentException.class,()->{ new FriendlyNPC(name, description, dialogue); });
	}
	
	

}
