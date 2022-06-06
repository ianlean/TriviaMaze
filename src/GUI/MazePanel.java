package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import TriviaMaze.Cell;
import TriviaMaze.TriviaMaze;

public class MazePanel extends JPanel implements Serializable
{

    private final TriviaMaze myMaze;
    private final BufferedImage myDoor;
    private final BufferedImage myIcon;

    private Character myCharacter;

    private int myXCoord = Frame.myController.getGameMaze().getMyX() * 55;
    private int myYCoord = Frame.myController.getGameMaze().getMyY() * 55;

    MazePanel(TriviaMaze theMaze) throws IOException
    {
        this.myMaze = theMaze;
        this.setSize(Frame.getMazeSize()*55, Frame.getMazeSize()*55);
        this.myIcon = ImageIO.read(getClass().getResource("/GUIPictures/Icon.png"));
        this.myDoor = ImageIO.read(getClass().getResource("/GUIPictures/door.jpeg"));
        new TexturePaint(this.myIcon, new Rectangle(0, 0, 50, 50));
        new TexturePaint(this.myDoor, new Rectangle(0, 0, 50, 50));
    }
    private void drawMaze(Graphics theG)
    {
        this.myXCoord = Frame.myController.getGameMaze().getMyX() * 55;
        this.myYCoord = Frame.myController.getGameMaze().getMyY() * 55;
        Graphics2D graphics2D = (Graphics2D) theG;

        Image resizedDoor = null;
        for (int i = 0; i < Frame.myController.getGameMaze().row(); i++)
        {
            for (int j = 0; j < Frame.myController.getGameMaze().column(); j++)
            {
                Rectangle2D rectangle =
                        new Rectangle2D.Double(i * 55, j * 55, 55, 55);
                if (Frame.myController.getGameMaze().getStatus(j, i) == Cell.RoomStatus.UNLOCKED)
                {
                    graphics2D.setColor(Color.WHITE);
                }
                else if (Frame.myController.getGameMaze().getStatus(j, i) == Cell.RoomStatus.SEALED)
                {
                    graphics2D.setColor(Color.BLACK);
                }
                else
                    graphics2D.setColor(Color.BLUE);
                graphics2D.fill(rectangle);
                graphics2D.setPaint(Color.ORANGE);
                graphics2D.draw(rectangle);
               resizedDoor = this.myDoor.getScaledInstance(55, 55, Image.SCALE_DEFAULT);
                
                Image resizeIcon = this.myIcon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                graphics2D.drawImage(resizeIcon, this.myXCoord, this.myYCoord, null);
            }
        }
        graphics2D.fillRect(55 * (Frame.myController.getGameMaze().row() - 1),
                55 * (Frame.myController.getGameMaze().column() - 1), 55, 55);
        graphics2D.drawImage(resizedDoor, (Frame.getMazeSize()-1)*55, (Frame.getMazeSize()-1)*55, null);
    }
    public void paintComponent(Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        this.drawMaze(theGraphics);
    }

    public void incrementX() {
        this.myXCoord = Frame.myController.getGameMaze().getMyX() * 55;
    }

    public void decrementX() {
        this.myXCoord = Frame.myController.getGameMaze().getMyX() * 55;
    }

    public void incrementY() {
        this.myYCoord =Frame.myController.getGameMaze().getMyY() * 55;
    }

    public void decrementY() {
        this.myYCoord=Frame.myController.getGameMaze().getMyY() * 55;
    }

}