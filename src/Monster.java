import javax.swing.*;
import java.awt.*;

public class Monster {

    // Constants and instance variables
    private final int MONSTER_HEIGHT = 75;
    private final int MONSTER_WIDTH = 100;
    private final int MONSTER_SPEED = 3;
    private GameViewer window;
    private Image image;
    private int x;
    private int y;
    private int dx;

    // Constructor
    public Monster(int x, int y, GameViewer window) {
        this.x = x;
        this.y = y;
        this.dx = MONSTER_SPEED;
        this.window = window;
        this.image = new ImageIcon("Resources/monster.png").getImage();
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Makes the monster move
    public void move() {
        // Changes x value by dx(speed of the monster)
        x += dx;
        // Makes it so that if touching either edge of the screen changes direction
        if (x > GameViewer.WINDOW_WIDTH - MONSTER_WIDTH || x < 0) {
            dx *= -1;
        }
    }

    // Draw method
    public void draw(Graphics g) {
        g.drawImage(image, x, y, MONSTER_WIDTH, MONSTER_HEIGHT, window);
    }
}
