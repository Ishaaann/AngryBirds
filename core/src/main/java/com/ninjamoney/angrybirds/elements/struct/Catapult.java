package com.ninjamoney.angrybirds.elements.struct;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.phy.TrajectoryPredictor;

public class Catapult {
    private float x; // X-coordinate of the catapult
    private float y; // Y-coordinate of the catapult
    private Texture catapultTexture = new Texture("elements/struct/catapult.png");
    private Birds currentBird; // Reference to the bird being manipulated
    private boolean isPulling = false; // Flag for pull state
    private float maxStretch = 10; // Maximum distance the bird can be stretched
    public TrajectoryPredictor trajectoryPredictor;


    public Catapult(float x, float y) {
        this.x = x;
        this.y = y;
        trajectoryPredictor = new TrajectoryPredictor();
    }

    public void setCurrentBird(Birds bird) {
        this.currentBird = bird;
    }

    public boolean isPulling() {
        return isPulling;
    }

    public Birds getCurrentBird() {
        return currentBird;
    }

    private boolean isMouseOverCatapult() {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis
        return mouseX >= x && mouseX <= x + catapultTexture.getWidth()
            && mouseY >= y && mouseY <= y + catapultTexture.getHeight();
    }

    public void pull(Birds bird) {
        if (isMouseOverCatapult() && !isPulling && bird != null && bird.getBirdBody() != null) {
            this.currentBird = bird;
            this.isPulling = true;
            bird.getBirdBody().setGravityScale(0); // Disable gravity while pulling
            bird.getBirdBody().setLinearVelocity(0, 0); // Stop all movement
        }
    }

    public void updatePull() {
        if (isPulling && currentBird != null && currentBird.getBirdBody() != null) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis



            Vector2 mousePosition = new Vector2(mouseX, mouseY);
            Vector2 catapultPosition = new Vector2(x,y);
            Vector2 stretchVector = mousePosition.sub(catapultPosition);

            // Limit the stretch distance to maxStretch
            if (stretchVector.len() > maxStretch) {
                stretchVector.nor().scl(maxStretch);
            }

            // Update bird's position relative to the catapult's base
            currentBird.getBirdBody().setTransform(catapultPosition.add(stretchVector), 0);
        }
    }

    public void release() {
        if (isPulling && currentBird != null && currentBird.getBirdBody() != null) {
            isPulling = false;

            Vector2 birdPosition = currentBird.getBirdBody().getPosition();
            Vector2 catapultPosition = new Vector2(160f,144f);
            Vector2 launchVector = catapultPosition.sub(birdPosition); // Direction from bird to catapult base
            float launchPower = launchVector.len() * 2000000000; // Scale the power by stretch distance (adjust scaling factor as needed)

            // Apply force to the bird
            currentBird.getBirdBody().applyLinearImpulse(launchVector.nor().scl(launchPower), birdPosition, true);
            currentBird.getBirdBody().setGravityScale(1); // Re-enable gravity

            // Clear the current bird
            currentBird = null;

        }
    }

    public Texture getCatapultTexture() {
        return catapultTexture;
    }

    public void dispose() {
        catapultTexture.dispose();
    }
}
