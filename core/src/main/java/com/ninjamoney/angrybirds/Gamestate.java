package com.ninjamoney.angrybirds;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonValue;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.elements.character.pig.Pigs;
import com.ninjamoney.angrybirds.levels.Level1;
import com.ninjamoney.angrybirds.levels.Level2;
import com.ninjamoney.angrybirds.levels.Level3;

import java.util.ArrayList;
import java.util.Queue;

public class Gamestate implements Json.Serializable {
    public boolean isPaused;
    public float score;
    int levelNumber;
    public ArrayList<PigState> pigsArray = new ArrayList<>();
    public ArrayList<BirdState> birdsArray = new ArrayList<>();
    public Queue<Birds> birdQueue;

    public Gamestate() {
    }

    @Override
    public void write(Json json) {
        
    }

    @Override
    public void read(Json json, JsonValue jsonValue) {

    }

    public static class PigState {
        public float positionX;
        public float positionY;
        public float velocityX, velocityY;
        public float health;

        public PigState(float positionX, float positionY, float velocityX, float velocityY, float health) {
            this.positionX = positionX;
            this.positionY = positionY;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.health = health;
        }
    }

    public static class BirdState {
        public float positionX;
        public float positionY;
        public float velocityX, velocityY;
        public float health;

        public BirdState(float positionX, float positionY, float health, float velocityX, float velocityY) {
            this.positionX = positionX;
            this.positionY = positionY;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.health = health;
        }
    }

    public void updateBirdStates(ArrayList<Birds> birds) {
        birdsArray.clear();
        for (Birds bird : birds) {
            birdsArray.add(new BirdState(bird.getBirdBody().getPosition().x, bird.getBirdBody().getPosition().y, bird.getHealth(),
                bird.getBirdBody().getLinearVelocity().x, bird.getBirdBody().getLinearVelocity().y));
        }
    }

    public void updatePigStates(ArrayList<Pigs> pigs) {
        pigsArray.clear();
        for (Pigs pig : pigs) {
            pigsArray.add(new PigState(pig.getPigBody().getPosition().x, pig.getPigBody().getPosition().y,
                pig.getPigBody().getLinearVelocity().x, pig.getPigBody().getLinearVelocity().y, pig.getHealth()));
        }
    }

    public void saveGameState(String filePath) {
        Json json = new Json();
        FileHandle fileHandle = Gdx.files.local(filePath);
        fileHandle.writeString(json.toJson(this), false);
        System.out.println("Game state saved!");
    }

    public static Gamestate loadGameState(String filePath) {
        Json json = new Json();
        FileHandle fileHandle = Gdx.files.local(filePath);
        Gamestate gamestate = json.fromJson(Gamestate.class, fileHandle.readString());
        System.out.println("Game state loaded!");
        return gamestate;
    }
}
