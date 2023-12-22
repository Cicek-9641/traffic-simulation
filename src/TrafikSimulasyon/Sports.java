package TrafikSimulasyon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sports extends Vehicle {
	
	Image myImage;
	
	public Sports(int newx, int newy) {
		super(newx,newy);
		width = 40;
		height = 20;
		speed = 12;
		try {
		myImage = ImageIO.read(new File("car.png"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
    public void slowDown() {
         speed -= 1;
         if (speed < 0) {
         speed = 0;
        }
    }
	
	public void paintMe(Graphics g) {
//		g.setColor(Color.RED);
//		g.fillRect(x, y, width, height);
		g.drawImage(myImage,x,y,null);
		
	}

}
