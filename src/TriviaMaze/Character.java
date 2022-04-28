package TriviaMaze;

import java.awt.*;

public class Character
{
    private TriviaMaze myMaze;
    private Point myCurrentLocation;

    public Character(TriviaMaze theMaze, int x, int y)
    {
        this.myMaze = theMaze;
        this.myCurrentLocation = new Point(x, y);
    }
}

