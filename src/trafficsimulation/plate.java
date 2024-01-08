package trafficsimulation;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import trafficsimulation.Road;

public class plate {

    int x;
    int y;
    boolean isDragging = false;

    Image myImage;

    public plate(int x, int y, Road road) {
        this.x = x;
        this.y = y;

        try {
            myImage = ImageIO.read(new File("levha.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        plateMouseListener mouseListener = new plateMouseListener(this, road);
        road.addMouseListener(mouseListener);
        road.addMouseMotionListener(mouseListener);

    }

    public void setImage(String imagePath) {
        try {
            myImage = ImageIO.read(new File("levha.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void paintMe(Graphics g) {
        g.drawImage(myImage, x, y, null);
    }

}
