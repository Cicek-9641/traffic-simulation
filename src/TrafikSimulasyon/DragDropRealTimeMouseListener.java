package TrafikSimulasyon;

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
		DragDropRealTimeLight dr = new DragDropRealTimeLight(0, 0, road);
		 reallight.isDragging = false;
		    
		    Timer timer = new Timer();
		    timer.schedule(new TimerTask() {
		        @Override
		        public void run() {
		            for (Vehicle car : road.getCars()) {
		                if (car instanceof Vehicle) {
		                    ((Vehicle) car).speed = 0;  //  durdur
		                }
		            }
		            road.repaint();		         
		            timer.cancel(); 
		        }
		    }, 5000); 
	 
		    dr.setImage("green.png");
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