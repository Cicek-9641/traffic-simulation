package trafficsimulation;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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
                        car.setSpeed(50); //tekrar bir bak!!!!  
                    }
                    road.repaint();
                    reallight.isGreen = true;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                ImageIcon newImageIcon = new ImageIcon("red.png");
                reallight.setImagee(newImageIcon);
                for (Vehicle car : road.getCarsOnLane(reallight.x, reallight.y)) {
                    car.setSpeed(100);
                    car.getPlaka();
                    int gecisHiz = car.getSpeed();
                  if(gecisHiz >= 100) {
                    System.out.println("KIRMIZIDA GECEN"+car.getPlaka());
                    double cezaMiktari = 1500;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date now = new Date();
                    String tarih = dateFormat.format(now);

                     try (PrintWriter writer = new PrintWriter(new FileWriter("ceza.txt", true))) {
                        writer.println("Plaka: " + car.getPlaka() + ", Ceza Tutari: " + cezaMiktari + " TL, Tarih: " + tarih);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                road.repaint();
                reallight.isGreen = false;
            }
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
    @Override
    public void mouseReleased(MouseEvent e) {
        reallight.isDragging = false;
    }
}
