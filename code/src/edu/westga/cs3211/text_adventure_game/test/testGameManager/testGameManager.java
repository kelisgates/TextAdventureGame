package edu.westga.cs3211.text_adventure_game.test.testGameManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.NPC;
import edu.westga.cs3211.text_adventure_game.model.Player;

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
        assertTrue(description.contains("EntryRoom"));
    }

    @Test
    void testMovePlayerNorth() {
        gameManager.setPlayerLocation("EntryRoom");
        gameManager.movePlayer(Actions.NORTH);
        assertEquals("NorthRoom", gameManager.getPlayerLocation().getRoomName());
    }

    @Test
    void testGenerateActionListWithEnemy() {
        gameManager.setPlayerLocation("DragonRoom");
        List<Actions> actions = gameManager.getActionOptions();
        assertTrue(actions.contains(Actions.FIGHT));
        assertTrue(actions.contains(Actions.FLEE));
    }

    @Test
    void testGenerateActionListWithHealingRoomNPC() {
        gameManager.setPlayerLocation("HealingRoom");
        List<Actions> actions = gameManager.getActionOptions();
        assertTrue(actions.contains(Actions.INTERACT));
    }

    @Test
    void testHandleActionMove() {
        gameManager.setPlayerLocation("EntryRoom");
        String result = gameManager.handleAction(Actions.NORTH);
        assertEquals("test", result);
        assertEquals("EntryRoom", gameManager.getPlayerLocation().getRoomName());
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
        gameManager.setPlayerLocation("EnemyRoom");
        gameManager.pickUpItem("Sword");
        String result = gameManager.handleAction(Actions.FIGHT);
        assertTrue(result.contains("fought the"));
        assertTrue(gameManager.getCanPickUpItems());
    }

    @Test
    void testHandleFlee() {
        gameManager.setPlayerLocation("EnemyRoom");
        gameManager.movePlayer(Actions.NORTH);
        String result = gameManager.handleAction(Actions.FLEE);
        assertEquals("You fled back to the previous room.", result);
    }

    @Test
    void testHandleInteractionWithAngel() {
        gameManager.setPlayerLocation("AngelRoom");
        String result = gameManager.handleAction(Actions.INTERACT);
        assertTrue(result.contains("Did you find it?"));
    }

    @Test
    void testHandlePickUpGem() {
        gameManager.setPlayerLocation("GemRoom");
        String result = gameManager.pickUpItem("BlueGem");
        assertEquals("You picked up the BlueGem.", result);
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
        gameManager.pickUpItem("Key");
        gameManager.setPlayerLocation("AdjacentRoomToGoal");
        String result = gameManager.handleAction(Actions.USE_KEY);
        assertTrue(result.contains("You used the key to enter the Goal Room"));
    }
}
