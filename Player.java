
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Only one object of this class will be created. This is the player object. It
 * has the ability to move all around the screen. (left, right, up, down)
 */
public class Player extends Actor {

    // these variables are used to ensure the player cannot spam click. 
    // When the player hold any of these buttons down, he only moves one space, not multiple.
    boolean upPressed = false;
    boolean downPressed = false;
    boolean rightPressed = false;
    boolean leftPressed = false;

    // variable to check if the player is touching a log. Need this to change the player's movements, as when he's on the log he moves with it.
    boolean playerTouchingLog = false;

    //Sound Effects
    GreenfootSound playerMoving = new GreenfootSound("PlayerMoving.wav");
    GreenfootSound onLog = new GreenfootSound("LogCreaking.wav");

    // variable to ensure that the 'onLog' doesn't play more than once if they player is still on the same log
    int soundTimesPlayed = 0;

    // tracks the player's score
    int score = 0;

    public Player() {
        setRotation(270);
    }

    // returns score. defined public so the 'Score' class can access the score, and then display it. 
    public int getScore() {
        return score;
    }

    public void act() {
        // scrolls player with the background
        setLocation(getX(), getY() + MyWorld.scrollRate);

        // will allow player to moves forward
        if (Greenfoot.isKeyDown("w")) {

            if (upPressed == false) {

                // moves 'forward', but in  reality it moves slightly diagonally because the player's viewpoint is slighlty tilted, not a birds eye view
                setLocation(getX() + 30, getY() - 75 + 10);

                // every time the player moves forward successfully, the score increments by 1.
                score++;

                // increases the spawn rate of cars as the score increases, making the game harder as it progresses
                getWorldOfType(MyWorld.class).spawnRateCar1 = getWorldOfType(MyWorld.class).spawnRateCar1 - 1;
                getWorldOfType(MyWorld.class).spawnRateCar2 = getWorldOfType(MyWorld.class).spawnRateCar2 - 1;
                getWorldOfType(MyWorld.class).spawnRateCar3 = getWorldOfType(MyWorld.class).spawnRateCar3 - 1;

                // to make the creakingSound play only once when on the same log
                soundTimesPlayed = 0;

                // plays the player moving music if it isn't played already. The if statement removes sound distortion because otherwise it would play
                // the same sound over itself.
                if (playerMoving.isPlaying() == false) {
                    playerMoving.play();
                }
            }

            upPressed = true;
        } else {
            // player has released the key, let him move again.
            upPressed = false;

        }

        //  will allow player to move right
        if (Greenfoot.isKeyDown("s")) {
            if (downPressed == false) {
                // moves back 
                setLocation(getX() - 30, getY() + 75 - 10);

                // plays the player moving music if it isn't played already. The if statement removes sound distortion because otherwise it would play
                // the same sound over itself.
                if (playerMoving.isPlaying() == false) {
                    playerMoving.play();
                }
            }

            downPressed = true;
        } else {
            // player has released the key, let him move again.
            downPressed = false;

        }

        //  will allow player to moves backward
        if (Greenfoot.isKeyDown("d")) {
            if (rightPressed == false) {
                // move right
                setLocation(getX() + 75, getY() + 25);

                // plays the player moving music if it isn't played already. The if statement removes sound distortion because otherwise it would play
                // the same sound over itself.
                if (playerMoving.isPlaying() == false) {
                    playerMoving.play();
                }
            }

            rightPressed = true;
        } else {
            // player has released the key, let him move again.
            rightPressed = false;

        }

        // will allow player to move left
        if (Greenfoot.isKeyDown("a")) {
            if (leftPressed == false) {
                // move left
                setLocation(getX() - 75, getY() - 25);

                // plays the player moving music if it isn't played already. The if statement removes sound distortion because otherwise it would play
                // the same sound over itself.
                if (playerMoving.isPlaying() == false) {
                    playerMoving.play();
                }
            }

            leftPressed = true;
        } else {
            // player has released the key, let him move again.
            leftPressed = false;

        }

        // move player when he gets onto log
        if (isTouching(Log.class)) {

            playerTouchingLog = true;
            // wont be scrolling
            Log logPlayerOn = (Log) getOneIntersectingObject(Log.class); // downcast Actor to Log

            //Player follows the Log's movement
            setLocation(logPlayerOn.getX(), logPlayerOn.getY());

            if (soundTimesPlayed == 0) {
                onLog.play();
                soundTimesPlayed++;
            }

        } else {
            playerTouchingLog = false;

        }

        // player has been hit by car. Game Over
        if (isTouching(Car.class)) {
            getWorldOfType(MyWorld.class).gameEnded = true;
        }

        // if out of the viewing screen. Game over
        if (getX() > getWorld().getWidth() || getX() < 0 || getY() < 0 || getY() > getWorld().getHeight()) {
            getWorldOfType(MyWorld.class).gameEnded = true;
        }

    }
}
