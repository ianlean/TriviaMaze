package TriviaMaze;

import java.sql.*;
import java.util.Scanner;
import org.sqlite.SQLiteDataSource;

public class TriviaMaze {

    private final Room[][] myMaze;
    private int myX;
    private int myY;
    private Room characterSpot;
    private Connection conn;

    private final SQLiteDataSource ds = new SQLiteDataSource();

    public TriviaMaze(final int theSize) throws SQLException { // for developing purposes I am auto-filling rooms
        myMaze = new Room[theSize][theSize];
        generateMaze();
        ds.setUrl("jdbc:sqlite:identifier.sqlite");
        conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM tf";
        ResultSet rs = stmt.executeQuery(query);
        String name = rs.getString("theQuestion");
        int id = rs.getInt( "theQuestion");

        System.out.println(name +" " + id);
    }
    private void generateMaze()
    {
        for (int row = 0; row < this.myMaze.length; row++)
        {
            for (int col = 0; col < this.myMaze.length; col++)
            {
                this.myMaze[row][col] = new Room("what is 1+1", "2", false);
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
        characterSpot = myMaze[0][0] = new Room("What is 1+1?", "2", true);
        characterSpot.setHasPlayer(true);
        myMaze[0][0].unlock();
        myX = 0;
        myY = 0;
        myMaze[0][0].setStatus(Cell.RoomStatus.UNLOCKED);
    }

    public boolean move(final int theRow, final int theCol) {
        // we want to make sure this position can be moved to
        if (validMove(theRow, theCol) &&
                myMaze[theRow][theCol].getMyStatus() != Cell.RoomStatus.SEALED) {
            return checkQuestion(theRow, theCol);
        } else { // let the caller know it is not valid
            return false;
        }
    }

    public boolean checkQuestion(final int theRow, final int theCol) {
        boolean answeredCorrect; //we want to ask a question if the room is locked,
        if (myMaze[theRow][theCol].getMyStatus() ==
            Cell.RoomStatus.UNLOCKED || (answerQuestion(myMaze[theRow][theCol]))) {
            changeDirection(theRow, theCol);
            return true;
        } else {
            return false;
        }
    }
    private void changeDirection(final int theRow, final int theCol) {
        characterSpot.setHasPlayer(false);
        characterSpot = myMaze[theRow][theCol]; //this method means we have confirmed
        myX = theCol;                           // the direction we want to move in
        myY = theRow;
        characterSpot.setHasPlayer(true);
    }

    private boolean answerQuestion(final Room theRoom) {
        Scanner scan = new Scanner(System.in); //we want to make sure the user answers
                                                //answers the question correctly
        System.out.println(theRoom.getMyQuestion());
        if(scan.next().equalsIgnoreCase(theRoom.getMyAnswer())) {
            theRoom.unlock();
            System.out.println("Correct!");
            return true;
        }
        theRoom.seal();
        System.out.println("Sealed");
        return false;
    }

    private boolean validMove(final int theRow, final int theCol) {
        return theRow >= 0 && theRow < myMaze.length //checking if this is in bounds
                && theCol >= 0 && theCol < myMaze[theRow].length;
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

    public Cell.RoomStatus getStatus(int theX, int theY)
    {
        return this.myMaze[theX][theY].getMyStatus();
    }
}
