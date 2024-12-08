package edu.westga.cs3211.text_adventure_game.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.viewmodel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 3211 & Shawn Bretthauer
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private ImageView AngelWingImage;

	@FXML
	private ImageView BlueGemImage;

	@FXML
	private ImageView GreenGemImage;

	@FXML
	private ImageView Key;

	@FXML
	private ImageView RedGemImage;

	@FXML
	private ImageView WhiteGemImage;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button buttonTakeAction;

	@FXML
	private ComboBox<Actions> comboBoxAvailableActions;

	@FXML
	private ImageView imageHealth;

	@FXML
	private TextArea textAreaMainText;

	@FXML
	private Label labelHealth;

	private ViewModel viewModel;

	@FXML
	void initialize() {
		this.viewModel = new ViewModel();
		this.bindFields();
		this.buttonListener();
	}

	private void bindFields() {
		this.textAreaMainText.textProperty().bind(this.viewModel.getLocationDescriptionProperty());
		this.comboBoxAvailableActions.setItems(this.viewModel.getActionProperty());
		this.comboBoxAvailableActions.getSelectionModel().selectFirst();
		this.labelHealth.textProperty().bind(this.viewModel.getPlayerHealthProperty());
		this.viewModel.getSelectedDirection()
				.bind(this.comboBoxAvailableActions.getSelectionModel().selectedItemProperty());
	}

	private void buttonListener() {
		this.buttonTakeAction.setOnAction((ActionEvent event) -> {
			this.viewModel.movePlayerGetLocatinDescription();
			this.comboBoxAvailableActions.getSelectionModel().selectFirst();
			this.checkForWin();
		});
	}

	private void checkForWin() {
		if (this.viewModel.getCheckForGoal()) {
			this.buttonTakeAction.setDisable(this.viewModel.getCheckForGoal());
			this.comboBoxAvailableActions.setDisable(this.viewModel.getCheckForGoal());
		}

	}
}
