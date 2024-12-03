package edu.westga.cs3211.text_adventure_game.model;

import java.util.HashMap;

/**
 * Text Adventure Game - World Manager
 * 
 * Handles the world loading and management.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class WorldManager {
	
	private final String startingLocation = "EntryRoom";
	private final String gameOverYouLoseText = "Looks like you lost all of your health. Why am I talking. You're dead, you can't hear me.\nRelaunch the game to play again.";
	
	private FileReader gameFiles;
	private WorldGenerator generatedWorld;
	private Player player;
	private Location playerLocation;
	
	private String description;
	
	private HashMap<String, Location> gameLocations;
	
	/**
	 * World Manager constructor
	 */
	public WorldManager() {
		this.gameFiles = new FileReader();
		this.generatedWorld = new WorldGenerator();
		this.player = new Player();
		
		this.description = "";
		
		this.initializedWorld();
	}
	
	private void initializedWorld() {
		this.generatedWorld.generateWorld();
		this.generatedWorld.getGameLocations();
	}
	
	private void playerAction(Actions action) {
		if (action == Actions.NORTH) {
//		    this.gameManager.movePlayer(Actions.NORTH);
		} else if (action == Actions.EAST) {
//		    this.gameManager.movePlayer(Actions.EAST);
		} else if (action == Actions.SOUTH) {
//		    this.gameManager.movePlayer(Actions.SOUTH);
		} else if (action == Actions.WEST) {
//		    this.gameManager.movePlayer(Actions.WEST);
		}
		
		if (action == Actions.ATTACK) {
			//Remove NPC Health
		}
		
		if (action == Actions.DEFEND) {
			//Damage to player == 0
		}
		
		if (action == Actions.TAKE) {
			for (int count = 0; count < this.playerLocation.items.size(); count++) {
				this.player.addItem(this.playerLocation.removeItem.getIndex(count));
			}
		}
	}
	
	private void getLocationDescription() {
		this.description = this.playerLocation.getRoomDescription();
	}
	
	private void getNPCDescrption() {
		this.description += this.playerLocation.getNPCDescription();
	}
	
	private void getHazardDescription() {
		if (location.hazard != null) {
			this.description += this.playerLocation.getHazardDescrption();
		}
	}
	
	private void removeNPCFromRoom() {
		if (this.playerLocation.getNPC.getHealth  <= 0) {
			this.playerLocation.remove(this.playerLocation.getNPC));
		}
	}
	
	private void removeItemFromRoomPlaceInPlayerInventory(Item item) {
		player.PutInInventory(location.RemoveItem(item));
	}
	
	private void removeItemFromInventoryPlaceInRoom(Item item) {
		playerLocation.placeItem(player.RemoveItem(item));
	}
}
