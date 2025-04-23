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
    private GameViewer window;
    private ArrayList<Platform> platforms;
    private Player player;
    private int score;
    private int state;
    private boolean gameOver;
    private Timer clock;

    public Game() {
        state = 0;
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
        player.setDx(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
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
            player.setY(SCROLL_CAP);
            for (int i = 0; i < platforms.size(); i++) {
                if (platforms.get(i).getY() >= GameViewer.WINDOW_HEIGHT) {
                    platforms.remove(i);
                }
                platforms.get(i).setY(platforms.get(i).getY() + changeY);
            }
        }
        window.repaint();
    }

    public static void main(String[] args) {
        Game doodleJump = new Game();
        doodleJump.playGame();
    }
}
