package GUI;


import TriviaMaze.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.Objects;

public class Frame extends JFrame
{
    private final static int WIDTH = 2000;
    private final static int HEIGHT = 2000;

    final static int MAZE_SIZE = 8;
//    private final TriviaMaze myMaze = new TriviaMaze(MAZE_SIZE);


    static TextPanel myTextBoxes;

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
//        RoomPanel roomView = new RoomPanel();
//        this.add(roomView);
//        roomView.setLocation(0, 0);
        this.add(mazeView);
        mazeView.setLocation(500, 0);

        //add button panel
        JPanel buttonPanel = new ButtonPanel();
        this.add(buttonPanel);
        buttonPanel.setBounds(1000, 0, 300, 100);

        //add question/answer panel
        myTextBoxes = new TextPanel();
        this.add(myTextBoxes);
        myTextBoxes.setBounds(500, 500, 800, 400);

        //test for save button
        //
        //
        JButton mySave=new JButton("Save Game");
        mySave.setMnemonic(KeyEvent.VK_S);
        mySave.setEnabled(true);
        mySave.addActionListener(event ->
        {
            FileDialog fd = new FileDialog(new JFrame(), "Save Game", FileDialog.SAVE);
            fd.setVisible(true);
            if (fd.getFile() == null) return;
            String fileName = fd.getFile();
            try
            {
                File f = new File(fd.getDirectory(), fileName);
                f.setWritable(true);
                FileOutputStream file = new FileOutputStream(f);
                ObjectOutputStream out =new ObjectOutputStream(file);

                out.writeObject(this);


                out.close();
                file.close();
            }
            catch (IOException theE)
            {
                theE.printStackTrace();
            }
        });
        buttonPanel.add(mySave);

        //test for load button
        //
        //
        JButton myLoad=new JButton("Load Game");
        myLoad.setMnemonic(KeyEvent.VK_L);
        myLoad.setEnabled(true);
        myLoad.addActionListener(e ->
        {
            FileDialog fd = new FileDialog(new JFrame(), "Load Game", FileDialog.LOAD);
            fd.setVisible(true);
            if (fd.getFile() == null) return;
            try
            {
                File f = new File(fd.getDirectory(), fd.getFile());
                FileInputStream file=new FileInputStream(f);
                ObjectInputStream in=new ObjectInputStream(file);

                Frame g= (Frame) in.readObject();
                this.add(g);

                in.close();
                file.close();

            } catch (ClassNotFoundException | IOException ex)
            {
                ex.printStackTrace();
            }

        });
        buttonPanel.add(myLoad);









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