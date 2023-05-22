package views.screens;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Player;
import views.Main;

public class RegisterMenu {
    @FXML
    public TextField username;
    @FXML
    public TextField nickname;
    @FXML
    public TextField password;

    @FXML
    public Text error;

    public void loginMenu() {
        Main.changeMenu("LoginMenu");
    }

    public void register() {
        String username = this.username.getText();
        String nickname = this.nickname.getText();
        String password = this.password.getText();

        if (username.isEmpty() || nickname.isEmpty() || password.isEmpty())
            error.setText("Fields cannot be empty");
        else if (Player.getPlayerByUsername(username) != null)
            error.setText("This username is already taken");
        else if (Player.getPlayerByNickname(nickname) != null)
            error.setText("This nickname is already taken");
        else {
            Player player = new Player(username, nickname, password);
            Player.addPlayer(player);
            Main.changeMenu("LoginMenu");
        }
    }
}
