package com.abraham;

import java.awt.*;
import java.io.IOException;

public class Main extends Canvas implements Runnable {

    public static final String GAME_TITLE = "Street Fighter";
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 220;
    public static final int SCALE = 2;

    private GameState gameState;

    public static void main(String[] args) throws IOException {
        Main game = new Main();

        game.start();
    }

    public void start() throws IOException{

    }
    @Override
    public void run() {

    }

    public State getGameState() {
        return gameState;
    }
}
