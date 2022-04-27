package TriviaMaze;

import java.awt.*;

public class Character
{
    private Maze myMaze;
    private Point myCurrentLocation;

    public Character(Maze theMaze, int x, int y)
    {
        this.myMaze = theMaze;
        this.myCurrentLocation = new Point(x, y);
    }
}

