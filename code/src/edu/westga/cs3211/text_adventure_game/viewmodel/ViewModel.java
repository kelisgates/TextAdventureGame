package edu.westga.cs3211.text_adventure_game.viewmodel;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
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
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class ViewModel {
	
	private StringProperty locationDescription;
	private ListProperty<Actions> movementDirection;
	private StringProperty playerHealth;
	private ObjectProperty<Actions> selectedAction;
	private Boolean isGameOver;
	
	private GameManager gameManager;
	
	/**
	 * Empty constructor for the ViewModel
	 */
	public ViewModel() {
		this.locationDescription = new SimpleStringProperty();
		this.movementDirection = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
		this.playerHealth = new SimpleStringProperty();
		this.selectedAction = new SimpleObjectProperty<Actions>();
		this.isGameOver = false;
		this.gameManager = new GameManager();
		
		this.setupGame();
	}
	
	private void setupGame() {
		this.setLocationDescriptionProperty();
		this.setActionList();
		this.setPlayerHealthProperty();
	}
	
	/**
	 * Moves the player
	 */
	public void movePlayerGetLocatinDescription() {
		Actions selectedAction = this.getSelectedAction().getValue();
		if (selectedAction == null) {
		    return;
		}
		
		this.gameManager.playerAction(selectedAction);
//		if (selectedDirection == Actions.NORTH) {
//		    this.gameManager.movePlayer(Actions.NORTH);
//		} else if (selectedDirection == Actions.EAST) {
//		    this.gameManager.movePlayer(Actions.EAST);
//		} else if (selectedDirection == Actions.SOUTH) {
//		    this.gameManager.movePlayer(Actions.SOUTH);
//		} else if (selectedDirection == Actions.WEST) {
//		    this.gameManager.movePlayer(Actions.WEST);
//		}
		
		this.setLocationDescriptionProperty();
		this.setActionList();
		this.setPlayerHealthProperty();	
		
		this.checkForEndGame();
	}
	
	private void checkForEndGame() {
		if (this.gameManager.checkRoomForGoal()) {
			this.isGameOver = true;
		}
		if (this.gameManager.getPlayerHealthPoints() <= 0) {
			this.isGameOver = true;
		}
	}
	
	/**
	 * Tells the view if the room is the goal
	 * 
	 * @return true if room is goalRoom, else false
	 */
	public Boolean getCheckForGoal() {
		return this.isGameOver;
	}
	
	/**
	 * Returns the location description to he view
	 * 
	 * @return locationDescription
	 */
	public StringProperty getLocationDescriptionProperty() {
		return this.locationDescription;
	}
	
	private void setLocationDescriptionProperty() {
		String description = this.gameManager.getLocationDescription();
        if (description != null) {
            this.locationDescription.set(description);
        }
	}
	
	/**
	 * Returns the 
	 * 
	 * @return movementDirection
	 */
	public ListProperty<Actions> getActionProperty() {
		return this.movementDirection;
	}
	
	private void setActionList() {
		List<Actions> options = this.gameManager.getMovementOptions();
        this.movementDirection.clear();
        this.movementDirection.setValue(FXCollections.observableArrayList(options));
	}
	
	/**
	 * Returns the player's health.
	 * 
	 * @return this.playerHealth
	 */
	public StringProperty getPlayerHealthProperty() {
		return this.playerHealth;
	}
	
	private void setPlayerHealthProperty() {
		this.playerHealth.set(Integer.toString(this.gameManager.getPlayerHealthPoints()));
	}
	
	/**
	 * Returns the selected direction
	 * 
	 * @return selectedDirection
	 */
	public ObjectProperty<Actions> getSelectedAction() {
		return this.selectedAction;
	}
	
	/**
	 * FOR TESTING PURPOSES ONLY
	 * 
	 * @param action direction to move player
	 */
	public void setSelectedAction(Actions action) {
		this.selectedAction.set(action);
	}
}
