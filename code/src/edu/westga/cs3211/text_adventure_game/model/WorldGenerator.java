package edu.westga.cs3211.text_adventure_game.model;

import java.util.HashMap;

public class WorldGenerator {

	private FileReader gameFiles;
	private HashMap<String, Location> gameLocations;
	private HashMap<String, Hazard> gameHazards;
	private HashMap<String, NPCs> gameNPCs;	//TODO: NPC class to be added
	
	public WorldGenerator() {
		this.gameFiles = new FileReader();
		
		this.getGameFiles();
	}
	
	private void getGameFiles() {
		this.gameLocations = this.gameFiles.getLocationMap();
		this.gameHazards = this.gameFiles.getHazards();
		this.gameNPCs = this.gameFiles.getNPCs();
	}
	
	public void generateWorld() {
		
	}
	
	private void setConnectedRoom() {
		//TODO: Connect the rooms
	}
	
	private void setItems() {
		//TODO: Place items in random rooms
	}
	
	private void setHazards() {
		//TODO: Place hazards
	}
	
	private void setNPCs() {
		//TODO: Place NPCs in random or appropriate rooms
		
		//Place Healer randomly
		//Place Dragon in DragonRoom
	}
	
	public HashMap<String, Location> getGameLocations() {
		return this.gameLocations;
	}
}
