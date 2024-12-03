package edu.westga.cs3211.text_adventure_game.model;

/**
 * Represents an item in the game.
 * 
 * @author MoriaEl
 * @version Fall 2024
 */
public class Item {
    private String itemName;
    private String description;

    /**
     * Constructs a new item.
     * @param itemName name of item.
     * @param description item's description.
     */
    public Item(String itemName, String description) {
        this.itemName = itemName;
        this.description = description;
    }

    /**
     * Gets the name of the item
     * 
     * @return the item's name.
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Gets item description
     * 
     * @return the item's description.
     */
    public String getDescription() {
        return this.description;
    }
}