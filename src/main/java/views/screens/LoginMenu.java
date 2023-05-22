package views.screens;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Player;
import views.Main;

public class LoginMenu {
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public Text error;


    public void login() {
        Player player;
        if (username.getText().isEmpty() || password.getText().isEmpty())
            error.setText("Fields cannot be empty");
        else if ((player = Player.getPlayerByUsername(username.getText())) == null)
            error.setText("Username or Password doesn't match");
        else if (!player.getPassword().equals(password.getText()))
            error.setText("Username or Password doesn't match");
        else {
            password.setText("");
            Player.setLoggedInPlayer(player);
            Main.changeMenu("MainMenu");
        }
    }

    public void registerMenu() {
        error.setText("");
        Main.changeMenu("RegisterMenu");
    }

    public void loginAsGuest() {
        Main.changeMenu("MainMenu");
    }
}
