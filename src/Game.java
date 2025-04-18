// Doodle Jump by Lucas Huang

import java.util.ArrayList;

public class Game {

    // Instance Variables
    private GameViewer window;
    private ArrayList<Platform> platforms;
    private Player player;
    private int score;
    private int state;
    private boolean gameOver;

    public Game() {
        state = 0;
        window = new GameViewer(this);
        gameOver = false;
        platforms = new ArrayList<Platform>();
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


    public static void main(String[] args) {
        Game doodleJump = new Game();
        doodleJump.playGame();
    }
}
