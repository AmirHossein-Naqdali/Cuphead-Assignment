package models;

import java.util.ArrayList;

public class Player {
    private String username;
    private final String nickname;
    private String password;
    private int highScore;

    private static Player loggedInPlayer = null;

    private static ArrayList<Player> players = new ArrayList<>();

    public Player(String username, String nickname, String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.highScore = 0;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        if (players != null)
            Player.players = players;
    }

    public static Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public static void setLoggedInPlayer(Player loggedInPlayer) {
        Player.loggedInPlayer = loggedInPlayer;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public static void addPlayer(Player player) {
        Player.players.add(player);
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : players)
            if (player.getUsername().equals(username)) return player;

        return null;
    }

    public static Player getPlayerByNickname(String nickname) {
        for (Player player : players)
            if (player.getNickname().equals(nickname)) return player;

        return null;
    }
}