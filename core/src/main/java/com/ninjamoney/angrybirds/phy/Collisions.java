
package com.ninjamoney.angrybirds.phy;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ninjamoney.angrybirds.elements.character.bird.Birds;
import com.ninjamoney.angrybirds.elements.character.pig.Pigs;
import com.ninjamoney.angrybirds.elements.struct.SolidObjects;
import com.ninjamoney.angrybirds.elements.struct.Wood;
import com.ninjamoney.angrybirds.levels.Level1;

import static com.ninjamoney.angrybirds.levels.Level1.onWoodHealthZero;
import static com.ninjamoney.angrybirds.levels.Level1.queueBodyForDestruction;

public class Collisions implements ContactListener {
    public static float score = 0;
    private PigHealthListener pigHealthListener;
    Stage stage;


    public Collisions(PigHealthListener pigHealthListener) {
        this.pigHealthListener = pigHealthListener;
        stage = new Stage();
    }

    @Override
    public void beginContact(Contact contact) {
        Body body1 = contact.getFixtureA().getBody();
        Body body2 = contact.getFixtureB().getBody();

        if (body1.getUserData() != null && body2.getUserData() != null) {
            if (body1.getUserData() instanceof Birds && body2.getUserData() instanceof Pigs) {
                handleBirdPigCollision((Birds) body1.getUserData(), (Pigs) body2.getUserData());
            } else if (body1.getUserData() instanceof Pigs && body2.getUserData() instanceof Birds) {
                handleBirdPigCollision((Birds) body2.getUserData(), (Pigs) body1.getUserData());
            } else if (body1.getUserData() instanceof Pigs && body2.getUserData() instanceof Wood) {
                handlePigWoodCollision((Pigs) body1.getUserData(), (Wood) body2.getUserData());
            } else if (body1.getUserData() instanceof Wood && body2.getUserData() instanceof Pigs) {
                handlePigWoodCollision((Pigs) body2.getUserData(), (Wood) body1.getUserData());
            } else if (body1.getUserData() instanceof Pigs && body2.getUserData() instanceof Body) {
                handlePigGroundCollision((Pigs) body1.getUserData(), (Body) body2.getUserData());
            } else if (body1.getUserData() instanceof Body && body2.getUserData() instanceof Pigs) {
                handlePigGroundCollision((Pigs) body2.getUserData(), (Body) body1.getUserData());
            } else if (body1.getUserData() instanceof SolidObjects) {
                handleSolidObjectCollision((SolidObjects) body1.getUserData(), body2);
            } else if (body2.getUserData() instanceof SolidObjects) {
                handleSolidObjectCollision((SolidObjects) body2.getUserData(), body1);
            }
        }
    }

    private void handleSolidObjectCollision(SolidObjects solidObject, Body otherBody) {
        float velocity = otherBody.getLinearVelocity().len();
        float damage = velocity * 0.05f; // Example damage calculation
        solidObject.setHp(solidObject.getHp() - damage);
        checkSolidObjectHealth(solidObject);
    }

    private void checkSolidObjectHealth(SolidObjects solidObject) {
        if (solidObject.getHp() <= 0) {
           queueBodyForDestruction(solidObject.getBody());
        }
    }

    private void handleWoodHP(Body ground, Wood wood) {
        float verticalVelocity = Math.abs(wood.getBody().getLinearVelocity().y);
        float damage = verticalVelocity * 0.05f; // Example damage calculation
        wood.setHp(wood.getHp() - damage);
        checkWoodHealth(wood);
    }

    private void checkWoodHealth(Wood wood) {
        if(wood.getHp() <= 0) {
            onWoodHealthZero(wood);
        }
    }

    private void handleBirdPigCollision(Birds bird, Pigs pig) {
        float velocity = bird.getBirdBody().getLinearVelocity().len();
        float damage = velocity * bird.getDamage() / 80;
        pig.setHealth(pig.getHealth() - damage);
        checkPigHealth(pig);
        score += damage;
    }

    private void handlePigWoodCollision(Pigs pig, Wood wood) {
        float velocity = pig.getPigBody().getLinearVelocity().len();
        float verticalVelocity = Math.abs(pig.getPigBody().getLinearVelocity().y);
        float damage = velocity * 0.05f + verticalVelocity * 0.05f; // Example damage calculation
        pig.setHealth(pig.getHealth() - damage);
        checkPigHealth(pig);
        score += damage;
    }

    private void handlePigGroundCollision(Pigs pig, Body ground) {
        float verticalVelocity = Math.abs(pig.getPigBody().getLinearVelocity().y);
        float damage = verticalVelocity * 0.05f; // Example damage calculation
        pig.setHealth(pig.getHealth() - damage);
        checkPigHealth(pig);
        score += damage;
    }

    private void checkPigHealth(Pigs pig) {
        Rectangle bounds = new Rectangle();
        bounds.set(0, 0, stage.getWidth(), stage.getHeight());
        if (pig.getHealth() <= 0 || !bounds.contains(pig.getPigBody().getPosition().x, pig.getPigBody().getPosition().y)) {
            pigHealthListener.onPigHealthZero(pig);
        }
    }

    @Override
    public void endContact(Contact contact) {
        // Handle end of contact if needed
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        // Handle pre-solve if needed
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
        // Handle post-solve if needed
    }
}
