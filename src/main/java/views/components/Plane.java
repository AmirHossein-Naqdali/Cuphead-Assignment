package views.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle {
    private static Plane instance;

    private Plane() {
        super(50, 250, 109, 91);
        this.setFill(new ImagePattern(new Image(String.valueOf(Plane.class.getResource("/Images/Cuphead.png")))));
    }

    public static Plane getInstance() {
        if (instance == null) instance = new Plane();

        return instance;
    }


    private double health = 10;
    private double strength = 2;
    private double defence = 2;

    public void moveUp() {
        if (this.getY() >= 35)
            this.setY(this.getY() - 10);
    }

    public void moveDown() {
        if (this.getY() <= 500)
            this.setY(this.getY() + 10);
    }

    public void moveLeft() {
        if (this.getX() >= 10)
            this.setX(this.getX() - 10);
    }

    public void moveRight() {
        if (this.getX() <= 780)
            this.setX(this.getX() + 10);
    }

    public boolean hasCollision(Rectangle block) {
        return block.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public void damage() {
        this.health -= this.defence;
    }

    public void reset() {
        health = 10;
        strength = 2;
        defence = 2;
        this.setX(50);
        this.setY(250);
    }
}
