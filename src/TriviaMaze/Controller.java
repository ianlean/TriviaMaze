package TriviaMaze;
/*
 * Assignment: Course Project "Trivia Maze"
 *
 * Instructor: Tom Capaul
 *
 * */
import java.io.Serializable;
import java.sql.SQLException;
/**
 * This is a class called "Controller", that makes the updates on the trivia maze
 * and change the state of the rooms
 *
 * @author Bohan Yang, Ian Mclean, Qinyu Tao
 * @version May 22nd 2022
 */
public class Controller implements Serializable
{
    /** trivia maze instance*/
    private TriviaMaze myGameMaze;

    /**
     * Constructor controller by instantiating with a certain size
     *
     * @param theSize, the maze size, row and column
     * @throws SQLException
     * */
    public Controller(final int theSize) throws SQLException
    {
        this.myGameMaze = new TriviaMaze(theSize);
    }

    public TriviaMaze getGameMaze() {
        return this.myGameMaze;
    }

    public void askDirection(final String theDirection)
    {
        if (theDirection.equalsIgnoreCase("w"))
        {
            this.myGameMaze.changeDirection(this.myGameMaze.getMyY(), this.myGameMaze.getMyX() - 1);
        }
        else if (theDirection.equalsIgnoreCase("e"))
        {
            this.myGameMaze.changeDirection(this.myGameMaze.getMyY(), this.myGameMaze.getMyX() + 1);
        }
        else if (theDirection.equalsIgnoreCase("s"))
        {
            this.myGameMaze.changeDirection(this.myGameMaze.getMyY() + 1, this.myGameMaze.getMyX());
        }
        else if (theDirection.equalsIgnoreCase("n"))
        {
            this.myGameMaze.changeDirection(this.myGameMaze.getMyY() - 1, this.myGameMaze.getMyX());
        }
        System.out.println(this.myGameMaze);
    }

    public Room findRoom(final String theDirection)
    {
        Room room = null;
        if (theDirection.equalsIgnoreCase("w"))
        {
            room = this.myGameMaze.getRoom(this.myGameMaze.getMyY(), this.myGameMaze.getMyX() - 1);
        }
        else if (theDirection.equalsIgnoreCase("e"))
        {
            room = this.myGameMaze.getRoom(this.myGameMaze.getMyY(), this.myGameMaze.getMyX() + 1);
        }
        else if (theDirection.equalsIgnoreCase("s"))
        {
            room = this.myGameMaze.getRoom(this.myGameMaze.getMyY() + 1, this.myGameMaze.getMyX());
        }
        else if (theDirection.equalsIgnoreCase("n"))
        {
            room = this.myGameMaze.getRoom(this.myGameMaze.getMyY() - 1, this.myGameMaze.getMyX());
        }
        return room;
    }

    public void setMyGameMaze(TriviaMaze theMaze) {this.myGameMaze = theMaze;}
}