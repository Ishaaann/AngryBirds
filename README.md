# Angry Birds Clone - LibGDX & Box2D

## Project Overview
This project is a clone of the popular game **Angry Birds**, developed using **LibGDX** and **Box2D**, for our semester project in the course CSE201 - AP under Prof. Arun Balaji Buduru.
## Prerequisites
- **Java Development Kit (JDK)**: Version 11 or higher.
- **Gradle**: For dependency management.
- **LibGDX Framework**: Included in the project setup.
- **Box2D**: Included in the project setup.

## Project Setup

### Step 1: Clone the Repository
Run the following command in your terminal to clone the repository:

`git clone https://github.com/ricky16x/AngryBirds`  
`cd AngryBirds`

### Step 2: Build the Project
To build the project, run:

`./gradlew build`

### Step 3: Run the Project
To start the game:

`./gradlew run`


## Key Features
- **Bird Flinging Mechanism**: Mimics the slingshot functionality of Angry Birds. We apply a set impulse when the bird is released (Catapult.java) which flings the bird.
- **Physics Integration**: Uses **Box2D** World the physics in this game.
- **Win/Lose Conditions**: Displays screen overlays for game results and provides options to repeat the level, or play the next level.
- **Destructible Environments**: Structures made of different materials that react to bird impacts.
- **Score Tracking**: Keeps track of the player's score based on the destruction caused.
- **Pause and Resume**: Allows the game to be paused and resumed.
- **Sound Effects and Music**: Includes background music.
- **Level Selection**: Allows the player to select the level they want to play.
- **Level Progression**: Unlocks the next level only when the current level is completed.
- **Level Design**: Each level has a unique design and structure.
- **Game Over Screen**: Displays a game over screen when the player runs out of birds.
- **Game Win Screen**: Displays a win screen when the player completes the level.
- **Game Menu**: Provides options to start the game, view the credits, and exit the game.

## Game Flow

1. Main Menu
    - Display Background
    - Display Logo
    - Display Play Button
    - Display Sound Button
    - Display Credits Label

2. Play Button Clicked
    - Transition to Level Selector Screen

3. Level Selector Screen
    - Display Available Levels
    - Level Selected
        - Transition to Game Screen

4. Game Screen
    - Initialize Level
    - Display Birds
    - Display Pigs
    - Display Obstacles
    - Display Slingshot

5. Game Play
    - Player Drags Bird on Slingshot
    - Player Releases Bird
        - Bird Flies
        - Bird Collides with Objects
        - Update Score
        - Check Win/Lose Conditions

6. Win/Lose Conditions
    - All Pigs Destroyed
        - Display Win Screen
        - Option to Play Next Level
    - All Birds Used
        - Display Lose Screen
        - Option to Retry Level

7. Pause/Resume
    - Player Pauses Game
        - Display Pause Menu
        - Option to Resume or Exit

8. Exit
    - Return to Main Menu




## OOP Concepts Used
### Inheritance
- **Base Classes**: The game uses base classes like `GameObject` for common properties and methods, which are inherited by specific game objects like `Bird`, `Pig`, and `Obstacle`.

### Encapsulation
- **Private Fields and Public Methods**: The game uses private fields to store the state of objects and public methods to access and modify these fields, ensuring controlled access.

### Polymorphism
- **Method Overriding**: Different game objects override methods from their base classes to provide specific implementations, such as the `update` method in `Bird` and `Pig` classes.

### Abstraction
- **Abstract Classes and Interfaces**: The game uses abstract classes and interfaces to define common behaviors that must be implemented by concrete classes, such as the `Renderable` interface for objects that can be drawn on the screen.


## Online Sources
- [LibGDX Documentation](https://libgdx.com/documentation/)
- [Box2D Manual](https://box2d.org/documentation/)
- [LibGDX Wiki](https://github.com/libgdx/libgdx/wiki)
- [Box2D FAQ](https://box2d.org/faq/)
- [The java Documentation](https://docs.oracle.com/en/java/javase/22/docs/api/index.html)
- [The Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)

For Images, we have mainly referred to [Google](https://google.com) and [Sprites](https://www.spriters-resource.com/)
The music is taken from [Angry Birds Drip by Josh Vanzetti](https://www.youtube.com/watch?v=vkxPx0QsEbQ)

