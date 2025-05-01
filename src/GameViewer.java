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
    private final int TEXT_START = 180;
    private final Font MAIN = new Font("SERIF", Font.BOLD, 24);
    private final Font LARGE = new Font("SERIF", Font.BOLD, 48);
    private final Color RED = new Color(172,40,57);
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

    // Prints out the score in the top right corner
    public void printScore(Graphics g, int width, int height) {
        String score = "Score: " + Integer.toString(game.getScore());
        g.drawString(score, width, height);
    }

    // Paints the game over message and the score below it
    public void paintEnd(Graphics g) {
            g.setFont(LARGE);
            printScore(g, TEXT_START, WINDOW_HEIGHT / 2);
            g.setColor(RED);
            g.drawString("Game Over", TEXT_START, WINDOW_HEIGHT / 3);
    }

    // Buffer to make graphics more smooth
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

    // Paint method
    public void myPaint(Graphics g) {
        // Draw background image
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        // If player is alive print the player, monster, platforms, etc.
        if (game.getState() == 0) {
            g.setColor(Color.BLACK);
            for (Platform p : game.getPlatforms()) {
                p.draw(g);
            }
            game.getMonster().draw(g);
            game.getPlayer().draw(g);
            g.setFont(MAIN);
            printScore(g, SCORE_WIDTH, SCORE_HEIGHT + TITLE_BAR_HEIGHT);
        }
        // If player is dead just do the end game method
        if (game.getState() == 1) {
            paintEnd(g);
        }
    }
}
