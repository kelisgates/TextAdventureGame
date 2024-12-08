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

	/**
	 * GameManager constructor.
	 * 
	 * @param gameFiles the FileReader instance containing game data
	 */
	public GameManager(FileReader gameFiles) {
		this.player = new Player();
		this.worldManager = new WorldManager(gameFiles);
		this.actionOptions = new ArrayList<>();
		this.initializeGameManager();
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

		this.checkForHazard(description);

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

	private void checkForHazard(StringBuilder description) {
		if (this.playerLocation.hasHazard()) {
			Hazard hazard = this.playerLocation.getHazard();
			description.append(hazard.getHazardDescription()).append("\n");
			this.player.reduceHealth(hazard.getHazardDamageValue());

			if (this.player.isDead()) {
				return this.handlePlayerDeath();
			}
		} else {
			description.append(this.playerLocation.getRoomDescription()).append("\n");
		}
	}

	private String handlePlayerDeath() {
		this.player.loseLife();
		if (this.player.hasLives()) {
			this.player.resetHealth();
			this.worldManager.shuffleWorld();
			this.playerLocation = this.worldManager.getStartingLocation();
			return "You have died and been returned to the hallway. Lives remaining: " + this.player.getLives();
		} else {
			return "Game Over! You have no lives left.";
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
			this.playerLocation = this.worldManager.getGameLocations().get(nextRoomName);
		}

		this.actionOptions.clear();
	}

	 /**
     * Generates the list of possible actions based on the current location.
     */
    private void generateActionList() {
        String[] options = this.playerLocation.getConnectedRooms();

        this.actionOptions.clear();

        this.moveMentActions(options);

        this.npcInteraction();

        this.itemActions();

        this.healingAction();

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
                return this.handleDrop();
            case HEAL:
                return this.handleHeal();
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
                int playerDamage = this.player.getInventory().getItem("Sword") != null ? 10 : 5;
                enemy.reduceHealth(playerDamage);
                this.player.reduceHealth(enemy.getAttackDamage());

                if (enemy.isDefeated()) {
                    this.playerLocation.removeNpc(enemy);
                    if (enemy.getItemDrop() != null) {
                        this.playerLocation.addItem(enemy.getItemDrop());
                    }
                    return "You defeated the " + enemy.getName() + "!";
                }

                if (this.player.isDead()) {
                    return this.handlePlayerDeath();
                }

                return "You attacked the " + enemy.getName() + ". It has " + enemy.getHealth() + " health remaining.";
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
        // Implement flee logic, e.g., move back to the previous room
        // For simplicity, inform the player
        return "You flee back to the previous room.";
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
                // Implement specific interactions based on NPC
                return friendly.getDialogue();
            }
        }
        return "No one to interact with.";
    }

    /**
     * Handles picking up an item.
     * 
     * @return pick-up result description
     */
    private String handlePickUp() {
        if (!this.playerLocation.getItems().isEmpty()) {
            Item item = this.playerLocation.getItems().get(0);
            this.player.getInventory().addItem(item);
            this.playerLocation.removeItem(item);
            return "You picked up the " + item.getItemName() + ".";
        }
        return "No items to pick up.";
    }

    /**
     * Handles dropping an item.
     * 
     * @return drop result description
     */
    private String handleDrop() {
        if (!this.player.getInventory().getItems().isEmpty()) {
            // For simplicity, drop the first item
            Item item = this.player.getInventory().getItems().get(0);
            this.player.getInventory().removeItem(item);
            this.playerLocation.addItem(item);
            return "You dropped the " + item.getItemName() + ".";
        }
        return "No items to drop.";
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


}
