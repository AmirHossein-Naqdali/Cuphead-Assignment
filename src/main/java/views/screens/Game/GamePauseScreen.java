package views.screens.Game;

import models.Game;
import views.Main;
import views.components.Boss;
import views.components.Plane;

public class GamePauseScreen {

    public void mute() {
        Main.isExitPressed = false;
        // TODO: 5/31/2022
    }

    public void backToMainMenu() {
        Main.isExitPressed = false;
        Plane.getInstance().reset();
        Boss.getInstance().reset();
        Game.getInstance().reset();
        Main.changeMenu("MainMenu");
    }

    public void restart() {
        Main.isExitPressed = false;
        Plane.getInstance().reset();
        Boss.getInstance().reset();
        Game.getInstance().reset();
        Main.newGame();
    }

    public void resume() {
        Main.isExitPressed = false;
        Main.newGame();
    }
}
