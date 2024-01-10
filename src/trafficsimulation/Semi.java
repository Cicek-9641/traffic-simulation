package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Semi extends Vehicle {

    Image myImage;

    public Semi(int newx, int newy, Road road,int speed) {
        super(newx, newy, road,speed);
        width = 120;
        height = 40;
       // speed = 5;
        fuelLevel = 90.0;

        try {
            myImage = ImageIO.read(new File("kamyon.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paintMe(Graphics g) {
        g.drawImage(myImage, x, y, null);

        int barWidth = (int) (width * (fuelLevel / 100));  
        g.setColor(Color.BLUE);  
        g.fillRect(x, y + height, barWidth, 5); 

        
        g.setColor(Color.BLACK);
        g.drawRect(x, y + height, width, 5);
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

}
