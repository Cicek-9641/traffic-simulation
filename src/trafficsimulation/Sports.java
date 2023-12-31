package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sports extends Vehicle {
	
	Image myImage;
	
 
    public Sports(int newx, int newy, Road road) {
    	super(newx,newy,road);
		width = 40;
		height = 20;
		speed = 100;
        fuelLevel = 80.0;

		try {
		myImage = ImageIO.read(new File("car.png"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	 
	 public void setSpeed(int newSpeed) {
	         speed = newSpeed;
    }
	public void paintMe(Graphics g) {
//		g.setColor(Color.RED);
//		g.fillRect(x, y, width, height);
		g.drawImage(myImage,x,y,null);
		
	}

}
