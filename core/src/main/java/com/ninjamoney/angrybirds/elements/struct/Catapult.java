package com.ninjamoney.angrybirds.elements.struct;////package com.ninjamoney.angrybirds.elements.struct;
////
////import com.badlogic.gdx.Gdx;
////import com.badlogic.gdx.graphics.Texture;
////
////public final class Catapult {
////    private float x; // x coordinate of the catapult
////    private float y; // y coordinate of the catapult
////    private Texture catapultTexture = new Texture("elements/struct/catapult.png");
////
////    public Catapult(float x, float y) {
////        this.x = x;
////        this.y = y;
////    }
////
////    public void drag() {
////        // Drag the catapult
////        float fx = Gdx.input.getX();
////        float fy = Gdx.input.getY();
////        if(fx >= x && fx <= x + catapultTexture.getWidth() && fy >= y && fy <= y + catapultTexture.getHeight()) {
////            // Drag the catapult
////        }
////    }
////
////    public void release() {
////        // Release the catapult
////    }
////
////    public Texture getCatapultTexture() {
////        return catapultTexture;
////    }
////}
//
//
//package com.ninjamoney.angrybirds.elements.struct;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
////import com.ninjamoney.angrybirds.elements.character.bird.Bird;
//import com.ninjamoney.angrybirds.elements.character.bird.Birds;
//
//public final class Catapult {
//    private float x; // X-coordinate of the catapult
//    private float y; // Y-coordinate of the catapult
//    private Texture catapultTexture = new Texture("elements/struct/catapult.png");
//    private Vector2 anchor; // Anchor point of the slingshot
//    private Birds currentBird; // Reference to the bird being manipulated
//    private boolean isPulling = false; // Flag for pull state
//    private float maxStretch = 150; // Maximum distance the bird can be stretched
//
//    public Catapult(float x, float y) {
//        this.x = x;
//        this.y = y;
//        anchor = new Vector2(x, y); // Set the anchor point at the base of the slingshot
//    }
//
//    // In Catapult.java
//    // In Catapult.java
//    public Texture getCatapultTexture() {
//        return catapultTexture;
//    }
//
//    private boolean isMouseOverCatapult() {
//        float mouseX = Gdx.input.getX();
//        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis
//        return mouseX >= x && mouseX <= x + catapultTexture.getWidth()
//            && mouseY >= y && mouseY <= y + catapultTexture.getHeight();
//    }
//
//    public void pull(Birds bird) {
//        if (isMouseOverCatapult() && !isPulling) {
//            this.currentBird = bird;
//            this.isPulling = true;
//            currentBird.getBirdBody().setGravityScale(0); // Disable gravity while pulling
//            currentBird.getBirdBody().setLinearVelocity(0, 0); // Stop all movement
//        }
//    }
//// In Catapult.java
//
//    public void updatePull() {
//        if (isPulling && currentBird != null) {
//            float mouseX = Gdx.input.getX();
//            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis
//
//            Vector2 mousePosition = new Vector2(mouseX, mouseY);
//            Vector2 stretchVector = mousePosition.sub(anchor);
//
//            // Get the bird's radius
//            float birdRadius = currentBird.getBirdBody().getFixtureList().get(0).getShape().getRadius();
//
//            // Limit the stretch distance to the bird's radius
//            if (stretchVector.len() > birdRadius) {
//                stretchVector.nor().scl(birdRadius);
//            }
//
//            // Update bird's position
//            currentBird.getBirdBody().setTransform(anchor.cpy().add(stretchVector), 0);
//        }
//    }
//
////    public void updatePull() {
////        if (isPulling && currentBird != null) {
////            float mouseX = Gdx.input.getX();
////            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis
////
////            Vector2 mousePosition = new Vector2(mouseX, mouseY);
////            Vector2 stretchVector = mousePosition.sub(anchor);
////
////            // Limit the stretch distance to maxStretch
////            if (stretchVector.len() > maxStretch) {
////                stretchVector.nor().scl(maxStretch);
////            }
////
////            // Update bird's position
////            currentBird.getBirdBody().setTransform(anchor.cpy().add(stretchVector), 0);
////        }
////    }
//
//    public void release() {
//        if (isPulling && currentBird != null) {
//            isPulling = false;
//
//            // Calculate launch vector
//            Vector2 birdPosition = currentBird.getBirdBody().getPosition();
//            Vector2 launchVector = anchor.cpy().sub(birdPosition); // Direction from bird to anchor
//            float launchPower = launchVector.len() * 10; // Scale the power by stretch distance
//
//            // Apply force to the bird
//            currentBird.getBirdBody().setLinearVelocity(launchVector.nor().scl(launchPower));
//
//            // Re-enable gravity
//            currentBird.getBirdBody().setGravityScale(1);
//
//            // Clear the current bird
//            currentBird = null;
//        }
//    }
//}
////
////    /**
////     * Starts pulling the bird.
////     */
////    public void pull(Birds bird) {
////        if (isMouseOverCatapult() && !isPulling) {
////            this.currentBird = bird;
////            this.isPulling = true;
////            currentBird.getBirdBody().setGravityScale(0); // Disable gravity while pulling
////            currentBird.getBirdBody().setLinearVelocity(0, 0); // Stop all movement
////        }
////    }
//
//    /**
//     * Updates the position of the bird as it is being pulled.
//     */
////    public void updatePull() {
////        if (isPulling && currentBird != null) {
////            float mouseX = Gdx.input.getX();
////            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis
////
////            Vector2 mousePosition = new Vector2(mouseX, mouseY);
////            Vector2 stretchVector = mousePosition.sub(anchor);
////
////            // Limit the stretch distance to maxStretch
////            if (stretchVector.len() > maxStretch) {
////                stretchVector.nor().scl(maxStretch);
////            }
////
////            // Update bird's position
////            currentBird.getBirdBody().setTransform(anchor.cpy().add(stretchVector), 0);
////        }
////    }
////
////    /**
////     * Releases the bird, applying a force to launch it.
////     */
////    public void release() {
////        if (isPulling && currentBird != null) {
////            isPulling = false;
////
////            // Calculate launch vector
////            Vector2 birdPosition = currentBird.getBirdBody().getPosition();
////            Vector2 launchVector = anchor.cpy().sub(birdPosition); // Direction from bird to anchor
////            float launchPower = launchVector.len() * 10; // Scale the power by stretch distance
////
////            // Apply force to the bird
////            currentBird.getBirdBody().setLinearVelocity(launchVector.nor().scl(launchPower));
////
////            // Re-enable gravity
////            currentBird.getBirdBody().setGravityScale(1);
////
////            // Clear the current bird
////            currentBird = null;
////        }
////    }
////
////    /**
////     * Check if the mouse is over the catapult area.
////     */
////    private boolean isMouseOverCatapult() {
////        float mouseX = Gdx.input.getX();
////        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis
////        return mouseX >= x && mouseX <= x + catapultTexture.getWidth()
////            && mouseY >= y && mouseY <= y + catapultTexture.getHeight();
////    }
////
////    public Texture getCatapultTexture() {
////        return catapultTexture;
////    }
////
////    public Vector2 getAnchor() {
////        return anchor;
////    }
////
////    public boolean isPulling() {
////        return isPulling;
////    }
////}

// In Catapult.java




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;

public final class Catapult {
    private float x; // X-coordinate of the catapult
    private float y; // Y-coordinate of the catapult
    private Texture catapultTexture = new Texture("elements/struct/catapult.png");
    private Vector2 anchor; // Anchor point of the slingshot
    private Birds currentBird; // Reference to the bird being manipulated
    private boolean isPulling = false; // Flag for pull state

    public Catapult(float x, float y) {
        this.x = x;
        this.y = y;
        anchor = new Vector2(x, y); // Set the anchor point at the base of the slingshot
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
        if (isMouseOverCatapult() && !isPulling && bird.getBirdBody() != null) {
            this.currentBird = bird;
            this.isPulling = true;
            currentBird.getBirdBody().setGravityScale(0); // Disable gravity while pulling
            currentBird.getBirdBody().setLinearVelocity(0, 0); // Stop all movement
        }
    }

    public void updatePull() {
        if (isPulling && currentBird != null) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y-axis

            Vector2 mousePosition = new Vector2(mouseX, mouseY);
            Vector2 stretchVector = mousePosition.sub(anchor);

            // Limit the stretch distance to a maximum value
            float maxStretch = 150; // Example maximum stretch distance
            if (stretchVector.len() > maxStretch) {
                stretchVector.nor().scl(maxStretch);
            }

            // Update bird's position relative to the anchor point
            currentBird.getBirdBody().setTransform(anchor.cpy().add(stretchVector), 0);
        }
    }

    public void release() {
        if (isPulling && currentBird != null) {
            isPulling = false;

            // Calculate launch vector
            Vector2 birdPosition = currentBird.getBirdBody().getPosition();
            Vector2 launchVector = anchor.cpy().sub(birdPosition); // Direction from bird to anchor
            float launchPower = launchVector.len() * 20; // Scale the power by stretch distance

            // Apply force to the bird
            currentBird.getBirdBody().applyLinearImpulse(launchVector.nor().scl(launchPower), birdPosition, true);

            // Re-enable gravity
            currentBird.getBirdBody().setGravityScale(1);

            // Clear the current bird
            currentBird = null;
        }
    }

    public Texture getCatapultTexture() {
        return catapultTexture;
    }
}
