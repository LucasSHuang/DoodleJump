import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player {

    // Constants and Instance Variables
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 1000;
    private final int PLATFORM_WIDTH = 100;
    private final int PLATFORM_HEIGHT = 20;
    private final int PLAYER_SIZE = 75;
    private final int START_X = 262;
    private final int START_Y = 100;
    private GameViewer window;
    private Image right;
    private Image left;
    private int x;
    private int y;
    private int dx;
    private double dy;

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

    public boolean touchingPlatform(Platform p) {
        int px = p.getX();
        int py = p.getY();
        boolean rightX = x + PLAYER_SIZE >= px && x <= px + PLATFORM_WIDTH;
        boolean rightY = y + PLAYER_SIZE >= py && y <= py + PLATFORM_HEIGHT;
        boolean falling = dy > 0;
        return rightX && rightY && falling;
    }

    public void move(ArrayList<Platform> platforms) {
        x += dx;
        if (x > WINDOW_WIDTH - PLAYER_SIZE) {
            x = 0;
        }
        else if (x < 0) {
            x = WINDOW_WIDTH - PLAYER_SIZE;
        }
        if (y == WINDOW_HEIGHT) {
            y= 0;
        }
        y += dy;
        dy += 1.5;
        if (dy > 3.5) {
            dy = 3.5;
        }
        for (Platform p : platforms) {
            if (touchingPlatform(p)) {
                jump();
            }
        }
    }
    public void jump() {
        dy = -30;
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
