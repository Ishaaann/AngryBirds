package com.ninjamoney.angrybirds.phy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TrajectoryPredictor {
    private static final float TIME_STEP = 0.25f; // Increased time step for more visible arc
    private static final int NUM_POINTS = 20; // Number of points to predict
    private static final float GRAVITY = -10f; // Adjusted gravity for game scale
    private static final float VELOCITY_SCALAR = 0.5f; // Scaling factor for velocity

    private Array<Vector2> trajectoryPoints;
    private Texture dotTexture;
    private Sprite dotSprite;

    public TrajectoryPredictor() {
        trajectoryPoints = new Array<Vector2>();
        dotTexture = new Texture("dot.png");
        dotSprite = new Sprite(dotTexture);
        dotSprite.setSize(5,5);
    }

    public void calculateTrajectory(Vector2 startPos, Vector2 velocity) {
        trajectoryPoints.clear();

        // Create temporary vectors to avoid modifying the input
        Vector2 currentPos = new Vector2(startPos);
        Vector2 currentVel = new Vector2(velocity);

        // Calculate trajectory points using verlet integration
        for (int i = 0; i < NUM_POINTS; i++) {
            trajectoryPoints.add(new Vector2(currentPos));

            // Update position and velocity for next point
            currentPos.x += currentVel.x * TIME_STEP;
            currentPos.y += currentVel.y * TIME_STEP;

            // Apply gravity to vertical velocity
            currentVel.y += GRAVITY * TIME_STEP;

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

            dotSprite.setSize(dotTexture.getWidth() * scale, dotTexture.getHeight() * scale);
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
