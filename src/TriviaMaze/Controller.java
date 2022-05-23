package TriviaMaze;

import java.io.Serializable;
import java.sql.SQLException;

public class Controller implements Serializable {
    private final TriviaMaze gameMaze;

    public Controller(int theSize) throws SQLException {
        gameMaze = new TriviaMaze(theSize);

    }

    public TriviaMaze getGameMaze() {
        return this.gameMaze;
    }

    public void askDirection(String direction) {
        promptDirection();

        if (direction.equalsIgnoreCase("w")) {
            this.gameMaze.changeDirection(this.gameMaze.getMyY(), this.gameMaze.getMyX() - 1);

        } else if (direction.equalsIgnoreCase("e")) {
            this.gameMaze.changeDirection(this.gameMaze.getMyY(), this.gameMaze.getMyX() + 1);

        } else if (direction.equalsIgnoreCase("s")) {
            this.gameMaze.changeDirection(this.gameMaze.getMyY() + 1, this.gameMaze.getMyX());

        } else if (direction.equalsIgnoreCase("n")) {
            this.gameMaze.changeDirection(this.gameMaze.getMyY() - 1, this.gameMaze.getMyX());

        }
        System.out.println(this.gameMaze);
    }

    public Room findRoom(String theDirection) {
        Room room = null;
        if (theDirection.equalsIgnoreCase("w")) {
            room = this.gameMaze.getRoom(this.gameMaze.getMyY(), this.gameMaze.getMyX() - 1);
        } else if (theDirection.equalsIgnoreCase("e")) {
            room = this.gameMaze.getRoom(this.gameMaze.getMyY(), this.gameMaze.getMyX() + 1);
        } else if (theDirection.equalsIgnoreCase("s")) {
            room = this.gameMaze.getRoom(this.gameMaze.getMyY() + 1, this.gameMaze.getMyX());

        } else if (theDirection.equalsIgnoreCase("n")) {
            room = this.gameMaze.getRoom(this.gameMaze.getMyY() - 1, this.gameMaze.getMyX());

        }
        return room;
    }

    public static void promptDirection() {
        System.out.println("---------------------------");
        System.out.println("CHOOSE YOUR DIRECTION:\n");
        System.out.println("N, E, S, W\n");
    }
}
