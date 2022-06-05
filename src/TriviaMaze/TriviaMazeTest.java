package TriviaMaze;

import GUI.Frame;
import TriviaMaze.*;
import org.junit.*;

import java.sql.*;

import static org.junit.Assert.*;


public class TriviaMazeTest
{
    // private TriviaMaze myMaze = new TriviaMaze(8);
    private final Controller controller = new Controller(Frame.MAZE_SIZE);

    public TriviaMazeTest() throws SQLException {
    }

    @Test
    public void testGenerateMaze()
    {   //this should not be deprecated, we  are assuring that our arrays are
        //initializing properly
        assertEquals(new TriviaMaze(8).getMaze(),
                new int[][]{{1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 0, 0, 0, 0, 1},
                            {1, 0, 1, 1, 0, 0, 0, 1},
                            {1, 0, 0, 1, 1, 0, 0, 1},
                            {1, 0, 0, 0, 1, 1, 0, 1},
                            {1, 0, 0, 0, 0, 1, 1, 1},
                            {1, 0, 0, 0, 0, 0, 1, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1}});
    }
    @Test // making sure status' work as expected
    public void testStatus()
    {
        assertEquals(Cell.RoomStatus.UNLOCKED, controller.getGameMaze().getStatus(0, 0));
        assertEquals(Cell.RoomStatus.LOCKED, controller.getGameMaze().getStatus(2, 1));
        assertEquals(Cell.RoomStatus.LOCKED, controller.getGameMaze().getStatus(5, 7));
        assertEquals(Cell.RoomStatus.SEALED, controller.getGameMaze().getStatus(6, 1));
        assertEquals(Cell.RoomStatus.SEALED, controller.getGameMaze().getStatus(4, 6));
    }
    @Test//proving our SQLite table is not null, seems trivial but I assure you it has been a issue
    public void testRoomQuestion() throws SQLException {
        Database db = new Database();
        Connection myConn= DriverManager.getConnection("jdbc:sqlite::resource:trivia.db");
        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("SELECT * FROM truefalse ;");
        assertNotEquals(null, myRs);
    }

    @Test
    public void testMovementInMaze() {
        TriviaMaze t = new TriviaMaze(Frame.MAZE_SIZE);
        t.changeDirection(3,3);
        assertEquals(3,t.getMyX());
        assertEquals(3,t.getMyY());
    }

    @Test
    public void testGameOver() throws SQLException {
        TriviaMaze t = new TriviaMaze(Frame.MAZE_SIZE);
        t.changeDirection(t.getMySize()-1,t.getMySize()-1);
        assertTrue(t.isGameOver());
    }
    @Test
    public void testInvalidMove() {
        TriviaMaze t = new TriviaMaze(Frame.MAZE_SIZE);
        t.changeDirection(-1,-2);//the TriviaMaze should interpret this as bad input
        assertEquals(0,t.getMyX());//coordinates should not have changed
        assertEquals(0,t.getMyY());
    }
}
