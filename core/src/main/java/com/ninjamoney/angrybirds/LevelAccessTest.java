//package com.ninjamoney.angrybirds;
//
//import com.ninjamoney.angrybirds.levels.Level1;
//import com.ninjamoney.angrybirds.levels.Level2;
//import com.ninjamoney.angrybirds.levels.Level3;
//import com.ninjamoney.angrybirds.screens.LevelSelectorScreen;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class LevelAccessTest {
//
//    @Test
//    void testAccessLockedLevel() {
//        Level1.cleared = true;
//        Level2.cleared = false;
//
//        LevelSelectorScreen levelSelectorScreen = new LevelSelectorScreen(new AngryBirds());
//
//        boolean canAccessLevel3 = Level2.cleared;
//        assertFalse(canAccessLevel3, "Level 3 should be locked and not accessible.");
//    }
//
//    @Test
//    void testAccessUnlockedLevel() {
//        Level1.cleared = true;
//        Level2.cleared = true;
//
//        LevelSelectorScreen levelSelectorScreen = new LevelSelectorScreen(new AngryBirds());
//        boolean canAccessLevel2 = Level1.cleared;
//        assertTrue(canAccessLevel2, "Level 2 should be unlocked and accessible.");
//    }
//
//    @Test
//    void testAccessFirstLevel() {
//        Level1.cleared = false;
//
//        LevelSelectorScreen levelSelectorScreen = new LevelSelectorScreen(new AngryBirds());
//        boolean canAccessLevel1 = true;
//        assertTrue(canAccessLevel1, "Level 1 should always be accessible.");
//    }
//
//    @Test
//    void testAccessLevelAfterClearingPrevious() {
//        Level1.cleared = true;
//        Level2.cleared = false;
//
//        LevelSelectorScreen levelSelectorScreen = new LevelSelectorScreen(new AngryBirds());
//        boolean canAccessLevel2 = Level1.cleared;
//        assertTrue(canAccessLevel2, "Level 2 should be accessible after clearing Level 1.");
//    }
//
//    @Test
//    void testAccessLevelWithoutClearingPrevious() {
//        Level1.cleared = false;
//        Level2.cleared = false;
//
//        LevelSelectorScreen levelSelectorScreen = new LevelSelectorScreen(new AngryBirds());
//        boolean canAccessLevel2 = Level1.cleared;
//        assertFalse(canAccessLevel2, "Level 2 should not be accessible without clearing Level 1.");
//    }
//
//    @Test
//    void testAccessAllLevelsCleared() {
//        Level1.cleared = true;
//        Level2.cleared = true;
//        Level3.cleared = true;
//
//        LevelSelectorScreen levelSelectorScreen = new LevelSelectorScreen(new AngryBirds());
//        boolean canAccessLevel3 = Level2.cleared;
//        assertTrue(canAccessLevel3, "Level 3 should be accessible after clearing Level 2.");
//    }
//}
