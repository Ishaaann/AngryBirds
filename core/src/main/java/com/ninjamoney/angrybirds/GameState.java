package com.ninjamoney.angrybirds;

import com.badlogic.gdx.math.Vector2;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.elements.character.pig.Pigs;
import com.ninjamoney.angrybirds.elements.struct.SolidObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<Pigs> piggas;
    public ArrayList<SolidObjects> solidObjects;
    public ArrayList<Birds> birdies;
    public ArrayList<Vector2> birdPositions;
    public ArrayList<Vector2> pigPositions;
    public ArrayList<Vector2> solidObjectPositions;
    public ArrayList<Float> pigHealth;
    public ArrayList<Float> solidObjectHealth;
    public ArrayList<Vector2> pigVelocity;
    public ArrayList<Vector2> solidObjectVelocity;
    public ArrayList<Vector2> birdVelocity;
    public ArrayList<Float> solidObjectsAngle;

    public ArrayList<Object> solidObjectsDestroyed;
    public ArrayList<Pigs> piggasDestroyed;

    public ArrayList<Object> bodiesToDestroy;
    public int levelNum;

    public GameState(ArrayList<Pigs> piggas, ArrayList<SolidObjects> solidObjects, ArrayList<Birds> birdies,
                     ArrayList<Vector2> birdPositions, ArrayList<Vector2> pigPositions, ArrayList<Vector2> solidObjectPositions,
                     ArrayList<Float> pigHealth, ArrayList<Float> solidObjectHealth, ArrayList<Vector2> pigVelocity,
                     ArrayList<Vector2> solidObjectVelocity, ArrayList<Vector2> birdVelocity, ArrayList<Object> solidObjectsDestroyed,
                     ArrayList<Pigs> piggasDestroyed, ArrayList<Object> bodiesToDestroy, int levelNum) {
        this.solidObjects = solidObjects != null ? solidObjects : new ArrayList<>();
        this.piggas = piggas != null ? piggas : new ArrayList<>();
        this.birdies = birdies != null ? birdies : new ArrayList<>();
        this.levelNum = levelNum;
        this.birdPositions = birdPositions != null ? birdPositions : new ArrayList<>();
        this.pigPositions = pigPositions != null ? pigPositions : new ArrayList<>();
        this.solidObjectPositions = solidObjectPositions != null ? solidObjectPositions : new ArrayList<>();
        this.solidObjectsAngle = solidObjectsAngle != null ? solidObjectsAngle : new ArrayList<>();
        this.pigHealth = pigHealth;
        this.solidObjectHealth = solidObjectHealth;
        this.pigVelocity = pigVelocity;
        this.solidObjectVelocity = solidObjectVelocity;
        this.birdVelocity = birdVelocity;
        this.solidObjectsDestroyed = solidObjectsDestroyed;
        this.piggasDestroyed = piggasDestroyed;
        this.bodiesToDestroy = bodiesToDestroy;
    }



}
