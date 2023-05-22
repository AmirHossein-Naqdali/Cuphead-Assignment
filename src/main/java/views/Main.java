package views;

import controllers.PlayerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;


public class Main extends Application {
    private static Scene scene;
    public static boolean isExitPressed = false;
    public static HashMap<String, Parent> roots = new HashMap<>();

    public static void main(String[] args) {
        PlayerController.getInstance().loadUsers();
        launch(args);
        PlayerController.getInstance().saveUsers();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(Objects.requireNonNull(loadFXML("LoginMenu")));
        Main.scene = scene;
        stage.setScene(scene);
        stage.setTitle("Cuphead");
        stage.setResizable(false);
        stage.setOnCloseRequest(windowEvent -> isExitPressed = true);
        stage.show();
    }

    public static void changeMenu(String menuName) {
        Main.scene.setRoot(loadFXML(menuName));
    }

    private static Parent loadFXML(String menuName) {
        if (roots.get(menuName) == null) {
            URL url = Main.class.getResource("/fxml/" + menuName + ".fxml");
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(url));
                roots.put(menuName, parent);
                return parent;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            return roots.get(menuName);
        return null;
    }

    public static void newGame() {
        roots.remove("Game/GameScreen");
        Main.changeMenu("Game/GameScreen");
    }

    public static void newScoreBoard() {
        roots.remove("Scoreboard");
        Main.changeMenu("Scoreboard");
    }
}
