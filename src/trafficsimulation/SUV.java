package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SUV extends Vehicle {

    Image myImage;

    public SUV(int newX, int newY,Road road) {
        super(newX, newY, road);
        width = 60;
        height = 30;
        speed = 8;        
        fuelLevel = 80.0;

        try {
            myImage = ImageIO.read(new File("araba.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void setImage(String imagePath) {
        try {
            myImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void paintMe(Graphics g) {
        // g.setColor(Color.GREEN);
        // g.fillRect(x, y, width, height);
        g.drawImage(myImage, x, y, null);

    }
    
    
}
