package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AraziAraci extends Vehicle {

    Image myImage;

    public AraziAraci(int newx, int newy, Road road, int speed) {
        super(newx, newy, road,speed);
        width = 60;
        height = 30;
     // speed = 8;        
        fuelLevel = 80.0;

        try {
            myImage = ImageIO.read(new File("araba.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public void setImage(String imagePath) {
        try {
            myImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void paintMe(Graphics g) {
        g.drawImage(myImage, x, y, null);

        int barWidth = (int) (width * (fuelLevel / 100));  
        g.setColor(Color.GREEN);  
        g.fillRect(x, y + height, barWidth, 5); 

        
        g.setColor(Color.BLACK);
        g.drawRect(x, y + height, width, 5);
        g.drawString("" + plaka, x, y - 10);

    }

}
