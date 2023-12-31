
package trafficsimulation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GasStationMouseListener extends MouseAdapter {
    
    private GasStation gasStation;
    private Road road;

    public GasStationMouseListener(GasStation gasStation, Road road) {
        this.gasStation = gasStation;
        this.road = road;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (mouseX >= gasStation.x && mouseX <= gasStation.x + gasStation.myImage.getWidth(null) &&
            mouseY >= gasStation.y && mouseY <= gasStation.y + gasStation.myImage.getHeight(null)) {
            gasStation.isDragging = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gasStation.isDragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (gasStation.isDragging) {
            gasStation.x = e.getX();
            gasStation.y = e.getY();
            road.repaint(); 
        }
    }
}

