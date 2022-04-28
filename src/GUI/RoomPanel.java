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
    Timer myT = new Timer(5, this);
    private double myX = 220, myY = 220, myVelX = 0, myVelY = 0;
    private BufferedImage myWallImage;
    private BufferedImage myDoNotEnterImage;
    private BufferedImage myIconImage;
    private BufferedImage myPathImage;

    private TexturePaint myWallTexture;
    private TexturePaint myDoNotEnterTexture;
    private TexturePaint myIconTexture;
    private TexturePaint myPathTexture;

    public RoomPanel() throws IOException
    {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.myT.start();
        this.myIconImage = ImageIO.read(new File("src/GUIPictures/Husky.png"));
        loadImages();
        loadTexture();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
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
        this.myPathTexture = new TexturePaint
                (this.myPathImage, new Rectangle(50, 50, 50, 50));
        this.myDoNotEnterTexture = new TexturePaint
                (this.myDoNotEnterImage, new Rectangle(180, 0, 50, 50));
    }
//    private void draw(final Graphics theGraphic)
//    {
//        Graphics2D graphics2D = (Graphics2D) theGraphic;
//        graphics2D.drawImage(this.myIconImage, (int) myX, (int) myY, null);
//        drawWall(graphics2D);
//        drawIcon(graphics2D);
//    }
    private void drawWall(final Graphics2D theWall)
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
    @Override
    public void paintComponent(Graphics theGraphics)
    {
        super.paintComponent(theGraphics);
        Graphics2D g2 = (Graphics2D) theGraphics;
        drawWall(g2);
        g2.drawImage(this.myIconImage, (int) myX, (int) myY, null);
    }
    public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (this.myX < 50 || this.myX > 450) this.myVelX = -this.myVelX;
        if (this.myY < 50 || this.myY > 450) this.myVelY = -this.myVelY;
        repaint();
        this.myX += this.myVelX;
        this.myY += this.myVelY;
    }
    public void up()
    {
        this.myVelY = -1.5;
        this.myVelX = 0;
    }
    public void down()
    {
        this.myVelY = 1.5;
        this.myVelX = 0;
    }
    public void left()
    {
        this.myVelY = 0;
        this.myVelX = -1.5;
    }
    public void right()
    {
        this.myVelY = 0;
        this.myVelX = 1.5;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) up();
        if (code == KeyEvent.VK_DOWN) down();
        if (code == KeyEvent.VK_LEFT) left();
        if (code == KeyEvent.VK_RIGHT) right();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN ||
                code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT)
        {
            this.myVelY = 0;
            this.myVelX = 0;
        }
    }
}
