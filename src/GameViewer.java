import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame {

    // Constants and Instance Variables
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 1000;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game game;

    // Constructor
    public GameViewer(Game game) {
        this.game = game;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Doodle Jump");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.createBufferStrategy(2);
    }

    // Buffer
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        for (Platform p : game.getPlatforms()) {
            p.draw(g);
        }
        game.getPlayer().draw(g);
    }
}
