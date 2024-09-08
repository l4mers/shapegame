package org.example;

import org.example.api.UserInfo;
import org.example.states.StateController;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //MEASURING
    private final int size = 32;
    private final int scale = 2;
    public final int tileSize = size * scale;
    private final int tileWidth = 16;
    private final int tileHeight = 16;
    public final int screenWidth = tileSize * tileWidth;
    public final int screenHeight = tileSize * tileHeight;

    //FPS
    double FPS = 60;

    //OBJECTS
    Thread gameThread;
    public StateController stateController = new StateController(this);
    public InputListener inputListener = new InputListener();
    public Player player = new Player();

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseListener(inputListener);
        this.addMouseMotionListener(inputListener);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double updateInterval = 1000000000 / FPS; //0.016666 sekund
        double delta = 0;
        long lastUpdate = System.nanoTime(); //nanosekunder för precision
        long currentTime;

        while (gameThread.isAlive()){

            currentTime = System.nanoTime();

            delta += (currentTime - lastUpdate) / updateInterval;
            lastUpdate = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        stateController.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        stateController.draw(g2);
        g2.dispose();
    }
}
