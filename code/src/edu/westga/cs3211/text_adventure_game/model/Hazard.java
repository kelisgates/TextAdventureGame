package edu.westga.cs3211.text_adventure_game.model;

/**
 * Text Adventure Game - Hazard Class
 * 
 * Holds all the information related to the hazard.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class Hazard {

	private String hazardName;
	private String hazardDescription;
	private int hazardDamageValue;
	
	/**
	 * Hazard constructor that requires a description and damage value
	 * 
	 * @param name the name of the hazard to be used as a key
	 * @param description the description of the hazard
	 * @param value the amount of damage the hazard does to the player
	 */
	public Hazard(String name, String description, int value) {
		this.hazardName = name;
		this.hazardDescription = description;
		this.hazardDamageValue = value;
	}
	
	/**
	 * Name of the hazard used as a key
	 * 
	 * @return hazardName
	 */
	public String getHazardName() {
		return this.hazardName;
	}
	
	/**
	 * The description of the hazard
	 * @return hazardDescription
	 */
	public String getHazardDescription() {
		return this.hazardDescription;
	}
	
	/**
	 * The amount of damage the hazard does to the player
	 * @return hazardDamageValue
	 */
	public int getHazardDamageValue() {
		return this.hazardDamageValue;
	}
}
