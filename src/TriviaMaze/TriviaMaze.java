package TriviaMaze;
/*
* Assignment: Course Project "Trivia Maze"
*
* Instructor: Tom Capaul
*
* */

import static TriviaMaze.Cell.RoomStatus.*;

/**
 * This is a class called "TriviaMaze". It is part of the model
 * for the trivia maze game. It includes the Rooms at 2-D array
 * current location with a character in the spot.
 *
 * @author Bohan Yang, Ian Mclean, Qinyu Tao
 * @version May 20th 2022
 */

public class TriviaMaze
{
    /** GenerateQuestion class instance here for a room to generate questions from database */
    private final GenerateQuestion myGenerator;

    /** a 2-D array of rooms to form the maze */
    private final Room[][] myMaze;

    /** the current location x to keep tracking */
    private int myX;

    /** the current location y to keep tracking */
    private int myY;

    /** a room object but represents a character */
    private static Room myCharacterSpot;

    /** a boolean value to record if the game is over yet */
    private boolean myGameOver = false;

    private boolean myHasLost = false;

    /**
     * Construct the trivia maze by accepting the size that is passing in
     * and get ready to generate the questions
     *
     * @param theSize, the size that represents the row and column
     * */
    TriviaMaze(final int theSize) // for developing purposes I am auto-filling rooms
    {
        this.myMaze = new Room[theSize][theSize];
        this.myGenerator = new GenerateQuestion();
        generateMaze();
    }

    /**
     * Generating the 2-D maze by iterating each row and column that is filled with
     * an individual room, each room is instantiated with a question pulled randomly
     * from database and instantiate the route, the starting point.
     * */
    private void generateMaze()
    {
        for (int row = 0; row < this.myMaze.length; row++)
        {
            for (int col = 0; col < this.myMaze.length; col++)
            {
                this.myMaze[row][col] = new Room(this.myGenerator.generateRandomQuestion(), false);
                if (row == 0 || col == 0 ||
                row == this.myMaze.length - 1 || col == this.myMaze.length - 1 ||
                row - col == 0 || row - col == 1)
                {
                    this.myMaze[row][col].setStatus(LOCKED);
                }
                else
                {
                    this.myMaze[row][col].setStatus(SEALED);
                }
            }
        }
        myCharacterSpot = myMaze[0][0] = new Room(null, true);
        myCharacterSpot.setHasPlayer(true);
        this.myMaze[0][0].unlock();
        this.myX = 0;
        this.myY = 0;
        this.myMaze[0][0].setStatus(UNLOCKED);
    }

    /**
     * The new location that we are moving to
     *
     * @param theRow, the new row that we are moving to
     * @param theCol, the new column that we are moving to
     * */
    protected void changeDirection(final int theRow, final int theCol)
    {
        if (validMove(theRow, theCol))
        {
            myCharacterSpot.setHasPlayer(false);
            myCharacterSpot = this.myMaze[theRow][theCol]; //this method means we have confirmed
            this.myX = theCol;                           // the direction we want to move in
            this.myY = theRow;
            myCharacterSpot.setHasPlayer(true);
            this.myMaze[this.myY][this.myX].setStatus(UNLOCKED);
        }
        if (this.myX == 7 && this.myY == 7)
        {
            this.myGameOver = true;
        }
    }
    public boolean hasRoute()
    {
        int[][] maze = getMaze();
        return gameOverHelper(maze, this.myX, this.myY);
    }
    private boolean gameOverHelper(final int[][] theMaze, final int theX, final int theY)
    {
        if (!((theX >= 0 && theX < theMaze.length) && (theY >= 0 && theY < theMaze.length)) || theMaze[theX][theY] == 0)
        {
            return false;
        }
        if (theX == this.myMaze.length - 1 && theY == this.myMaze.length - 1)
        {
            return true;
        }
        theMaze[theX][theY] = 0;
        return gameOverHelper(theMaze, theX + 1, theY) || gameOverHelper(theMaze, theX - 1, theY)
                || gameOverHelper(theMaze, theX, theY + 1) || gameOverHelper(theMaze, theX, theY - 1);
    }
    private int[][] getMaze()
    {
        int[][] maze = new int[TriviaMaze.this.myMaze.length][TriviaMaze.this.myMaze.length];
        for (int i = 0; i < maze.length; i++)
        {
            for (int j = 0; j < maze.length; j++)
            {
                switch (getStatus(i, j))
                {
                    case UNLOCKED, LOCKED -> maze[j][i] = 1;
                    case SEALED -> maze[j][i] = 0;
                }
            }
        }
        return maze;
    }
    /**
     * Helper method to check if this is a valid move, which means contain the movement
     * with in a range and do not run into a locked or sealed room.
     *
     * @param theRow, the row that is current located
     * @param theCol, the column that is current located
     *
     * @return a boolean type to indicate a possible move or not
     * */
    private boolean validMove(final int theRow, final int theCol)
    {
        return theRow >= 0 && theRow < this.myMaze.length //checking if this is in bounds
                && theCol >= 0 && theCol < this.myMaze[theRow].length
                && this.myMaze[theRow][theCol].getMyStatus() != SEALED;
    }


    /**
     * a getter method to get information if the game is over
     * @return a boolean type to indicate if the game is over
     * */
    public boolean isGameOver()
    {
        return myGameOver;
    }

    public boolean isGameLost() { return hasRoute(); }

    /**
     * a getter method to get the current row;
     * @return the current row.
     * */
    protected int getMyX() {
        return myX;
    }
    /**
     * a getter method to get the current column;
     * @return the current column.
     * */
    protected int getMyY() {
        return myY;
    }
    /**
     * a getter method to get the total rows of this maze
     * @return the row size
     * */
    public int row()
    {
        return this.myMaze.length;
    }
    /**
     * a getter method to get the total column of this maze
     * @return the column size
     * */
    public int column()
    {
        return this.myMaze.length;
    }

    /**
     * a getter method to get the status of the room of a certain row and column in the maze
     *
     * @param theRow, the row
     * @param theCol, the column
     * @return the status of the room in the maze
     * */
    public Cell.RoomStatus getStatus(final int theRow, final int theCol)
    {
        return this.myMaze[theRow][theCol].getMyStatus();
    }

    /**
     * a getter method to get a room by its row and column in the maze
     *
     * @param theRow, the row
     * @param theCol, the column
     * @return the room in the maze location
     * */
    protected Room getRoom(final int theRow, final int theCol) {
            return this.myMaze[theRow][theCol];
    }

}
