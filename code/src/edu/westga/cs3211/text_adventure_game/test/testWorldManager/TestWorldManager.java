package edu.westga.cs3211.text_adventure_game.test.testWorldManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.NPC;
import edu.westga.cs3211.text_adventure_game.model.WorldManager;

public class TestWorldManager {

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
	
	@Test
	public void testWorldManagerConstructor() {
		WorldManager worldManager = new WorldManager();
		
		assertNotEquals(null, worldManager);
	}
	
	@Test
	public void testWorldManagerGetStartingLocation() {
		WorldManager worldManager = new WorldManager();
		
		assertEquals("EntryRoom", worldManager.getStartingLocation().getRoomName());
	}
	
	@Test
	public void testWorldManagerShuffleWorld() {
		WorldManager worldManager = new WorldManager();
		
		worldManager.shuffleWorld();
		
		HashMap<String, Location> newGameLocations = worldManager.getGameLocations();
		
		assertNotEquals(newGameLocations, gameLocations);
	}
	
	@Test
	public void testWorldManagerPrintWorldGrid() {
		WorldManager worldManager = new WorldManager();
		
		worldManager.shuffleWorld();
		worldManager.printWorldGrid();
	}
}
