public class Room {
    private final String myQuestion;
    private final boolean hasPlayer;
    private final boolean isWall;
    public Room(String theQuestion, boolean thePlayer) {
        myQuestion = theQuestion;
        hasPlayer = thePlayer;
        isWall = false;
    }

    public Room() {
        // This is constructor is for if we want
        // a room to be a wall
        isWall = true;
        hasPlayer = false;
        myQuestion = null;
    }

    public String getMyQuestion() {
        return myQuestion;
    }

    public boolean getHasPlayer() {
        return hasPlayer;
    }
}
