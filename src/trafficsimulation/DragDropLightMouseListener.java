package TrafikSimulasyon;

 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragDropLightMouseListener extends MouseAdapter {
    
    private DragDropLight dragdroplight;
    private Road road;

    public DragDropLightMouseListener(DragDropLight dragdroplight, Road road) {
        this.dragdroplight = dragdroplight;
        this.road = road;
    }
 
	 
	@Override
    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (mouseX >= dragdroplight.x && mouseX <= dragdroplight.x + dragdroplight.myImage.getWidth(null) &&
            mouseY >= dragdroplight.y && mouseY <= dragdroplight.y + dragdroplight.myImage.getHeight(null)) {
        	dragdroplight.isDragging = true;
        }
    }
	@Override
	public void mouseReleased(MouseEvent e) {
		
		dragdroplight.isDragging = false;
  	   // int slowDownAmount = -5;  
	    for (Vehicle car : road.getCars()) {
	        if (car instanceof Vehicle) {
	            ((Vehicle) car).speed=0;    
	        }
	    }
 	    road.repaint();
	}
  
    @Override
    public void mouseDragged(MouseEvent e) {
     
        if (dragdroplight.isDragging) {
        	dragdroplight.x = e.getX();
        	dragdroplight.y = e.getY();
            road.repaint();  
        }
    }
}