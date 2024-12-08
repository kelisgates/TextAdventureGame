package edu.westga.cs3211.text_adventure_game.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3211.text_adventure_game.model.Actions;
import edu.westga.cs3211.text_adventure_game.model.FileReader;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
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
		FileReader fileReader = new FileReader("src/edu/westga/cs3211/text_adventure_game/assets/gameLocations.txt",
				"src/edu/westga/cs3211/text_adventure_game/assets/hazards.txt",
				"src/edu/westga/cs3211/text_adventure_game/assets/npc.txt",
				"src/edu/westga/cs3211/text_adventure_game/assets/items.txt");

		GameManager gameManager = new GameManager(fileReader);

		this.viewModel = new ViewModel(gameManager);

		this.bindFields();
		this.buttonListener();
		this.loadItemImages();
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
				String result = this.viewModel.dropItem("AngelWings");
				this.textAreaMainText.setText(result);
			}
		});

		this.blueGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getBlueGemVisibleProperty().get()) {
				String result = this.viewModel.dropItem("BlueGem");
				this.textAreaMainText.setText(result);
			}
		});

		this.greenGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getGreenGemVisibleProperty().get()) {
				String result = this.viewModel.dropItem("GreenGem");
				this.textAreaMainText.setText(result);
			}
		});

		this.key.setOnMouseClicked(event -> {
			if (this.viewModel.getKeyVisibleProperty().get()) {
				String result = this.viewModel.dropItem("Key");
				this.textAreaMainText.setText(result);
			}
		});

		this.whiteGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getWhiteGemVisibleProperty().get()) {
				String result = this.viewModel.dropItem("WhiteGem");
				this.textAreaMainText.setText(result);
			}
		});

		this.redGemImage.setOnMouseClicked(event -> {
			if (this.viewModel.getRedGemVisibleProperty().get()) {
				String result = this.viewModel.dropItem("RedGem");
				this.textAreaMainText.setText(result);
			}
		});

		this.swordImage.setOnMouseClicked(event -> {
			if (this.viewModel.getSwordVisibleProperty().get()) {
				String result = this.viewModel.dropItem("Sword");
				this.textAreaMainText.setText(result);
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
			this.textAreaMainText.setText(result);
			this.comboBoxAvailableActions.getSelectionModel().selectFirst();
			this.checkForWinOrGameOver(result);
		});
	}

	private void loadItemImages() {
		this.angelWingImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/AngelWings.jpg"));
		this.blueGemImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/BlueGem.jpg"));
		this.greenGemImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/GreenGem.jpg"));
		this.key.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/Key.jpg"));
		this.redGemImage.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/RedGem.jpg"));
		this.whiteGemImage
				.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/WhiteGem.jpg"));
		this.swordImage.setImage(new Image("file:src/edu/westga/cs3211/text_adventure_game/assets/images/Sword.jpg"));

		this.initializeItemVisibility();
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

	private void initializeItemVisibility() {
		this.angelWingImage.setDisable(true);
		this.blueGemImage.setDisable(true);
		this.greenGemImage.setDisable(true);
		this.key.setDisable(true);
		this.redGemImage.setDisable(true);
		this.whiteGemImage.setDisable(true);
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
