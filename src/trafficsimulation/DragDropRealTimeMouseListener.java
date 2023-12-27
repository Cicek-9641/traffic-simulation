package trafficsimulation;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

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
	public void mouseReleased(MouseEvent e) {
		 int mouseY = e.getY();
 
		reallight.isDragging = false;
  	   // int slowDownAmount = -5;  
		if (mouseY < 350) {
	    for (Vehicle car : road.getCars()) {
	        if (car instanceof Vehicle) {
	            ((Vehicle) car).speed=0;    
	        }
	    }
 	    road.repaint();
	}
		else {
			  for (Vehicle car : road.getCars()) {
			        if (car instanceof Vehicle) {
			            ((Vehicle) car).speed=25;    
			        }
			    }
		 	    road.repaint();
			
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