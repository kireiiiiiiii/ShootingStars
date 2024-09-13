![project banner](./project.resources/banner.png)

# ⭐️ Shooting Stars ⭐️

### Specifications

This game was build using Java.

## Goal of the game

The goal of the game is to shoot as many stars from the sky as posible, in a given time limit. Each successfuly shot star will earn you 10 points. Try to achieve the highest score possible, before the time runs out. For every miss (click outside the circle) 10 points will be subtracted from your score. However the your score is floored to 10 points, so you can't go negative.☺️

## How to set up

1. **Download and Setup**: Ensure you have Java installed on your system. Download the game files and extract them to your desired location or clone the repository.
2. **Run the Game**: Open the folder in a compiler like VS Code. There run the AppMain.java file.
3. **Packaging**: For packaging, use the <code>gradle shadowjar</code> command.
   _NOTE_: the <code>gradle jar</code> command doesn't work, because it for some reason doesn't include my Maven plugins
4. **Distribution**: For packaging into a distribution version (<code>.zip</code>) use <code>gradle asseble</code>.

## Controls

-   **S Key**: Start the game
-   **Left Mouse Click**: Click on targets
-   **R Key**: Restart the game
-   **ESC Key:**: Pause the game

## Requirements

-   Java Development Kit (JDK) installed on your system

## Future goals/tasks

-   Better target position generation (no overlaying with the score board, or on the edge of the frame)
-   Random target rotation
-   Better target hitbox (include the tips of the star, not just the center)
-   SFX and music
-   Add an exit button to the main screen

## Contact Information:

-   If you have any questions, feedback, or issues with the game, please contact me on my instagram [@\_kireiiiiiiii](https://www.instagram.com/_kireiiiiiiii)

## Known Issues/Bugs:

-   The star hitbox is a circle, and is smaller than the star image.
