package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class PlayerController {
    private static PlayerController instance;

    private PlayerController() {
    }

    public static PlayerController getInstance() {
        if (instance == null) instance = new PlayerController();
        return instance;
    }


    public void logout() {
        Player.setLoggedInPlayer(null);
    }

    public void removeAccount(Player player) {
        Player.setLoggedInPlayer(null);
        Player.getPlayers().remove(player);
    }

    public void sortPlayers() {
        ArrayList<Player> players = Player.getPlayers();
        class sort implements Comparator<Player> {
            @Override
            public int compare(Player player1, Player player2) {
                int scoreSort = player1.getHighScore() - player2.getHighScore();
                int nameSort = player1.getNickname().compareTo(player2.getNickname());

                if (scoreSort == 0) return nameSort;
                else return scoreSort;
            }
        }
        players.sort(new sort());
    }


    public void loadUsers() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src", "main", "resources", "UserDatabase.json")));
            Player.setPlayers(new Gson().fromJson(json,
                    new TypeToken<ArrayList<Player>>() {
                    }.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveUsers() {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(Paths.get("src", "main", "resources", "UserDatabase.json")));
            fileWriter.write(new Gson().toJson(Player.getPlayers()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
