package TriviaMaze;

import java.util.Scanner;

public class TriviaMaze {

    private Room[][] myMaze = new Room[10][10];
    private int myX;
    private int myY;
    private Room characterSpot;
    public TriviaMaze() { // for developing purposes I am auto-filling rooms
        initializeMaze();
    }

    private void initializeMaze() {
        // we want the far left corner to be where the player starts
        characterSpot = myMaze[0][0] = new Room("What is 1+1?", "2", true);
        myMaze[0][0].unlock();
        myX = 0;
        myY = 0;
        for (int row = 0; row < myMaze.length; row++) { //we want to fill the maze with rooms to go in
            for (int col = 0; col < myMaze[row].length; col++) {
                if (row == 0 && col == 0) {continue;}
                myMaze[row][col] = new Room("What is 1+1?", "2", false);
            }
        }
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
        characterSpot = myMaze[theRow][theCol]; //this method means we have confirmed
        myX = theCol;                           // the direction we want to move in
        myY = theRow;
    }

    private boolean answerQuestion(final Room theRoom) {
        Scanner scan = new Scanner(System.in); //we want to make sure the user answers
                                                //answers the question correctly
        System.out.println(theRoom.getMyQuestion());
        if(scan.next().equalsIgnoreCase(theRoom.getMyAnswer())) {
            System.out.println("Correct!");
            return true;
        }
        theRoom.seal();
        System.out.println("WRONG! YOU STUPID IDIOT! This door is now sealed for good.");
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
                if (myMaze[row][col] == characterSpot) {
                    s.append(" C  ");
                } else {
                    s.append(myMaze[row][col].toString()).append(" ");
                }
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
}
