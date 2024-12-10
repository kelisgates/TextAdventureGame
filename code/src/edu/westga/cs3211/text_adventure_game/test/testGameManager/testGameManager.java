package edu.westga.cs3211.text_adventure_game.test.testGameManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.NPC;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class testGameManager {
	
	private static HashMap<String, Location> gameLocations;
	private static HashMap<String, NPC> gameNPCs;
	private static FileReader testRead;
	
	@BeforeAll
	public static void init() {
		testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		
		testRead.loadAllData();
		gameLocations = testRead.getLocations();
		gameNPCs = testRead.getNPCs();
	}
	
	@Test
	public void testGameManagerGetPlayer() {
		GameManager gameManager = new GameManager();
		Player player = new Player();
		
		assertEquals(player.getHealth(), gameManager.getPlayer().getHealth());
		assertEquals(player.getLives(), gameManager.getPlayer().getLives());
		assertEquals(player.getHealth(), gameManager.getPlayerHealth());
	}
	
	@Test
	public void testGameManagerLocationDescriptionNoNPC() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		testRead.loadAllData();
		
		GameManager gameManager = new GameManager();
		String locationDescription = "You are in the hallway. It's dimly lit and eerie.\n";
		
		assertEquals(locationDescription, gameManager.getLocationDescription());
	}
	
	@Test
	public void testGameManagerLocationWithNPCInteraction() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		testRead.loadAllData();
		
		GameManager gameManager = new GameManager();
		
		gameManager.setPlayerLocation("HealingRoom");
		String result = gameManager.handleAction(Actions.INTERACT);
		
		
		
		Location location = gameManager.getPlayerLocation();
		NPC npc = location.getNpcs().get(0);
		
		assertEquals(result, npc.getDialogue());
		
	}
	
	@Test
	public void testGameManagerLocationWithNPCAngelInteraction() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		testRead.loadAllData();
		
		GameManager gameManager = new GameManager();
		
		gameManager.setPlayerLocation("AngelRoom");
		String result = gameManager.handleAction(Actions.INTERACT);
		
		
		
		Location location = gameManager.getPlayerLocation();
		NPC npc = location.getNpcs().get(0);
		
		assertEquals(result, npc.getDialogue());
		
	}
	
	@Test
	public void testGameManagerPickupKeyWithoutGems() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		testRead.loadAllData();
		
		GameManager gameManager = new GameManager();
		
		gameManager.setPlayerLocation("GoalRoom");
		String result = gameManager.pickUpItem("Key");
		assertEquals("Key", gameManager.getPlayer().getInventory().getItems().get(0).getItemName());
		System.out.print(result);
		
	}
	
}
