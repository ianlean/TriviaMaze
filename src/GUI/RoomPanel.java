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

public class RoomPanel extends JPanel implements ActionListener, KeyListener
{
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;

    private BufferedImage myWallImage;
    private BufferedImage myDoNotEnterImage;
    private BufferedImage myIconImage;
    private BufferedImage myPathImage;

    private TexturePaint myWallTexture;
    private TexturePaint myDoNotEnterTexture;
    private TexturePaint myIconTexture;
    private TexturePaint myPathTexture;
    RoomPanel() throws IOException
    {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        loadImages();
        loadTexture();

    }
    private void loadImages() throws IOException
    {
        this.myWallImage = ImageIO.read(new File("src/GUIPictures/wall.png"));
        this.myDoNotEnterImage = ImageIO.read(new File("src/GUIPictures/doNotEnter.png"));
        this.myIconImage = ImageIO.read(new File("src/GUIPictures/Husky.png"));
        this.myPathImage = ImageIO.read(new File("src/GUIPictures/path.png"));


    }
    private void loadTexture()
    {
        this.myWallTexture = new TexturePaint
                (this.myWallImage, new Rectangle(0,0, 50,50));
        this.myIconTexture = new TexturePaint
                (this.myIconImage, new Rectangle(220, 220, 50, 50));
        this.myPathTexture = new TexturePaint
                (this.myPathImage, new Rectangle(50, 50, 50, 50));
        this.myDoNotEnterTexture = new TexturePaint
                (this.myDoNotEnterImage, new Rectangle(180, 0, 50, 50));
    }
    private void draw(Graphics theGraphic)
    {
        Graphics2D graphics2D = (Graphics2D) theGraphic;
        drawWall(graphics2D);
        drawIcon(graphics2D);
    }
    private void drawWall(Graphics2D theWall)
    {
        theWall.setPaint(this.myWallTexture);
        theWall.fillRect(0,0,50, 50);
        theWall.fillRect(50,0,50, 50);
        theWall.fillRect(100,0,50, 50);
        theWall.fillRect(150,0,50, 50);
        theWall.fillRect(300,0,50, 50);
        theWall.fillRect(350,0,50, 50);
        theWall.fillRect(400,0,50, 50);
        theWall.fillRect(450,0,50, 50);
        theWall.fillRect(0,50,50, 50);
        theWall.fillRect(0,100,50, 50);
        theWall.fillRect(0,150,50, 50);
        theWall.fillRect(0,300,50, 50);
        theWall.fillRect(0,350,50, 50);
        theWall.fillRect(0,400,50, 50);
        theWall.fillRect(0,450,50, 50);
        theWall.fillRect(50,450,50, 50);
        theWall.fillRect(100,450,50, 50);
        theWall.fillRect(150,450,50, 50);
        theWall.fillRect(300,450,50, 50);
        theWall.fillRect(350,450,50, 50);
        theWall.fillRect(400,450,50, 50);
        theWall.fillRect(450,0,50, 50);
        theWall.fillRect(450,50,50, 50);
        theWall.fillRect(450,100,50, 50);
        theWall.fillRect(450,150,50, 50);
        theWall.fillRect(450,300,50, 50);
        theWall.fillRect(450,350,50, 50);
        theWall.fillRect(450,400,50, 50);
        theWall.fillRect(450,450,50, 50);
    }
    private void drawIcon(Graphics2D theIcon)
    {
        theIcon.setPaint(this.myIconTexture);
        theIcon.fillRect(220, 220, 50, 50);
    }
    @Override
    public void paintComponent(Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        draw(theGraphics);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
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
