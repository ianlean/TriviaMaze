package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class RoomPanel extends JPanel implements ActionListener, KeyListener
{
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;

    private BufferedImage myWallImage;
    private BufferedImage myDoNotEnterImage;
    private BufferedImage myIconImage;
    private BufferedImage myPathImage;

//    private TexturePaint myWallTexture;
//    private TexturePaint myDoNotEnterTexture;
//    private TexturePaint myIconTexture;
//    private TexturePaint myPathTexture;
    RoomPanel() throws IOException
    {
        loadImages();

    }
    private void loadImages() throws IOException
    {
        this.myWallImage = ImageIO.read(new File("src/GUIPictures/wall.png"));
        this.myDoNotEnterImage = ImageIO.read(new File("src/GUIPictures/doNotEnter.png"));
        this.myIconImage = ImageIO.read(new File("src/GUIPictures/Husky.png"));
        this.myPathImage = ImageIO.read(new File("src/GUIPictures/path.png"));
    }
    private void draw(Graphics theGraphic)
    {
        Graphics2D graphics2D = (Graphics2D) theGraphic;
        drawWall(graphics2D);
    }
    private void drawWall(Graphics2D theWall)
    {
        theWall.setPaint((Paint) myWallImage);
        theWall.fillRect(0,0,100, 100);
        theWall.fillRect(100,0,100, 100);
        theWall.fillRect(200,0,100, 100);
        theWall.fillRect(300,0,100, 100);
        theWall.fillRect(500,0,100, 100);
        theWall.fillRect(600,0,100, 100);
        theWall.fillRect(700,0,100, 100);
        theWall.fillRect(0,100,100, 100);
        theWall.fillRect(0,200,100, 100);
        theWall.fillRect(0,300,100, 100);
        theWall.fillRect(0,500,100, 100);
        theWall.fillRect(0,600,100, 100);
        theWall.fillRect(0,700,100, 100);
        theWall.fillRect(0,700,100, 100);
        theWall.fillRect(100,700,100, 100);
        theWall.fillRect(200,700,100, 100);
        theWall.fillRect(300,700,100, 100);
        theWall.fillRect(500,700,100, 100);
        theWall.fillRect(600,700,100, 100);
        theWall.fillRect(700,700,100, 100);
        theWall.fillRect(700,0,100, 100);
        theWall.fillRect(700,100,100, 100);
        theWall.fillRect(700,200,100, 100);
        theWall.fillRect(700,300,100, 100);
        theWall.fillRect(700,500,100, 100);
        theWall.fillRect(700,600,100, 100);
        theWall.fillRect(700,700,100, 100);
    }
    @Override
    public void paintComponent(Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        draw(theGraphics);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
