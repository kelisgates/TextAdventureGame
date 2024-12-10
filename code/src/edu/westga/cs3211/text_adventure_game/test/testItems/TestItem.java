package edu.westga.cs3211.text_adventure_game.test.testItems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.Item;

public class TestItem {

	private static HashMap<String, Item> gameItems;
	
	@BeforeAll
	public static void init() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/testLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/testHazard.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/testNPCs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/testItems.txt");
		
		testRead.loadAllData();
		gameItems = testRead.getItems();
	}
	
	@Test
	public void testItemConstructorValid() {
		String name = "WhiteGem";
		String description = "A pure white gemstone.";
		Item item = new Item(name, description);
		
		assertEquals(name, item.getItemName());
		assertEquals(description, item.getDescription());
	}
	
	@Test
	public void testValidItemLoad() {
		HashMap<String, Item> nullList = null;
		
		assertNotEquals(nullList, gameItems);
	}
	
	@Test
	public void testValidItem() {
		String itemName = "WhiteGem";
		
		assertEquals(itemName, gameItems.get(itemName).getItemName());
	}
	
	@Test
	public void testInvalidItem() {
		String firstItemName = "WhiteGem";
		String secondItemName = "AngelWings";
		
		assertNotEquals(secondItemName, gameItems.get(firstItemName).getItemName());
	}
	
	@Test
	public void testValidNameDescrription() {
		String itemName = "Sword";
		String itemDescription = "A sharp sword ideal for combat.";
		
		assertEquals(itemName, gameItems.get(itemName).getItemName());
		assertEquals(itemDescription, gameItems.get(itemName).getDescription());
	}
	
	@Test
	public void testInvalidNameDescrption() {
		String firstItemName = "GreenGem";
		String secondItemName = "Key";
		String secondItemDescription = "A mysterious black key that opens the goal room.";
		
		assertNotEquals(secondItemName, gameItems.get(firstItemName).getItemName());
		assertNotEquals(secondItemDescription, gameItems.get(firstItemName).getDescription());
	}
	
}
