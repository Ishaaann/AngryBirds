package com.ninjamoney.angrybirds;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import com.esotericsoftware.kryo.kryo5.Kryo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class KryoGameState {

    private Kryo kryo;

    public KryoGameState() {
        kryo = new Kryo();
        kryo.register(GameState.class);  // Register the GameState class
        kryo.register(Vector2.class);    // Register the Vector2 class (or any other custom class)
    }

    // Save the game state to a file with a dynamic name like "Level1Save.kryo"
    public void saveGameState(GameState gameState, int level) {
        String fileName = "Level" + level + "Save.kryo"; // Create a dynamic file name like Level1Save.kryo
        try (Output output = new Output(new FileOutputStream(fileName))) {
            kryo.writeObject(output, gameState); // Save the game state
            System.out.println("GameState saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to save game state: " + e.getMessage());
        }
    }

    // Load the game state from a file (returns the game state)
    public GameState loadGameState(String fileName) {
        try (Input input = new Input(new FileInputStream(fileName))) {
            GameState gameState = kryo.readObject(input, GameState.class);
            System.out.println("GameState loaded from " + fileName);
            return gameState;
        } catch (IOException e) {
            System.err.println("Failed to load game state: " + e.getMessage());
            return null;
        }
    }

    // Load the game state for a specific level (using the file name)
    public GameState loadLevelGameState(int level) {
        String fileName = "Level" + level + "Save.kryo";  // Construct file name for the specific level
        return loadGameState(fileName);  // Load the game state from the corresponding file
    }
}
