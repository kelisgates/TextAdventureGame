package edu.westga.cs3211.text_adventure_game.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.viewmodel.ViewModel;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author CS 3211 & Shawn Bretthauer and MoriaEl
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private ImageView angelWingImage;

	@FXML
	private ImageView blueGemImage;

	@FXML
	private ImageView greenGemImage;

	@FXML
	private ImageView key;

	@FXML
	private ImageView redGemImage;

	@FXML
	private ImageView whiteGemImage;

	@FXML
	private ImageView swordImage;

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
		this.loadItemImages();
		
		this.viewModel.displayWorldMapInConsole();
	}

	private void bindFields() {
		this.textAreaMainText.textProperty().bind(this.viewModel.getLocationDescriptionProperty());
		this.comboBoxAvailableActions.setItems(this.viewModel.getMovementDirectionProperty());
		this.comboBoxAvailableActions.getSelectionModel().selectFirst();
		this.labelHealth.textProperty().bind(this.viewModel.getPlayerHealthProperty());
		this.viewModel.getSelectedDirection()
				.bind(this.comboBoxAvailableActions.getSelectionModel().selectedItemProperty());

		this.bindItemVisibility();
		this.bindDisableItemToVisibility();
		this.eventhandlerForDroppingItems();
	}

	private void eventhandlerForDroppingItems() {
		this.angelWingImage.setOnMouseClicked(event -> {
			if (this.viewModel.getAngelWingsVisibleProperty().get()) {
				this.viewModel.dropItem("AngelWings");
			}
		});

		this.blueGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getBlueGemVisibleProperty().get()) {
				this.viewModel.dropItem("BlueGem");
			}
		});

		this.greenGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getGreenGemVisibleProperty().get()) {
				this.viewModel.dropItem("GreenGem");
			}
		});

		this.key.setOnMouseClicked(event -> {
			if (this.viewModel.getKeyVisibleProperty().get()) {
				this.viewModel.dropItem("Key");
			}
		});

		this.whiteGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getWhiteGemVisibleProperty().get()) {
				this.viewModel.dropItem("WhiteGem");
			}
		});

		this.redGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getRedGemVisibleProperty().get()) {
				this.viewModel.dropItem("RedGem");
			}
		});

		this.swordImage.setOnMouseClicked(event -> {
			if (this.viewModel.getSwordVisibleProperty().get()) {
				this.viewModel.dropItem("Sword");
			}
		});
	}

	private void bindItemVisibility() {
		this.angelWingImage.visibleProperty().bind(this.viewModel.getAngelWingsVisibleProperty());
		this.blueGemImage.visibleProperty().bind(this.viewModel.getBlueGemVisibleProperty());
		this.greenGemImage.visibleProperty().bind(this.viewModel.getGreenGemVisibleProperty());
		this.key.visibleProperty().bind(this.viewModel.getKeyVisibleProperty());
		this.whiteGemImage.visibleProperty().bind(this.viewModel.getWhiteGemVisibleProperty());
		this.redGemImage.visibleProperty().bind(this.viewModel.getRedGemVisibleProperty());
		this.swordImage.visibleProperty().bind(this.viewModel.getSwordVisibleProperty());
	}

	private void buttonListener() {
		this.buttonTakeAction.setOnAction((ActionEvent event) -> {
			Actions action = this.comboBoxAvailableActions.getSelectionModel().getSelectedItem();
			if (action == null) {
				this.textAreaMainText.setText("Please select an action.");
				return;
			}
			String result = this.viewModel.handleAction(action);
			this.comboBoxAvailableActions.getSelectionModel().selectFirst();
			this.checkForWinOrGameOver(result);
		});
	}

	private void loadItemImages() {
		this.angelWingImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/Images/AngelWing.jpg"));
		this.blueGemImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/Images/BlueGem.jpg"));
		this.greenGemImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/Images/GreenGem.jpg"));
		this.key.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/Images/Key.jpg"));
		this.redGemImage.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/Images/RedGem.jpg"));
		this.whiteGemImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/WhiteGem.jpg"));
		this.swordImage.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/Images/Sword.jpg"));

	}

	private void bindDisableItemToVisibility() {
		this.angelWingImage.disableProperty().bind(Bindings.not(this.angelWingImage.visibleProperty()));
		this.blueGemImage.disableProperty().bind(Bindings.not(this.blueGemImage.visibleProperty()));
		this.greenGemImage.disableProperty().bind(Bindings.not(this.greenGemImage.visibleProperty()));
		this.key.disableProperty().bind(Bindings.not(this.key.visibleProperty()));
		this.redGemImage.disableProperty().bind(Bindings.not(this.redGemImage.visibleProperty()));
		this.whiteGemImage.disableProperty().bind(Bindings.not(this.whiteGemImage.visibleProperty()));
		this.swordImage.disableProperty().bind(Bindings.not(this.swordImage.visibleProperty()));
	}

	private void checkForWinOrGameOver(String result) {
		if (result.contains("Game Over")) {
			PauseTransition pause = new PauseTransition(Duration.seconds(4));
			pause.setOnFinished(e -> Platform.exit());
			pause.play();
		}

		if (this.viewModel.getCheckForGoal()) {
			PauseTransition pause = new PauseTransition(Duration.seconds(4));
			pause.setOnFinished(e -> Platform.exit());
			pause.play();
		}
	}
}
