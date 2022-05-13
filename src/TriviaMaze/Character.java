package TriviaMaze;

import java.awt.*;
import java.io.Serializable;

public class Character implements Serializable
{
    private static long serialVersionUID=4874303903866067117L;
    private int myX,myY;
    private TriviaMaze myMaze;
    private Point myCurrentLocation;

    public Character() {
        myX=0;
        myY=0;
    }

    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }



//    public Character(TriviaMaze theMaze, int x, int y)
//    {
//        this.myMaze = theMaze;
//        this.myCurrentLocation = new Point(x, y);
//    }
}

