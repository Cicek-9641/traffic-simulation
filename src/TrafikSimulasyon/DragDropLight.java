package TrafikSimulasyon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import TrafikSimulasyon.Road;

public class DragDropLight {
	 int x;
	    int y;
	    boolean isDragging = false;

	    Image myImage;

	    public DragDropLight(int x, int y, Road road) {
	        this.x = x;
	        this.y = y;

	        try {
	            myImage = ImageIO.read(new File("stoppolice.png")); 
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
 
	        DragDropLightMouseListener mouseListener = new DragDropLightMouseListener(this, road);
	        road.addMouseListener(mouseListener);
	        road.addMouseMotionListener(mouseListener);

	    }
	 

	    public void setImage(String imagePath) {
	        try {
	            myImage = ImageIO.read(new File("levha.jpg"));
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	    }
	    public void changeImage(String imagePath) {
	        try {
	            myImage = ImageIO.read(new File("green.png"));
 	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    public void paintMe(Graphics g) {
	       
	        g.drawImage(myImage, x, y, null);

	    }

}
