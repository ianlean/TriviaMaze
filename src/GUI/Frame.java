package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

<<<<<<< HEAD
public class Frame extends JFrame implements KeyListener
=======
public class Frame extends JFrame
>>>>>>> origin/Kevin
{
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;
    public Frame() throws IOException
    {
        this.setTitle("Game");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(new RoomPanel());
        pack();
    }
<<<<<<< HEAD
    public static void main(String[] args) throws IOException {
=======
    public static void main(String[] args) {
>>>>>>> origin/Kevin
        EventQueue.invokeLater(() ->
        {
            try {
                new Frame();
            } catch (IOException e) {
<<<<<<< HEAD
                throw new RuntimeException(e);
=======
                e.printStackTrace();
>>>>>>> origin/Kevin
            }
        });
    }

<<<<<<< HEAD
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
=======
>>>>>>> origin/Kevin
}
