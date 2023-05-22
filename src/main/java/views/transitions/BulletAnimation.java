package views.transitions;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import models.Game;
import views.components.Boss;
import views.components.Bullet;

public class BulletAnimation extends Transition {
    private final Bullet bullet;
    private final Pane parent;

    public BulletAnimation(Bullet bullet, Pane parent) {
        this.bullet = bullet;
        this.parent = parent;
        setCycleDuration(Duration.millis(3500));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        bullet.setX(bullet.getX() + 4);

        setOnFinished(actionEvent -> parent.getChildren().remove(bullet));

        if (bullet.hasCollision(Boss.getInstance())) {
            bullet.setFill(null);
            parent.getChildren().remove(bullet);
            Boss.getInstance().damage();
            Game.getInstance().addScore(3);
        }
    }
}
