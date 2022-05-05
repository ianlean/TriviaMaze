package TriviaMaze;

import java.util.Scanner;

public class Controller {
    private final TriviaMaze gameMaze;

    public Controller(TriviaMaze theMaze) {
        gameMaze = theMaze;
    }

    public boolean askDirection(String direction) {
        boolean moved = false;
        promptDirection();
        Scanner scan = new Scanner(System.in);

        if (direction.equalsIgnoreCase("w")) {
            moved = this.gameMaze.move(this.gameMaze.getMyY(), this.gameMaze.getMyX() - 1);

        } else if (direction.equalsIgnoreCase("e")) {
            moved = this.gameMaze.move(this.gameMaze.getMyY(), this.gameMaze.getMyX() + 1);

        } else if (direction.equalsIgnoreCase("s")) {
            moved = this.gameMaze.move(this.gameMaze.getMyY() + 1, this.gameMaze.getMyX());

        } else if (direction.equalsIgnoreCase("n")) {
            moved = this.gameMaze.move(this.gameMaze.getMyY() - 1, this.gameMaze.getMyX());

        }
        System.out.println(this.gameMaze);
        return moved;
    }

    public static void promptDirection() {
        System.out.println("---------------------------");
        System.out.println("CHOOSE YOUR DIRECTION:\n");
        System.out.println("N, E, S, W\n");
    }
}
