package GUI;


import TriviaMaze.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;

public class Frame extends JFrame implements Serializable
{
    private final static int WIDTH = 2000;
    private final static int HEIGHT = 2000;

    private final static int MAZE_SIZE = 8;
//    private final TriviaMaze myMaze = new TriviaMaze(MAZE_SIZE);


    static TextPanel textBoxes;

    static final Controller myController;
    static {
        try {
            myController = new Controller(MAZE_SIZE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static MazePanel mazeView;
    static {
        try {
            mazeView = new MazePanel(myController.getGameMaze());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static String myCur;

    public Frame() throws IOException, SQLException {

        this.setTitle("Maze Game");
        final ImageIcon uwImage = new ImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/GUIPictures/w.gif")))
                .getImage().getScaledInstance(60, 40, Image.SCALE_DEFAULT));
        this.setIconImage(uwImage.getImage());
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //add menu bar
        Menu myMenuBar = new Menu();
        this.add(myMenuBar);
        this.setJMenuBar(myMenuBar);

        // add room panel
        RoomPanel roomView = new RoomPanel();
        this.add(roomView);
        roomView.setLocation(0, 0);
        this.add(mazeView);
        mazeView.setLocation(500, 0);

        //add button panel
        JPanel buttonPanel = new ButtonPanel();
        this.add(buttonPanel);
        buttonPanel.setBounds(1000, 0, 300, 100);

        //add question/answer panel
        textBoxes = new TextPanel();
        this.add(textBoxes);
        textBoxes.setBounds(500, 500, 800, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            try {
                new Frame();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
    }
}