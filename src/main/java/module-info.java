module Cuphead {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.google.gson;

    opens views to javafx.controls;
    opens views.screens to javafx.controls;
    opens controllers to com.google.gson;
    opens models to com.google.gson;
    exports views;
    exports views.screens;
    exports views.screens.Game;
    opens views.screens.Game to javafx.controls;
}