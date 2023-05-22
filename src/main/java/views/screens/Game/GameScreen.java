package views.screens.Game;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.Game;
import models.Player;
import views.Main;
import views.components.*;
import javafx.fxml.FXML;
import views.transitions.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class GameScreen {
    @FXML
    public Pane parent;

    private double planeInitialHealth;


    @FXML
    public void initialize() {
        if (Game.getInstance().getStartingTime() == null)
            Game.getInstance().setStartingTime(LocalTime.now());
        parent.getChildren().add(createPlane());
        parent.getChildren().add(createBoss());
        parent.getChildren().add(createControlBar());


        Platform.runLater(() -> parent.getChildren().get(0).requestFocus());
        addMiniBosses().start();
        addEgg().start();
        updateControlBar().start();
    }

    private HBox createControlBar() {
        HBox hBox = new HBox();
        planeInitialHealth = Plane.getInstance().getHealth();
        ProgressBar playerHealthBar = new ProgressBar(1);
        Text playerHealth = new Text("Your health:" + Plane.getInstance().getHealth());
        Text playerScore = new Text("Score:" + Game.getInstance().getScore());

        ProgressBar bossHealthBar = new ProgressBar(1);
        Text bossHealth = new Text("Boss Health:" + Boss.getInstance().getHealth());
        Text timer = new Text("00:00");
        Button button = new Button("Pause");
        button.setFocusTraversable(false);
        button.setOnMouseClicked(mouseEvent -> {
            Main.isExitPressed = true;
            Main.changeMenu("Game/GamePauseScreen");
        });
        hBox.getChildren().add(playerHealthBar);
        hBox.getChildren().add(playerHealth);
        hBox.getChildren().add(playerScore);
        hBox.getChildren().add(bossHealthBar);
        hBox.getChildren().add(bossHealth);
        hBox.getChildren().add(timer);
        hBox.getChildren().add(button);
        return hBox;
    }

    private void setControlBar() {
        HBox hBox = null;
        for (Node child : parent.getChildren())
            if (child instanceof HBox) hBox = (HBox) child;

        ProgressBar playerHealthBar = (ProgressBar) hBox.getChildren().get(0);
        Text playerHealth = (Text) hBox.getChildren().get(1);
        Text playerScore = (Text) hBox.getChildren().get(2);
        ProgressBar bossHealthBar = (ProgressBar) hBox.getChildren().get(3);
        Text bossHealth = (Text) hBox.getChildren().get(4);
        Text timer = (Text) hBox.getChildren().get(5);

        if (Plane.getInstance().getHealth() <= 0) {
            Game.getInstance().setEndingTime(LocalTime.now());
            Game.getInstance().setScore(0);
            Main.isExitPressed = true;
            Main.changeMenu("Game/EndGameScreen");
        } else if (Boss.getInstance().getHealth() <= 0) {
            Game.getInstance().setEndingTime(LocalTime.now());
            Game.getInstance().setScore((int) (Game.getInstance().getScore() + 100 * (Plane.getInstance().getHealth() / planeInitialHealth)));
            if (Player.getLoggedInPlayer() != null)
                Player.getLoggedInPlayer().setHighScore(Player.getLoggedInPlayer().getHighScore() + Game.getInstance().getScore());
            Main.isExitPressed = true;
            Main.changeMenu("Game/EndGameScreen");
        }
        playerHealthBar.setProgress(Plane.getInstance().getHealth() / planeInitialHealth);
        playerHealth.setText("Your health:" + Plane.getInstance().getHealth());
        playerScore.setText("Score:" + Game.getInstance().getScore());
        bossHealthBar.setProgress(Boss.getInstance().getHealth() / 40);
        bossHealth.setText("Boss Health:" + Boss.getInstance().getHealth());
        Duration duration = Duration.between(Game.getInstance().getStartingTime(), LocalTime.now());
        timer.setText(duration.toMinutes() + ":" + duration.toSeconds() % 60);
    }

    private Thread updateControlBar() {
        return new Thread(() -> {
            while (!Main.isExitPressed) {
                setControlBar();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Plane createPlane() {
        Plane plane = Plane.getInstance();
        plane.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode().getName()) {
                case "Left":
                case "A":
                    plane.moveLeft();
                    break;
                case "Right":
                case "D":
                    plane.moveRight();
                    break;
                case "Up":
                case "W":
                    plane.moveUp();
                    break;
                case "Down":
                case "S":
                    plane.moveDown();
                    break;
                case "Space":
                    Bullet bullet = new Bullet();
                    parent.getChildren().add(bullet);
                    BulletAnimation bulletAnimation = new BulletAnimation(bullet, parent);
                    bulletAnimation.play();
                    break;
            }
        });
        return plane;
    }

    private Boss createBoss() {
        Boss boss = Boss.getInstance();
        BossFlyAnimation bossFlyAnimation = new BossFlyAnimation(boss);
        bossFlyAnimation.play();
        return boss;
    }

    private ArrayList<MiniBoss> createMiniBoss() {
        int y = (int) (Math.random() * 465 + 35);
        MiniBoss miniBoss1;
        MiniBoss miniBoss2;
        MiniBoss miniBoss3;
        if (Double.compare(Math.random(), 0.5) < 0) {
            miniBoss1 = new MiniBoss("yellow", 0, y);
            miniBoss2 = new MiniBoss("purple", 125, y);
            miniBoss3 = new MiniBoss("yellow", 250, y);
        } else {
            miniBoss1 = new MiniBoss("purple", 0, y);
            miniBoss2 = new MiniBoss("yellow", 125, y);
            miniBoss3 = new MiniBoss("purple", 250, y);
        }
        new MiniBossFlyAnimation(miniBoss1, parent).play();
        new MiniBossFlyAnimation(miniBoss2, parent).play();
        new MiniBossFlyAnimation(miniBoss3, parent).play();
        ArrayList<MiniBoss> miniBosses = new ArrayList<>();
        miniBosses.add(miniBoss1);
        miniBosses.add(miniBoss2);
        miniBosses.add(miniBoss3);
        new MiniBossMovingAnimation(miniBosses, parent).play();

        return miniBosses;
    }

    private Thread addMiniBosses() {
        return new Thread(() -> {
            while (!Main.isExitPressed) {
                Platform.runLater(() -> parent.getChildren().addAll(createMiniBoss()));
                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < 13; i++) {
                        if (!Main.isExitPressed) Thread.sleep(1000);
                        else break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            MiniBossFlyAnimation.setMiniBossFlyAnimations(new ArrayList<>());
        });
    }

    private Egg createEgg() {
        Egg egg = new Egg();
        new EggMovementAnimation(egg, parent).play();
        new BossShootAnimation().play();
        return egg;
    }

    private Thread addEgg() {
        return new Thread(() -> {
            while (!Main.isExitPressed) {
                try {
                    Thread.sleep(4000);
                    Platform.runLater(() -> parent.getChildren().add(createEgg()));
                    if (!Main.isExitPressed)
                        Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
