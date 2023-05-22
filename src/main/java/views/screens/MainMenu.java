package views.screens;

import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.Player;
import views.Main;

import java.util.HashMap;

public class MainMenu {
    @FXML
    public Button profileButton;

    @FXML
    public void initialize() {
        if (Player.getLoggedInPlayer() == null)
            profileButton.setDisable(true);
    }

    public void startNewGame() {
        Main.newGame();
    }

    public void showScoreboard() {
        Main.newScoreBoard();
    }

    public void settings() {
        Main.changeMenu("Settings");
    }

    public void logout() {
        PlayerController.getInstance().logout();
        Main.roots = new HashMap<>();
        Main.changeMenu("LoginMenu");
    }

    public void profileMenu() {
        Main.changeMenu("ProfileMenu");
    }
}
