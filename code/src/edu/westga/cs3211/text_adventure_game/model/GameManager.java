package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Text Adventure Game - Game Manager
 * 
 * Handles all of the interactions between classes.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class GameManager {
		
	private final String startingLocation = "EntryRoom";
	private final String gameOverYouLoseText = "Looks like you lost all of your health. Why am I talking. You're dead, you can't hear me.\nRelaunch the game to play again.";
	
	private FileReader gameFiles;
	private Player player;
	private Location playerLocation;
	private List<Actions> movementOptions;
	
	private HashMap<String, Location> gameLocations;
	private HashMap<String, Hazard> gameHazards;

	/**
	 * Empty GameManager constructor
	 */
	public GameManager() {
		this.player = new Player();
		this.gameFiles = new FileReader();
		this.movementOptions = new ArrayList<Actions>();
		
		this.initializeGameManger();
	}
	
	private void initializeGameManger() {
		this.setGameLocationsHazards();
	}
	
	private void setGameLocationsHazards()  {
		this.gameLocations = this.gameFiles.getLocationMap();
		this.gameHazards = this.gameFiles.getHazards();
		
		this.playerLocation = this.gameLocations.get(this.startingLocation);
	}
	
	/**
	 * Gets the room's description to be displayed.
	 * 
	 * @return gameLocations.getRoomDescription
	 */
	public String getLocationDescription() {
		if (this.playerLocation.getHazardCheck()) {
			this.player.reducePlayerHitPoint(this.gameHazards.get(this.playerLocation.getHazardName()).getHazardDamageValue());
			
			if (this.player.getPlayerHitPoints() <= 0) {
				return this.gameOverYouLoseText;
			}
			
			return this.gameHazards.get(this.playerLocation.getHazardName()).getHazardDescription();
		}
		
		return this.gameLocations.get(this.playerLocation.getRoomName()).getRoomDescription();
	}
	
	/**
	 * Returns the current HP for the player.
	 * 
	 * @return player.getPlayerHitPoints
	 */
	public int getPlayerHealthPoints() {
		return this.player.getPlayerHitPoints();
	}
	
	/**
	 * Moves the player from one location to the next based on their choice.
	 * 
	 * @param action the chosen player actions for movement
	 */
	public void movePlayer(Actions action) {
		String[] connectedRoom = this.playerLocation.getConnectedRooms();
		
		this.playerLocation = this.gameLocations.get(connectedRoom[action.getIndexValue()]);

		this.movementOptions.clear();
	}
	
	/**
	 * Gets the list of all possible directions the player can move.
	 */
	public void getActionList() {
		String[] options = this.playerLocation.getConnectedRooms();
		
		this.movementOptions.clear();
		
		for (int index = 0; index < options.length; index++) {
			if (!options[index].isBlank()) {
				
				this.movementOptions.add(Actions.getActionByIndex(index));
			}
		}
	}
	
	/**
	 * Returns the list of movement options to the ViewModel
	 * 
	 * @return movementOptions
	 */
	public List<Actions> getMovementOptions() {
		this.getActionList();
		
		return this.movementOptions;
	}
	
	/**
	 * Checks the player's current location if it is the goal.
	 * 
	 * @return true if player.getIsGoal() == true, else false
	 */
	public Boolean checkRoomForGoal() {
		return this.playerLocation.getIsGoal();
	}
}
