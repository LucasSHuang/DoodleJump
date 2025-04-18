// Doodle Jump by Lucas Huang

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game implements java.awt.event.KeyListener {

    // Instance Variables
    private GameViewer window;
    private ArrayList<Platform> platforms;
    private Player player;
    private int score;
    private int state;
    private boolean gameOver;

    public Game() {
        state = 0;
        platforms = new ArrayList<Platform>();
        window = new GameViewer(this);
        player = new Player(window);
        gameOver = false;
        window.addKeyListener(this);
    }

    public int getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public Player getPlayer() {
        return player;
    }

    public void generatePlatforms() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            int x = (int)(Math.random() * 500);
            int y = 1000 - i * 100;
            platforms.add(new Platform(x, y, window));
        }
    }

    public void playGame() {
        generatePlatforms();
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    public static void main(String[] args) {
        Game doodleJump = new Game();
        doodleJump.playGame();
    }
}
