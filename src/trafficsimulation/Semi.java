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

	public Semi(int newx, int newy, Road road) {
		super(newx,newy,road);
		width = 120;
		height = 40;
		speed = 50;
		fuelLevel = 80.0;
		
		try {
			myImage = ImageIO.read(new File("kamyon.jpg"));
			}catch(IOException ex) {
				ex.printStackTrace();
			}
	}
	
    public void slowDown(int slowDownAmount) {
        speed -= slowDownAmount;
     	System.out.println(speed);

        if (speed < 0) {
         speed = 0;
     	System.out.println(speed);

     	System.out.println("pres");

     	      }
    } 

	public void paintMe(Graphics g) {
	    //g.setColor(Color.BLUE);
		//g.fillRect(x, y, width, height);
		g.drawImage(myImage,x,y,null);

	}

	public void hizlanButton(int hizlanAmount) {
		  speed += hizlanAmount;
	        if (speed < 0) {
	         speed = 0;
	        }		
	}
 


}
