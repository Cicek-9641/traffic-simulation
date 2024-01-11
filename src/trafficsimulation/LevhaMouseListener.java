package trafficsimulation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevhaMouseListener extends MouseAdapter {

    private Levha dragdroplevha;
    private Road road;

    public LevhaMouseListener(Levha dragdroplevha, Road road) {
        this.dragdroplevha = dragdroplevha;
        this.road = road;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (mouseX >= dragdroplevha.x && mouseX <= dragdroplevha.x + dragdroplevha.myImage.getWidth(null)
                && mouseY >= dragdroplevha.y && mouseY <= dragdroplevha.y + dragdroplevha.myImage.getHeight(null)) {
            dragdroplevha.isDragging = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragdroplevha.isDragging = false;
       
        for (Vehicle car : road.getCars()) {
            if (car instanceof Vehicle) {
                ((Vehicle) car).speed = 30;

             }
        }

        road.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragdroplevha.isDragging) {

            dragdroplevha.x = e.getX();
            dragdroplevha.y = e.getY();
            road.repaint();
        }
    }
}
