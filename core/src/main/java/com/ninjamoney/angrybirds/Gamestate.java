package com.ninjamoney.angrybirds;

import com.badlogic.gdx.Gdx;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.elements.character.pig.Pigs;

import java.io.*;
import java.util.ArrayList;
import java.util.Queue;

public class Gamestate implements Serializable {
    private static final long serialVersionUID = 1L;
    public boolean isPaused;
    public float score;
    int levelNumber;
    public ArrayList<Pigs> pigsArray = new ArrayList<>();
    public ArrayList<Birds> birdsArray = new ArrayList<>();
    public Queue<Birds> birdQueue;

    public static class PigState implements Serializable {
        public float positionX;
        public float positionY;
        public float health;
    }

    public static class BirdState implements Serializable {
        public float positionX;
        public float positionY;
        public float health;
    }

    public void saveGameState(String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Gdx.files.local(filePath).file()))) {
            out.writeObject(this);
            System.out.println("Game state saved!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save game state.");
        }
    }

    public static Gamestate loadGameState(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Gdx.files.local(filePath).file()))) {
            return (Gamestate) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to load game state.");
            return null;
        }
    }
}
