package edu.westga.cs3211.text_adventure_game.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Text Adventure Game - ViewModel
 * 
 * Binds the GameManager and the MainWindow
 * 
 * @author Shawn Bretthauer and MoriaEL
 * @version Fall 2024
 */
public class ViewModel {

	private StringProperty locationDescription;
	private ListProperty<Actions> movementDirection;
	private StringProperty playerHealth;
	private ObjectProperty<Actions> selectedDirection;
	private BooleanProperty isGameOver;

	private BooleanProperty angelWingsVisible;
	private BooleanProperty blueGemVisible;
	private BooleanProperty greenGemVisible;
	private BooleanProperty keyVisible;
	private BooleanProperty whiteGemVisible;
	private BooleanProperty redGemVisible;
	private BooleanProperty swordVisible;

	private GameManager gameManager;

	/**
	 * Constructor for ViewModel.
	 */
	public ViewModel() {
		this.locationDescription = new SimpleStringProperty();
		this.movementDirection = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
		this.playerHealth = new SimpleStringProperty();
		this.selectedDirection = new SimpleObjectProperty<>();
		this.isGameOver = new SimpleBooleanProperty(false);

		this.initializeItemVisibility();

		this.gameManager = new GameManager();

		this.setupGame();
	}

	private void initializeItemVisibility() {
		this.angelWingsVisible = new SimpleBooleanProperty(false);
		this.blueGemVisible = new SimpleBooleanProperty(false);
		this.greenGemVisible = new SimpleBooleanProperty(false);
		this.keyVisible = new SimpleBooleanProperty(false);
		this.whiteGemVisible = new SimpleBooleanProperty(false);
		this.redGemVisible = new SimpleBooleanProperty(false);
		this.swordVisible = new SimpleBooleanProperty(false);
	}

	private void setupGame() {
		this.updateLocationDescription();
		this.updateMovementDirection();
		this.updatePlayerHealth();
		this.updateItemVisibility();
	}

	/**
	 * Handles the player's chosen action and updates the game state.
	 * 
	 * @param action the action to perform
	 * @return result description
	 */
	public String handleAction(Actions action) {
	    String result = this.gameManager.handleAction(action);

	    switch (action) {
	        case INTERACT:
	            this.addAndUpdateLocationDescription(result);
	            break;

	        case PICK_UP:
	            this.gameManager.pickUpItem(this.gameManager.getPlayerLocation().getItems().get(0).getItemName());
	            
	            break;

	        case FIGHT:
	            this.addAndUpdateLocationDescription(result);
	            break;

	        default:
	            this.updateLocationDescription();
	            break;
	    }
	    
	    this.updateMovementDirection();
	    this.updatePlayerHealth();
	    this.updateItemVisibility();

	    return result;
	}
	
	private void addAndUpdateLocationDescription(String text) {
		this.getLocationDescriptionProperty().set(this.gameManager.getLocationDescription() + "\n\n" + text);
	}
	
	/**
	 * Drops a specific item.
	 * 
	 * @param itemName the name of the item to drop
	 * @return drop result description
	 */
	public String dropItem(String itemName) {
		String result = this.gameManager.dropItem(itemName);
		if (this.gameManager.getPlayerLocation().getRoomName().equals("GoalRoom") && itemName.equals("Key")) {
    		this.isGameOver.set(true);
    	}
		this.addAndUpdateLocationDescription(result);
		this.updateMovementDirection();
		this.updatePlayerHealth();
		this.updateItemVisibility();
		return result;
	}

	/**
	 * Picks up a specific item.
	 * 
	 * @param itemName the name of the item to pick up
	 * @return pick-up result description
	 */
	public String pickUpItem(String itemName) {
		String result = this.gameManager.pickUpItem(itemName);
		this.updateLocationDescription();
		this.updateMovementDirection();
		this.updatePlayerHealth();
		this.updateItemVisibility();
		return result;
	}

	private void updateMovementDirection() {
		this.movementDirection.set(FXCollections.observableArrayList(this.gameManager.getActionOptions()));
	}

	private void updateLocationDescription() {
		this.locationDescription.set(this.gameManager.getLocationDescription());
	}

	private void updatePlayerHealth() {
		this.playerHealth.set(Integer.toString(this.gameManager.getPlayerHealth()));
		int health = Integer.parseInt(this.playerHealth.get());
		if (health <= 0) {
			this.isGameOver.set(true);
			this.addAndUpdateLocationDescription("You died");
		}
	}

	private void updateItemVisibility() {
		this.angelWingsVisible.set(this.gameManager.getPlayer().getInventory().getItem("AngelWings") != null);
		this.blueGemVisible.set(this.gameManager.getPlayer().getInventory().getItem("BlueGem") != null);
		this.greenGemVisible.set(this.gameManager.getPlayer().getInventory().getItem("GreenGem") != null);
		this.keyVisible.set(this.gameManager.getPlayer().getInventory().getItem("Key") != null);
		this.whiteGemVisible.set(this.gameManager.getPlayer().getInventory().getItem("WhiteGem") != null);
		this.redGemVisible.set(this.gameManager.getPlayer().getInventory().getItem("RedGem") != null);
		this.swordVisible.set(this.gameManager.getPlayer().getInventory().getItem("Sword") != null);
	}
	
	/**
	 * Prints the game world as a matrix.
	 */
	public void displayWorldMapInConsole() {
		this.gameManager.displayWorldMap();
	}

	/**
	 * Gets the location description property.
	 * 
	 * @return locationDescription
	 */
	public StringProperty getLocationDescriptionProperty() {
		return this.locationDescription;
	}
	
	/**
	 * Gets game over property.
	 * 
	 * @return game over boolean
	 */
	public BooleanProperty getGameOverProperty() {
		return this.isGameOver;
	}

	/**
	 * Gets the movement direction list property.
	 * 
	 * @return movementDirection
	 */
	public ListProperty<Actions> getMovementDirectionProperty() {
		return this.movementDirection;
	}

	/**
	 * Gets the player's health property.
	 * 
	 * @return playerHealth
	 */
	public StringProperty getPlayerHealthProperty() {
		return this.playerHealth;
	}

	/**
	 * Gets the selected direction property.
	 * 
	 * @return selectedDirection
	 */
	public ObjectProperty<Actions> getSelectedDirection() {
		return this.selectedDirection;
	}

	/**
	 * Checks if the game is over.
	 * 
	 * @return true if game is over, else false
	 */
	public Boolean getCheckForGoal() {
		return this.isGameOver.getValue();
	}
	
	/**
	 * Gets the angelWingsVisibleProperty
	 * 
	 * @return this.angelWingsVisible
	 */
	public BooleanProperty getAngelWingsVisibleProperty() {
		return this.angelWingsVisible;
	}

	/**
	 * gets the blueGemVisibleProperty
	 * 
	 * @return this.blueGemVisible
	 */
	public BooleanProperty getBlueGemVisibleProperty() {
		return this.blueGemVisible;
	}

	/**
	 * Gets the greenGemVisibleProperty
	 * 
	 * @return this.greenGemVisible
	 */
	public BooleanProperty getGreenGemVisibleProperty() {
		return this.greenGemVisible;
	}

	/**
	 * Gets the keyVisibleProperty
	 * 
	 * @return this.keyVisible
	 */
	public BooleanProperty getKeyVisibleProperty() {
		return this.keyVisible;
	}

	/**
	 * Gets the whiteGemVisibleProperty
	 * 
	 * @return this.whiteGemVisible
	 */
	public BooleanProperty getWhiteGemVisibleProperty() {
		return this.whiteGemVisible;
	}

	/**
	 * Gets the redGemVisibleProperty
	 * 
	 * @return this.redGemVisible
	 */
	public BooleanProperty getRedGemVisibleProperty() {
		return this.redGemVisible;
	}

	/**
	 * Gets the swordVisibleProperty
	 * 
	 * @return this.swordVisible
	 */
	public BooleanProperty getSwordVisibleProperty() {
		return this.swordVisible;
	}
	
	/**get game manager
	 * 
	 * @return gamemanager object
	 */
	public GameManager getGameManager() {
		return this.gameManager;
	}
}
