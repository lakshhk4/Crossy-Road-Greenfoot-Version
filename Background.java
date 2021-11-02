
import greenfoot.*;

/**
 * Objects of this class have the image of the game highway. They will be used
 * for the infinite scrolling background Two objects of this class will be
 * created. When one image is put on top of the other, it creates an effect of
 * seamless transition. Thus, I require only two images to create the infinite
 * scrolling background
 */
public class Background extends Actor {

    public void act() {
        // always scroll the image.
        scroll();
    }

    public void scroll() {
        // moves the images down, so the player cannot just stay in one position. He has to keep moving.
        setLocation(getX(), getY() + MyWorld.scrollRate);

        // If they image is out of the viewing field, place it on top of the image it is currently on top of.
        // This allows for the infinite scrolling background
        if (getY() > getImage().getHeight() + (getImage().getHeight() / 2)) {
            setLocation(getX(), getY() - getWorld().getHeight() * 2);
        }

    }

}
