package views.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class MiniBoss extends Rectangle {
    private final String color;
    private int health;

    public MiniBoss(String color, int dx, int y) {
        super(1200 - dx, y, 159 / 1.5, 109 / 1.5);
        this.color = color;
        this.health = 2;

        if (color.equals("yellow")) setYellowBackground(0);
        else setPurpleBackground(0);
    }

    public void setYellowBackground(int index) {
        this.setFill(new ImagePattern(new Image(String.valueOf(MiniBoss.class.getResource("/Images/MiniBossFly/Yellow/" + index + ".png")))));
    }

    public void setPurpleBackground(int index) {
        this.setFill(new ImagePattern(new Image(String.valueOf(MiniBoss.class.getResource("/Images/MiniBossFly/Purple/" + index + ".png")))));
    }

    public String getColor() {
        return color;
    }

    public int getHealth() {
        return health;
    }

    public void damage() {
        this.health--;
    }
}
