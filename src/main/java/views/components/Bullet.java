package views.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {
    public Bullet() {
        super(Plane.getInstance().getX() + 100, Plane.getInstance().getY() + 40, 72, 15);
        this.setFill(new ImagePattern(new Image(String.valueOf(Bullet.class.getResource("/Images/Bullet.png")))));
    }

    public boolean hasCollision(Rectangle block) {
        return block.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
