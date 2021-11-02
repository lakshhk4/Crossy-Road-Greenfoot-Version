
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Objects of this class are the logs in the water that the player has to jump
 * on.
 */
public class Log extends Actor {

    // variable used as an identifier, as first row logs and second row logs move differently.
    boolean firstRow;

    public Log(boolean firstRow) {
        this.firstRow = firstRow;
        setRotation(18);

        // Setting the length of the log at random, so that each log has different lengths
        getImage().scale(getImage().getWidth() - (800 + Greenfoot.getRandomNumber(100)), getImage().getHeight() - 230);

    }

    public void act() {

        if (firstRow) {
            // moving left to right as its a first row log
            setLocation(getX() + 3, getY() + MyWorld.scrollRate + 1);
        } else {
            // moving right to left as its a second row log

            // travels faster than log from first row.
            setLocation(getX() - 6, getY() + MyWorld.scrollRate - 2);
        }

    }

}
