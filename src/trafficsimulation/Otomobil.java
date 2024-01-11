package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Otomobil extends Vehicle {

    Image myImage;

    public Otomobil(int newx, int newy, Road road,int speed) {
        super(newx, newy, road,speed);
        width = 40;
        height = 20;       
        fuelLevel = 100.0;

        try {
            myImage = ImageIO.read(new File("carr.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }
    
    public void paintMe(Graphics g) {
    	
         g.drawImage(myImage, x, y, null);

         g.setColor(Color.WHITE);
         g.drawString("" + plaka, x, y - 10);

         int barWidth = (int) (width * (fuelLevel / 100));
         g.setColor(Color.YELLOW);
         g.fillRect(x, y + height, barWidth, 5);

         g.setColor(Color.BLACK);
         g.drawRect(x, y + height, width, 5);
    }

//    public void paintMe(Graphics g) {
//        g.drawImage(myImage, x, y, null);
//
//        int barWidth = (int) (width * (fuelLevel / 100));  
//        g.setColor(Color.YELLOW);  
//        g.fillRect(x, y + height, barWidth, 5); 
//
//        
//        g.setColor(Color.BLACK);
//        g.drawRect(x, y + height, width, 5);
//    }

}
