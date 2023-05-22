package views.transitions;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import views.components.Boss;

public class BossShootAnimation extends Transition {
    private final Boss boss;

    public BossShootAnimation() {
        this.boss = Boss.getInstance();
        setCycleDuration(Duration.millis(700));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int index = (int) (Math.floor(v * 11) + 1);
        setBackground(index);
    }

    private void setBackground(int index) {
        boss.setFill(new ImagePattern(new Image(String.valueOf(getClass().getResource("/Images/BossShoot/" + index + ".png")))));
    }
}
