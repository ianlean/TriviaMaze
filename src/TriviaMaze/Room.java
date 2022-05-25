package TriviaMaze;
/*
 * Assignment: Course Project "Trivia Maze"
 *
 * Instructor: Tom Capaul
 *
 * */
import TriviaMaze.Question.Question;

import java.io.Serial;
import java.io.Serializable;

/**
 * This is a class called "Room". It is a subclass of cell that
 * The room will contain a question
 *
 * @author Bohan Yang, Ian Mclean, Qinyu Tao
 * @version May 21st 2022
 */
public class Room extends Cell implements Serializable
{
    /** the question that will come along when entering this room. */
    private final Question myQuestion;

    /** the status that the room in the maze contains. */
    private RoomStatus myStatus;

    private boolean hasPlayer;

    /**
     * Construct the room with a question and set initial status of room to lock
     *
     * @param theQuestion, the question that is pulled from database
     * */
//    protected Room(final Question theQuestion)
//    {
//        this.myQuestion = theQuestion;
//        this.myStatus = RoomStatus.LOCKED;
//    }
    public Room(final Question theQuestion, final boolean thePlayer) {
        myQuestion = theQuestion;
        hasPlayer = thePlayer;
        myStatus = RoomStatus.LOCKED;
    }
    public void setHasPlayer(final boolean thePlayer) {
        this.hasPlayer = thePlayer;
    }
    /**
     * a getter method to return the question that the room contains
     *
     * @return the question
     * */
    public Question getMyQuestion()
    {
        return this.myQuestion;
    }

    /**
     * a getter method to return the room status that either be
     * locked, unlocked, or sealed
     *
     * @return the status of that room
     * */
    public RoomStatus getMyStatus() {return this.myStatus;}

    /**
     * a setter method to set the status to a room
     *
     * @param theStatus, the status that is going to assign to the room
     * */
    public void setStatus(final RoomStatus theStatus)
    {
        this.myStatus = theStatus;
    }

    /**
     * Unlock a room
     * */
    public void unlock()
    {
        if (this.myStatus == RoomStatus.SEALED)
        {
            throw new RuntimeException("Attempted to unlock a sealed door");
        }
        else if(this.myStatus == RoomStatus.UNLOCKED)
        {
            throw new RuntimeException("Room is already unlocked");
        }
        else
        {
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
