package edu.westga.cs3211.text_adventure_game.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * World Generator Class to randomize the room locations
 * 
 * @author Shawn Bretthauer, MoriaEl, kg00281
 * 
 * @version Fall 2024
 */
public class WorldGenerator {

	private FileReader fileReader;
	private HashMap<String, Location> gameLocations;
    private Location[][] worldGrid;
	
    /**
     * WordGenerator Constructor
     * 
     * @param gameLocations gameLocations
     */
    public WorldGenerator(HashMap<String, Location> gameLocations) {
    	this.fileReader = new FileReader();
        this.gameLocations = this.fileReader.getLocations();
        
        this.worldGrid = new Location[4][4];
        this.generateWorld();
    }
	
    /**
     * Randomized the connected rooms
     */
    public void generateWorld() {
        List<Location> locations = new ArrayList<>(this.gameLocations.values());
        Collections.shuffle(locations);
        
        int index = 0;
        for (int rows = 0; rows < this.worldGrid.length; rows++) {
            for (int coloumns = 0; coloumns < this.worldGrid[rows].length; coloumns++) {
                if (index < locations.size()) {
                	this.worldGrid[rows][coloumns] = locations.get(index);
                    index++;
                } else {
                	this.worldGrid[rows][coloumns] = null;
                }
            }
        }

        this.setConnectedRooms();
    }
    
    private void setConnectedRooms() {
        for (int rows = 0; rows < this.worldGrid.length; rows++) {
            for (int coloumns = 0; coloumns < this.worldGrid[rows].length; coloumns++) {
                Location currentLocation = this.worldGrid[rows][coloumns];
                if (currentLocation != null) {
                    String[] connectedRooms = new String[4];

                    if (rows > 0 && this.worldGrid[rows - 1][coloumns] != null) {
                        connectedRooms[0] = this.worldGrid[rows - 1][coloumns].getRoomName();
                    } else {
                        connectedRooms[0] = "";
                    }

                    if (coloumns < this.worldGrid[rows].length - 1 && this.worldGrid[rows][coloumns + 1] != null) {
                        connectedRooms[1] = this.worldGrid[rows][coloumns + 1].getRoomName();
                    } else {
                        connectedRooms[1] = "";
                    }

                    if (rows < this.worldGrid.length - 1 && this.worldGrid[rows + 1][coloumns] != null) {
                        connectedRooms[2] = this.worldGrid[rows + 1][coloumns].getRoomName();
                    } else {
                        connectedRooms[2] = "";
                    }

                    if (coloumns > 0 && this.worldGrid[rows][coloumns - 1] != null) {
                        connectedRooms[3] = this.worldGrid[rows][coloumns - 1].getRoomName();
                    } else {
                        connectedRooms[3] = "";
                    }

                    currentLocation.setConnectedRooms(connectedRooms);
                }
            }
        }
    }

    /**
     * Gets the starting location (EntryRoom)
     * 
     * @return starting location
     */
    public Location getStartingLocation() {
        for (Location[] row : this.worldGrid) {
            for (Location loc : row) {
                if (loc != null && loc.getRoomName().equals("EntryRoom")) {
                    return loc;
                }
            }
        }
        return null;
    }

    /**
     * Gets the game locations
     * 
     * @return gameLocations
     */
    public HashMap<String, Location> getGameLocations() {
        return this.gameLocations;
    }
    
    /**
     * Gets the game locations
     * 
     * @return gameLocations
     */
    public HashMap<String, Location> getGameHazards() {
        return this.gameLocations;
    }
}
