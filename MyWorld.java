import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class MyWorld extends World {

    // Scroll rate of the game
    public static int scrollRate;

    // Spawn timer
    private int spawnTimer = 1;

    // Spawn rate
    public int spawnRateCar1 = 150;
    public int spawnRateCar2 = 175;
    public int spawnRateCar3 = 200;

    // creatng background images
    Background back1 = new Background();
    Background back2 = new Background();

    // creating PLayer
    Player player = new Player();

    // tag for car spawning
    Tag tagCar1 = new Tag();
    Tag tagCar3 = new Tag();
    Tag tagCar2 = new Tag();

    // tags for plank spawning
    Tag tagLog1 = new Tag();
    Tag tagLog2 = new Tag();

    // tag for water beginning
    Tag tagWaterBase = new Tag();

    // score 
    Score score = new Score(player);
    // variable to see whether the game has ended.
    public boolean gameEnded = false;

    // Greenfoot Sounds
    GreenfootSound carHonk = new GreenfootSound("CarHonk.wav");
    GreenfootSound carMoving = new GreenfootSound("CarMoving.wav");

    // to store the coordinates of the river
    ArrayList<Double> yCoordinatesBottom = new ArrayList();

    // spawn car speeds
    public MyWorld() {
        super(808, 450, 1, false); // 808x446

        scrollRate = 1;
        // Player isn't touching logs during begining

        addObject(back1, getWidth() / 2, getHeight() / 2);
        addObject(back2, getWidth() / 2, -1 * getHeight() / 2);
        addObject(player, 30, 415);  // 415

        addObject(tagCar1, 30, 353);
        addObject(tagCar3, 30, 203);
        addObject(tagCar2, 789, 505); // 725. 444

        // add plank tags
        addObject(tagLog1, 30, 115);
        addObject(tagLog2, 789, 745);// 745

        // add waterbase tag
        addObject(tagWaterBase, 0, 150);

        // add Score
        addObject(score, 30, 30);

        // to make the Player appear on top of the rest of the Actors
        setPaintOrder(Score.class, Player.class);
        
        // storing the coordinates of the river
        for (int i = 0; i < 809; i++) { 
            yCoordinatesBottom.add(0.3168316832 * i);
        }
    }

    public void act() {

        // running spawner
        runSpawnTimer();
        
        // playing soundeffects
        if (carMoving.isPlaying() == false) {
            carMoving.play();
        }
        
        // changing scroll rate of background based on player's location on the screen so that the player cannot leave the viewing field
        if (player.getY() < 100) {
            scrollRate = 5;
        } else if (player.getY() < 200) {
            scrollRate = 4;
        } else if (player.getY() < 300) {
            scrollRate = 3;
        } else {
            scrollRate = 1;
        }

        // Tests to check if player is in water and is not on the log.
        if (player.getY() < tagWaterBase.getY() + yCoordinatesBottom.get(player.getX()) && player.getY() > (tagWaterBase.getY() + yCoordinatesBottom.get(player.getX())) - 150 && player.playerTouchingLog != true) {
            gameEnded = true; // if in water, and not touching log, game over
        }

        
        // game over
        if (gameEnded) {

            removeObject(player);
            addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
            carMoving.stop();
            Greenfoot.stop();

        }

    }

    private void runSpawnTimer() {

        // Spawning logs for log row1
        if (spawnTimer % 80 == 0) { // the closer to 1, the faster the actors will spawn
            Log log1 = new Log(true);
            //addObject(log1, tagLog1.getX(), tagLog1.getY());
            addObject(log1, tagCar3.getX(), tagCar3.getY() - 75);
        }
        // Spawning logs for log row2

        if (spawnTimer % 90 == 0) {
            Log log2 = new Log(false);
            addObject(log2, tagLog2.getX(), tagLog2.getY());
        }

        // Spawning cars for car row1
        if (spawnTimer % spawnRateCar1 == 0) {
            Car actualCar1 = new Car(20);
            addObject(actualCar1, tagCar1.getX(), tagCar1.getY());

        }

        //// Spawning cars for car row2
        if (spawnTimer % spawnRateCar2 == 0) {
            Car actualCar2 = new Car(195);
            addObject(actualCar2, tagCar2.getX(), tagCar2.getY());
        }

        // Spawning cars for car row3
        if (spawnTimer % spawnRateCar3 == 0) {
            Car actualCar3 = new Car(20);
            addObject(actualCar3, tagCar3.getX(), tagCar3.getY());

            // playing car honk music
            if (carHonk.isPlaying() == false) {
                carHonk.play();
            }
        }

        spawnTimer++;

    }

}
