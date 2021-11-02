
import greenfoot.*;

/**
 * Objects of this class are the cars that the player has to avoid.
 */
public class Car extends Actor {

    // The different speeds at which the car will travel. Chosen at random.
    int speedType;

    public Car(int rotation) {

        // Cars spawning from lane 2 move right to left, and thus have a different rotation,than cars that move from
        // left to right (Lane 1 and Lane 3 cars)
        setRotation(rotation);

        // Giving the car a random number to determine its color.
        int whichSkin = Greenfoot.getRandomNumber(3);

        // Assigning the color based on number generated above.
        if (whichSkin == 0) {
            setImage("car01.png");
        } else if (whichSkin == 1) {
            setImage("car02.png");
        } else {
            setImage("car03.png");
        }

        // The cars can have two different speeds, and the code below assings it a type (faster or slower). 
        speedType = Greenfoot.getRandomNumber(2);
    }

    public void act() {

        // Making the car move
        if (getRotation() == 20) { // getRotation() to identify which car it is. left or right moving 
            // This is a car from either lane 1 or lane 3 (moving left to right)

            // Identifying speed type
            if (speedType == 0) {
                setLocation(getX() + 6, getY() + MyWorld.scrollRate + 2); // as car travels diagonally, it moves slightly faster than the background
            } else {
                setLocation(getX() + 3, getY() + MyWorld.scrollRate + 1); // scroll faster than background
            }

        } else {
            // This is a car from lane 2 (moving right to left)

            // Identifying speed type
            if (speedType == 0) {
                setLocation(getX() - 6, getY() + MyWorld.scrollRate - 2); // scroll faster than background
            } else {
                setLocation(getX() - 3, getY() + MyWorld.scrollRate - 1); // scroll faster than background
            }

        }

    }
}
