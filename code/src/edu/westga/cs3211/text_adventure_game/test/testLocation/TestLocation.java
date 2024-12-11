package edu.westga.cs3211.text_adventure_game.test.testLocation;

//import static org.junit.Assert.assertNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//import java.util.HashMap;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import edu.westga.cs3211.text_adventure_game.model.EnemyNPC;
//import edu.westga.cs3211.text_adventure_game.model.FileReader;
//import edu.westga.cs3211.text_adventure_game.model.FriendlyNPC;
//import edu.westga.cs3211.text_adventure_game.model.Hazard;
//import edu.westga.cs3211.text_adventure_game.model.Item;
//import edu.westga.cs3211.text_adventure_game.model.Location;

public class TestLocation {

//	private static HashMap<String, Location> gameLocations;
//	
//	@BeforeAll
//	public static void init() {
//		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/testLocations.txt", 
//				"src/edu/westga/cs3211/text_adventure_game//assets/testHazard.txt",
//				"src/edu/westga/cs3211/text_adventure_game//assets/testNPCs.txt",
//				"src/edu/westga/cs3211/text_adventure_game//assets/testItems.txt");
//		
//		testRead.loadAllData();
//		gameLocations = testRead.getLocations();
//	}
//	
//	@Test
//	public void testValidLocationConstructorNoHazard() {
//		String name = "EntryRoom";
//		String descriptionOne = "Entry room, dead center.";
//		String descriptionTwo = "Entry room, dead center. 2";
//		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
//		boolean isGoal = false;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		assertEquals(name, location.getRoomName());
//		assertEquals(descriptionOne, location.getRoomDescription());
//		assertEquals(descriptionTwo, location.getRoomDescription());
//		assertEquals(connectedRooms, location.getConnectedRooms());
//		assertEquals(false, location.hasHazard());
//		assertNull(location.getHazard());
//		assertEquals(isGoal, location.isGoal());
//	}
//	
//	@Test
//	public void testValidLocationConstructorHazard() {
//		String name = "EntryRoom";
//		String descriptionOne = "Entry room, dead center.";
//		String descriptionTwo = "Entry room, dead center. 2";
//		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
//		Hazard hazard = new Hazard("WolfHazard", "The wolf lunges at you with sharp teeth!", 4);
//		boolean isGoal = false;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, hazard, isGoal);
//		
//		assertEquals(name, location.getRoomName());
//		assertEquals(descriptionOne, location.getRoomDescription());
//		assertEquals(descriptionTwo, location.getRoomDescription());
//		assertEquals(connectedRooms, location.getConnectedRooms());
//		assertEquals(true, location.hasHazard());
//		assertEquals(hazard, location.getHazard());
//		assertEquals(isGoal, location.isGoal());
//	}
//	
//	@Test
//	public void testValidLoadLocation() {
//		String name = "EntryRoom";
//		String descriptionOne = "Entry room, dead center.";
//		String descriptionTwo = "Entry room, dead center. 2";
//		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
//		boolean isGoal = false;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		assertEquals(name, gameLocations.get(name).getRoomName());
//		assertEquals(descriptionOne, gameLocations.get(name).getRoomDescription());
//		assertEquals(descriptionTwo, gameLocations.get(name).getRoomDescription());
//		assertEquals(connectedRooms[0], gameLocations.get(name).getConnectedRooms()[0]);
//		assertEquals(connectedRooms[1], gameLocations.get(name).getConnectedRooms()[1]);
//		assertEquals(connectedRooms[2], gameLocations.get(name).getConnectedRooms()[2]);
//		assertEquals(connectedRooms[3], gameLocations.get(name).getConnectedRooms()[3]);
//		assertEquals(false, gameLocations.get(name).hasHazard());
//		assertNull(location.getHazard());
//		assertEquals(isGoal, gameLocations.get(name).isGoal());
//	}
//	
//	@Test
//	public void testInvalidLoadLocation() {
//		String wrongName = "CenterRight";
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		assertNotEquals(name, gameLocations.get(wrongName).getRoomName());
//		assertNotEquals(descriptionOne, gameLocations.get(wrongName).getRoomDescription());
//		assertNotEquals(descriptionTwo, gameLocations.get(wrongName).getRoomDescription());
//		assertNotEquals(connectedRooms[0], gameLocations.get(wrongName).getConnectedRooms()[0]);
//		assertNotEquals(connectedRooms[1], gameLocations.get(wrongName).getConnectedRooms()[1]);
//		assertNotEquals(connectedRooms[2], gameLocations.get(wrongName).getConnectedRooms()[2]);
//		assertNotEquals(connectedRooms[3], gameLocations.get(wrongName).getConnectedRooms()[3]);
//		assertNotEquals(true, gameLocations.get(wrongName).hasHazard());
//		assertNull(location.getHazard());
//		assertNotEquals(isGoal, gameLocations.get(wrongName).isGoal());
//	}
//	
//	@Test
//	public void testLocationAddItem() {
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//			
//		String itemName = "BlueGem";
//		String itemDescription = "A radiant blue gemstone.";
//		Item item = new Item(itemName, itemDescription);
//		
//		location.addItem(item);
//		
//		assertEquals(1, location.getItems().size());
//	}
//	
//	@Test
//	public void testLocationRemoveItem() {
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//			
//		String itemName1 = "BlueGem";
//		String itemDescription1 = "A radiant blue gemstone.";
//		Item item1 = new Item(itemName1, itemDescription1);
//		
//		String itemName2 = "AngelWings";
//		String itemDescription2 = "Beautiful wings that belong to the angel.";
//		Item item2 = new Item(itemName2, itemDescription2);
//		
//		location.addItem(item1);
//		location.addItem(item2);
//		assertEquals(2, location.getItems().size());
//		
//		location.removeItem(item1);
//		assertEquals(1, location.getItems().size());
//	}
//	
//	@Test
//	public void testLocationGetItemExactSpelling() {
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		String itemName = "BlueGem";
//		String itemDescription = "A radiant blue gemstone.";
//		Item item = new Item(itemName, itemDescription);
//		
//		location.addItem(item);
//		
//		assertEquals(itemName, location.getItem(itemName).getItemName());
//			
//	}
//	
//	@Test
//	public void testLocationGetItemTwoItems() {
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		String itemName1 = "BlueGem";
//		String itemDescription1 = "A radiant blue gemstone.";
//		Item item1 = new Item(itemName1, itemDescription1);
//		String itemName2 = "WhiteGem";
//		String itemDescription2 = "A pure white gemstone.";
//		Item item2 = new Item(itemName2, itemDescription2);
//		
//		location.addItem(item1);
//		location.addItem(item2);
//		
//		assertEquals(itemName2, location.getItem(itemName2).getItemName());
//			
//	}
//	
//	@Test
//	public void testLocationGetItemSimilarSpelling() {
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		String itemName = "BlueGem";
//		String itemDescription = "A radiant blue gemstone.";
//		Item item = new Item(itemName, itemDescription);
//		
//		location.addItem(item);
//		
//		assertEquals(itemName, location.getItem("blueGEM").getItemName());
//	}
//	
//	@Test void testLocationGetItemNull() {
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] connectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft",""};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		String itemName = "BlueGem";
//		
//		assertNull(location.getItem(itemName));
//	}
//	
//	@Test
//	public void testLocationAddGetFriendlyNPC() {
//		String name = "EntryRoom";
//		String descriptionOne = "Entry room, dead center.";
//		String descriptionTwo = "Entry room, dead center. 2";
//		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
//		boolean isGoal = false;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		String npcName = "Angel";
//		String npcDescription = "A radiant angel with a warm smile.";
//		String npcDialog = "\"Hey there player, I have a request to make. My wings were stolen by a dragon. Help find them for me, and I will reward you.\"";
//		FriendlyNPC friendly = new FriendlyNPC(npcName, npcDescription, npcDialog);
//		
//		location.addNpc(friendly);
//		assertEquals(1, location.getNpcs().size());
//	}
//	
//	@Test
//	public void testLocationDeleteFriendlyNPC() {
//		String name = "EntryRoom";
//		String descriptionOne = "Entry room, dead center.";
//		String descriptionTwo = "Entry room, dead center. 2";
//		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
//		boolean isGoal = false;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		String npcName = "Angel";
//		String npcDescription = "A radiant angel with a warm smile.";
//		String npcDialog = "\"Hey there player, I have a request to make. My wings were stolen by a dragon. Help find them for me, and I will reward you.\"";
//		FriendlyNPC friendly = new FriendlyNPC(npcName, npcDescription, npcDialog);
//		
//		location.addNpc(friendly);
//		assertEquals(1, location.getNpcs().size());
//		
//		location.removeNpc(friendly);
//		assertEquals(0, location.getNpcs().size());
//	}
//	
//	@Test
//	public void testLocationAddGetEnemyNPC() {
//		String name = "EntryRoom";
//		String descriptionOne = "Entry room, dead center.";
//		String descriptionTwo = "Entry room, dead center. 2";
//		String[] connectedRooms = new String[] {"TopCenter","CenterRight","BottomCenter","CenterLeft"};
//		boolean isGoal = false;
//		Location location = new Location(name, descriptionOne, descriptionTwo, connectedRooms, null, isGoal);
//		
//		String npcName = "Angel";
//		String npcDescription = "A radiant angel with a warm smile.";
//		String npcDialog = "\"Hey there player, I have a request to make. My wings were stolen by a dragon. Help find them for me, and I will reward you.\"";
//		int damage = 2;
//		
//		String itemName = "BlueGem";
//		String itemDescription = "A radiant blue gemstone.";
//		Item item = new Item(itemName, itemDescription);
//
//		EnemyNPC enemy = new EnemyNPC(npcName, npcDescription, npcDialog, damage, item);
//		
//		location.addNpc(enemy);
//		assertEquals(1, location.getNpcs().size());
//	}
//	
//	@Test
//	public void testLocationSetConnectingRooms() {
//		String name = "CenterLeft";
//		String descriptionOne = "Center left hallway.";
//		String descriptionTwo = "Center left hallway. 2";
//		String[] oldConnectedRooms = new String[] {"TopLeft","EntryRoom","BottomLeft","Blank"};
//		boolean isGoal = true;
//		Location location = new Location(name, descriptionOne, descriptionTwo, oldConnectedRooms, null, isGoal);
//		
//		String[] newConnectedRooms = new String[] {"NewLocation1","NewLocation2","NewLocation3","NewLocation4"};
//		
//		assertEquals(oldConnectedRooms[0], location.getConnectedRooms()[0]);
//		assertEquals(oldConnectedRooms[1], location.getConnectedRooms()[1]);
//		assertEquals(oldConnectedRooms[2], location.getConnectedRooms()[2]);
//		assertEquals(oldConnectedRooms[3], location.getConnectedRooms()[3]);
//		
//		location.setConnectedRooms(newConnectedRooms);
//		
//		assertEquals(newConnectedRooms[0], location.getConnectedRooms()[0]);
//		assertEquals(newConnectedRooms[1], location.getConnectedRooms()[1]);
//		assertEquals(newConnectedRooms[2], location.getConnectedRooms()[2]);
//		assertEquals(newConnectedRooms[3], location.getConnectedRooms()[3]);
//	}
}
