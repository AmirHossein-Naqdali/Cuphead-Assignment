package views.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {
    private static Boss instance;

    private Boss() {
        super(550, 150, 651 / 1.8, 509 / 1.8);
        setBackground(0);
    }

    public static Boss getInstance() {
        if (instance == null) instance = new Boss();

        return instance;
    }


    private double health = 40;


    public double getHealth() {
        return health;
    }

    public void damage() {
        this.health -= Plane.getInstance().getStrength();
    }

    public void reset() {
        health = 40;
        this.setX(550);
        this.setY(150);
    }

    public void setBackground(int index) {
        this.setFill(new ImagePattern(new Image(String.valueOf(Boss.class.getResource("/Images/BossFly/" + index + ".png")))));
    }
}
