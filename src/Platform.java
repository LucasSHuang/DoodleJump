import javax.swing.*;
import java.awt.*;

public class Platform {

    // Constants and Instance Variables
    public static final int PLATFORM_WIDTH = 100;
    public static final int PLATFORM_HEIGHT = 20;
    private GameViewer window;
    private Image platform;
    private int x;
    private int y;

    // Constructor
    public Platform(int x, int y, GameViewer window) {
        this.x = x;
        this.y = y;
        this.window = window;
        this.platform = new ImageIcon("Resources/platform.jpg").getImage();
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

    // Draw method
    public void draw(Graphics g) {
        g.drawImage(platform, x, y, PLATFORM_WIDTH, PLATFORM_HEIGHT, window);
    }
}
