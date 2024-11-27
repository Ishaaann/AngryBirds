package com.ninjamoney.angrybirds;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    public List<Vector2> birdPositions;    // Bird positions
    public List<Vector2> birdVelocities;   // Bird velocities
    public List<Vector2> pigPositions;     // Pig positions
    public List<Vector2> structurePositions; // Structure positions (blocks, catapults, etc.)
    public Vector2 slingshotPosition;       // Slingshot position
    public int levelsCleared;               // Track levels cleared

    public GameState() {
        birdPositions = new ArrayList<>();
        birdVelocities = new ArrayList<>();
        pigPositions = new ArrayList<>();
        structurePositions = new ArrayList<>();
        levelsCleared = 0;  // Default to 0, can be incremented as the levels are cleared
    }
}
