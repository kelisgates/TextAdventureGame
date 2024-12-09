package edu.westga.cs3211.text_adventure_game.test.testFileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.NPC;

public class TestFileReader {

	private static HashMap<String, Location> gameLocations;
	private static HashMap<String, Hazard> gameHazards;
	private static HashMap<String, NPC> gameNPCs;
	private static HashMap<String, Item> gameItems;
	
	@BeforeAll
	public static void init() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/testLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/testHazard.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/testNPCs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/testItems.txt");
		
		testRead.loadAllData();
		gameLocations = testRead.getLocations();
		gameHazards = testRead.getHazards();
		gameNPCs = testRead.getNPCs();
		gameItems = testRead.getItems();
	}
	
	@Test
	public void testValidLocationEntryRoom() {
		String name = "EntryRoomTest";
		String description1 = "Entry room, dead center.";
		String description2 = "Entry room, dead center. 2";
		String[] connectedRooms = new String[] {"TopCenter", "CenterRight", "BottomCenter", "CenterLeft"};
		String hazardName = "";
		boolean isGoal = false;
		Location callLocation = new Location(name, description1, description2, connectedRooms, null, isGoal);
		
		assertEquals(callLocation.getRoomName(), gameLocations.get(name).getRoomName());
//		assertEquals(callLocation.getRoomDescription(), gameLocations.get(name).getRoomDescription());
//		assertEquals(callLocation.getRoomDescription(), gameLocations.get(name).getRoomDescription());
//		assertEquals(callLocation.hasHazard(), gameLocations.get(name).hasHazard());
//		assertEquals(callLocation.getRoomDescription(), gameLocations.get(name).getRoomDescription());
//		assertEquals(callLocation.isGoal(), gameLocations.get(name).isGoal());
	}
}
