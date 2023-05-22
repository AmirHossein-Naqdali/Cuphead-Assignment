package views.screens;

import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.Player;
import views.Main;

import java.util.ArrayList;

public class Scoreboard {
    @FXML
    public VBox vbox;

    @FXML
    public void initialize() {
        PlayerController.getInstance().sortPlayers();
        ArrayList<Player> players = Player.getPlayers();

        vbox.getChildren().addAll(createBoxes(players));
        vbox.getChildren().add(createBackButton());
    }

    private ArrayList<HBox> createBoxes(ArrayList<Player> players) {
        ArrayList<HBox> hBoxes = new ArrayList<>();

        for (int i = 0; i < Math.min(10, players.size()); i++) {
            HBox hBox = new HBox();
            hBox.getChildren().add(new Text(i + 1 + ") " + players.get(i).getNickname() + " | " + players.get(i).getHighScore()));

            hBox.setBackground(new Background(new BackgroundFill(Color.rgb(180, 240, 230), null, null)));
            hBox.setMaxSize(400, 30);
            hBox.setMinSize(400, 30);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setStyle("-fx-font-family: 'Yu Gothic Light'");
            hBox.setStyle("-fx-font-size: 20");
            hBox.setStyle("-fx-border-color: #6e6c6c");

            if (i == 0)
                hBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 225, 65), null, null)));
            else if (i == 1)
                hBox.setBackground(new Background(new BackgroundFill(Color.rgb(195, 195, 195), null, null)));
            else if (i == 2)
                hBox.setBackground(new Background(new BackgroundFill(Color.rgb(235, 160, 95), null, null)));

            hBoxes.add(hBox);
        }

        return hBoxes;
    }

    private Button createBackButton() {
        Button button = new Button("Back");
        button.setOnMouseClicked(mouseEvent -> Main.changeMenu("MainMenu"));
        return button;
    }
}
