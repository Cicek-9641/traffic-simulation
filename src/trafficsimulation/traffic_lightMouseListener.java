package trafficsimulation;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class traffic_lightMouseListener extends MouseAdapter {

    private traffic_light reallight;
    private Road road;

    public traffic_lightMouseListener(traffic_light reallight, Road road) {
        this.reallight = reallight;
        this.road = road;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (mouseX >= reallight.x && mouseX <= reallight.x + reallight.myImage.getWidth(null)
                && mouseY >= reallight.y && mouseY <= reallight.y + reallight.myImage.getHeight(null)) {
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
                    for (Vehicle car : road.getCarsOnLane(reallight.x, reallight.y)) {
                        car.setSpeed(30);
                    }
                    road.repaint();
                    reallight.isGreen = true;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                ImageIcon newImageIcon = new ImageIcon("red.png");
                reallight.setImagee(newImageIcon);

                // Kırmızı ışık sırasında belirli bir sayıda aracın geçmesi durumunda ceza işlemlerini uygula
                if (reallight.getVehiclesOnRed().size() >= 3) {
                    Ceza ceza = new Ceza("Kırmızı Işıktan Geçme", 500);
                    ceza.applyPenalty(reallight.getVehiclesOnRed());
                }

                for (Vehicle car : road.getCarsOnLane(reallight.x, reallight.y)) {
                    car.setSpeed(0);
                }
                road.repaint();
                reallight.clearVehiclesOnRed();
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
