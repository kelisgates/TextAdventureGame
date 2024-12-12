package edu.westga.cs3211.text_adventure_game.test.testGameManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Location;

public class testGameManager {
	
	private GameManager gameManager;

    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
    }

    @Test
    void testConstructorInitializesFieldsCorrectly() {
        assertNotNull(gameManager.getPlayer());
        assertNotNull(gameManager.getPlayerLocation());
        assertTrue(gameManager.getCanPickUpItems());
    }

    @Test
    void testSetPlayerLocation() {
        String locationName = "EntryRoom";
        gameManager.setPlayerLocation(locationName);
        assertEquals(locationName, gameManager.getPlayerLocation().getRoomName());
    }

    @Test
    void testDisplayWorldMap() {
        // Test ensures no exceptions occur.
        gameManager.displayWorldMap();
    }

    @Test
    void testLocationDescription() {
        gameManager.setPlayerLocation("EntryRoom");
        String description = gameManager.getLocationDescription();
        
        assertNotNull(description);
        assertTrue(description.contains("You are in the hallway. It's dimly lit and eerie."));
    }

    @Test
    void testGameManagerMovePlayerNorth() {
    	gameManager.setPlayerLocation("EntryRoom");
        Location locationOne = gameManager.getPlayerLocation();
        
        String result = gameManager.handleAction(Actions.NORTH);
        Location locationTwo = gameManager.getPlayerLocation();
        
        assertNotEquals(locationOne, locationTwo);
    }
    
    @Test
    void testGameManagerMovePlayerEast() {
        gameManager.setPlayerLocation("EntryRoom");
        Location locationOne = gameManager.getPlayerLocation();
        
        String result = gameManager.handleAction(Actions.EAST);
        Location locationTwo = gameManager.getPlayerLocation();
        
        assertNotEquals(locationOne, locationTwo);
    }
    
    @Test
    void testGameManagerMovePlayerSouth() {
        gameManager.setPlayerLocation("EntryRoom");
        Location locationOne = gameManager.getPlayerLocation();
        
        String result = gameManager.handleAction(Actions.SOUTH);
        Location locationTwo = gameManager.getPlayerLocation();
        
        assertNotEquals(locationOne, locationTwo);
    }

    @Test
    void testGameManagerMovePlayerWest() {
        gameManager.setPlayerLocation("EntryRoom");
        Location locationOne = gameManager.getPlayerLocation();
        
        String result = gameManager.handleAction(Actions.WEST);
        Location locationTwo = gameManager.getPlayerLocation();
        
        assertNotEquals(locationOne, locationTwo);
    }
    
    @Test
    void testGenerateActionListWithEnemy() {
        gameManager.setPlayerLocation("DragonRoom");
        List<Actions> actions = gameManager.getActionOptions();
        assertTrue(actions.contains(Actions.FIGHT));
    }

    @Test
    void testGenerateActionListWithHealingRoomNPC() {
        gameManager.setPlayerLocation("HealingRoom");
        List<Actions> actions = gameManager.getActionOptions();
        assertTrue(actions.contains(Actions.INTERACT));
    }

    @Test
    void testHandleFightWithoutSword() {
        gameManager.setPlayerLocation("GoblinRoom");
        String result = gameManager.handleAction(Actions.FIGHT);
        assertTrue(result.contains("was not phased"));
        assertTrue(gameManager.getPlayerHealth() < 10);
    }

    @Test
    void testHandleFightWithSword() {
        gameManager.setPlayerLocation("WeaponRoom");
        gameManager.pickUpItem("Sword");
        gameManager.setPlayerLocation("WolfRoom");
        String result = gameManager.handleAction(Actions.FIGHT);
        assertTrue(result.contains("fought the"));
        assertTrue(gameManager.getCanPickUpItems());
    }

    @Test
    void testHandleFirstInteractionWithAngel() {
        gameManager.setPlayerLocation("AngelRoom");
        String result = gameManager.handleAction(Actions.INTERACT);
        assertTrue(result.contains("Hey there player"));
    }
    
    @Test
    void testHandleSecondInteractionWithAngel() {
        gameManager.setPlayerLocation("AngelRoom");
        gameManager.handleAction(Actions.INTERACT);
        String result = gameManager.handleAction(Actions.INTERACT);
        
        assertTrue(result.contains("Did you find it?"));
    }

    @Test
    void testHandlePickUpGem() {
        gameManager.setPlayerLocation("WeaponRoom");
        gameManager.pickUpItem("Sword");
        gameManager.setPlayerLocation("WolfRoom");
        gameManager.handleAction(Actions.FIGHT);
        String result = gameManager.pickUpItem("WhiteGem");
        assertEquals("You picked up the WhiteGem.", result);
    }

    @Test
    void testDropGemInHiddenRoomCorrectOrder() {
        gameManager.setPlayerLocation("HiddenRoom");
        gameManager.pickUpItem("BlueGem");
        String result = gameManager.dropItem("BlueGem");
        assertTrue(result.contains("Current gem placement order"));
    }

    @Test
    void testUseKeyToWin() {
    	gameManager.setPlayerLocation("WeaponRoom");
        gameManager.pickUpItem("Sword");
        
        gameManager.setPlayerLocation("DragonRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("AngelWings");
        gameManager.pickUpItem("RedGem");
        
        gameManager.setPlayerLocation("WolfRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("WhiteGem");
        
        gameManager.setPlayerLocation("GoblinRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("GreenGem");
        
        gameManager.setPlayerLocation("AngelRoom");
        gameManager.handleAction(Actions.INTERACT);
        gameManager.pickUpItem("BlueGem");
        
        gameManager.setPlayerLocation("HiddenRoom");
        gameManager.dropItem("BlueGem");
        gameManager.dropItem("GreenGem");
        gameManager.dropItem("RedGem");
        gameManager.dropItem("WhiteGem");
    	
        gameManager.pickUpItem("Key");
        gameManager.setPlayerLocation("GoalRoom");
        String result = gameManager.dropItem("Key");
        assertTrue(result.contains("You used the key to enter the Goal Room."));
    }
    
    @Test
    public void testGameManagerPlayerAtHazardLocation() {
    	String expected = "This hallway is connected to multiple paths, with faint growls echoing from some of the rooms, the floor seems unstable.\nThere was a hole in the floor. You tripped.";
    	
    	assertEquals(10, gameManager.getPlayerHealth());
    	
    	gameManager.setPlayerLocation("Hall04");
    	gameManager.getLocationDescription();
    	
    	assertEquals(9, gameManager.getPlayerHealth());
    }
    
    @Test
    public void testGameManagerLocationNPCDescription() {
    	gameManager.setPlayerLocation("HealingRoom");
    	String expected = "A soothing aura fills the room. A healer stands ready to aid you.\nAn old healer with kind eyes.";
    	String result = gameManager.getLocationDescription();
    	
    	System.out.println(expected);
    	System.out.println(result);
    	
    	assertEquals(expected, result);
    }
    
    @Test
    public void testGameManagerLocationDescriptionYouSee() {
    	gameManager.setPlayerLocation("WeaponRoom");
        gameManager.pickUpItem("Sword");
        
        gameManager.setPlayerLocation("DragonRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("AngelWings");
        gameManager.pickUpItem("RedGem");
        
        gameManager.setPlayerLocation("WolfRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("WhiteGem");
        
        gameManager.setPlayerLocation("GoblinRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("GreenGem");
        
        gameManager.setPlayerLocation("AngelRoom");
        gameManager.handleAction(Actions.INTERACT);
        gameManager.pickUpItem("BlueGem");
        
        gameManager.setPlayerLocation("HiddenRoom");
        gameManager.dropItem("BlueGem");
        gameManager.dropItem("GreenGem");
        gameManager.dropItem("RedGem");
        gameManager.dropItem("WhiteGem");
        
        assertTrue(gameManager.getLocationDescription().contains("You see a "));
        
        gameManager.pickUpItem("Key");
    }
    
    @Test
    public void testGameManagerGetBlueGem() {
    	gameManager.setPlayerLocation("WeaponRoom");
        gameManager.pickUpItem("Sword");
        
        gameManager.setPlayerLocation("DragonRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("AngelWings");
        gameManager.pickUpItem("RedGem");
        
        gameManager.setPlayerLocation("WolfRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("WhiteGem");
        
        gameManager.setPlayerLocation("GoblinRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("GreenGem");
        
        gameManager.setPlayerLocation("AngelRoom");
        gameManager.handleAction(Actions.INTERACT);
        gameManager.dropItem("AngelWings");

        assertTrue(gameManager.getLocationDescription().contains("Here is a blue gem"));
    }
    
    @Test
    public void testGameMangerSinglePlayerDeath() {
    	gameManager.setPlayerLocation("DragonRoom");
    	gameManager.handleAction(Actions.FIGHT);
    	
    	assertEquals(3, gameManager.getPlayerHealth());
    	
    	gameManager.handleAction(Actions.FIGHT);
    	
    	assertEquals(10, gameManager.getPlayerHealth());
    }
    
    @Test
    public void testGameManagerTripelPlayerDeath() {
    	gameManager.setPlayerLocation("DragonRoom");
    	gameManager.handleAction(Actions.FIGHT);
    	gameManager.handleAction(Actions.FIGHT);
    	
    	assertEquals(10, gameManager.getPlayerHealth());
    	
    	gameManager.setPlayerLocation("DragonRoom");
    	gameManager.handleAction(Actions.FIGHT);
    	gameManager.handleAction(Actions.FIGHT);
    	
    	assertEquals(10, gameManager.getPlayerHealth());
    	
    	gameManager.setPlayerLocation("DragonRoom");
    	gameManager.handleAction(Actions.FIGHT);
    	gameManager.handleAction(Actions.FIGHT);
    	
    	assertEquals(0, gameManager.getPlayerHealth());
    }
    
    @Test
    public void testGameManagerHealerInteraction() {
    	gameManager.setPlayerLocation("DragonRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.setPlayerLocation("HealingRoom");
        gameManager.handleAction(Actions.INTERACT);
    }
    
    @Test
    public void testGameManagerAngelInteraction() {
    	gameManager.setPlayerLocation("WeaponRoom");
        gameManager.pickUpItem("Sword");
        
        gameManager.setPlayerLocation("DragonRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("AngelWings");
        gameManager.pickUpItem("RedGem");
        
        gameManager.setPlayerLocation("WolfRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("WhiteGem");
        
        gameManager.setPlayerLocation("GoblinRoom");
        gameManager.handleAction(Actions.FIGHT);
        gameManager.pickUpItem("GreenGem");
        
        gameManager.setPlayerLocation("AngelRoom");
        gameManager.handleAction(Actions.INTERACT);
        
        System.out.println(gameManager.handleAction(Actions.INTERACT));
        System.out.println(gameManager.dropItem("AngelWings"));
        System.out.println(gameManager.handleAction(Actions.INTERACT));
        
        //TODO
    }
}
