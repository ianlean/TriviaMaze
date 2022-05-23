

package TriviaMaze;

import TriviaMaze.Question.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class TriviaMaze implements Serializable {

    private final GenerateQuestion generator;
    private final Room[][] myMaze;
    private int myX;
    private int myY;
    private static Room characterSpot;

    private boolean gameOver = false;

    TriviaMaze(final int theSize) { // for developing purposes I am auto-filling rooms
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

    void changeDirection(final int theRow, final int theCol) {
        if (validMove(theRow, theCol)) {
            characterSpot.setHasPlayer(false);
            characterSpot = myMaze[theRow][theCol]; //this method means we have confirmed
            myX = theCol;                           // the direction we want to move in
            myY = theRow;
            characterSpot.setHasPlayer(true);
            myMaze[myY][myX].setStatus(Cell.RoomStatus.UNLOCKED);
        }
        if (myX == 7 && myY == 7) {
            gameOver = true;
        }
    }
    boolean validMove(final int theRow, final int theCol) {
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

    public boolean isGameOver() {
        return gameOver;
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

   public Room getRoom(int theRow, int theCol) {
        return myMaze[theRow][theCol];
    }

    public int[] getSaveLocation(){
        int[] saveLocation = new int[2];
        saveLocation[0]=getMyX();
        saveLocation[1]=getMyY();
        return saveLocation;
    }
    public void setSaveLocation(int[] saveLocation){

        myX=saveLocation[0];
        myY=saveLocation[1];
    }





}