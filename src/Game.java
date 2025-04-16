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
    }

    public int getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public void generatePlatform() {

    }

    public static void main(String[] args) {

    }
}
