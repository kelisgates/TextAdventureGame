package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Text Adventure Game - Location Class
 * 
 * Holds all the information related to the location.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class Location {

	private String roomName;
	private boolean firstTimeEntry;
	private String roomDescription1;
	private String roomDescription2;
	private String[] connectedRooms;
	private Boolean isGoal;
	private List<Item> items;
	private List<NPC> npcs;
	private Hazard hazard;

	/**
	 * Constructor for room with needed attributes
	 * 
	 * @param name           the name of the room
	 * @param description1   the initial description
	 * @param description2   the subsequent description
	 * @param connectedRooms array of connected rooms
	 * @param hazard         hazard in the room (can be null)
	 * @param isGoal         whether this room is the goal
	 */
	public Location(String name, String description1, String description2, String[] connectedRooms, Hazard hazard,
			boolean isGoal) {
		this.roomName = name;
		this.firstTimeEntry = true;
		this.roomDescription1 = description1;
		this.roomDescription2 = description2;
		this.connectedRooms = connectedRooms;
		this.hazard = hazard;
		this.isGoal = isGoal;
		this.items = new ArrayList<Item>();
		this.npcs = new ArrayList<NPC>();
	}

	/**
	 * Gets the name of the room to use as a key
	 * 
	 * @return roomName
	 */
	public String getRoomName() {
		return this.roomName;
	}

	/**
	 * Returns the appropriate room description based on if the room has initially
	 * or returned
	 * 
	 * @return description1 if firstTimeEntry == true, description2 if
	 *         firstTimeEntry == false
	 */
	public String getRoomDescription() {
		if (this.firstTimeEntry) {
			this.firstTimeEntry = false;
			return this.roomDescription1;
		}
		return this.roomDescription2;
	}
	
	/**
     * Sets the array of connected rooms.
     * 
     * @param connectedRooms array of connected room names
     */
    public void setConnectedRooms(String[] connectedRooms) {
        this.connectedRooms = connectedRooms;
    }


	/**
	 * Returns the String array of connected rooms.
	 * 
	 * @return connectedRooms
	 */
	public String[] getConnectedRooms() {
		return this.connectedRooms;
	}

	/**
	 * Checks if the room has a hazard.
	 * 
	 * @return true if hazard is present
	 */
	public boolean hasHazard() {
		return this.hazard != null;
	}

	/**
	 * Gets the hazard in the room.
	 * 
	 * @return hazard
	 */
	public Hazard getHazard() {
		return this.hazard;
	}

	/**
	 * Checks if the room is the goal room.
	 * 
	 * @return true if room is goal
	 */
	public boolean isGoal() {
		return this.isGoal;
	}

	/**
	 * Gets the items in the room.
	 * 
	 * @return items
	 */
	public List<Item> getItems() {
		return this.items;
	}

	/**
	 * Adds an item to the room.
	 * 
	 * @param item to add
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}

	/**
	 * Removes an item from the room.
	 * 
	 * @param item to remove
	 * @return true if item was removed
	 */
	public boolean removeItem(Item item) {
		return this.items.remove(item);
	}

	/**
	 * Gets the NPCs in the room.
	 * 
	 * @return npcs
	 */
	public List<NPC> getNpcs() {
		return this.npcs;
	}

	/**
	 * Adds an NPC to the room.
	 * 
	 * @param npc to add
	 */
	public void addNpc(NPC npc) {
		this.npcs.add(npc);
	}

	/**
	 * Removes an NPC from the room.
	 * 
	 * @param npc to remove
	 * @return true if npc was removed
	 */
	public boolean removeNpc(NPC npc) {
		return this.npcs.remove(npc);
	}

}
