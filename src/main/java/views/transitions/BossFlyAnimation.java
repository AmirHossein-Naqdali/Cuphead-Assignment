package views.transitions;

import javafx.animation.Transition;
import javafx.util.Duration;
import views.components.Boss;
import views.components.Plane;

public class BossFlyAnimation extends Transition {
    private final Boss boss;

    public BossFlyAnimation(Boss boss) {
        this.boss = boss;
        setCycleDuration(Duration.millis(400));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int index = (int) Math.floor(v * 5);
        boss.setBackground(index);
        if (Plane.getInstance().hasCollision(boss)) {
            Plane.getInstance().setX(50);
            Plane.getInstance().setY(250);
            Plane.getInstance().damage();
        }
    }
}
