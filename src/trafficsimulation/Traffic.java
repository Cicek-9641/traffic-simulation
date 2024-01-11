package trafficsimulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Traffic implements Runnable, ActionListener {

    JFrame frame = new JFrame("NESNE PROJE ÖDEVİ - TRAFİK SİMÜLASYONU");

    Road road = new Road();

    JButton start = new JButton("Baslat");
    JButton stop = new JButton("Duraklat");
    JLabel throughput = new JLabel("Verim:0");
    JButton restart = new JButton("Restart");

    Container south = new Container();

    JButton kamyon = new JButton("Kamyon");
    JButton araziA = new JButton("Arazi Aracı");
    JButton otomobil = new JButton("Otomobil");

    JButton add = new JButton("Şerit arttır");
    JButton interest = new JButton("Şerit azalt");

    Container west = new Container();

    boolean running = false;

    int carCount = 0;
    long startTime = 0;
    int update = 150;
    int serit = 300;

    Timer timer;
    boolean isRed = true;

    JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 0);
    JLabel speedLabel = new JLabel("Hız: 0");

    public Traffic() {

        frame.setSize(1370, 700);
        frame.setLayout(new BorderLayout());
        frame.add(road, BorderLayout.CENTER);

        west.setLayout(new GridLayout(5, 1));

        kamyon.setBackground(Color.ORANGE);
        araziA.setBackground(Color.PINK);
        otomobil.setBackground(Color.lightGray);
        start.setBackground(Color.green);
        stop.setBackground(Color.red);
        
        add.setBackground(Color.MAGENTA);
        interest.setBackground(Color.BLUE);

        west.add(kamyon);
        west.add(araziA);
        west.add(otomobil);
        
        west.add(add);
        west.add(interest);

        south.setLayout(new GridLayout(1, 6));

        south.add(start);
        start.addActionListener(this);

        south.add(stop);
        stop.addActionListener(this);

        south.add(restart);  
        restart.addActionListener(this);
        restart.setBackground(Color.orange);

        south.add(throughput);
        south.add(speedLabel);

        south.add(new JLabel("Hız Artırma: "));
        south.add(speedSlider);

        west.setLayout(new GridLayout(5, 1));
       

        west.add(kamyon);
        kamyon.addActionListener(this);

        west.add(araziA);
        araziA.addActionListener(this);

        west.add(otomobil);
        otomobil.addActionListener(this);

        west.add(add);
        add.addActionListener(this);
        
        west.add(interest);
        interest.addActionListener(this);

        frame.add(south, BorderLayout.SOUTH);
        frame.add(west, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.repaint();

        speedSlider.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                int speedValue = speedSlider.getValue();
                speedLabel.setText("Hız: " + speedValue);
                
                for (Vehicle car : road.getCars()) {                   
                    car.setSpeed(speedValue);
                }
            }
        });
    }

    public void restart() {
        running = false;  
        road.clearCars();  
        road.repaint();    
        running = true;   
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        int speedValue = speedSlider.getValue();
       // System.out.println(speedValue);

        if (event.getSource().equals(start)) {
            if (running == false) {
                running = true;
           
                startTime = System.currentTimeMillis();
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
        if (event.getSource().equals(add)) {
            serit = serit + update;
        }
        if (event.getSource().equals(interest)) {
            serit = serit - update;
        }

        if (event.getSource().equals(kamyon)) {
            Kamyon kamyon = new Kamyon(0, 30, road, 45);
            road.addCar(kamyon);

            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < serit; y = y + 150) {
                    kamyon.setX(x);
                    kamyon.setY(y);
                    if (road.collision(x, y, kamyon) == false) {
                        frame.repaint();
                        return;

                    }

                }
            }
        }

        if (event.getSource().equals(araziA)) {
            AraziAraci araziA = new AraziAraci(0, 30, road, 70);
            road.addCar(araziA);
            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < serit; y = y + 150) {
                    araziA.setX(x);
                    araziA.setY(y);
                    if (road.collision(x, y, araziA) == false) {
                        frame.repaint();
                        return;

                    }

                }
            }
        }

        if (event.getSource().equals(otomobil)) {
            Otomobil otomobil = new Otomobil(0, 30, road, 80);
            road.addCar(otomobil);

            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < serit; y = y + 150) {
                    otomobil.setX(x);
                    otomobil.setY(y);
                    if (road.collision(x, y, otomobil) == false) {
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
            road.refillFuelForNextVehicle(); 
            carCount = road.getCarCount();
            double throughtputCalc = carCount / (1000 * (double) (System.currentTimeMillis()) - startTime);
            throughput.setText("Throughtput" + throughtputCalc);
            frame.repaint();
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
 

}
