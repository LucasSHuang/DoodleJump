import javax.swing.*;
import java.awt.*;

public class Monster {
    private final int MONSTER_HEIGHT = 75;
    private final int MONSTER_WIDTH = 100;
    private final int MONSTER_SPEED = 3;
    private GameViewer window;
    private Image image;
    private int x;
    private int y;
    private int dx;

    public Monster(int x, int y, GameViewer window) {
        this.x = x;
        this.y = y;
        this.dx = MONSTER_SPEED;
        this.window = window;
        this.image = new ImageIcon("Resources/monster.png").getImage();
    }

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

    public void move() {
        x += dx;
        if (x > GameViewer.WINDOW_WIDTH - MONSTER_WIDTH) {
            dx *= -1;
        }
        else if (x < 0) {
            dx *= -1;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, MONSTER_WIDTH, MONSTER_HEIGHT, window);
    }
}
