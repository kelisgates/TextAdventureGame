package edu.westga.cs3211.text_adventure_game.test.testGameManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.NPC;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class testGameManager {
	
	private static HashMap<String, Location> gameLocations;
	private static HashMap<String, Hazard> gameHazards;
	private static HashMap<String, NPC> gameNPCs;
	private static HashMap<String, Item> gameItems;
	private static FileReader testRead;
	
	@BeforeAll
	public static void init() {
		testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		
		testRead.loadAllData();
		gameLocations = testRead.getLocations();
		gameHazards = testRead.getHazards();
		gameNPCs = testRead.getNPCs();
		gameItems = testRead.getItems();
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
		String locationDescription = "You are in the hallway. It's dimly lit and eerie.";
		
		assertEquals(locationDescription, gameManager.getLocationDescription());
	}
	
	@Test
	public void testGameManagerLocationDescriptionWithNPC() {
		FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt", 
				"src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/npcs.txt",
				"src/edu/westga/cs3211/text_adventure_game//assets/items.txt");
		testRead.loadAllData();
		
		GameManager gameManager = new GameManager();
		String locationDescription = "You are in the hallway. It's dimly lit and eerie. \n"
				+ "";
		
		gameManager.movePlayer(Actions.NORTH);
		
		System.out.println(gameManager.getLocationDescription());
	}
}
