package edu.westga.cs3211.text_adventure_game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Text Adventure Game - FileReader class
 * 
 * File Reader will read in a file of the game locations given to it by the
 * GameManager.
 * 
 * @author Shawn Bretthauer and MoriaEl
 * @version Fall 2024
 */
public class FileReader {
	
	private String gameMapFile;
	private String hazardFile;
	private String npcFile;
	private String itemFile;

	private HashMap<String, Location> gameLocations;
	private HashMap<String, Hazard> hazards;
	private HashMap<String, NPC> npcs;
	private HashMap<String, Item> items;

	/**
	 * FileReader Constructor
	 */
	public FileReader() {
		this.gameMapFile = "src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt";
		 this.hazardFile = "src/edu/westga/cs3211/text_adventure_game/assets/hazards.txt";
		 this.npcFile = "src/edu/westga/cs3211/text_adventure_game/assets/npcs.txt";
		 this.itemFile = "src/edu/westga/cs3211/text_adventure_game/assets/items.txt";
		
		 this.gameLocations = new HashMap<>();
		 this.hazards = new HashMap<>();
		 this.npcs = new HashMap<>();
		 this.items = new HashMap<>();
		 
		 this.loadAllData();
	}
	
	/**
	 * FileReader Constructor
	 * 
	 * @param gameMapFile name of the game location file
	 * @param hazardFile  name of the hazard file
	 * @param npcFile     name of the NPC file
	 * @param itemFile    name of the item file
	 */
	public FileReader(String gameMapFile, String hazardFile, String npcFile, String itemFile) {
		this.gameMapFile = gameMapFile;
		this.hazardFile = hazardFile;
		this.npcFile = npcFile;
		this.itemFile = itemFile;

		this.gameLocations = new HashMap<>();
		this.hazards = new HashMap<>();
		this.npcs = new HashMap<>();
		this.items = new HashMap<>();
		
		this.loadAllData();
	}

	/**
	 * Load all game data.
	 */
	public void loadAllData() {
		this.loadItems();
		this.loadHazards();
		this.loadNpcs();
		this.loadLocations();
	}

	private void loadItems() {
		try (Scanner scanner = new Scanner(new File(this.itemFile))) {
			while (scanner.hasNextLine()) {
				String name = scanner.nextLine();
				
				if (name.isEmpty()) {
					continue;
				}
				
				String description = scanner.nextLine();
				Item item = new Item(name, description);
				this.items.put(name, item);
			}
		} catch (FileNotFoundException exception) {
			System.err.println("Item file not found.");
		}
	}

	private void loadHazards() {
		try (Scanner scanner = new Scanner(new File(this.hazardFile))) {
			while (scanner.hasNextLine()) {
				String name = scanner.nextLine().trim();
				 if (name.isEmpty()) {
		                continue;
		            }
				String description = scanner.nextLine().trim();
				int damage = Integer.parseInt(scanner.nextLine().trim());
				Hazard hazard = new Hazard(name, description, damage);
				this.hazards.put(name, hazard);
			}
		} catch (FileNotFoundException exception) {
			System.err.println("Hazard file not found.");
		}
	}

	private void loadNpcs() {
		try (Scanner scanner = new Scanner(new File(this.npcFile))) {
			while (scanner.hasNextLine()) {
				String type = scanner.nextLine();
				
				if (type.isEmpty()) {
					continue;
				}

				String name = scanner.nextLine();
				String description = scanner.nextLine();
				String dialogue = scanner.nextLine();

				if (type.equalsIgnoreCase("Enemy")) {
					int attackDamage = Integer.parseInt(scanner.nextLine());
					String itemDropName = scanner.nextLine();
					Item itemDrop = this.items.get(itemDropName);
					
					EnemyNPC enemy = new EnemyNPC(name, description, dialogue, attackDamage, itemDrop);
					this.npcs.put(name, enemy);
				} else if (type.equalsIgnoreCase("Friendly")) {
					FriendlyNPC friendly = new FriendlyNPC(name, description, dialogue);
					this.npcs.put(name, friendly);
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("NPC file not found.");
		}
	}

	private void loadLocations() {
		try (Scanner scanner = new Scanner(new File(this.gameMapFile))) {
			while (scanner.hasNextLine()) {
				String name = scanner.nextLine().trim();
				if (name.isEmpty()) {
					continue;
				}
				
				String description1 = scanner.nextLine();
				String description2 = scanner.nextLine();
				String hazardName = scanner.nextLine();
				boolean isGoal = Boolean.parseBoolean(scanner.nextLine());

				Hazard hazard = this.hazards.get(hazardName);

				Location location = new Location(name, description1, description2, hazard, isGoal);

				if (name.equals("WeaponRoom")) {
					location.addItem(this.items.get("Sword"));
				}

				this.addNpcToLocation(name, location);

				this.addGemToLocation(name, location);
				
				 if (name.equals("HiddenRoom")) {
		                location.addItem(this.items.get("Key"));
		            }

				this.gameLocations.put(name, location);
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Game map file not found.");
		}
	}

	private void addGemToLocation(String name, Location location) {
		if (name.equals("DragonRoom")) {
			location.addItem(this.items.get("RedGem"));
			location.addItem(this.items.get("AngelWings"));
		}
		if (name.equals("GoblinRoom")) {
			location.addItem(this.items.get("GreenGem"));
		}
		if (name.equals("WolfRoom")) {
			location.addItem(this.items.get("WhiteGem"));
		}
		if (name.equals("AngelRoom")) {
			location.addItem(this.items.get("BlueGem"));
		}
	}

	private void addNpcToLocation(String name, Location location) {
		if (name.equals("DragonRoom")) {
			location.addNpc((EnemyNPC) this.npcs.get("Dragon"));
		}
		if (name.equals("GoblinRoom")) {
			location.addNpc((EnemyNPC) this.npcs.get("Goblin"));
		}
		if (name.equals("WolfRoom")) {
			location.addNpc((EnemyNPC) this.npcs.get("Wolf"));
		}
		if (name.equals("AngelRoom")) {
			location.addNpc((FriendlyNPC) this.npcs.get("Angel"));
		}
		if (name.equals("HealingRoom")) {
			location.addNpc((FriendlyNPC) this.npcs.get("Healer"));
		}
	}

	/**
	 * Gets the locations in a HashMap
	 * 
	 * @return gameLocations
	 */
	public HashMap<String, Location> getLocations() {
		return this.gameLocations;
	}

	/**
	 * Gets the hazards in a HashMap
	 * 
	 * @return hazards
	 */
	public HashMap<String, Hazard> getHazards() {
		return this.hazards;
	}

	/**
	 * Gets a hashmap of available NPCs
	 * 
	 * @return npcs
	 */
	public HashMap<String, NPC> getNPCs() {
		return this.npcs;
	}

	/**
	 * Gets a hashmap of items.
	 * 
	 * @return items
	 */
	public HashMap<String, Item> getItems() {
		return this.items;
	}
}