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
    private final int BUG_FACTOR = 150;
    private final int MONSTER_Y = -3000;
    private GameViewer window;
    private ArrayList<Platform> platforms;
    private Player player;
    private Monster monster;
    private boolean touchingMonster;
    private int score;
    private int state;
    private Timer clock;

    public Game() {
        state = 0;
        score = 0;
        platforms = new ArrayList<Platform>();
        window = new GameViewer(this);
        monster = new Monster(generateX(), MONSTER_Y, window);
        player = new Player(window);
        touchingMonster = false;
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

    public Monster getMonster() {
        return monster;
    }

    public int generateX() {
        return (int) (Math.random() * 500);
    }

    public int getMinY() {
        int minY = GameViewer.WINDOW_HEIGHT;
        for (int i = 0; i < platforms.size(); i++) {
            if (platforms.get(i).getY() < minY) {
                minY = platforms.get(i).getY();
            }
        }
        return minY;
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

    public void gameOver(int py) {
        if (state == 1) {
            clock.stop();
        }
        else if (py > GameViewer.WINDOW_HEIGHT || touchingMonster) {
            state++;
            window.repaint();
        }
    }

    public void changeY(int py) {
        if (py < SCROLL_CAP) {
            int changeY = SCROLL_CAP - py;
            score += changeY;
            player.setY(SCROLL_CAP);
            changeMonsterY(changeY);
            changePlatformY(changeY);
        }
    }

    public void changeMonsterY(int changeY) {
        monster.setY(monster.getY() + changeY);
        if (monster.getY() >= GameViewer.WINDOW_HEIGHT - BUG_FACTOR) {
            monster.setX(generateX());
            monster.setY(MONSTER_Y);
        }
    }

    public void changePlatformY(int changeY) {
        int minY = getMinY();
        for (Platform p: platforms) {
            p.setY(p.getY() + changeY);
            if (p.getY() >= GameViewer.WINDOW_HEIGHT - BUG_FACTOR) {
                p.setX(generateX());
                p.setY(generateY(minY));
            }
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
        gameOver(py);
        changeY(py);
        window.repaint();
    }

    public static void main(String[] args) {
        Game doodleJump = new Game();
        doodleJump.playGame();
    }
}
