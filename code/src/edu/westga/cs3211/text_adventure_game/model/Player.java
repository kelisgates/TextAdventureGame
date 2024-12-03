package edu.westga.cs3211.text_adventure_game.model;

/**
 * Text Adventure Game - Player Class
 * 
 * Holds all the information related to the player.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class Player {

	private int playerHitPoints;

	/**
	 * Constructor for a new Player object
	 */
	public Player() {
		this.playerHitPoints = 10;
	}

	/**
	 * Reduces the Player's hit points by the damage value of the hazard.
	 * 
	 * @param damage the damage value of the hazard
	 */
	public void reducePlayerHitPoint(int damage) {
		this.playerHitPoints = this.playerHitPoints - damage;

		if (this.playerHitPoints < 0) {
			this.playerHitPoints = 0;
		}
	}

	/**
	 * Returns the current HP of the player.
	 * 
	 * @return playerHitPoints
	 */
	public int getPlayerHitPoints() {
		return this.playerHitPoints;
	}
}
