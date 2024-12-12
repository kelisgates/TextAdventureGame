package edu.westga.cs3211.text_adventure_game.test.WorldGenerator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Hazard;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.WorldGenerator;

public class TestWorldGenerator {

    private WorldGenerator generator;
    private HashMap<String, Location> mockLocations;

    @BeforeEach
    public void setUp() {
    	mockLocations = new HashMap<>();
        
        generator = new WorldGenerator(mockLocations);
        
        String name = "DragonHazard";		
        String description = "The dragon breathes fire towards you!";		
        int damage = 4;		
        Hazard newHazard = new Hazard(name, description, damage);

        Location entryRoom = new Location("EntryRoom", "Description1", "Description2", null, false);
        Location dragonRoom = new Location("DragonRoom", "Description1", "Description2", newHazard, false);
        Location goblinRoom = new Location("GoblinRoom", "Description1", "Description2", null, false);

        mockLocations.put("EntryRoom", entryRoom);
        mockLocations.put("DragonRoom", dragonRoom);
        mockLocations.put("GoblinRoom", goblinRoom);

    }

    @Test
    public void testGenerateWorldPresetRoomConnections() {
    	generator.setGameLocations(mockLocations, false);
    	
        Location[][] worldGrid = generator.getWorldGrid();
        
        
        assertNotNull(worldGrid);
        
        
        assertNotNull(worldGrid[0][0]);  
        assertNotNull(worldGrid[0][1]);  
        assertNotNull(worldGrid[0][2]);  
        
        Location dragonRoom = worldGrid[0][0];
        Location goblinRoom = worldGrid[0][1];
        Location entryRoom = worldGrid[0][2];
        
        generator.printWorldGrid();

        assertArrayEquals(new String[] { "", "", "", "GoblinRoom" }, entryRoom.getConnectedRooms());
        assertArrayEquals(new String[] { "", "GoblinRoom", "", "" }, dragonRoom.getConnectedRooms());
        assertArrayEquals(new String[] { "", "EntryRoom", "", "DragonRoom" }, goblinRoom.getConnectedRooms());
        
    }
    
    @Test
    public void testSetConnectedRoomsFullyConnectedGrid() {
        generator.setGameLocations(mockLocations, false);

        Location[][] worldGrid = generator.getWorldGrid();
        worldGrid[0][0] = mockLocations.get("DragonRoom");
        worldGrid[0][1] = mockLocations.get("GoblinRoom");
        worldGrid[0][2] = mockLocations.get("EntryRoom");

        generator.setGameLocations(mockLocations, false);

        assertArrayEquals(new String[] { "", "GoblinRoom", "", "" }, worldGrid[0][0].getConnectedRooms());
        assertArrayEquals(new String[] { "", "EntryRoom", "", "DragonRoom" }, worldGrid[0][1].getConnectedRooms());
        assertArrayEquals(new String[] { "", "", "", "GoblinRoom" }, worldGrid[0][2].getConnectedRooms());
    }

    @Test
    public void testSetConnectedRoomsEmptyGrid() {
        generator.setGameLocations(mockLocations, false);

        Location[][] worldGrid = generator.getWorldGrid();
        for (int i = 0; i < worldGrid.length; i++) {
            for (int j = 0; j < worldGrid[i].length; j++) {
                worldGrid[i][j] = null;
            }
        }

        generator.setGameLocations(mockLocations, false);

        for (int i = 0; i < worldGrid.length; i++) {
            for (int j = 0; j < worldGrid[i].length; j++) {
                assertNull(worldGrid[i][j]);
            }
        }
    }

    @Test
    public void testGetLocations() {
        generator.setGameLocations(mockLocations, false);
        HashMap<String, Location> retrievedLocations = generator.getGameLocations();

        assertNotNull(retrievedLocations);

        assertTrue(retrievedLocations.containsKey("EntryRoom"));
        assertTrue(retrievedLocations.containsKey("DragonRoom"));
        assertTrue(retrievedLocations.containsKey("GoblinRoom"));

        assertNotNull(retrievedLocations.get("EntryRoom"));
        assertNotNull(retrievedLocations.get("DragonRoom"));
        assertNotNull(retrievedLocations.get("GoblinRoom"));

        assertEquals("Description1", retrievedLocations.get("EntryRoom").getRoomDescription());
        assertEquals("Description1", retrievedLocations.get("DragonRoom").getRoomDescription());
        assertEquals("Description1", retrievedLocations.get("GoblinRoom").getRoomDescription());
    }
    
    @Test
    public void testShuffleLocations() {
        generator.setGameLocations(mockLocations, false);

        Location[][] originalWorldGrid = generator.getWorldGrid();
        

        String[] originalRoomNames = Arrays.stream(originalWorldGrid)
                                            .flatMap(Arrays::stream)
                                            .filter(room -> room != null)
                                            .map(Location::getRoomName)
                                            .toArray(String[]::new);


        generator.setGameLocations(mockLocations, true); 

        Location[][] shuffledWorldGrid = generator.getWorldGrid();

        String[] shuffledRoomNames = Arrays.stream(shuffledWorldGrid)
                                            .flatMap(Arrays::stream)
                                            .filter(room -> room != null)
                                            .map(Location::getRoomName)
                                            .toArray(String[]::new);

        assertFalse(Arrays.equals(originalRoomNames, shuffledRoomNames), "The locations should be shuffled.");
    }

    @Test
    public void testGetStartingLocation() {
        Location startingLocation = generator.getStartingLocation();
        assertNotNull(startingLocation);
        assertEquals("EntryRoom", startingLocation.getRoomName());
    }
    
    @Test
    public void testGetStartingLocationWhenEntryRoomIsPresent() {
        generator.setGameLocations(mockLocations, false);

        Location startingLocation = generator.getStartingLocation();

        assertNotNull(startingLocation);
        assertEquals("EntryRoom", startingLocation.getRoomName());
    }

    @Test
    public void testGetStartingLocationWhenGridContainsNullLocations() {
        generator.setGameLocations(mockLocations, false);

        Location[][] worldGrid = generator.getWorldGrid();
        worldGrid[0][1] = null;

        Location startingLocation = generator.getStartingLocation();

        assertNotNull(startingLocation);
        assertEquals("EntryRoom", startingLocation.getRoomName());
    }

    @Test
    public void testGetStartingLocationWhenEntryRoomIsAbsent() {
        mockLocations.remove("EntryRoom");
        generator.setGameLocations(mockLocations, false);

        Location startingLocation = generator.getStartingLocation();

        assertNull(startingLocation);
    }

    
    
    
    
}

