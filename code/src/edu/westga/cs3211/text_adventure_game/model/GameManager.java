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
		
	private Player player;
	private Location playerLocation;
	private WorldManager worldManager;
	private List<Actions> actionOptions;
	
	private HashMap<String, Location> gameLocations;

	/**
	 * Empty GameManager constructor
	 */
	public GameManager() {
		this.player = new Player();
		this.worldManager = new WorldManager();
		this.actionOptions = new ArrayList<Actions>();
		
		this.initializeGameManger();
	}
	
	private void initializeGameManger() {
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
	public void playerAction(Actions action) {
		this.worldManager.playerAction(action);
		
		this.actionOptions.clear();
	}
	
	/**
	 * Gets the list of all possible directions the player can move.
	 */
	public void getActionList() {
		String[] options = this.playerLocation.getConnectedRooms();
		
		this.actionOptions.clear();
		
		for (int index = 0; index < options.length; index++) {
			if (!options[index].isBlank()) {
				
				this.actionOptions.add(Actions.getActionByIndex(index));
			}
		}
		
		// Added following options from Actions enum
		if (playerLocation.hasEnemy) {
			this.actionOptions.add(Actions.getActionByIndex(4));
			this.actionOptions.add(Actions.getActionByIndex(5));
		}
		
		if (playerLocation.NPC > 0) {
			this.actionOptions.add(Actions.getActionByIndex(6) + location.NPCs.getNPCName());
		}
		
//		for (int index = 0; index < playerLocation.items.size(); index++) {
//			this.actionsOptions.add(Actions.getActionByIndex(7) + playerLocation.items.getIndex(index).getItemName());
//		}
		if ()
		
		for (int index = 0; index < player.playerLocation.size(); index++) {
			this.actionOptions.add(Actions.getActionByIndex(8) + player.playerLocation.getIndex(index).getItemName());
		}
	}
	
	/**
	 * Returns the list of movement options to the ViewModel
	 * 
	 * @return movementOptions
	 */
	public List<Actions> getMovementOptions() {
		this.getActionList();
		
		return this.actionOptions;
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
