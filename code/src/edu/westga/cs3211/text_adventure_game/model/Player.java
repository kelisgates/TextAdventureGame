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

    private int lives;
    private int health;
    private Inventory inventory;

    /**
     * Constructor for a new Player object
     */
    public Player() {
        this.lives = 3;
        this.health = 10;
        this.inventory = new Inventory();
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
        this.health = 10;
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
}