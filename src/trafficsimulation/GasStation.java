// GasStation.java
package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GasStation {

    int x;
    int y;
    boolean isDragging = false;

    Image myImage;

    public GasStation(int x, int y, Road road) {
        this.x = x;
        this.y = y;

        try {
            myImage = ImageIO.read(new File("benzinlik_1.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        GasStationMouseListener mouseListener = new GasStationMouseListener(this, road);
        road.addMouseListener(mouseListener);
        road.addMouseMotionListener(mouseListener);

    }

    
    
    public void setImage(String imagePath) {
        try {
            myImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    public void paintMe(Graphics g) {
        // g.setColor(Color.ORANGE);
        // g.fillRect(x, y, 50, 30);
        g.drawImage(myImage, x, y, null);
        
    }
}
