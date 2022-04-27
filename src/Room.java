public class Room {
    private final String myQuestion;

    private final String myAnswer;
    private final boolean hasPlayer;
    private final boolean isWall;
    public Room(String theQuestion,String theAnswer ,boolean thePlayer) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        hasPlayer = thePlayer;
        isWall = false;
    }

    public Room() {
        // This is constructor is for if we want
        // a room to be a wall
        isWall = true;
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
