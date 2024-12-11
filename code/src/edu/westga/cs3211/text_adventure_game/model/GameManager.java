package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Text Adventure Game - Game Manager
 * 
 * Handles all of the interactions between classes.
 * 
 * @author Shawn Bretthauer and MoriaEl
 * @version Fall 2024
 */
public class GameManager {

	private Player player;
	private Location playerLocation;
	private WorldManager worldManager;
	private List<Actions> actionOptions;
	private Location previousLocation;
	private Set<String> defeatedEnemies;
	private boolean canPickUpItems;
	private boolean unlockedKey;
	private boolean completedQuest;
	private List<String> gemPlacementOrder;
	private int gemCounter;

	/**
	 * GameManager constructor.
	 */
	public GameManager() {
		this.player = new Player();
		this.worldManager = new WorldManager();
		this.actionOptions = new ArrayList<>();
		this.previousLocation = null;
		this.defeatedEnemies = new HashSet<>();
		this.canPickUpItems = true;
		this.unlockedKey = false;
		this.completedQuest = false;
		this.gemCounter = 0;
		this.gemPlacementOrder = new ArrayList<>();
		this.initializeGameManager();
	}

	/**
	 * Gets the player
	 * 
	 * @return the player object
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Gets if player can pick up item
	 * 
	 * @return bool if player can pick up item
	 */
	public boolean getCanPickUpItems() {
		return this.canPickUpItems;
	}
	
	/**
	 * sets player location (main use is for testing purposes)
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param name name of location you want to set player at
	 */
	public void setPlayerLocation(String name) {
		this.playerLocation = this.worldManager.getGameLocations().get(name);
	}
	
	/**gets player current location 
	 * @precondition none
	 * @postcondition none
	 * @return location of player
	 */
	public Location getPlayerLocation() {
		return this.playerLocation;
	}
	
	/**
	 * Prints the game world as a matrix.
	 */
	public void displayWorldMap() {
		this.worldManager.printWorldGrid();
	}

	/**
	 * Initializes the game by setting the starting location.
	 */
	private void initializeGameManager() {
		this.playerLocation = this.worldManager.getStartingLocation();
	}
	
	/**
	 * Retrieves the current room's description.
	 * 
	 * @return the room description
	 */
	public String getLocationDescription() {
		StringBuilder description = new StringBuilder();

		description.append(this.playerLocation.getRoomDescription()).append("\n");
		
		if (this.playerLocation.hasHazard()) {
			this.appendHazardDescription(description);
			this.player.reduceHealth(this.playerLocation.getHazard().getHazardDamageValue());
		}
		
		this.appendNpcDescription(description);
		if (this.playerLocation.getNpcs().isEmpty()) {
			this.appendItemDescription(description);
		}
		
		if (this.gemCounter == 4) {
			description.append("You have all required Gems, find the secret room.");
		}
		
		return description.toString();
	}
	
	private void appendHazardDescription(StringBuilder description) {
		description.append(this.playerLocation.getHazard().getHazardDescription()).append("\n");
	}

	private void appendItemDescription(StringBuilder description) {
		for (Item item : this.playerLocation.getItems()) {
			if (item.getItemName().equals("Key")) {
				if (!this.unlockedKey) {
					description.append("To get the key find all gems.");
				} else {
					description.append("You can use your key here.");
				}
			} else {
				description.append("You see a ").append(item.getItemName()).append(" here. You can now pick it up. \n");
			}
		}
	}

	private void appendNpcDescription(StringBuilder description) {
		for (NPC npc : this.playerLocation.getNpcs()) {
			description.append(npc.getDescription()).append("\n");
			if (this.completedQuest && this.playerLocation.getRoomName().equals("AngelRoom")) {
				description.append("Here is a blue gem. Collect all the gems and place in hidden room. Remember BGRW.");
			}
		}
	}

	/**
	 * Returns the current HP for the player.
	 * 
	 * @return player.getPlayerHitPoints
	 */
	public int getPlayerHealth() {
		return this.player.getHealth();
	}

	/**
	 * Moves the player to a new location based on the chosen action.
	 * 
	 * @param action the action representing movement
	 */
	public void movePlayer(Actions action) {
		String[] connectedRooms = this.playerLocation.getConnectedRooms();
		String nextRoomName = "";

		switch (action) {
		case NORTH:
			nextRoomName = connectedRooms[0];
			break;
		case EAST:
			nextRoomName = connectedRooms[1];
			break;
		case SOUTH:
			nextRoomName = connectedRooms[2];
			break;
		case WEST:
			nextRoomName = connectedRooms[3];
			break;
		default:
			break;
		}

		if (!nextRoomName.isEmpty()) {
			this.previousLocation = this.playerLocation;
			this.playerLocation = this.worldManager.getGameLocations().get(nextRoomName);
			this.canPickUpItems = false;
			}

		this.actionOptions.clear();
	}

	/**
	 * Generates the list of possible actions based on the current location.
	 */
	public void generateActionList() {
		String[] options = this.playerLocation.getConnectedRooms();

		this.actionOptions.clear();

		this.moveMentActions(options);

		this.npcInteraction();

		this.itemActions();

	}

	/**
	 * Retrieves the list of available actions.
	 * 
	 * @return list of actions
	 */
	public List<Actions> getActionOptions() {
		this.generateActionList();
		return this.actionOptions;
	}

	private void itemActions() {
		
		boolean hasUndefeatedEnemy = false;
		boolean angelWingsReturned = false;
		
		for (NPC npc : this.playerLocation.getNpcs()) {
	        if (npc instanceof EnemyNPC && !this.defeatedEnemies.contains(npc.getName())) {
	            hasUndefeatedEnemy = true;
	            break;
	        }
	    }
		
		if (this.playerLocation.getRoomName().equals("AngelRoom")) { 
	        angelWingsReturned = false;
	        for (Item item : this.playerLocation.getItems()) {
	            if (item.getItemName().equalsIgnoreCase("AngelWings")) {
	                angelWingsReturned = true;
	                break;
	            }
	        }
	    }
		
		if (!this.playerLocation.getItems().isEmpty()) {
			this.actionOptions.add(Actions.PICK_UP);
		}
		if (this.playerLocation.getRoomName().equals("HiddenRoom") && !this.unlockedKey) {
			this.actionOptions.remove(Actions.PICK_UP);
		}
		if (hasUndefeatedEnemy) {
			this.actionOptions.remove(Actions.PICK_UP);
		}
		if (this.playerLocation.getRoomName().equals("AngelRoom") && !angelWingsReturned) {
			this.actionOptions.remove(Actions.PICK_UP);
		}
		
	}

	private void npcInteraction() {
		for (NPC npc : this.playerLocation.getNpcs()) {
			if (npc instanceof EnemyNPC) {
				this.actionOptions.add(Actions.FIGHT);
				break;
			} else if (npc instanceof FriendlyNPC) {
				this.actionOptions.add(Actions.INTERACT);
			}
		}
	}

	private void moveMentActions(String[] options) {
		if (!options[0].isEmpty()) {
			this.actionOptions.add(Actions.NORTH);
		}
		if (!options[1].isEmpty()) {
			this.actionOptions.add(Actions.EAST);
		}
		if (!options[2].isEmpty()) {
			this.actionOptions.add(Actions.SOUTH);
		}
		if (!options[3].isEmpty()) {
			this.actionOptions.add(Actions.WEST);
		}
	}

	/**
	 * Checks if the player has reached the goal location.
	 * 
	 * @return true if in goal room, else false
	 */
	public boolean checkForGoal() {
		return this.playerLocation.isGoal();
	}

	/**
	 * Handles the player's chosen action and returns the resulting description.
	 * 
	 * @param action the action to perform
	 * @return result description
	 */
	public String handleAction(Actions action) {
		switch (action) {
		case NORTH:
		case EAST:
		case SOUTH:
		case WEST:
			this.movePlayer(action);
			return "test";
		case FIGHT:
			return this.handleFight();
		case INTERACT:
			return this.handleInteraction();
		case PICK_UP:
			return this.handlePickUp();
		default:
			return "Invalid action.";
		}
	}

	/**
	 * Handles the fight action against an enemy NPC.
	 * 
	 * @return fight result description
	 */
	private String handleFight() {
		EnemyNPC enemy = null;
		for (NPC npc : this.playerLocation.getNpcs()) {
			if (npc instanceof EnemyNPC) {
				enemy = (EnemyNPC) npc;
			}
				
			boolean hasSword = this.player.getInventory().getItem("Sword") != null;
				
			if (!hasSword) {	
				this.player.reduceHealth(enemy.getAttackDamage());	
				return "You attempted to throw a punch, but " + enemy.getName() + " was not phased." + "\n" + enemy.getDescription();
			}
				
			if (this.player.getHealth() >= 0 && hasSword) {
				this.playerLocation.removeNpc(enemy);
				this.defeatedEnemies.add(npc.getName());
				this.canPickUpItems = true;

				return "You fought the " + enemy.getName() + " and won!";
			} else {
				return this.handlePlayerDeath();
			}
			
		}
		return "No enemy to fight.";
	}

	/**
	 * Handles interaction with a friendly NPC.
	 * 
	 * @return interaction result description
	 */
	private String handleInteraction() {
		for (NPC npc : this.playerLocation.getNpcs()) {
			if (npc instanceof FriendlyNPC) {
				FriendlyNPC friendly = (FriendlyNPC) npc;
				if (friendly.getName().equals("Angel")) {
					return this.handleAngelInteraction(friendly);
				} else if (friendly.getName().equals("Healer")) {
					this.player.addHealth(5);
					return friendly.getDialogue();
				}
			}
		}
		return "No one to interact with.";
	}

	/**
	 * Handles the interaction logic with the Angel NPC.
	 * 
	 * @param angel the Angel NPC
	 * @return interaction result description
	 */
	private String handleAngelInteraction(FriendlyNPC angel) {
		if (!this.player.hasGivenQuest()) {
			this.player.setGivenQuest(true);
			return angel.getDialogue();
		} else {
			return "Did you find it?";
		}
	}
	
	/**
	 * Handles dropping an item.
	 * 
	 * @param itemName the name of the item to drop
	 * @return drop result description
	 */
	public String dropItem(String itemName) {
        Item item = this.player.getInventory().getItem(itemName);
        if (item != null) {
            this.player.getInventory().removeItem(item);
            this.playerLocation.addItem(item);
            if (this.isGem(itemName)) {
            	this.gemCounter -= 1;
            }
        }
        
        if (this.playerLocation.getRoomName().equals("AngelRoom")) {
        	if (item.getItemName().equals("AngelWings")) {
        		this.completedQuest = true;
        		return "Thank you!";
        	}
        }
        
        if (this.playerLocation.getRoomName().equals("GoalRoom")) {
        	if (item.getItemName().equals("Key")) {
        		return "You used the key to enter the Goal Room. You win! Game Over";
        	}
        }
        
        if (this.playerLocation.getRoomName().equals("HiddenRoom")) {
             if (this.isGem(itemName)) {
                this.gemPlacementOrder.add(itemName);
                return this.handleGemPlacement();
            }
             return "You dropped the " + item.getItemName() + ".";
        }
            
        return "You don't have the " + itemName + " to drop.";
    }
    
    private boolean isGem(String itemName) {
        return itemName.equalsIgnoreCase("BlueGem") 
        		|| itemName.equalsIgnoreCase("GreenGem") 
        		|| itemName.equalsIgnoreCase("RedGem") 
        		|| itemName.equalsIgnoreCase("WhiteGem");
    }
    
    private String handleGemPlacement() {
        List<String> correctOrder = List.of("BlueGem", "GreenGem", "RedGem", "WhiteGem");
        
        StringBuilder placementDisplay = new StringBuilder("Current gem placement order: ");
        for (String gem : this.gemPlacementOrder) {
            placementDisplay.append(gem).append(" -> ");
        }
        if (placementDisplay.length() > 0) {
            placementDisplay.setLength(placementDisplay.length() - 4);
        }
        
        if (this.gemPlacementOrder.size() == correctOrder.size() && this.gemPlacementOrder.equals(correctOrder)) {
        	this.unlockedKey = true;
            Item key = this.handlePickupKey();
            if (key != null) {
                this.playerLocation.addItem(key);
                return placementDisplay + " You have placed all gems in the correct order! The Key is now available to pick up.";
            } else {
                return "Key is already placed.";
            }
        } else if (this.gemPlacementOrder.size() == correctOrder.size() && !this.gemPlacementOrder.equals(correctOrder)) {
        	this.returnGemsToPlayerInventory();
            this.gemPlacementOrder.clear();
            return placementDisplay +  " Incorrect gem placement order. Please try again.";
        }
        return placementDisplay + " Place all gems in the correct order to obtain the Key.";
    }

	private void returnGemsToPlayerInventory() {
		for (String currGem: this.gemPlacementOrder) {
			this.pickUpItem(currGem);
		}
		
	}

	private Item handlePickupKey() {
		Item key = this.worldManager.getGameLocations().get("HiddenRoom").getItem("Key");
		return key;
	}
	
	/**
	 * Handles picking up a specific item.
	 * 
	 * @param itemName the name of the item to pick up
	 * @return pick-up result description
	 */
	public String pickUpItem(String itemName) {
        Item item = this.playerLocation.getItem(itemName);
        if (item != null) {
            this.player.getInventory().addItem(item);
            this.playerLocation.removeItem(item);
            this.canPickUpItems = false;
            if (this.isGem(itemName)) {
            	this.gemCounter += 1;
            }
            return "You picked up the " + item.getItemName() + ".";
        }
        return "There is no " + itemName + " to pick up.";
    }

	/**
	 * Handles picking up an item.
	 * 
	 * @return pick-up result description
	 */
	private String handlePickUp() {
		return "Picked Up Item.";
	}
	
	private String handlePlayerDeath() {
		this.player.loseLife();
		if (this.player.hasLives()) {
			this.player.resetHealth();
			this.worldManager.shuffleWorld();
			this.displayWorldMap();
			this.playerLocation = this.worldManager.getStartingLocation();
			this.previousLocation = null;
			this.canPickUpItems = false;
			return "You have died and been returned to the hallway. Lives remaining: " + this.player.getLives();
		} else {
			return "Game Over! You have no lives left.";
		}
	}
}
