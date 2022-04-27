package TriviaMaze;

public class Cell
{
    public enum RoomStatus
    {
        UNLOCKED, LOCKED, SEALED
    }
    private RoomStatus myStatus;
    public Cell()
    {
        this.myStatus = RoomStatus.SEALED;
    }
    public void setStatus(RoomStatus theStatus)
    {
        this.myStatus = theStatus;
    }
}
