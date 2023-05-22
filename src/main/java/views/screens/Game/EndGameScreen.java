package views.screens.Game;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import models.Game;
import views.Main;
import views.components.Boss;
import views.components.Plane;

import java.time.Duration;

public class EndGameScreen {
    @FXML
    public Text title;
    @FXML
    public Text score;
    @FXML
    public Text time;

    @FXML
    public void initialize() {
        if (Game.getInstance().getScore() > 0)
            title.setText("Victory");
        else
            title.setText("Lose");
        score.setText("Score: " + Game.getInstance().getScore());
        Duration duration = Duration.between(Game.getInstance().getStartingTime(), Game.getInstance().getEndingTime());
        time.setText("Game duration: " + duration.toMinutes() + ":" + duration.toSeconds() % 60);
    }


    public void restart() {
        Main.isExitPressed = false;
        Plane.getInstance().reset();
        Boss.getInstance().reset();
        Game.getInstance().reset();
        Main.newGame();
    }

    public void backToMainMenu() {
        Main.isExitPressed = false;
        Plane.getInstance().reset();
        Boss.getInstance().reset();
        Game.getInstance().reset();
        Main.changeMenu("MainMenu");
    }
}
