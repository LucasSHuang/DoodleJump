import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {

    // Constants and Instance Variables
    private final int PLAYER_SIZE = 75;
    private final int START_X = 262;
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
        this.right = new ImageIcon("Resources/right.png").getImage();
        this.left = new ImageIcon("Resources/left.png").getImage();
        this.x = START_X;
        this.y = START_Y;
        this.dx = 0;
        this.dy = 0;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public boolean touchingPlatform() {
        return false;
    }

    public void move(int direction) {
        if (direction == 1) {
            x += 4;
            dx = 1;
        }
        else if (direction == -1) {
            x -= 4;
            dx = -1;
        }
    }
    public void jump() {

    }

    public void draw(Graphics g) {
        if (dx >= 0) {
            g.drawImage(right, x, y, PLAYER_SIZE, PLAYER_SIZE, window);
        }
        else {
            g.drawImage(left, x, y, PLAYER_SIZE, PLAYER_SIZE, window);
        }
    }
}
