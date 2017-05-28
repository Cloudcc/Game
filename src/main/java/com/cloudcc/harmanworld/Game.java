package com.cloudcc.harmanworld;

import com.cloudcc.harmanworld.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
/**
 * Created by Tobias on 27.05.2017.
 */
public class Game extends Canvas implements Runnable {

    public static int width = 300;                    //Fensterbreite
    public static int height = width / 16 * 9;        //Fensterhöhe
    public int scale = 3;                             //Skalierungsgröße

    private Thread gameThread;
    private JFrame frame;
    private boolean running = false;                  //Programm läuft (hier: nein)

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   //Draw Image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();         //Access this Image

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width,height);

        frame = new JFrame();

    }

    public synchronized void start() {
        running = true;
        gameThread = new Thread(this, "Display");
        gameThread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {
            update();
            render();
        }
    }

    public void update() {

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);                                        //3 == Triple Buffering runs faster with 3 not 4 5 or 10 :)
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(110, 177, 244));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();                                                        //Releases System Resources "Removes Graphics"
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);         //1. SetResizable
        game.frame.setTitle("Harman's World");
        game.frame.add(game);
        game.frame.pack();                      //Set Size of Components
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();

    }
}
