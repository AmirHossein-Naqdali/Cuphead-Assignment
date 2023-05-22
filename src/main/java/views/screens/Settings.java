package views.screens;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import views.Main;
import views.components.Plane;

public class Settings {
    @FXML
    public ToggleGroup group;


    public void muteGameSound() {
        // TODO: 5/28/2022
    }

    public void backToMainMenu() {
        RadioButton radioButton = (RadioButton) group.getSelectedToggle();
        if (radioButton.getText().equals("Easy")) {
            Plane.getInstance().setHealth(20);
            Plane.getInstance().setStrength(3);
            Plane.getInstance().setDefence(1);
        } else if (radioButton.getText().equals("Hard")) {
            Plane.getInstance().setHealth(4);
            Plane.getInstance().setStrength(1);
            Plane.getInstance().setDefence(3);
        } else if (radioButton.getText().equals("Medium")) {
            Plane.getInstance().setHealth(10);
            Plane.getInstance().setStrength(2);
            Plane.getInstance().setDefence(2);
        }
        Main.changeMenu("MainMenu");
    }
}
