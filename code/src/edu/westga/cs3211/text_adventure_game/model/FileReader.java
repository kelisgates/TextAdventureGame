package edu.westga.cs3211.text_adventure_game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Text Adventure Game - FileReader class
 * 
 * File Reader will read in a file of the game locations given to it by the GameManager.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class FileReader {

	//For Testing
	private final String gameLocationsFileLocation = "src/edu/westga/cs3211/text_adventure_game/assets/testLocations.txt";
	private final String gameHazardsFileLocation = "src/edu/westga/cs3211/text_adventure_game//assets/testHazards.txt";
	private final String gameNPCFileLocation = "src/edu/westga/cs3211/text_adventure_game//assets/testNPC.txt":
	
	//For Play
	//private final String gameLocationsFileLocation = "src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt";
	//private final String gameHazardsFileLocation = "src/edu/westga/cs3211/text_adventure_game//assets/hazards.txt";
	//private final String gameNPCFileLocation = "":
	
	private String gameMapFile;
	private String hazardFile;
	private String npcFile;
	
	private HashMap<String, Location> gameLocations;
	private HashMap<String, Hazard> hazards;
	private HashMap<String, NPC> npcs;
	
	/**
	 * Basic FileReader constructor
	 */
	public FileReader() {
		this.gameMapFile = gameLocationsFileLocation;
		this.hazardFile = gameHazardsFileLocation;
		this.npcFile = gameNPCFileLocation;
		
		this.gameLocations = new HashMap<String, Location>();
		this.hazards = new HashMap<String, Hazard>();
		this.npcs = new HashMap<String, NPC>();
		
		this.loadInGameMapFile();
		this.loadInHazardFile();
		this.loadInNPCFile();
	}
	
	/**
	 * Overloaded Constructor with the file name.
	 * 
	 * @param gameMapFile name of the game location file
	 * @param hazardFile name of the file with hazards
	 */
	public FileReader(String gameMapFile, String hazardFile, String npcFile) {
		this.gameMapFile = gameMapFile;
		this.hazardFile = hazardFile;
		this.npcFile = npcFile;
		
		this.gameLocations = new HashMap<String, Location>();
		this.hazards = new HashMap<String, Hazard>();
		
		this.loadInGameMapFile();
		this.loadInHazardFile();
		this.loadInNPCFile();
	}
	
	private void loadInGameMapFile() {
		int lineCounter = 0;
		
		String name = "";
		String description = "";
		
		try {
			File loadInFile = new File(this.gameMapFile);
			Scanner fileReader = new Scanner(loadInFile);
			
			while (fileReader.hasNext()) {
				String data = fileReader.nextLine();
								
				switch (lineCounter) {
				case 0:
					name = data;
					lineCounter++;
					break;
				case 1:
					description = data;
					lineCounter++;
					break;
				default:
					Location newLocation = new Location(name, description);
					this.gameLocations.put(newLocation.getRoomName(), newLocation);
					lineCounter = 0;
					break;
				}
			}
			
			fileReader.close();
		} catch (FileNotFoundException fnfException) {
			System.err.println("FILE NOT FOUND: WORLD MAP");
		}
	}
	
	private void loadInHazardFile() {
		int lineCounter = 0;
		
		String name = "";
		String description = "";
		int damageValue = 0;
		
		try {
			File loadInFile = new File(this.hazardFile);
			Scanner fileReader = new Scanner(loadInFile);
			
			while (fileReader.hasNext()) {
				String data = fileReader.nextLine();
				
				switch (lineCounter % 3) {
				case 0:
					name = data;
					lineCounter++;
					break;
				case 1:
					description = data;
					lineCounter++;
					break;
				case 2:
					damageValue = Integer.parseInt(data);
					lineCounter++;
					Hazard newHazard = new Hazard(name, description, damageValue);
					this.hazards.put(newHazard.getHazardName(), newHazard);
					break;
				}
			}
			
			fileReader.close();
		} catch (FileNotFoundException fnfException) {
			System.err.println("FILE NOT FOUND: HAZARDS");
		}
	}
	
	private void loadInNPCFile() {
		int lineCounter = 0;
		String name = "";
		String dialog = "";
		
		try {
			File loadInFile = new File(this.npcFile);
			Scanner fileReader = new Scanner(loadInFile);
			
			while (fileReader.hasNext()) {
				String data = fileReader.nextLine();
				
				switch (lineCounter % 2) {
				case 0:
					name = data;
					break;
				case 1:
					dialog = data;
					NPC newNPC = new NPC(name, dialog);
					this.npcs.put(newNPC.getName(), newNPC);
					break;
				}
			}
		}
	}
	
	/**
	 * Gets the locations in a HashMap
	 * 
	 * @return gameLocations
	 */
	public HashMap<String, Location> getLocationMap() {
		return this.gameLocations;
	}
	
	/**
	 * Gets the hazards in a HashMap
	 * 
	 * @return hazards
	 */
	public HashMap<String, Hazard> getHazards() {
		return this.hazards;
	}
	
	/**
	 * Gets a hashmap of available NPCs
	 * 
	 * @return npcs
	 */
	public HashMap<String, NPC> getNPCs() {
		return this.npcs;
	}
}
