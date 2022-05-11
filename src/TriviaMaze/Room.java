package TriviaMaze;

import TriviaMaze.Question.Question;

import java.sql.Connection;
import java.sql.SQLException;
//import org.sqlite.SQLiteDataSource;
public class Room extends Cell
{
    private final Question myQuestion;

    private final String myAnswer;
    private boolean hasPlayer;

    private RoomStatus myStatus;
    public Room(Question theQuestion, String theAnswer , boolean thePlayer) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        hasPlayer = thePlayer;
        myStatus = RoomStatus.LOCKED;
    }

    public Room() {
        // This is constructor is for if we want
        // a room to be a wall
        myStatus = RoomStatus.SEALED;
        hasPlayer = false;
        myQuestion = null;
        myAnswer = null;
    }

    public Question getMyQuestion() {
        return myQuestion;
    }

    public boolean getHasPlayer() {
        return hasPlayer;
    }

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
