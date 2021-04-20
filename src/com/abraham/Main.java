package com.abraham;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Main extends Canvas implements Runnable {

    public static final String GAME_TITLE = "Street Fighter";
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 220;
    public static final int SCALE = 2;

    private GameState gameState;
    private Keys key;
    private int count = 0;
    public boolean running;
    private JFrame frame;

    public Main(){
        frame = new JFrame(GAME_TITLE);
        frame.setSize(WINDOW_WIDTH * SCALE, WINDOW_HEIGHT * SCALE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);

        frame.setUndecorated(false);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        setFocusable(false);

        // user input
        key = new Keys();
        frame.addKeyListener(key);

        // pack everything
        frame.pack();

    }

    public synchronized void start() throws IOException{

        // the program is running...
        running = true;

        // pre-load images
        Images.init();

        // states
        gameState = new GameState(this);
        State.setState(gameState);

        // thread this class
        new Thread(this).start();

    }
    @Override
    public void run() {
        // init vars
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / 60.0;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        // while the program is running....
        while (running) {

            // get the current system time
            long now = System.nanoTime();
            // find delta by taking difference between now and last
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            // can render each frame...
            boolean canRender = true;

            // if ratio is greater than one, meaning...
            while (delta >= 1) {
				/* if the current time - last / n, where n
				   can be any real number is greater than 1
			    update the game...*/
                ticks++;
                update();
                delta--;
                canRender = true;
            }

            // sleep program so that not to many frames are produced (reduce lag)
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // if can render...
            if (canRender) {
                // increment frames and render
                frames++;
                createGame();

            }

            // if one second has passed...
            if (System.currentTimeMillis() - lastTimer > 1000) {
                // increment last timer, output frames to user
                lastTimer += 1000;
                frames = 0;
                ticks = 0;
            }
        }

    }

    public void createGame(){

        BufferStrategy bs = getBufferStrategy();

        // create a double buffering strategy
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // create temp white rect that fills screen
        g.setColor(new Color(50, 50, 50));
        //g.fillRect(0, 0, getWidth(), getHeight());

        /* ALL DRAWING HERE */

        ImageIcon forestStage = new ImageIcon(Main.class.getResource("/forest_stage.gif"));

        g.drawImage(forestStage.getImage(), -800, -220, forestStage.getIconWidth() * 2, forestStage.getIconHeight() * 2,null);


        // if current state exist, then render
        if (State.getState() != null) {
            count++;
            State.getState().render(g);
        }

        /* ALL DRAWING HERE */
//
        g.dispose();
        bs.show();

    }

    public void update(){
        key.move();

        if(State.getState() != null){
            count++;
            State.getState().timer();
        }
    }

    public void quit(){
        running = false;
    }

    public State getGameState() {
        return gameState;
    }

    public static void main(String[] args) throws IOException {
        Main game = new Main();

        game.start();
    }

    public Keys getKey(){
        return key;
    }

}
