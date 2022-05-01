import TriviaMaze.TriviaMaze;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TriviaMaze gameMaze = new TriviaMaze();
        Scanner keyBoard = new Scanner(System.in);
        System.out.println(gameMaze);
        while(true) {
            askDirection(gameMaze);
            System.out.println(gameMaze);
        }
    }

    public static void askDirection(TriviaMaze theMaze) {
        boolean moved = false;
        promptDirection();
        Scanner scan = new Scanner(System.in);
        String direction = scan.next();
        while (!moved) {
            if (direction.equalsIgnoreCase("w")) {
                moved = theMaze.move(theMaze.getMyY(), theMaze.getMyX() - 1);

            } else if (direction.equalsIgnoreCase("e")) {
                moved = theMaze.move(theMaze.getMyY(), theMaze.getMyX() + 1);

            } else if (direction.equalsIgnoreCase("s")) {
               moved = theMaze.move(theMaze.getMyY() + 1, theMaze.getMyX());

            } else if (direction.equalsIgnoreCase("n")) {
                moved = theMaze.move(theMaze.getMyY() - 1, theMaze.getMyX());

            }
            if (!moved) {
                System.out.println("Not a valid direction try again");
                direction = scan.next();
            }
        }
    }

    public static void promptDirection() {
        System.out.println("---------------------------");
        System.out.println("CHOOSE YOUR DIRECTION:\n");
        System.out.println("N, E, S, W\n");
    }
}
