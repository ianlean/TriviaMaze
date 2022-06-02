package Test;

import TriviaMaze.*;
import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class TriviaMazeTest
{
    // private TriviaMaze myMaze = new TriviaMaze(8);
    private final Controller controller = new Controller(8);

    public TriviaMazeTest() throws SQLException {
    }

    @Test
    public void testGenerateMaze()
    {
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
    @Test
    public void testStatus()
    {
        assertEquals(Cell.RoomStatus.UNLOCKED, controller.getGameMaze().getStatus(0, 0));
        assertEquals(Cell.RoomStatus.LOCKED, controller.getGameMaze().getStatus(2, 1));
        assertEquals(Cell.RoomStatus.LOCKED, controller.getGameMaze().getStatus(5, 7));
        assertEquals(Cell.RoomStatus.SEALED, controller.getGameMaze().getStatus(6, 1));
        assertEquals(Cell.RoomStatus.SEALED, controller.getGameMaze().getStatus(4, 6));
    }
    @Test
    public void testRoomQuestion()
    {

    }

}
