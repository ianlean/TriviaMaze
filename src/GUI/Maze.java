package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import TriviaMaze.Cell;
import TriviaMaze.TriviaMaze;
//import TriviaMaze.*;

public class Maze extends JPanel
{
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    private TriviaMaze myMaze;
    private BufferedImage myGreenFlag;
    private BufferedImage myIcon;
    private TexturePaint myIconTexture;
    private TexturePaint myGreenFlagTexture;

   private Character myCharacter;
    //private static Room characterSpot;

    private int myXCoord = 0;
    private int myYCoord = 0;

    Maze(TriviaMaze theMaze) throws IOException
    {
        this.myMaze = theMaze;
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.myIcon = ImageIO.read(new File("src/GUIPictures/Icon.png"));
        this.myGreenFlag = ImageIO.read(new File("src/GUIPictures/GreenFlag.png"));
        this.myIconTexture = new TexturePaint
                (this.myIcon, new Rectangle(0, 0, 50, 50));
        this.myGreenFlagTexture = new TexturePaint
                (this.myGreenFlag, new Rectangle(0, 0, 50, 50));
    }
    private void drawMaze(Graphics theG)
    {
        Graphics2D graphics2D = (Graphics2D) theG;

        for (int i = 0; i < this.myMaze.row(); i++)
        {
            for (int j = 0; j < this.myMaze.column(); j++)
            {
                Rectangle2D rectangle =
                        new Rectangle2D.Double(i * 55, j * 55, 55, 55);
                if (this.myMaze.getStatus(j, i) == Cell.RoomStatus.UNLOCKED)
                {
                    graphics2D.setColor(Color.WHITE);
                }
                else if (this.myMaze.getStatus(j, i) == Cell.RoomStatus.SEALED)
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
//        graphics2D.setPaint(this.myIconTexture);
//        graphics2D.setPaint(this.myGreenFlagTexture);
        graphics2D.fillRect(50, 50, 50, 50);
        graphics2D.fillRect(50 * (this.myMaze.row() - 1),
                50 * (this.myMaze.column() - 1), 55, 55);
    }
    public void paintComponent(Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        drawMaze(theGraphics);
    }

    public void incrementX() {
        this.myXCoord+=55;
    }

    public void decrementX() {
        this.myXCoord-=55;
    }

    public void incrementY() {
        this.myYCoord+=55;
    }

    public void decrementY() {
        this.myYCoord-=55;
    }

    public Character getCharacter(){
        return myCharacter;
    }

    public void setCharacter(Character theCharacter){
        myCharacter=theCharacter;
    }
//
//    public static Room getCharacterSpot() {
//        return characterSpot;
//    }
//    public static void setCharacterSpot(Room theCharactetSpot){
//        characterSpot=theCharactetSpot;
//
//    }

}
