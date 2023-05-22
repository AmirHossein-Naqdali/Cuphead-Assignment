package models;

import java.time.LocalTime;

public class Game {
    private static Game instance;

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();

        return instance;
    }


    private LocalTime startingTime = null;
    private LocalTime endingTime = null;

    private int score = 0;

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void reset() {
        startingTime = null;
        endingTime = null;
        score = 0;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
