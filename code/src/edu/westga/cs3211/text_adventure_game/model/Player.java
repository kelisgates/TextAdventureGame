package edu.westga.cs3211.text_adventure_game.model;

/**
 * Text Adventure Game - Player Class
 * 
 * Holds all the information related to the player.
 * 
 * @author Shawn Bretthauer and MoriaEL
 * @version Fall 2024
 */
public class Player {

    private static final int PLAYERSHEALTH = 10;
	private static final int PLAYERSLIVES = 3;
	private int lives;
    private int health;
    private Inventory inventory;
    private boolean givenQuest; 

    /**
     * Constructor for a new Player object
     */
    public Player() {
        this.lives = PLAYERSLIVES;
        this.health = PLAYERSHEALTH;
        this.inventory = new Inventory();
        this.givenQuest = false;
    }

    /**
     * Reduces the Player's health by the damage value. 
     * 
     * @param damage the damage value
     */
    public void reduceHealth(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    /**
     * Returns the current health of the player.
     * 
     * @return health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Checks if the player is dead.
     * 
     * @return true if health <= 0
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Resets the player's health to full.
     */
    public void resetHealth() {
        this.health = PLAYERSHEALTH;
    }

    /**
     * Gets the player's inventory.
     * 
     * @return inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Gets the player's remaining lives.
     * 
     * @return lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Reduces the player's lives by one.
     */
    public void loseLife() {
        this.lives--;
    }

    /**
     * Checks if the player has lives remaining.
     * 
     * @return true if lives > 0
     */
    public boolean hasLives() {
        return this.lives > 0;
    }
    
    /**
     * Checks if the player has given the quest.
     * 
     * @return true if quest given, else false
     */
    public boolean hasGivenQuest() {
        return this.givenQuest;
    }
    
    /**
     * Sets the quest as given.
     * 
     * @param givenQuest true if quest has been given
     */
    public void setGivenQuest(boolean givenQuest) {
        this.givenQuest = givenQuest;
    }
}