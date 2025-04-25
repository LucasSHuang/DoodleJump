import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {

    // Constants and Instance Variables
    private final int PLAYER_SIZE = 65;
    private final int START_X = 262;
    private final int START_Y = 100;
    private final double GRAVITY = 1.5;
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
        x += dx;
        if (x > GameViewer.WINDOW_WIDTH - PLAYER_SIZE) {
            x = 0;
        }
        else if (x < 0) {
            x = GameViewer.WINDOW_WIDTH - PLAYER_SIZE;
        }
    }

    public void fall() {
        y += dy;
        dy += GRAVITY;
        if (dy > 4) {
            dy = 4;
        }
    }

    public boolean touchingPlatform(Platform p) {
        int px = p.getX();
        int py = p.getY();
        boolean rightX = x + PLAYER_SIZE >= px && x <= px + Platform.PLATFORM_WIDTH;
        boolean rightY = y + PLAYER_SIZE >= py && y <= py + Platform.PLATFORM_HEIGHT;
        boolean falling = dy > 0;
        return rightX && rightY && falling;
    }

    public void move(ArrayList<Platform> platforms) {
        setX();
        fall();
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
