package views.transitions;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import views.components.Egg;
import views.components.Plane;

public class EggMovementAnimation extends Transition {
    private final Egg egg;
    private final Pane parent;

    public EggMovementAnimation(Egg egg, Pane parent) {
        this.egg = egg;
        this.parent = parent;
        setCycleDuration(Duration.millis(2500));
        setCycleCount(1);
        setDelay(Duration.millis(150));
    }

    @Override
    protected void interpolate(double v) {
        if (Double.compare(egg.getX(), 560) == 0)
            egg.setFill(new ImagePattern(new Image(String.valueOf(Egg.class.getResource("/Images/Egg.png")))));

        egg.setX(egg.getX() - 8);

        if (Plane.getInstance().hasCollision(egg)) {
            egg.setFill(null);
            Plane.getInstance().damage();
        }
        setOnFinished(actionEvent -> parent.getChildren().remove(egg));
    }
}
