package views.transitions;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import views.components.MiniBoss;

import java.util.ArrayList;

public class MiniBossFlyAnimation extends Transition {
    private final MiniBoss miniBoss;
    private final Pane parent;
    private boolean isStopped;

    private static ArrayList<MiniBossFlyAnimation> miniBossFlyAnimations = new ArrayList<>();

    public MiniBossFlyAnimation(MiniBoss miniBoss, Pane parent) {
        this.miniBoss = miniBoss;
        this.parent = parent;
        miniBossFlyAnimations.add(this);
        setCycleDuration(Duration.millis(400));
        setCycleCount(20);
    }

    @Override
    protected void interpolate(double v) {
        int index = (int) Math.floor(v * 3);
        if (isStopped)
            miniBoss.setFill(null);
        else if (miniBoss.getColor().equals("yellow"))
            miniBoss.setYellowBackground(index);
        else
            miniBoss.setPurpleBackground(index);

        setOnFinished(actionEvent -> {
            parent.getChildren().remove(miniBoss);
            miniBossFlyAnimations.clear();
        });
    }


    public static ArrayList<MiniBossFlyAnimation> getMiniBossFlyAnimations() {
        return miniBossFlyAnimations;
    }

    public static void setMiniBossFlyAnimations(ArrayList<MiniBossFlyAnimation> miniBossFlyAnimations) {
        MiniBossFlyAnimation.miniBossFlyAnimations = miniBossFlyAnimations;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }
}
