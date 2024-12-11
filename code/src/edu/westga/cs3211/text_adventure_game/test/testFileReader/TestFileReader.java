package edu.westga.cs3211.text_adventure_game.test.testFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.EnemyNPC;
import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.NPC;

public class TestFileReader {

	private static HashMap<String, Location> gameLocations;
	private static HashMap<String, NPC> gameNPCs;
	private static HashMap<String, Item> gameItems;
	
	@BeforeAll
	public static void init() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		
		testRead.loadAllData();
		gameLocations = testRead.getLocations();
		gameNPCs = testRead.getNPCs();
		gameItems = testRead.getItems();
	}
	
//	@Test
//	public void testLocationLoading() {
//		String name = "EntryRoom";
//		String description1 = "You are in the hallway. It's dimly lit and eerie.";
//		String description2 = "You are back in the hallway.";
//		String[] connectedRooms = new String[] {"AngelRoom", "WeaponRoom", "HealingRoom", "WolfRoom"};
//		
//		boolean isGoal = false;
//		Location callLocation = new Location(name, description1, description2, connectedRooms, null, isGoal);
//		
//		assertEquals(callLocation.getRoomName(), gameLocations.get(name).getRoomName());
//		assertEquals(callLocation.getRoomDescription(), gameLocations.get(name).getRoomDescription());
//		assertEquals(callLocation.getRoomDescription(), gameLocations.get(name).getRoomDescription());
//		assertEquals(callLocation.hasHazard(), gameLocations.get(name).hasHazard());
//		assertEquals(callLocation.getRoomDescription(), gameLocations.get(name).getRoomDescription());
//		assertEquals(callLocation.isGoal(), gameLocations.get(name).isGoal());
//	}
	
	@Test
	public void testItemLoading() {
		String name = "GreenGem";
		String description = "A shimmering green gemstone.";
		
		assertEquals(name, gameItems.get(name).getItemName());
		assertEquals(description, gameItems.get(name).getDescription());
	}
	
	@Test
	public void testFriendlyNPCLoading() {
		String name = "Angel";
		String description = "A radiant angel with a warm smile.";
		String dialog = "\"Hey there player, I have a request to make. My wings were stolen by a dragon. Help find them for me, and I will reward you.\"";
		
		assertEquals(name, gameNPCs.get(name).getName());
		assertEquals(description, gameNPCs.get(name).getDescription());
		assertEquals(dialog, gameNPCs.get(name).getDialogue());
	}
	
	@Test
	public void testEnemyNPCLoading() {
		String name = "Dragon";
		String description = "A fearsome dragon with scales like armor.";
		String dialog = "The dragon roars and prepares to attack!";
		int damage = 7;
		
		String itemName = "AngelWings";
		String itemDescription = "Beautiful wings that belong to the angel.";
		Item item = new Item(itemName, itemDescription);
		
		EnemyNPC loadedNPC = (EnemyNPC) gameNPCs.get(name);
		
		assertEquals(name, loadedNPC.getName());
		assertEquals(description, loadedNPC.getDescription());
		assertEquals(dialog, loadedNPC.getDialogue());
		assertEquals(damage, loadedNPC.getAttackDamage());
		assertEquals(item.getItemName(), loadedNPC.getItemDrop().getItemName());
		assertEquals(item.getDescription(), loadedNPC.getItemDrop().getDescription());
	}
}
