
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Only one object of this class will be created. It is used to track and
 * display the player's score.
 *
 */
public class Score extends Actor {

    // will take the 'player' as an instance variable, so it can view its score.
    Player player = new Player();

    public Score(Player player) {
        this.player = player;

        // Creates an image of the score.
        GreenfootImage score = new GreenfootImage(String.valueOf(player.getScore()), 40, Color.RED, Color.WHITE, Color.BLACK);
        //Sets the image of the score.
        setImage(score);
    }

    public void act() {
        if (getWorldOfType(MyWorld.class).gameEnded != true) {
            // Keeps updating the score.
            GreenfootImage score = new GreenfootImage(String.valueOf(player.getScore()), 40, Color.RED, Color.WHITE, Color.BLACK);
            setImage(score);
        } else {
            // if game is ended, the score stops adding, and is displayed at a different location.
            setLocation(606, 250);
        }

    }
}
