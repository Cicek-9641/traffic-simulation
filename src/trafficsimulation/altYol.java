package TrafikSimulasyon;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class altYol extends JPanel {
    private JButton secondRoadButton = new JButton("Second Road");

    final int LANE_HEIGHT = 120; 
    final int ROAD_WIDTH = 800; 

    ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
    int carCount = 0;

    private boolean isRedLight = true;

    public altYol() {
        super();
        setBackground(Color.GRAY);
        add(secondRoadButton);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (int a = LANE_HEIGHT; a < 200; a = a + LANE_HEIGHT) {
            for (int b = 0; b < getWidth(); b = b + 20) {
                g.fillRect(b, a, 15, 3);
            }
        }
        for (int a = 0; a < cars.size(); a++) {
            cars.get(a).paintMe(g);
        }
    }

}
