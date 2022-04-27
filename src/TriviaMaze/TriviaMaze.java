package TriviaMaze;

public class TriviaMaze {

    private Room[][] myMaze = new Room[5][5];
    private int myX;

    private int myY;
    private Room characterSpot;
    public TriviaMaze() { // for developing purposes I am auto-filling rooms
        initializeMaze();
    }

    private void initializeMaze() {
        // we want the far left corner to be where the player starts
        characterSpot = myMaze[0][0] = new Room("What is 1+1?", "2", true);
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
        if (validMove(theRow, theCol)) {
            characterSpot = myMaze[theRow][theCol];
            myX = theCol;
            myY = theRow;
            return true;
        } else {
            return false;
        }
    }

    private boolean validMove(final int theRow, final int theCol) {
        return theRow >= 0 && theRow < myMaze.length
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
