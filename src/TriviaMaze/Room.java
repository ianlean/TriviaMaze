package TriviaMaze;

import TriviaMaze.Question.Question;
public class Room extends Cell
{
    private final Question myQuestion;
    private boolean hasPlayer;

    private RoomStatus myStatus;
    public Room(Question theQuestion, boolean thePlayer) {
        myQuestion = theQuestion;
        hasPlayer = thePlayer;
        myStatus = RoomStatus.LOCKED;
    }
    public Question getMyQuestion() {
        return myQuestion;
    }

    public void setHasPlayer(final boolean thePlayer) {
        this.hasPlayer = thePlayer;
    }

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
