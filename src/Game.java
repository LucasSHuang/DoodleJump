// Doodle Jump by Lucas Huang
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {

    // Instance Variables and Constants
    private final int DELAY_IN_MILLISECONDS = 7;
    private final int PLAYER_SPEED = 4;
    private final int SCROLL_CAP = 400;
    private final int NUM_PLATFORMS = 13;
    private final int BUG_FACTOR = 150;
    private final int MONSTER_Y = -4000;
    private GameViewer window;
    private ArrayList<Platform> platforms;
    private Player player;
    private Monster monster;
    private int score;
    private int state;
    private Timer clock;

    // Constructor
    public Game() {
        state = 0;
        score = 0;
        platforms = new ArrayList<Platform>();
        window = new GameViewer(this);
        // Gets platforms into the arraylist
        generatePlatforms();
        monster = new Monster(generateX(), MONSTER_Y, window);
        player = new Player(window);
        window.addKeyListener(this);
    }

    // Getters
    public int getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public Player getPlayer() {
        return player;
    }

    public Monster getMonster() {
        return monster;
    }

    // Randomly generate an x coordinate on the screen
    public int generateX() {
        return (int) (Math.random() * 500);
    }

    // Get the y coordinate of the highest platform
    public int getMinY() {
        int minY = GameViewer.WINDOW_HEIGHT;
        for (Platform p: platforms) {
            if (p.getY() < minY) {
                minY = p.getY();
            }
        }
        return minY;
    }

    // Generate a random y value at least 50 pixels away from the highest point
    public int generateY(int minY) {
        return minY - ((int) (Math.random() * 50) + 50);
    }

    // Create platforms
    public void generatePlatforms() {
            for (int i = 0; i < NUM_PLATFORMS; i++) {
                // Generate a random x value
                int x = generateX();

                int y = generateY(GameViewer.WINDOW_HEIGHT - i * (GameViewer.WINDOW_HEIGHT / NUM_PLATFORMS));
                platforms.add(new Platform(x, y, window));
            }
    }

    // Checks to see if the player is touching the monster
    public boolean isTouchingMonster() {
        int mx = monster.getX();
        int my = monster.getY();
        int px = player.getX();
        int py = player.getY();
        // Logic for if player is touching
        boolean rightX = px + Player.PLAYER_SIZE >= mx && px <= mx + Platform.PLATFORM_WIDTH;
        boolean rightY = py + Player.PLAYER_SIZE >= my && py <= my + Platform.PLATFORM_HEIGHT;
        return rightX && rightY;
    }

    // Checks if game is over
    public void gameOver(int py) {
        // If game over stop the action performed method
        if (state == 1) {
            clock.stop();
        }
        // Check to see if player has fallen or touched the monster and if so game is over
        else if (py > GameViewer.WINDOW_HEIGHT || isTouchingMonster()) {
            state++;
        }
    }

    // Change the y-coordinates of player, platforms, and monster
    public void changeY(int py) {
        // If player is too high will set him down and adjust all the platforms down
        if (py < SCROLL_CAP) {
            int changeY = SCROLL_CAP - py;
            // Add difference between scroll cap and player height to the score
            score += changeY;
            player.setY(SCROLL_CAP);
            changeMonsterY(changeY);
            changePlatformY(changeY);
        }
    }

    // Change the monster's y coordinates
    public void changeMonsterY(int changeY) {
        // Changes the y value
        monster.setY(monster.getY() + changeY);
        // If monster is below the screen set it back to its original height
        if (monster.getY() >= GameViewer.WINDOW_HEIGHT - BUG_FACTOR) {
            monster.setX(generateX());
            monster.setY(MONSTER_Y);
        }
    }

    // Change platforms y coordinates
    public void changePlatformY(int changeY) {
        // Gets the highest platforms y coordinates
        int minY = getMinY();
        for (Platform p: platforms) {
            // Changes the platforms y
            p.setY(p.getY() + changeY);
            // If platform is below the screen move it back up with random coordinates
            if (p.getY() >= GameViewer.WINDOW_HEIGHT - BUG_FACTOR) {
                p.setX(generateX());
                p.setY(generateY(minY));
            }
        }
    }

    // Starts the clock for the actionperformed method
    public void playGame() {
        clock = new Timer(DELAY_IN_MILLISECONDS, this);
        clock.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    @Override
    // Makes it so that when you release the button the player doesn't move
    public void keyReleased(KeyEvent e) {
        player.setDx(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                // Sets the dx so that the player moves left
                player.setDx(-PLAYER_SPEED);
                break;
            case KeyEvent.VK_RIGHT:
                // Sets the dx so that the player moves right
                player.setDx(PLAYER_SPEED);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Player moves when you are holding one of the arrow keys
        player.move(platforms);
        // Gets the player's y value
        int py = player.getY();
        // Checks for game over
        gameOver(py);
        // Monster moves
        monster.move();
        // Changes all the y values so that platforms and monster reset
        changeY(py);
        window.repaint();
    }

    public static void main(String[] args) {
        Game doodleJump = new Game();
        doodleJump.playGame();
    }
}