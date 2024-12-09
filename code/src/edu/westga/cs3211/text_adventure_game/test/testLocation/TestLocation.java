package edu.westga.cs3211.text_adventure_game.test.testLocation;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;

public class TestLocation {

	private static HashMap<String, Location> gameLocations;
	
	@BeforeAll
	public static void init() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/testLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/testHazard.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/testNPCs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/testItems.txt");
		
		testRead.loadAllData();
		gameLocations = testRead.getLocations();
	}
	
	@Test
	public void testValidLocationConstructorNoHazard() {
		String name = "EntryRoom";
		String descriptionOne = "Entry room, dead center.";
		String descriptionTwo = "Entry room, dead center. 2";
		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
		boolean isGoal = false;
		Location testLocation = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
		
		assertEquals(name, testLocation.getRoomName());
		assertEquals(descriptionOne, testLocation.getRoomDescription());
		assertEquals(descriptionTwo, testLocation.getRoomDescription());
		assertEquals(connectedRooms, testLocation.getConnectedRooms());
		assertEquals(false, testLocation.hasHazard());
		assertNull(testLocation.getHazard());
		assertEquals(isGoal, testLocation.isGoal());
	}
	
	@Test
	public void testInvalidLocationConstructorHazard() {
		String name = "EntryRoom";
		String descriptionOne = "Entry room, dead center.";
		String descriptionTwo = "Entry room, dead center. 2";
		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
		Hazard hazard = new Hazard("WolfHazard", "The wolf lunges at you with sharp teeth!", 4);
		boolean isGoal = false;
		Location testLocation = new Location(name, descriptionOne, descriptionTwo, connectedRooms, hazard, isGoal);
		
		assertEquals(name, testLocation.getRoomName());
		assertEquals(descriptionOne, testLocation.getRoomDescription());
		assertEquals(descriptionTwo, testLocation.getRoomDescription());
		assertEquals(connectedRooms, testLocation.getConnectedRooms());
		assertEquals(true, testLocation.hasHazard());
		assertEquals(hazard, testLocation.getHazard());
		assertEquals(isGoal, testLocation.isGoal());
	}
	
	@Test
	public void testValidLoadLocation() {
		String name = "EntryRoom";
		String descriptionOne = "Entry room, dead center.";
		String descriptionTwo = "Entry room, dead center. 2";
		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
		boolean isGoal = false;
		Location testLocation = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
		
		assertEquals(name, this.gameLocations.get(name).getRoomName());
		assertEquals(descriptionOne, this.gameLocations.get(name).getRoomDescription());
		assertEquals(descriptionTwo, this.gameLocations.get(name).getRoomDescription());
		assertEquals(connectedRooms[0], this.gameLocations.get(name).getConnectedRooms()[0]);
		assertEquals(connectedRooms[1], this.gameLocations.get(name).getConnectedRooms()[1]);
		assertEquals(connectedRooms[2], this.gameLocations.get(name).getConnectedRooms()[2]);
		assertEquals(connectedRooms[3], this.gameLocations.get(name).getConnectedRooms()[3]);
		assertEquals(false, this.gameLocations.get(name).hasHazard());
		assertNull(testLocation.getHazard());
		assertEquals(isGoal, this.gameLocations.get(name).isGoal());
	}
	
	@Test
	public void testInvalidLoadLocation() {
		String wrongName = "CenterRight";
		String name = "CenterLeft";
		String descriptionOne = "Center left hallway.";
		String descriptionTwo = "Center left hallway. 2";
		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
		boolean isGoal = true;
		Location testLocation = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
		
		assertNotEquals(name, this.gameLocations.get(wrongName).getRoomName());
		assertNotEquals(descriptionOne, this.gameLocations.get(wrongName).getRoomDescription());
		assertNotEquals(descriptionTwo, this.gameLocations.get(wrongName).getRoomDescription());
		assertNotEquals(connectedRooms[0], this.gameLocations.get(wrongName).getConnectedRooms()[0]);
		assertNotEquals(connectedRooms[1], this.gameLocations.get(wrongName).getConnectedRooms()[1]);
		assertNotEquals(connectedRooms[2], this.gameLocations.get(wrongName).getConnectedRooms()[2]);
		assertNotEquals(connectedRooms[3], this.gameLocations.get(wrongName).getConnectedRooms()[3]);
		assertNotEquals(true, this.gameLocations.get(wrongName).hasHazard());
		assertNull(testLocation.getHazard());
		assertNotEquals(isGoal, this.gameLocations.get(wrongName).isGoal());
	}
}
