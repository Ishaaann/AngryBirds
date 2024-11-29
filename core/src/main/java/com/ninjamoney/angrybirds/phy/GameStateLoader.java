package com.ninjamoney.angrybirds.phy;

import com.ninjamoney.angrybirds.GameState;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class GameStateLoader {

    private static HashMap<Integer, GameState> levelWiseGameState = new HashMap<>();

    public static GameState loadLevelWiseGameData(int levelNum) {
        File levelMapFile = new File("C:\\Users\\Ishaan\\bhaari padhai\\2023248_2023449\\AngryBirds\\saves\\levelWiseData.dat");

        try {
            if (levelMapFile.exists() && levelMapFile.length() > 0) {
                try (FileInputStream levelMapInputStream = new FileInputStream(levelMapFile);
                     ObjectInputStream levelMapObjectInputStream = new ObjectInputStream(levelMapInputStream)) {
                    levelWiseGameState = (HashMap<Integer, GameState>) levelMapObjectInputStream.readObject();
                    System.out.println("Level-wise metadata loaded successfully.");
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error reading level metadata: " + e.getMessage());
                }
            } else {
                System.out.println("No level-wise metadata found or file is empty.");
            }

            File levelSaved = new File("C:\\Users\\Ishaan\\bhaari padhai\\2023248_2023449\\AngryBirds\\saves\\level_" + levelNum + "_data.dat");

            if (levelSaved.exists() && levelSaved.length() > 0) {
                try (FileInputStream gameDataInputStream = new FileInputStream(levelSaved);
                     ObjectInputStream gameDataObjectInputStream = new ObjectInputStream(gameDataInputStream)) {
                    GameState gameState = (GameState) gameDataObjectInputStream.readObject();
                    System.out.println("Game data for level " + levelNum + " loaded successfully.");
                    return gameState;
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error loading game data for level " + levelNum + ": " + e.getMessage());
                }
            } else {
                System.out.println("No game data found for level " + levelNum + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
