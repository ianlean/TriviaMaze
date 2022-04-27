package TriviaMaze;

public class Room extends Cell{
    private final String myQuestion;

    private final String myAnswer;
    private final boolean hasPlayer;
    private RoomStatus myStatus;
    public Room(String theQuestion,String theAnswer ,boolean thePlayer) {
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

    public String getMyQuestion() {
        return myQuestion;
    }

    public boolean getHasPlayer() {
        return hasPlayer;
    }

    @Override
    public String toString() {
        return "|_|";
    }
}
