package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SUV extends Vehicle {
	Image myImage;
 	public SUV(int newx, int newy,Road road) {
		super(newx,newy,road);
		width = 60;
		height = 30;
		speed = 80;
        fuelLevel = 80.0;

		try {
			myImage = ImageIO.read(new File("araba.jpg"));
			}catch(IOException ex) {
				ex.printStackTrace();
			}
	}

	  public void setSpeed(int newSpeed) {
 	         speed = newSpeed;
	    }	
	
	public void paintMe(Graphics g) {
	 
		g.drawImage(myImage,x,y,null);
	}
 

}
