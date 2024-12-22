package edu.westga.cs3211.text_adventure_game.test.testInventory;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Inventory;
import edu.westga.cs3211.text_adventure_game.model.Item;

public class TestInventory {

	@Test
	public void testInventoryConstructor() {
		Inventory inventory = new Inventory();
		
		assertEquals(0, inventory.getSize());
	}
	
	@Test
	public void testInventoryAddItem() {
		Inventory inventory = new Inventory();
		Item item = new Item("Sword", "A sharp sword ideal for combat.");
		
		inventory.addItem(item);
		
		assertEquals(1, inventory.getSize());
	}
	
	@Test
	public void testInvetoryRemoveItem() {
		Inventory inventory = new Inventory();
		Item item = new Item("Sword", "A sharp sword ideal for combat.");
		
		inventory.addItem(item);
		inventory.removeItem(item);
		
		assertEquals(0, inventory.getSize());
	}
	
	@Test
	public void testInventoryRemoveFirstItem() {
		Inventory inventory = new Inventory();
		Item item1 = new Item("Sword", "A sharp sword ideal for combat.");
		Item item2 = new Item("RedGem", "A glowing red gemstone.");
		Item item3 = new Item("Key", "A mysterious black key that opens the goal room.");
		
		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);
		inventory.removeItem(item1);
		
		assertEquals(2, inventory.getSize());
		assertEquals(item2, inventory.getItem(item2.getItemName()));
		assertEquals(item3, inventory.getItem(item3.getItemName()));
	}
	
	@Test
	public void testInventoryRemoveMiddleItem() {
		Inventory inventory = new Inventory();
		Item item1 = new Item("Sword", "A sharp sword ideal for combat.");
		Item item2 = new Item("RedGem", "A glowing red gemstone.");
		Item item3 = new Item("Key", "A mysterious black key that opens the goal room.");
		
		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);
		inventory.removeItem(item2);
		
		assertEquals(2, inventory.getSize());
		assertEquals(item1, inventory.getItem(item1.getItemName()));
		assertEquals(item3, inventory.getItem(item3.getItemName()));
	}
	
	@Test
	public void testInventoryRemoveLasttem() {
		Inventory inventory = new Inventory();
		Item item1 = new Item("Sword", "A sharp sword ideal for combat.");
		Item item2 = new Item("RedGem", "A glowing red gemstone.");
		Item item3 = new Item("Key", "A mysterious black key that opens the goal room.");
		
		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);
		inventory.removeItem(item3);
		
		assertEquals(2, inventory.getSize());
		assertEquals(item1, inventory.getItem(item1.getItemName()));
		assertEquals(item2, inventory.getItem(item2.getItemName()));
	}
	
	@Test
	public void testInventoryNullReturn() {
		Inventory inventory = new Inventory();
		Item item = new Item("Sword", "A sharp sword ideal for combat.");
		inventory.addItem(item);
		String wrongName = "Key";
		
		assertNull(inventory.getItem(wrongName));
	}
	
	@Test
	public void testInventoryGetAllItems() {
		Inventory inventory = new Inventory();
		Item item1 = new Item("Sword", "A sharp sword ideal for combat.");
		Item item2 = new Item("RedGem", "A glowing red gemstone.");
		Item item3 = new Item("Key", "A mysterious black key that opens the goal room.");
		List<Item> itemList = new ArrayList<Item>();
		
		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		
		assertEquals(3, inventory.getSize());
		assertEquals(itemList, inventory.getItems());
	}
}
