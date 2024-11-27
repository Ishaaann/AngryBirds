//package com.ninjamoney.angrybirds;
//
//import org.junit.jupiter.api.Test;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//import com.ninjamoney.angrybirds.elements.character.bird.Birds;
//import com.ninjamoney.angrybirds.elements.character.bird.Red;
//import com.ninjamoney.angrybirds.elements.character.pig.Pigs;
//import com.ninjamoney.angrybirds.elements.character.pig.SmallPig;
//import com.ninjamoney.angrybirds.elements.struct.Wood;
//import com.ninjamoney.angrybirds.levels.Level1;
//import org.junit.jupiter.api.BeforeEach;
//import static org.junit.jupiter.api.Assertions.*;
//
//class HealthReductionTest {
//
//    private Level1 level;
//    private Birds red;
//    private Pigs pig;
//
//    @BeforeEach
//    void setUp() {
//        level = new Level1(new AngryBirds(), 1, false);
//        red = new Red();
//        pig = new SmallPig();
//    }
//
//
//    @Test
//    void testPigHealthReductionOnContact() {
//        float initialHealth = pig.HEALTH;
//        pig.setHealth(pig.getHealth() - 10); // Simulate contact
//        assertTrue(pig.getHealth() < initialHealth, "Pig health should reduce on contact.");
//    }
//
//    @Test
//    void testBlockHPReductionOnContact() {
//        World world = new World(new Vector2( -9.8f, 0), true);
//        Wood wood = new Wood(world, "plank", 0, 0, 0,0);
//        float iHP = wood.getHp();
//        wood.setHp(iHP-5);
//        assertTrue(wood.getHp() < iHP, "Block health should reduce on contact.");
//    }
//}
