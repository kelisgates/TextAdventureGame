package edu.westga.cs3211.text_adventure_game.test.testViewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.viewmodel.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestViewModel {

	@Test
	public void testViewModelStartingDescription() {
		ViewModel viewModel = new ViewModel();
		StringProperty description = new SimpleStringProperty();
		description.set("You enter a mysterious dungeon and a stone slab shuts behind you. You face South and you see you can move East or West.");
		
		assertEquals(description.getValue(), viewModel.getLocationDescriptionProperty().getValue());
	}
	
	@Test
	public void testViewModelSmokeRoomDescription() {
		ViewModel viewModel = new ViewModel();
		StringProperty description = new SimpleStringProperty();
		description.set("The room quickly fills with smoke. You cannot see any details in the room.");
		
		viewModel.setSelectedDirection(Actions.WEST);
		viewModel.movePlayerGetLocatinDescription();
		
		assertEquals(description.getValue(), viewModel.getLocationDescriptionProperty().getValue());
		assertEquals(false, viewModel.getCheckForGoal());
	}
	
	@Test
	public void testViewModelReachGoalRoom() {
		ViewModel viewModel = new ViewModel();
		StringProperty description = new SimpleStringProperty();
		description.set("You enter a roundish room and see a cake sitting on a table in the center of the room.");
		
		viewModel.setSelectedDirection(Actions.EAST);
		viewModel.movePlayerGetLocatinDescription();
		viewModel.setSelectedDirection(Actions.SOUTH);
		viewModel.movePlayerGetLocatinDescription();
		
		assertEquals(description.getValue(), viewModel.getLocationDescriptionProperty().getValue());
		assertEquals(true, viewModel.getCheckForGoal());
	}
}
