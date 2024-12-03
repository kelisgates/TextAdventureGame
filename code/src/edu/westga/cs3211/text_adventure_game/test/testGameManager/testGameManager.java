package edu.westga.cs3211.text_adventure_game.test.testGameManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class testGameManager {
	
	@Test
	public void testGameManagerStartingLocation() {
		String name = "EntryRoom";
		String descriptionOne = "You enter a mysterious dungeon and a stone slab shuts behind you. You face South and you see you can move East or West.";
		String descriptionTwo = "You are back at the entrence. You can move East or West.";
		String[] connectedRooms = new String[] {"","DartRoom","","SmokeRoom"};
		boolean hasHazard = false;
		boolean isGoal = false;
		
		GameManager gameManager = new GameManager();
		Location playerLocation = new Location(name, descriptionOne, descriptionTwo, connectedRooms, hasHazard, null, isGoal);
		
		assertEquals(playerLocation.getRoomDescription(), gameManager.getLocationDescription());
	}
	
	@Test
	public void testGameManagerPlayerMovementEast() {
		String hazardName = "SmokeRoom";
		String description = "The room quickly fills with smoke. You cannot see any details in the room.";
		int damage = 0;
		
		
		GameManager gameManager = new GameManager();
		Hazard hazard = new Hazard(hazardName, description, damage);
		
		gameManager.playerAction(Actions.WEST);
		
		assertEquals(hazard.getHazardDescription(), gameManager.getLocationDescription());
	}
	
	@Test
	public void testGameManagerPlayerHealthFull() {
		GameManager gameManager = new GameManager();
		Player newPlayer = new Player();
	
		assertEquals(newPlayer.getPlayerHitPoints(), gameManager.getPlayerHealthPoints());
	}
	
	@Test
	public void testGameManagerPlayerHealthPartialSingleHit() {
		GameManager gameManager = new GameManager();
		Player newPlayer = new Player();
	
		newPlayer.reducePlayerHitPoint(2);
				
		gameManager.playerAction(Actions.EAST);
		
		assertEquals("A dart shoots out and hits you in the neck. It stung.", gameManager.getLocationDescription());
		assertEquals(newPlayer.getPlayerHitPoints(), gameManager.getPlayerHealthPoints());
	}
	
	@Test
	public void testGameManagerPlayerHealthPartialDoubleHit() {
		GameManager gameManager = new GameManager();
		Player newPlayer = new Player();
	
		newPlayer.reducePlayerHitPoint(4);

		assertEquals("You enter a mysterious dungeon and a stone slab shuts behind you. You face South and you see you can move East or West.", gameManager.getLocationDescription());
		gameManager.playerAction(Actions.EAST);
		assertEquals("A dart shoots out and hits you in the neck. It stung.", gameManager.getLocationDescription());
		gameManager.playerAction(Actions.WEST);
		assertEquals("You are back at the entrance. You can move East or West.", gameManager.getLocationDescription());
		gameManager.playerAction(Actions.EAST);
		assertEquals("A dart shoots out and hits you in the neck. It stung.", gameManager.getLocationDescription());
		assertEquals(newPlayer.getPlayerHitPoints(), gameManager.getPlayerHealthPoints());
	}
	
	@Test
	public void testGameManagerGetActionListEntryRoom() {
		GameManager gameManager = new GameManager();
		
		ArrayList<Actions> actionList = new ArrayList<Actions>();
		actionList.add(Actions.EAST);
		actionList.add(Actions.WEST);
		
		assertEquals(actionList, gameManager.getMovementOptions());
	}
	
	@Test
	public void testGameManagerGetActionListSmokeRoom() {
		GameManager gameManager = new GameManager();
		
		gameManager.playerAction(Actions.WEST);
		
		ArrayList<Actions> actionList = new ArrayList<Actions>();
		actionList.add(Actions.EAST);
		
		assertEquals(actionList, gameManager.getMovementOptions());
	}
	
	@Test
	public void testGameManagerGetActionListDartRoom() {
		GameManager gameManager = new GameManager();
		
		gameManager.playerAction(Actions.EAST);
		
		ArrayList<Actions> actionList = new ArrayList<Actions>();
		actionList.add(Actions.SOUTH);
		actionList.add(Actions.WEST);
		
		assertEquals(actionList, gameManager.getMovementOptions());
	}
	
	@Test
	public void testGameManagerReachGoalRoomValid() {
		GameManager gameManager = new GameManager();
		
		gameManager.playerAction(Actions.EAST);
		gameManager.playerAction(Actions.SOUTH);
		
		assertEquals("You enter a roundish room and see a cake sitting on a table in the center of the room.", gameManager.getLocationDescription());
		assertEquals(true, gameManager.checkRoomForGoal());
	}
	
	@Test
	public void testGameManagerReachGoalRoomInvalid() {
		GameManager gameManager = new GameManager();
		
		gameManager.playerAction(Actions.WEST);
		
		assertEquals(false, gameManager.checkRoomForGoal());
	}
	
	@Test
	public void testGameManagerKillPlayer() {
		GameManager gameManager = new GameManager();
		String gameOverText = "Looks like you lost all of your health. Why am I talking. You're dead, you can't hear me.\nRelaunch the game to play again.";;
		
		gameManager.playerAction(Actions.EAST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.WEST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.EAST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.WEST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.EAST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.WEST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.EAST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.WEST);
		gameManager.getLocationDescription();
		gameManager.playerAction(Actions.EAST);
		gameManager.getLocationDescription();
		
		assertEquals(0, gameManager.getPlayerHealthPoints());
		assertEquals(gameOverText, gameManager.getLocationDescription());
	}
}
