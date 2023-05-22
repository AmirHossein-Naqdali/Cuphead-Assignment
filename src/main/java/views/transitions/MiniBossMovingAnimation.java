package views.transitions;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import models.Game;
import views.components.Bullet;
import views.components.MiniBoss;
import views.components.Plane;

import java.util.ArrayList;

public class MiniBossMovingAnimation extends Transition {
    private final ArrayList<MiniBoss> miniBosses;
    private final Pane parent;

    public MiniBossMovingAnimation(ArrayList<MiniBoss> miniBosses, Pane parent) {
        this.miniBosses = miniBosses;
        this.parent = parent;
        setCycleDuration(Duration.millis(8000));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        for (MiniBoss miniBoss : miniBosses)
            miniBoss.setX(miniBoss.getX() - 4);

        for (int i = 0; i < miniBosses.size(); i++) {
            if (Plane.getInstance().hasCollision(miniBosses.get(i))) {
                Plane.getInstance().setX(50);
                Plane.getInstance().setY(250);
                Plane.getInstance().damage();
                MiniBossFlyAnimation.getMiniBossFlyAnimations().get(i).setStopped(true);
                miniBosses.remove(miniBosses.get(i));
                break;
            }
        }

        for (int i = 0; i < miniBosses.size(); i++) {
            MiniBoss miniBoss = miniBosses.get(i);
            for (Node child : parent.getChildren()) {
                if (child instanceof Bullet && ((Bullet) child).hasCollision(miniBoss)) {
                    ((Bullet) child).setFill(null);
                    parent.getChildren().remove(child);
                    Game.getInstance().addScore(2);
                    miniBoss.damage();
                    if (miniBoss.getHealth() == 0) {
                        MiniBossFlyAnimation.getMiniBossFlyAnimations().get(i).setStopped(true);
                        miniBosses.remove(miniBosses.get(i));
                    }
                    break;
                }
            }
        }
    }
}
