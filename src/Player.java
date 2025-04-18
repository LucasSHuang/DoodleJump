import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {

    // Constants and Instance Variables
    private final int START_X = 300;
    private final int START_Y = 600;
    private GameViewer window;
    private Image right;
    private Image left;
    private int x;
    private int y;
    private int dx;
    private int dy;

    public Player(GameViewer window) {
        this.window = window;
        this.right = new ImageIcon("Resources/right.jpg").getImage();
        this.left = new ImageIcon("Resources/left.jpg").getImage();
        this.x = START_X;
        this.y = START_Y;
        this.dx = 0;
        this.dy = 0;
    }

    public boolean touchingPlatform() {
        return false;
    }

    public void move() {
        y += dy;
        dy += 1;
    }
    public void jump() {

    }

    public void draw(Graphics g) {
        if (dx >= 0) {
            g.drawImage(right, x, y, 75, 75, window);
        }
        else {
            g.drawImage(left, x, y, 75, 75, window);
        }

    }
}
