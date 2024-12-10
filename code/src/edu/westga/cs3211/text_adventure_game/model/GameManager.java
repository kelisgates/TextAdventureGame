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
	private List<String> gemPlacementOrder;

	/**
	 * GameManager constructor.
	 */
	public GameManager() {
		this.player = new Player();
		this.worldManager = new WorldManager();
		this.actionOptions = new ArrayList<>();
		this.previousLocation = null;
		this.defeatedEnemies = new HashSet<>();
		this.canPickUpItems = false;
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
	 * Initializes the game by setting the starting location.
	 */
	private void initializeGameManager() {
		this.playerLocation = this.worldManager.getStartingLocation();
	}
	
	public void setPlayerLocation(String name) {
		this.playerLocation = this.worldManager.getGameLocations().get(name);
	}
	
	public Location getPlayerLocation() {
		return this.playerLocation;
	}

	/**
	 * Retrieves the current room's description.
	 * 
	 * @return the room description
	 */
	public String getLocationDescription() {
		StringBuilder description = new StringBuilder();

		description.append(this.playerLocation.getRoomDescription()).append("\n");
		this.appendNpcDescription(description);
		this.appendItemDescription(description);

		return description.toString();
	}

	private void appendItemDescription(StringBuilder description) {
		for (Item item : this.playerLocation.getItems()) {
			description.append("You see a ").append(item.getItemName()).append(" here.\n");
		}
	}

	private void appendNpcDescription(StringBuilder description) {
		for (NPC npc : this.playerLocation.getNpcs()) {
			description.append(npc.getDescription()).append("\n");
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

		this.healingAction();

		this.useKey();

	}

	private void useKey() {
		if (this.player.getInventory().getItem("Key") != null) {
			this.actionOptions.add(Actions.USE_KEY);
		}
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

	private void healingAction() {
		if (this.playerLocation.getRoomName().equals("HealingRoom") && this.player.getHealth() < 10) {
			this.actionOptions.add(Actions.HEAL);
		}
	}

	private void itemActions() {
		if (!this.playerLocation.getItems().isEmpty()) {
			this.actionOptions.add(Actions.PICK_UP);
		}
		if (!this.player.getInventory().getItems().isEmpty()) {
			this.actionOptions.add(Actions.DROP);
		}
	}

	private void npcInteraction() {
		for (NPC npc : this.playerLocation.getNpcs()) {
			if (npc instanceof EnemyNPC) {
				this.actionOptions.add(Actions.FIGHT);
				this.actionOptions.add(Actions.FLEE);
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
			return this.getLocationDescription();
		case FIGHT:
			return this.handleFight();
		case FLEE:
			return this.handleFlee();
		case INTERACT:
			return this.handleInteraction();
		case PICK_UP:
			return this.handlePickUp();
		case DROP:
			return "Please click on the item image to drop it.";
		case HEAL:
			return this.handleHeal();
		case USE_KEY:
			return this.handleUseKey();
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
		for (NPC npc : this.playerLocation.getNpcs()) {
			if (npc instanceof EnemyNPC) {
				EnemyNPC enemy = (EnemyNPC) npc;

				boolean hasSword = this.player.getInventory().getItem("Sword") != null;
				if (this.player.getHealth() >= 8 && hasSword) {
					this.playerLocation.removeNpc(enemy);
					this.defeatedEnemies.add(npc.getName());
					this.canPickUpItems = true;

					if (enemy.getItemDrop() != null) {
						this.playerLocation.addItem(enemy.getItemDrop());
					}
					return "You fought the " + enemy.getName() + " and won!";
				} else {
					return this.handlePlayerDeath();
				}
			}
		}
		return "No enemy to fight.";
	}

	/**
	 * Handles the flee action.
	 * 
	 * @return flee result description
	 */
	private String handleFlee() {
		if (this.previousLocation != null) {
			Location temp = this.playerLocation;
			this.playerLocation = this.previousLocation;
			this.previousLocation = temp;
			return "You fled back to the previous room.";
		}
		return "There is nowhere to flee!";
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
					//TODO: handle healing player
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
            
            if (this.playerLocation.getRoomName().equals("HiddenRoom")) {
                if (this.isGem(itemName)) {
                    this.gemPlacementOrder.add(itemName);
                    return this.handleGemPlacement();
                }
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
        
        if (this.gemPlacementOrder.size() == correctOrder.size()) {
            if (this.gemPlacementOrder.equals(correctOrder)) {
            	
                Item key = this.handlePickupKey();
                if (key != null) {
                    this.playerLocation.addItem(key);
                    this.worldManager.getGameLocations().get("GoalRoom").removeItem(key);
                    return "You have placed all gems in the correct order! The Key is now available to pick up.";
                } else {
                    return "Key is already placed.";
                }
            } else {
                this.gemPlacementOrder.clear();
                return "Incorrect gem placement order. Please try again.";
            }
        }
        return "Place all gems in the correct order to obtain the Key.";
    }

	private Item handlePickupKey() {
		Item key = this.worldManager.getGameLocations().get("GoalRoom").getItem("Key");
		return key;
	}
	
	/**
	 * Handles picking up a specific item.
	 * 
	 * @param itemName the name of the item to pick up
	 * @return pick-up result description
	 */
	public String pickUpItem(String itemName) {
        if (!this.canPickUpItems) {
            return "You cannot pick up items now.";
        }
        Item item = this.playerLocation.getItem(itemName);
        if (item != null) {
            this.player.getInventory().addItem(item);
            this.playerLocation.removeItem(item);
            this.canPickUpItems = false;
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
		if (this.canPickUpItems && !this.playerLocation.getItems().isEmpty()) {
			return "Please click on the item image to pick it up.";
		}
		return "No items to pick up.";
	}
	
	 private String handleUseKey() {
	        if (this.player.getInventory().getItem("Key") != null) {
	            Location goalRoom = this.worldManager.getGameLocations().get("GoalRoom");
	            String[] goalConnectedRooms = goalRoom.getConnectedRooms();
	            boolean isAdjacent = false;
	            for (String roomName : goalConnectedRooms) {
	                if (roomName.equals(this.playerLocation.getRoomName())) {
	                    isAdjacent = true;
	                    break;
	                }
	            }
	            if (isAdjacent) {
	                this.playerLocation = goalRoom;
	                return "You used the key to enter the Goal Room. You win!";
	            } else {
	                return "There is nothing to use the key on here.";
	            }
	        }
	        return "You don't have the key to use.";
	    }

	/**
	 * Handles healing in the HealingRoom.
	 * 
	 * @return heal result description
	 */
	private String handleHeal() {
		if (this.player.getHealth() < 10) {
			this.player.resetHealth();
			return "You have been healed to full health.";
		}
		return "Your health is already full.";
	}
	
	private String handlePlayerDeath() {
		this.player.loseLife();
		if (this.player.hasLives()) {
			this.player.resetHealth();
			this.worldManager.shuffleWorld();
			this.playerLocation = this.worldManager.getStartingLocation();
			this.previousLocation = null;
			this.canPickUpItems = false;
			return "You have died and been returned to the hallway. Lives remaining: " + this.player.getLives();
		} else {
			return "Game Over! You have no lives left.";
		}
	}
}
