package views.screens;

import controllers.PlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.Player;
import views.Main;

public class ProfileMenu {
    @FXML
    public TextField username;
    @FXML
    public TextField oldPassword;
    @FXML
    public TextField newPassword;

    @FXML
    public Text usernameLog;
    @FXML
    public Text passwordLog;

    public void changeUsername() {
        passwordLog.setText("");
        usernameLog.setFill(Color.rgb(250, 10, 10));
        if (username.getText().isEmpty())
            usernameLog.setText("You should fill this field");
        else if (Player.getPlayerByUsername(username.getText()) != null)
            usernameLog.setText("This username is already taken");
        else {
            Player.getLoggedInPlayer().setUsername(username.getText());
            username.setText("");
            usernameLog.setFill(Color.rgb(80, 190, 55));
            usernameLog.setText("Username changed successfully");
        }
    }

    public void changePassword() {
        usernameLog.setText("");
        passwordLog.setFill(Color.rgb(250, 10, 10));
        if (oldPassword.getText().isEmpty() || newPassword.getText().isEmpty())
            passwordLog.setText("Fields cannot be empty");
        else if (!Player.getLoggedInPlayer().getPassword().equals(oldPassword.getText()))
            passwordLog.setText("Password is incorrect");
        else if (oldPassword.getText().equals(newPassword.getText()))
            passwordLog.setText("Passwords are identical");
        else {
            Player.getLoggedInPlayer().setPassword(newPassword.getText());
            oldPassword.setText("");
            newPassword.setText("");
            passwordLog.setFill(Color.rgb(80, 190, 55));
            passwordLog.setText("Password changed successfully");
        }
    }

    public void logout() {
        PlayerController.getInstance().logout();
        Main.changeMenu("LoginMenu");
    }

    public void deleteAccount() {
        PlayerController.getInstance().removeAccount(Player.getLoggedInPlayer());
        Main.changeMenu("LoginMenu");
    }

    public void backToMainMenu() {
        Main.changeMenu("MainMenu");
    }
}
