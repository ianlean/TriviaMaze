package TriviaMaze;

import java.io.Serializable;

public class Cell implements Serializable
{
    public enum RoomStatus
    {
        UNLOCKED, LOCKED, SEALED
    }
    protected RoomStatus myStatus;
//    public Cell()
//    {
//        this.myStatus = RoomStatus.SEALED;
//    }
    public void setStatus(RoomStatus theStatus)
    {
        this.myStatus = theStatus;
    }
}
