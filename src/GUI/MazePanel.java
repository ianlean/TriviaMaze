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
//import TriviaMaze.*;

public class MazePanel extends JPanel implements Serializable
{
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    private final TriviaMaze myMaze;
    private final BufferedImage myGreenFlag;
    private final BufferedImage myIcon;

    private Character myCharacter;
    //private static Room characterSpot;

    private int myXCoord = Frame.myController.getGameMaze().getMyX() * 55;
    private int myYCoord = Frame.myController.getGameMaze().getMyY() * 55;

    MazePanel(TriviaMaze theMaze) throws IOException
    {
        this.myMaze = theMaze;
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.myIcon = ImageIO.read(new File("src/GUIPictures/Icon.png"));
        this.myGreenFlag = ImageIO.read(new File("src/GUIPictures/GreenFlag.png"));
        TexturePaint myIconTexture = new TexturePaint
                (this.myIcon, new Rectangle(0, 0, 50, 50));
        TexturePaint myGreenFlagTexture = new TexturePaint
                (this.myGreenFlag, new Rectangle(0, 0, 50, 50));
    }
    private void drawMaze(Graphics theG)
    {
        myXCoord= Frame.myController.getGameMaze().getMyX() * 55;
        myYCoord=Frame.myController.getGameMaze().getMyY() * 55;
        Graphics2D graphics2D = (Graphics2D) theG;

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
                Image resizeFlag = this.myGreenFlag.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                graphics2D.drawImage(resizeFlag, 500, 500, null);
                Image resizeIcon = this.myIcon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                graphics2D.drawImage(resizeIcon, myXCoord, myYCoord, null);
            }
        }
        graphics2D.fillRect(55 * (Frame.myController.getGameMaze().row() - 1),
                55 * (Frame.myController.getGameMaze().column() - 1), 55, 55);
    }
    public void paintComponent(Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        drawMaze(theGraphics);
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