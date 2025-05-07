import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {

    // Constants and Instance Variables
    public static final int PLAYER_SIZE = 65;
    private final int START_X = 262;
    private final int START_Y = 100;
    private final double GRAVITY = 1.5;
    private final double GRAVITY_LIMIT = 7;
    private GameViewer window;
    private Image right;
    private Image left;
    private int x;
    private int y;
    private int dx;
    private double dy;

    // Constructor
    public Player(GameViewer window) {
        this.window = window;
        this.right = new ImageIcon("Resources/right.png").getImage();
        this.left = new ImageIcon("Resources/left.png").getImage();
        this.x = START_X;
        this.y = START_Y;
        this.dx = 0;
        this.dy = 0;
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX() {
        // Changes the x value by dx
        x += dx;
        // Makes it so that if the player goes off the screen it wraps around to the opposite side
        if (x > GameViewer.WINDOW_WIDTH - PLAYER_SIZE) {
            x = 0;
        }
        else if (x < 0) {
            x = GameViewer.WINDOW_WIDTH - PLAYER_SIZE;
        }
    }

    // Makes the player fall(or rise) at the speed of dx until a certain speed is reached
    public void fall() {
        y += dy;
        dy += GRAVITY;
        if (dy > GRAVITY_LIMIT) {
            dy = GRAVITY_LIMIT;
        }
    }

    // Checks to see if the player is touching a platform
    public boolean touchingPlatform(Platform p) {
        // Gets the platform x and y coordinates
        int px = p.getX();
        int py = p.getY();
        // Makes sure the player is within the x parameters of the platform
        boolean rightX = x + PLAYER_SIZE >= px && x <= px + Platform.PLATFORM_WIDTH;
        // Makes sure the player is within the y parameters of the platform
        boolean rightY = y + PLAYER_SIZE >= py && y <= py + Platform.PLATFORM_HEIGHT;
        // Makes sure the player is falling
        boolean falling = dy == GRAVITY_LIMIT;
        return rightX && rightY && falling;
    }

    // Moves the player
    public void move(ArrayList<Platform> platforms) {
        setX();
        fall();
        // Checks if the player is touching for all platforms
        for (Platform p : platforms) {
            // If touching a platform the player jumps
            if (touchingPlatform(p)) {
                jump();
            }
        }
    }

    // Makes the player jump
    public void jump() {
        dy = -30;
    }

    // Draws the player
    public void draw(Graphics g) {
        // Makes it so that if the player is moving right the player faces right
        if (dx >= 0) {
            g.drawImage(right, x, y, PLAYER_SIZE, PLAYER_SIZE, window);
        }
        // If the player is moving left it faces left
        else {
            g.drawImage(left, x, y, PLAYER_SIZE, PLAYER_SIZE, window);
        }
    }
}
