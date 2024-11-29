package com.ninjamoney.angrybirds.phy;

import com.ninjamoney.angrybirds.GameState;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.elements.character.pig.Pigs;
import com.ninjamoney.angrybirds.elements.struct.SolidObjects;
import com.badlogic.gdx.math.Vector2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameStateListener implements Serializable {
    private static HashMap<Integer, GameState> levelWiseGameState = new HashMap<>();

    public void saveGameState(ArrayList<SolidObjects> solidObjects, ArrayList<Pigs> piggas,
                              ArrayList<Birds> birdies, ArrayList<Object> solidObjectsDestroyed,
                              ArrayList<Pigs> piggasDestroyed, ArrayList<Object> bodiesToDestroy, int levelNum) {
        ArrayList<Vector2> solidObjectPositions = new ArrayList<>();
        for (Object so : solidObjects) {
            if(so instanceof SolidObjects){
                if(((SolidObjects) so).getBody() != null) {
                    solidObjectPositions.add(((SolidObjects) so).getBody().getPosition());
                }
            }
        }

        ArrayList<Vector2> pigPositions = new ArrayList<>();
        for (Pigs pig : piggas) {
            if(pig.getPigBody() != null){
                pigPositions.add(pig.getPigBody().getPosition());
            }
        }
        ArrayList<Vector2> birdPositions = new ArrayList<>();
        for (Object bird : birdies) {
            birdPositions.add(((Birds) bird).getBirdBody().getPosition());
        }

        ArrayList<Float> pigHealth = new ArrayList<>();
        for (Pigs pig : piggas) {
            pigHealth.add(pig.getHealth());
        }

        ArrayList<Float> solidObjectHealth = new ArrayList<>();
        for (Object so : solidObjects) {
            if (so instanceof SolidObjects) {
                if (((SolidObjects) so).getBody() != null) {
                    solidObjectHealth.add(((SolidObjects) so).getHp());
                }
            }
        }

        ArrayList<Vector2> pigVelocity = new ArrayList<>();
        for (Pigs pig : piggas) {
            if(pig.getPigBody() != null){
                pigVelocity.add(pig.getPigBody().getLinearVelocity());
            }
        }

        ArrayList<Vector2> solidObjectVelocity = new ArrayList<>();
        for (Object so : solidObjects) {
            if (so instanceof SolidObjects) {
                if (((SolidObjects) so).getBody() != null) {
                    solidObjectVelocity.add(((SolidObjects) so).getBody().getLinearVelocity());
                }
            }
        }

        ArrayList<Vector2> birdVelocity = new ArrayList<>();
        for (Object bird : birdies) {
            birdVelocity.add(((Birds) bird).getBirdBody().getLinearVelocity());
        }

        GameState data = new GameState(piggas, solidObjects, birdies,
            birdPositions, pigPositions, solidObjectPositions,
            pigHealth, solidObjectHealth, pigVelocity, solidObjectVelocity,
            birdVelocity, solidObjectsDestroyed , piggasDestroyed, bodiesToDestroy, levelNum);
        saveLevelWise(levelNum, data);
    }


    public void saveLevelWise(int levelNum, GameState gameState) {
        File saveDir = new File("saves");
        File levelFile = new File(saveDir, "level_" + levelNum + "_data.dat");
        File levelMapFile = new File(saveDir, "levelWiseData.dat");

        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        try {
            FileOutputStream gameDataOutputStream = new FileOutputStream(levelFile);
            ObjectOutputStream gameDataObjectOutputStream = new ObjectOutputStream(gameDataOutputStream);
            gameDataObjectOutputStream.writeObject(gameState);
            gameDataObjectOutputStream.close();
            System.out.println("Game data for level " + levelNum + " saved successfully.");
            levelWiseGameState.put(levelNum, gameState);

            FileOutputStream levelMapOutputStream = new FileOutputStream(levelMapFile);
            ObjectOutputStream levelMapObjectOutputStream = new ObjectOutputStream(levelMapOutputStream);
            levelMapObjectOutputStream.writeObject(levelWiseGameState);
            levelMapObjectOutputStream.close();
            System.out.println("Level-wise metadata saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static GameState loadLevelWiseGameData(int levelNum) {
        File saveDir = new File("saves");
        File levelMapFile = new File(saveDir, "levelWiseData.dat");

        try {
            if (levelMapFile.exists() && levelMapFile.length() > 0) {
                System.out.println("Loading level-wise metadata...");
                try (FileInputStream levelMapInputStream = new FileInputStream(levelMapFile);
                     ObjectInputStream levelMapObjectInputStream = new ObjectInputStream(levelMapInputStream)) {
                    levelWiseGameState = (HashMap<Integer, GameState>) levelMapObjectInputStream.readObject();
                    System.out.println("Level-wise metadata loaded successfully.");
                } catch (EOFException e) {
                    System.out.println("File is empty: " + levelMapFile.getPath());
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error reading level metadata: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("No level-wise metadata found or file is empty.");
            }

            File levelSaved = new File(saveDir, "level_" + levelNum + "_data.dat");

            if (levelSaved.exists() && levelSaved.length() > 0) {
                try (FileInputStream gameDataInputStream = new FileInputStream(levelSaved);
                     ObjectInputStream gameDataObjectInputStream = new ObjectInputStream(gameDataInputStream)) {
                    GameState gameState = (GameState) gameDataObjectInputStream.readObject();
                    System.out.println("Game data for level " + levelNum + " loaded successfully.");
                    return gameState;
                } catch (EOFException e) {
                    System.out.println("Game data for level " + levelNum + " is empty or corrupted.");
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error loading game data for level " + levelNum + ": " + e.getMessage());
                    e.printStackTrace();
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
