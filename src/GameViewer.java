import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame {

    // Constants and Instance Variables
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 1000;
    public static final int TITLE_BAR_HEIGHT = 23;
    private final int SCORE_WIDTH = 20;
    private final int SCORE_HEIGHT = 50;
    private final Font MAIN = new Font("PLAIN", Font.BOLD, 24);
    private Image background;
    private Game game;

    // Constructor
    public GameViewer(Game game) {
        this.game = game;
        this.background = new ImageIcon("Resources/background.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Doodle Jump");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        this.createBufferStrategy(2);
    }

    public void printScore(Graphics g) {
        g.setFont(MAIN);
        String score = "Score: " + Integer.toString(game.getScore());
        g.drawString(score, SCORE_WIDTH, SCORE_HEIGHT + TITLE_BAR_HEIGHT);
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
        g.setColor(Color.BLACK);
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        printScore(g);
        for (Platform p : game.getPlatforms()) {
            p.draw(g);
        }
        game.getPlayer().draw(g);
    }
}
