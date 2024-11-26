package com.ninjamoney.angrybirds.phy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ninjamoney.angrybirds.levels.Level1;

public class TrajectoryPredictor {
    private static final float TIME_STEP = 0.5f; // Smaller time step for more precise prediction
    private static final int NUM_POINTS = 50; // More points for smoother trajectory
    private static final float GRAVITY = -9.8f; // Standard gravity
    private static final float VELOCITY_SCALAR = 1f; // Increased velocity scaling

    private Array<Vector2> trajectoryPoints;
    private Texture dotTexture;
    private Sprite dotSprite;
    TextureRegion dotTR;

    public TrajectoryPredictor() {
        trajectoryPoints = new Array<Vector2>();
        dotTexture = new Texture("elements/char/bomb.png");
        dotTR = new TextureRegion(dotTexture,50,50);

        dotSprite = new Sprite(dotTexture);
        dotSprite.setSize(10,10);
    }

    public void calculateTrajectory(Vector2 startPos, Vector2 velocity) {
        trajectoryPoints.clear();

        // Create temporary vectors to avoid modifying the input
        Vector2 currentPos = new Vector2(startPos);
        Vector2 currentVel = new Vector2(velocity);

        // Calculate trajectory points using verlet integration
        for (int i = 0; i < NUM_POINTS; i++) {
            trajectoryPoints.add(new Vector2(currentPos));

            currentVel.y += GRAVITY * TIME_STEP;
            // Update position and velocity for next point
            currentPos.x += currentVel.x * TIME_STEP;
            currentPos.y += currentVel.y * TIME_STEP;

            // Apply gravity to vertical velocity


            // Don't add points below ground level
            if (currentPos.y < 0) break;
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < trajectoryPoints.size; i++) {
            Vector2 point = trajectoryPoints.get(i);

            // Calculate size and transparency based on the point's index
            float scale = 0.2f * (1.0f - (float) i / NUM_POINTS);
            float alpha = 1.0f - (float) i / NUM_POINTS;

            dotSprite.setSize(dotTR.getRegionWidth() * scale, dotTR.getRegionHeight() * scale);
            dotSprite.setOriginCenter();
            dotSprite.setPosition(point.x - dotSprite.getWidth() / 2, point.y - dotSprite.getHeight() / 2);
            dotSprite.setColor(1, 1, 1, alpha);

            dotSprite.draw(batch);
        }
    }

    public void updateTrajectory(Vector2 slingshotPos, Vector2 dragPos) {
        // Calculate launch velocity based on pull distance
        Vector2 dragDirection = new Vector2(dragPos).sub(slingshotPos);

        // Scale the velocity based on drag distance
        float launchPower = dragDirection.len() * VELOCITY_SCALAR;

        // Create launch velocity vector in opposite direction of drag
        Vector2 launchVelocity = dragDirection.nor().scl(-launchPower);

        calculateTrajectory(slingshotPos, launchVelocity);
    }

    public void dispose() {
        dotTexture.dispose();
    }
}
