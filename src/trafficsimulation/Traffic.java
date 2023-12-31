package trafficsimulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Traffic implements Runnable, ActionListener {
    //AAAA

    JFrame frame = new JFrame("NESNE");

    Road road = new Road();

    JButton start = new JButton("Baslat");
    JButton stop = new JButton("Duraklat");
    JLabel throughput = new JLabel("Verim:0");
    JButton restart = new JButton("restart");

    Container south = new Container();

    JButton semi = new JButton("SEMI");
    JButton suv = new JButton("SUV");
    JButton sports = new JButton("Spor");

    Container west = new Container();

    boolean running = false;

    int carCount = 0;
    long startTime = 0;

    Timer timer;
    boolean isRed = true;

    JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 0);
    JLabel speedLabel = new JLabel("Hız: 0");

    public Traffic() {

        frame.setSize(1370, 700);
        frame.setLayout(new BorderLayout());
        frame.add(road, BorderLayout.CENTER);

        west.setLayout(new GridLayout(4, 1));

        semi.setBackground(Color.ORANGE);
        suv.setBackground(Color.PINK);
        sports.setBackground(Color.lightGray);
        start.setBackground(Color.green);
        stop.setBackground(Color.red);

        west.add(semi);
        west.add(suv);
        west.add(sports);

        south.setLayout(new GridLayout(1, 6));

        south.add(start);
        start.addActionListener(this);

        south.add(stop);
        stop.addActionListener(this);

        south.add(restart);   // restart butonu
        restart.addActionListener(this);

        south.add(throughput);

        south.add(speedLabel);

        south.add(new JLabel("Hız Artırma: "));
        south.add(speedSlider);

        west.setLayout(new GridLayout(4, 1));

        west.add(semi);
        semi.addActionListener(this);

        west.add(suv);
        suv.addActionListener(this);

        west.add(sports);
        sports.addActionListener(this);

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
                    if (car instanceof Vehicle) {
                        //   ((Vehicle) car).speed = 30;  
                        //   ((Semi) car).speed = speedValue; 

                        //    ((Sports) car).speed = speedValue;  
                        //   ((SUV) car).speed = speedValue;  
                    }
                }

            }
        });
    }

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

        int speedValue = speedSlider.getValue();
        System.out.println(speedValue);

        if (event.getSource().equals(start)) {
            if (running == false) {
                running = true;
                //	road.resetCarCount();
                startTime = System.currentTimeMillis();
                Thread t = new Thread(this);
                t.start();
//			     for (Vehicle car : road.getCars()) {
//	                    if (car instanceof Vehicle) {
//	                    //   
//		              ((Vehicle) car).fuelLevel = 10000;  
//  
// 
//	                    }
//	                }

            }
        }

        if (event.getSource().equals(restart)) {
            restart();
        }

        if (event.getSource().equals(stop)) {
            running = false;
        }

        if (event.getSource().equals(semi)) {

            int slowDownAmount = 2;

            Semi semi = new Semi(0, 30, road);
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
            SUV suv = new SUV(0, 30, road);
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
            Sports sports = new Sports(0, 30, road);
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
            road.refillFuelForNextVehicle(); // Refill fuel for the next vehicle in the queue
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
