package edu.westga.cs3211.text_adventure_game.test.testHazard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.Hazard;

public class TestHazard {

    private static HashMap<String, Hazard> gameHazards;

    @BeforeAll
    public static void init() {
        FileReader testRead = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/testLocations.txt", 
                "src/edu/westga/cs3211/text_adventure_game/assets/testHazard.txt",
                "src/edu/westga/cs3211/text_adventure_game/assets/testNPCs.txt",
                "src/edu/westga/cs3211/text_adventure_game/assets/testItems.txt");
        
        testRead.loadAllData();
        gameHazards = testRead.getHazards();
    }

    @Test
    public void testValidHazardConstructor() {
        String name = "Arrow";
        String description = "An arrow shots out of the wall and hits you.";
        int damage = 4;
        Hazard newHazard = new Hazard(name, description, damage);

        assertEquals(name, newHazard.getHazardName());
        assertEquals(description, newHazard.getHazardDescription());
        assertEquals(damage, newHazard.getHazardDamageValue());
    }

    @Test
    public void testInvalidHazardConstructor() {
        String firstHazardName = "Pothole";
        String firstHazardDescription = "There was a hole in the floor. You tripped.";
        int firstHazardDamage = 1;
        Hazard firstHazard = new Hazard(firstHazardName, firstHazardDescription, firstHazardDamage);

        String secondHazardName = "Arrow";
        String secondHazardDescription = "An arrow shots out of the wall and hits you.";
        int secondHazardDamage = 4;
        Hazard secondHazard = new Hazard(secondHazardName, secondHazardDescription, secondHazardDamage);

        assertNotEquals(firstHazard.getHazardName(), secondHazard.getHazardName());
        assertNotEquals(firstHazard.getHazardDescription(), secondHazard.getHazardDescription());
        assertNotEquals(firstHazard.getHazardDamageValue(), secondHazard.getHazardDamageValue());
    }

    @Test
    public void testValidLoadedHazard() {
        String name = "Pothole";
        String description = "There was a hole in the floor. You tripped.";
        int damage = 1;

        assertEquals(name, gameHazards.get(name).getHazardName());
        assertEquals(description, gameHazards.get(name).getHazardDescription());
        assertEquals(damage, gameHazards.get(name).getHazardDamageValue());
    }

    @Test
    public void testInvalidLoadedHazard() {
        String wrongName = "Arrow";
        String name = "Pothole";
        String description = "There was a hole in the floor. You tripped.";
        int damage = 1;

        assertNotEquals(name, gameHazards.get(wrongName).getHazardName());
        assertNotEquals(description, gameHazards.get(wrongName).getHazardDescription());
        assertNotEquals(damage, gameHazards.get(wrongName).getHazardDamageValue());
    }
}
