## Pac-Man 

- Pac-Man is a maze arcade game developed and released by Namco in 1980. 
 This is an implementation of the game.(Work in progress)

## SEM Group 29

- Teaching Assistant : Noah

## Group Members
 
* Swastik Agarwal. Platform: Windows 10, using Intellij.
* Akif Yüksel. Platform: Windows 10, using Intellij.
* Shubhankar Darbari. Platform: Windows 10, using Intellij.
* Jason Bloom. Platform: MacOS, using Intellij.
* Lesley Franschman. Platform: Windows 10, using Intellij.

## Getting Started

### IntelliJ
1. Git clone the project
2. Open IntelliJ and create new project
3. Select 'Gradle' in the following screen as external model
4. To see pac-man in action: `run java/StartScreen`

## Prototype (6/12/2019)

### Game elements:
- Authentication:
    - Login Page: Authenticate a user.
    - Register Page: Add a new user.
- Start of the game:
    - **MapParser**: Initialize map(any text file with special characters) with player, pellets and ghost.

### Game features:
- Pac-Man can move **UP, DOWN, LEFT** and **RIGHT** using **WASD** and **arrow keys**
- **Randy**, the ghost can move in any random directions
- Unit Collisions:
    - **Player, pellet**: Pellet gets eaten by the player
    - **Player, wall**: Player can not move through walls
    - **Ghost, wall**: Ghost can not move through walls
    - **Ghost, pellet**: Nothing happens
- Game winning conditions:
    - Game **ends** and displays a popup messgae when all the pellets on the board are eaten by pac-man.
- Extras:
    - **Wrap-around**: Allows pacman and the ghost to get to the other side of the map.

## Testing (Final Iteration)

### Test coverage
- We focused on the branch coverage for testing the game and got a **76%** (Approx) branch coverage.
- We also performed some user testing to get better design choices for the game.
- GUI Testing was performed as one of the system testing.
 
