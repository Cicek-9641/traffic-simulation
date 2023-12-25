package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Semi extends Vehicle {

    Image myImage;

    public Semi(int newX, int newY, Road road) {
        super(newX, newY, road );
        width = 100;
        height = 40;
        speed = 5;       
        fuelLevel = 90.0;

        try {
            myImage = ImageIO.read(new File("kamyon.jpg"));
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
        //g.setColor(Color.BLUE);
        //g.fillRect(x, y, width, height);
        g.drawImage(myImage, x, y, null);

    }

}
