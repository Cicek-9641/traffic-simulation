package trafficsimulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Traffic implements ActionListener, Runnable {

    JFrame frame = new JFrame("traffic sımulator");
    Road road = new Road();

    //South Container
    JButton start = new JButton("start");
    JButton stop = new JButton("stop");
    JButton restart = new JButton("restart");

    Container south = new Container();

    // west Container    
    JButton semi = new JButton("add semi");
    JButton suv = new JButton("add suv");
    JButton sports = new JButton("add sports");
    

    Container west = new Container();

    boolean running = false;
 
 
    public Traffic() {
        frame.setSize(1370, 700);
        frame.setLayout(new BorderLayout());
        frame.add(road, BorderLayout.CENTER);

        south.setLayout(new GridLayout(1, 6));
        south.add(start);
        start.addActionListener(this);
        south.add(stop);
        stop.addActionListener(this);
        
        south.add(restart);   // restart butonu
        restart.addActionListener(this);
        
        frame.add(south, BorderLayout.SOUTH);

        west.setLayout(new GridLayout(3, 1));

        semi.setBackground(Color.ORANGE);
        suv.setBackground(Color.PINK);
        sports.setBackground(Color.lightGray);


        west.add(semi);
        semi.addActionListener(this);
        
        west.add(suv);
        suv.addActionListener(this);
        
        west.add(sports);
        sports.addActionListener(this);
        
        frame.add(west, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.repaint();
    }

    public static void main(String[] args) {
        new Traffic();

    }
    
    
    // Yeni restart metodu
    public void restart() {
        running = false;  // Simülasyonu durdur
        road.clearCars();  // Araçları temizle
        road.repaint();    // Paneli tekrar çiz
        running = true;   // Simülasyonu tekrar başlat
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(start)) {
            if (running == false) {
                running = true;
                Thread t = new Thread(this);
                t.start();
            }
        }
        
        if (event.getSource().equals(restart)) {
            restart();
        }
          
        if (event.getSource().equals(stop)) {
            running = false;
        }
        if (event.getSource().equals(semi)) {
            Semi semi = new Semi(0, 30);
            road.addCar(semi);
            
            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 600; y = y + 150) {
                    semi.setX(x);
                    semi.setY(y);
                    if (road.collision(x, y, semi) == false) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
        if (event.getSource().equals(suv)) {
            SUV suv = new SUV(0, 30);
            road.addCar(suv);
            
            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 600; y = y + 150) {
                    suv.setX(x);
                    suv.setY(y);
                    if (road.collision(x, y, suv) == false) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }

        if (event.getSource().equals(sports)) {
            Sports sports = new Sports(0, 30);
            road.addCar(sports);
            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 600; y = y + 150) {
                    sports.setX(x);
                    sports.setY(y);
                    if (road.collision(x, y, sports) == false) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        while (running == true) {
            road.step();
            frame.repaint();
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
