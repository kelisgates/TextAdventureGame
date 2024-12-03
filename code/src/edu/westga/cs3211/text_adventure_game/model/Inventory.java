package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player's inventory.
 * 
 * @author MoriaEL
 * @version Fall 2024
 */
public class Inventory {
	private List<Item> items;

	/**
	 * The inventory constructor
	 */
	public Inventory() {
		this.items = new ArrayList<Item>();
	}

	/**
	 * Adds item to inventory
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param item Item to be added
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}

	/**
	 * Removes and verify if item is removed from inventory
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param item item to be removed
	 * @return true if removed, false otherwise
	 */
	public boolean removeItem(Item item) {
		return this.items.remove(item);
	}

	/**
	 * Gets item
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param itemName item to get
	 * @return item
	 */
	public Item getItem(String itemName) {
		for (Item item : this.items) {
			if (item.getItemName().equalsIgnoreCase(itemName)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Gets the list of items in inventory
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return list of inventory items
	 */
	public List<Item> getItems() {
		return this.items;
	}
}
