package trafficsimulation;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class DragDropRealTimeMouseListener extends MouseAdapter {
    
    private DragDropRealTimeLight reallight;
    private Road road;

    public DragDropRealTimeMouseListener(DragDropRealTimeLight reallight, Road road) {
        this.reallight = reallight;
        this.road = road;
    }
 
	 
	@Override
    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();
  
        if (mouseX >= reallight.x && mouseX <= reallight.x + reallight.myImage.getWidth(null) &&
            mouseY >= reallight.y && mouseY <= reallight.y + reallight.myImage.getHeight(null)) {
        	reallight.isDragging = true;
         } 
    } 
 
 
	@Override
	public void mouseClicked(MouseEvent e) {
	    int mouseX = e.getX();
	    int mouseY = e.getY();

	    if (mouseX >= reallight.x && mouseX <= reallight.x + reallight.myImage.getWidth(null)
	            && mouseY >= reallight.y && mouseY <= reallight.y + reallight.myImage.getHeight(null)) {

	        if (!reallight.isGreen) {
	            ImageIcon newImageIcon = new ImageIcon("green.png");
	            try {
	                reallight.setImage(newImageIcon);
	                for (Vehicle car : road.getCars()) {
	                    if (car instanceof Vehicle) {
	                    //    ((Vehicle) car).speed = 30;  
	                        ((Semi) car).speed = 50;  
	                        ((Sports) car).speed = 100;  
	                        ((SUV) car).speed = 80;  

	                        System.out.println("DEVAM ET");
	                    }
	                }
	                road.repaint();
	                reallight.isGreen = true;  
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        } else {
	            ImageIcon newImageIcon = new ImageIcon("red.png");
	            
	                reallight.setImagee(newImageIcon);
	                for (Vehicle car : road.getCars()) {
	                    if (car instanceof Vehicle) {
	                        ((Vehicle) car).speed = 0;  
	                        System.out.println("DUR");
	                    }
	                }
	                road.repaint();
	                reallight.isGreen = false;  
	        
	        }
	    }
	}
 
 	
    @Override
    public void mouseDragged(MouseEvent e) {
    	
        if (reallight.isDragging) {
        	reallight.x = e.getX();
        	reallight.y = e.getY();
            road.repaint();  
        }
    }
    
  
    
}