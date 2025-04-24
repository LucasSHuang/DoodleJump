// Doodle Jump by Lucas Huang

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {

    // Instance Variables
    private final int DELAY_IN_MILLISECONDS = 7;
    private final int PLAYER_SPEED = 4;
    private final int SCROLL_CAP = 400;
    private final int NUM_PLATFORMS = 13;
    private GameViewer window;
    private ArrayList<Platform> platforms;
    private Player player;
    private int score;
    private int state;
    private boolean gameOver;
    private Timer clock;

    public Game() {
        state = 0;
        score = 0;
        platforms = new ArrayList<Platform>();
        window = new GameViewer(this);
        player = new Player(window);
        gameOver = false;
        window.addKeyListener(this);
        clock = new Timer(DELAY_IN_MILLISECONDS, this);
        clock.start();
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

    public int generateX() {
        return (int) (Math.random() * 500);
    }

    public int generateY(int minY) {
        return minY - ((int) (Math.random() * 50) + 50);
    }

    public void generatePlatforms() {
            for (int i = 0; i < NUM_PLATFORMS; i++) {
                int x = generateX();
                int y = generateY(GameViewer.WINDOW_HEIGHT - i * (GameViewer.WINDOW_HEIGHT / NUM_PLATFORMS));
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
        player.setDx(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                player.setDx(-PLAYER_SPEED);
                break;
            case KeyEvent.VK_RIGHT:
                player.setDx(PLAYER_SPEED);
                break;
        }
        window.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move(platforms);
        int py = player.getY();
        if (py < SCROLL_CAP) {
            int changeY = SCROLL_CAP - py;
            score += changeY;
            player.setY(SCROLL_CAP);
            int minY = platforms.get(platforms.size() - 1).getY();
            for (int i = 0; i < platforms.size(); i++) {
                platforms.get(i).setY(platforms.get(i).getY() + changeY);
                if (platforms.get(i).getY() < minY) {
                    minY = platforms.get(i).getY();
                }
                if (platforms.get(i).getY() >= GameViewer.WINDOW_HEIGHT - GameViewer.TITLE_BAR_HEIGHT) {
                    platforms.get(i).setX(generateX());
                    platforms.get(i).setY(generateY(minY));
                }
            }
        }
        window.repaint();
    }

    public static void main(String[] args) {
        Game doodleJump = new Game();
        doodleJump.playGame();
    }
}
