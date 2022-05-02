package GUI;

import TriviaMaze.TriviaMaze;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Frame extends JFrame
{
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;
    private TriviaMaze myMaze = new TriviaMaze(10);
    public Frame() throws IOException
    {
        this.setTitle("Game");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(new RoomPanel(), BorderLayout.WEST);
        this.add(new Maze(this.myMaze));
        pack();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            try {
                new Frame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
