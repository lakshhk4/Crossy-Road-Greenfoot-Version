
import greenfoot.*;

/**
 * The tag is a class whose object's are invisible. The purpose of Tag Objects
 * is to note down key positions that change as the game progresses. These
 * positions the Tag notes, are then used as reference points or spawning
 * locations for other actors.
 *
 * Tag objects are given a starting position (for instance: on the first car
 * lane). As the background moves, so does the Tag object, and therefore, it
 * always stays on the first car lane, and thus, its coordinates can be used as
 * the spawn point of cars that spawn from the the first lane.
 */
public class Tag extends Actor {

    public Tag() {
        // removes the 'Greenfoot foot' image, making the tag invicible
        setImage((GreenfootImage) null);
    }

    public void act() {
        // moves the tag to its next location once it crosses the viewing field.
        if (getY() >= 450) {
            setLocation(getX(), getY() - 450);
        }

        scroll(); // so the tags scroll with the background

    }

    public void scroll() {
        // tag scrolls at the same rate as the background.
        setLocation(getX(), getY() + MyWorld.scrollRate);
    }
}
