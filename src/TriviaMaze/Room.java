package TriviaMaze;

import TriviaMaze.Question.Question;

public class Room
{
    public enum RoomStatus
    {
        UNLOCKED, LOCKED, SEALED
    }
    private RoomStatus status;
    private int x, y;
    private Question myQuestion;

    // private final String myAnswer;
    // private boolean hasPlayer;

    private RoomStatus myStatus;
    public Room()
    {
        setStatus(RoomStatus.SEALED);
    }
//    public Room(Question theQuestion, String theAnswer , boolean thePlayer) {
//        myQuestion = theQuestion;
//        myAnswer = theAnswer;
//        hasPlayer = thePlayer;
//        myStatus = RoomStatus.LOCKED;
//    }
    public Room(int x, int y)
    {
        this();
        this.setX(x);
        this.setY(y);
    }
    public Room(Question question, int x, int y)
    {
        this(x, y);
        this.myQuestion = question;
    }

//    public Room() {
//        // This is constructor is for if we want
//        // a room to be a wall
//        myStatus = RoomStatus.SEALED;
//        hasPlayer = false;
//        myQuestion = null;
//        myAnswer = null;
//    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Question getMyQuestion() {
        return myQuestion;
    }

    // public boolean getHasPlayer() {
//        return hasPlayer;
//    }

    public void setHasPlayer(final boolean thePlayer) {
        this.hasPlayer = thePlayer;
    }

    public String getMyAnswer() {return myAnswer;}

    public RoomStatus getMyStatus() {return myStatus;}

    public void setStatus(RoomStatus theStatus)
    {
        this.myStatus = theStatus;
    }

    public void seal() {this.setStatus(RoomStatus.SEALED);}

    public void unlock() {
        if (this.myStatus == RoomStatus.SEALED) {
            throw new RuntimeException("Attempted to unlock a sealed door");
        } else if(this.myStatus == RoomStatus.UNLOCKED) {
            throw new RuntimeException("Room is already unlocked");
        } else {
            this.myStatus = RoomStatus.UNLOCKED;
        }
    }

    @Override
    public String toString() {
        if (this.hasPlayer) {
            return "|C|";
        } else if (this.getMyStatus() == RoomStatus.SEALED) {
            return "|X|";
        } else if(this.getMyStatus() == RoomStatus.LOCKED) {
            return "|?|";
        } else {
            return "|_|";
        }
    }
}
