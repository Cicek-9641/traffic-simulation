package trafficsimulation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import trafficsimulation.Road;

public class Levha {

    int x;
    int y;
    boolean isDragging = false;
    Image myImage;

    public Levha(int x, int y, Road road) {
        this.x = x;
        this.y = y;

        try {
            myImage = ImageIO.read(new File("levha.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        LevhaMouseListener mouseListener = new LevhaMouseListener(this, road);
        road.addMouseListener(mouseListener);
        road.addMouseMotionListener(mouseListener);

    }

 

    public void paintMe(Graphics g) {
        g.drawImage(myImage, x, y, null);
    }

    Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
