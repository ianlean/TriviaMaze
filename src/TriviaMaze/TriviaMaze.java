package TriviaMaze;

import java.sql.*;
import java.util.Scanner;

import TriviaMaze.Question.Question;
import org.sqlite.SQLiteDataSource;

public class TriviaMaze {

    private GenerateQuestion generator;
    private final Room[][] myMaze;
    private int myX;
    private int myY;
    private Room characterSpot;
    private Connection conn;

    private final SQLiteDataSource ds = new SQLiteDataSource();

    public TriviaMaze(final int theSize) throws SQLException { // for developing purposes I am auto-filling rooms
        myMaze = new Room[theSize][theSize];
        generator = new GenerateQuestion();
        generateMaze();
    }
    private void generateMaze()
    {
        for (int row = 0; row < this.myMaze.length; row++)
        {
            for (int col = 0; col < this.myMaze.length; col++)
            {
                this.myMaze[row][col] = new Room(generator.generateRandomQuestion(), false);
                if (row == 0 || col == 0 ||
                row == this.myMaze.length - 1 || col == this.myMaze.length - 1 ||
                row - col == 0 || row - col == 1)
                {
                    this.myMaze[row][col].setStatus(Cell.RoomStatus.LOCKED);
                } else {
                    this.myMaze[row][col].setStatus(Cell.RoomStatus.SEALED);
                }
            }
        }
        characterSpot = myMaze[0][0] = new Room(null, true);
        characterSpot.setHasPlayer(true);
        myMaze[0][0].unlock();
        myX = 0;
        myY = 0;
        myMaze[0][0].setStatus(Cell.RoomStatus.UNLOCKED);
    }

    public void changeDirection(final int theRow, final int theCol) {
        if (validMove(theRow, theCol)) {
            characterSpot.setHasPlayer(false);
            characterSpot = myMaze[theRow][theCol]; //this method means we have confirmed
            myX = theCol;                           // the direction we want to move in
            myY = theRow;
            characterSpot.setHasPlayer(true);
            myMaze[myY][myX].setStatus(Cell.RoomStatus.UNLOCKED);
        }
    }
    public boolean validMove(final int theRow, final int theCol) {
        return theRow >= 0 && theRow < myMaze.length //checking if this is in bounds
                && theCol >= 0 && theCol < myMaze[theRow].length
                && myMaze[theRow][theCol].getMyStatus() != Cell.RoomStatus.SEALED;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < myMaze.length; row++) {
            s.append("\n");
            for (int col = 0; col < myMaze[row].length; col++) {
                s.append(myMaze[row][col].toString()).append(" ");
            }
        }
        return s.toString();
    }


    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }

    public int row()
    {
        return this.myMaze.length;
    }
    public int column()
    {
        return this.myMaze.length;
    }

    public Cell.RoomStatus getStatus(int theRow, int theCol)
    {
        return this.myMaze[theRow][theCol].getMyStatus();
    }

    public Question getQuestion(int theRow, int theCol) {
        if (validMove(theRow, theCol)) {
            return myMaze[theRow][theCol].getMyQuestion();
        } else {
            return null;
        }
    }

    public Room getRoom(int theRow, int theCol) {
        if (validMove(theRow, theCol)) {
            return myMaze[theRow][theCol];
        } else {
            return null;
        }
    }

    public Room getCharacterSpot() {
        return characterSpot;
    }
}
