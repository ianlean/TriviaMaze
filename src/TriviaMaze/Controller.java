package TriviaMaze;

import GUI.MazePanel;
import GUI.QuestionPanel;
import GUI.RoomPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Controller {
    protected TriviaMaze gameMaze;
    protected Point point;

    public Controller(TriviaMaze theMaze, int x, int y) {
        gameMaze = theMaze;
        this.point = new Point(x, y);
    }
    public int getX()
    {
        return point.x;
    }
    public int getY()
    {
        return point.y;
    }

    public void moveLeft()
    {
        move(getX() - 1, getY());
    }

    public void moveRight()
    {
        move(getX() + 1, getY());
    }

    public void moveUp()
    {
        move(getX(), getY() - 1);
    }

    public void moveDown()
    {
        move(getX(), getY() + 1);
    }

    public void move(int x, int y)
    {
        if (gameMaze.checkRoom(x, y) == TriviaMaze.Room.RoomStatus.UNLOCKED)
        {
            point.setLocation(x, y);
        }
        if (gameMaze.checkRoom(x, y) == TriviaMaze.Room.RoomStatus.LOCKED)
        {
            Room room = gameMaze.getRoom(x, y);
            QuestionPanel.getInstance().setRoom(room);
        }

        MazePanel.getInstance().repaint();
        RoomPanel.getInstance().repaint();
    }
    public void reset()
    {
        this.gameMaze.reset();
        this.point = new Point(0, 0);
        RoomPanel.getInstance().repaint();
        MazePanel.getInstance().repaint();
        QuestionPanel.getInstance().reset();
    }

//    public boolean askDirection(String direction) {
//        boolean moved = false;
//        promptDirection();
//        Scanner scan = new Scanner(System.in);
//
//        if (direction.equalsIgnoreCase("w")) {
//            moved = this.gameMaze.move(this.gameMaze.getMyY(), this.gameMaze.getMyX() - 1);
//
//        } else if (direction.equalsIgnoreCase("e")) {
//            moved = this.gameMaze.move(this.gameMaze.getMyY(), this.gameMaze.getMyX() + 1);
//
//        } else if (direction.equalsIgnoreCase("s")) {
//            moved = this.gameMaze.move(this.gameMaze.getMyY() + 1, this.gameMaze.getMyX());
//
//        } else if (direction.equalsIgnoreCase("n")) {
//            moved = this.gameMaze.move(this.gameMaze.getMyY() - 1, this.gameMaze.getMyX());
//
//        }
//        System.out.println(this.gameMaze);
//        return moved;
//    }

//    public static void promptDirection() {
//        System.out.println("---------------------------");
//        System.out.println("CHOOSE YOUR DIRECTION:\n");
//        System.out.println("N, E, S, W\n");
//    }
    public void gameOver()
    {
        if (this.gameMaze.checkRoom(TriviaMaze.MAZE_SIZE - 1, TriviaMaze.MAZE_SIZE - 1)
                == Room.RoomStatus.SEALED || !hasRoute())
        {
            lose();
        }
        if (point.x == TriviaMaze.MAZE_SIZE - 1 && point.y == TriviaMaze.MAZE_SIZE - 1)
        {
            win();
        }
    }
    private void win()
    {
        int code = JOptionPane.showConfirmDialog(null, "Good job! You Win \n" +
                "Do you want to play again?", "Game over", JOptionPane.YES_NO_CANCEL_OPTION);
        if (code == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            reset();
        }
    }
    private void lose()
    {
        int code = JOptionPane.showConfirmDialog(null, "You lost \n" +
                "Do you want to play again?", "Game over", JOptionPane.YES_NO_CANCEL_OPTION);
        if (code == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            reset();
        }
    }
    private boolean hasRoute()
    {
        int[][] maze = getMaze();
        return gameOverHelper(maze, point.x, point.y);
    }
    private boolean gameOverHelper(final int[][] theMaze, final int x, final int y)
    {
        if (!(x >= 0 && x < theMaze.length)
                && (y >= 0 && y < theMaze.length)
                || theMaze[x][y] == 0)
        {
            return false;
        }
        if (x == TriviaMaze.MAZE_SIZE - 1 && y == TriviaMaze.MAZE_SIZE - 1)
        {
            return true;
        }
        theMaze[x][y] = 0;
        return gameOverHelper(theMaze, x + 1, y) || gameOverHelper(theMaze, x - 1, y)
                || gameOverHelper(theMaze, x, y + 1) || gameOverHelper(theMaze, x, y - 1);
    }
    private int[][] getMaze()
    {
        int[][] maze = new int[TriviaMaze.MAZE_SIZE][TriviaMaze.MAZE_SIZE];
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze.length; j++)
            {
                switch (gameMaze.checkRoom(i, j))
                {
                    case UNLOCKED, LOCKED -> maze[i][j] = 1;
                    case SEALED -> maze[i][j] = 0;
                }
            }
        }
        return maze;
    }
    public void save()
    {
        String saveGame = JOptionPane.showInputDialog(null,
                "Your save name: ", "Save Name", JOptionPane.WARNING_MESSAGE);
        if (saveGame == null) return;
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < TriviaMaze.MAZE_SIZE; i++)
        {
            for (int j = 0; i < TriviaMaze.MAZE_SIZE; j++)
            {
                switch (this.gameMaze.checkRoom(i, j)) {
                    case UNLOCKED -> code.append(0);
                    case LOCKED -> code.append(1);
                    case SEALED -> code.append(2);
                }
            }
        }
        code.append(getX());
        code.append(getY());
        Database.getInstance().save(saveGame, code);
    }
}
