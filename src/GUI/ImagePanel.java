package GUI;

import javax.swing.*;
import java.awt.*;

class ImagePanel extends JComponent {
    private Image myImage;
    public ImagePanel(Image theImage) {
        this.myImage = theImage;
    }
    @Override
    protected void paintComponent(Graphics theG) {
        super.paintComponent(theG);
        Graphics2D graphics2D = (Graphics2D) theG;
        Image backGround = this.myImage.getScaledInstance(1525, 1030, Image.SCALE_DEFAULT);
        graphics2D.drawImage(backGround, 0, 0, this);
    }
}
