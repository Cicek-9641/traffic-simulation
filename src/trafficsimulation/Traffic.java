//package trafficsimulation;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JSlider;
//import javax.swing.Timer;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//public class Traffic implements Runnable, ActionListener {
//
//    JFrame frame = new JFrame("NESNE");
//    
//
//    Road road = new Road();
//
//    JButton start = new JButton("Baslat");
//    JButton stop = new JButton("Duraklat");
//    JLabel throughput = new JLabel("Verim:0");
//    JButton restart = new JButton("Restart");
//
//    Container south = new Container();
//
//    JButton semi = new JButton("SEMI");
//    JButton suv = new JButton("SUV");
//    JButton sports = new JButton("Spor");
// 
//    Container west = new Container();
//
//    boolean running = false;
//
//    int carCount = 0;
//    long startTime = 0;
//
//    Timer timer;
//    boolean isRed = true;
//
//    JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 0);
//    JLabel speedLabel = new JLabel("Hız: 0");
//
//    public Traffic() {
//
//        frame.setSize(1370, 700);
//        frame.setLayout(new BorderLayout());
//        frame.add(road, BorderLayout.CENTER);
//
//        west.setLayout(new GridLayout(5, 1));
//
//        semi.setBackground(Color.ORANGE);
//        suv.setBackground(Color.PINK);
//        sports.setBackground(Color.lightGray);
//        start.setBackground(Color.green);
//        stop.setBackground(Color.red);
//         
//
//        west.add(semi);
//        west.add(suv);
//        west.add(sports);
//  
//        south.setLayout(new GridLayout(1, 6));
//
//        south.add(start);
//        start.addActionListener(this);
//
//        south.add(stop);
//        stop.addActionListener(this);
//
//        south.add(restart);   // restart butonu
//        restart.addActionListener(this);
//        restart.setBackground(Color.orange);
//        south.add(throughput);
//
//        south.add(speedLabel);
//
//        south.add(new JLabel("Hız Artırma: "));
//        south.add(speedSlider);
//
//        west.setLayout(new GridLayout(3, 1));
//
//        west.add(semi);
//        semi.addActionListener(this);
//
//        west.add(suv);
//        suv.addActionListener(this);
//
//        west.add(sports);
//        sports.addActionListener(this);
//
//        frame.add(south, BorderLayout.SOUTH);
//        frame.add(west, BorderLayout.WEST);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
//        frame.repaint();
//
//        speedSlider.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                int speedValue = speedSlider.getValue();
//                speedLabel.setText("Hız: " + speedValue);
//
//                // Araçların başlangıç hızlarını alarak güncelleyin
//                for (Vehicle car : road.getCars()) {
//                     
//                    car.setSpeed(speedValue);
//                }
//            }
//        });
//    }
//
//    public void restart() {
//        running = false;  // Simülasyonu durdur
//        road.clearCars();  // Araçları temizle
//        road.repaint();    // Paneli tekrar çiz
//        running = true;   // Simülasyonu tekrar başlat
//        Thread t = new Thread(this);
//        t.start();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent event) {
//
//        int speedValue = speedSlider.getValue();
//        System.out.println(speedValue);
//
//        if (event.getSource().equals(start)) {
//            if (running == false) {
//                running = true;
//                //	road.resetCarCount();
//                startTime = System.currentTimeMillis();
//                Thread t = new Thread(this);
//                t.start();
////			     for (Vehicle car : road.getCars()) {
////	                    if (car instanceof Vehicle) {
////	                    //   
////		              ((Vehicle) car).fuelLevel = 10000;  
////  
//// 
////	                    }
////	                }
//
//            }
//        }
//
//
//        if (event.getSource().equals(restart)) {
//            restart();
//        }
//
//        if (event.getSource().equals(stop)) {
//            running = false;
//        }
//
//        if (event.getSource().equals(semi)) {
//
//          //  int slowDownAmount = 2;
//
//            Semi semi = new Semi(0, 30, road, 8);
//            road.addCar(semi);
//
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 600; y = y + 150) {
//                    semi.setX(x);
//                    semi.setY(y);
//                    if (road.collision(x, y, semi) == false) {
//                        frame.repaint();
//                        return;
//
//                    }
//
//                }
//            }
//        }
//
//        if (event.getSource().equals(suv)) {
//            SUV suv = new SUV(0, 30, road, 8);
//            road.addCar(suv);
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 600; y = y + 150) {
//                    suv.setX(x);
//                    suv.setY(y);
//                    if (road.collision(x, y, suv) == false) {
//                        frame.repaint();
//                        return;
//
//                    }
//
//                }
//            }
//        }
//
//        if (event.getSource().equals(sports)) {
//            Sports sports = new Sports(0, 30, road, 12);
//            road.addCar(sports);
//
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 600; y = y + 150) {
//                    sports.setX(x);
//                    sports.setY(y);
//                    if (road.collision(x, y, sports) == false) {
//                        frame.repaint();
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void run() {
//        while (running == true) {
//            road.step();
//            road.refillFuelForNextVehicle(); // Refill fuel for the next vehicle in the queue
//            carCount = road.getCarCount();
//            double throughtputCalc = carCount / (1000 * (double) (System.currentTimeMillis()) - startTime);
//            throughput.setText("Throughtput" + throughtputCalc);
//            frame.repaint();
//            try {
//                Thread.sleep(100);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//}

//package trafficsimulation;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JSlider;
//import javax.swing.Timer;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//
//public class Traffic implements Runnable, ActionListener {
//
//    JFrame frame = new JFrame("NESNE PROJE ÖDEVİ - TRAFİK SİMÜLASYONU");
//
//    Road road = new Road();
//    
//    JButton start = new JButton("Başlat");
//    JButton stop = new JButton("Duraklat");
//    JLabel throughput = new JLabel("Verim:0");
//    JButton restart = new JButton("Restart");
//
//    Container south = new Container();
//
//    JButton kamyonet = new JButton("Kamyonet");
//    JButton arazi = new JButton("Arazi Taşıtı");
//    JButton otomobil = new JButton("Otomobil");
//
//    Container west = new Container();
//
//    boolean running = false;
//
//    int carCount = 0;
//    long startTime = 0;
//
//    Timer timer;
//    boolean isRed = true;
//
//    JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 500, 0);
//    JLabel speedLabel = new JLabel("Hız: 0");
//
//    public Traffic() {
//    	Vehicle arac = new Vehicle(0, 0, road, 50);
//    	arac.setPlaka("34 ABC 123");
//    	arac.showPlaka();
//    	
//
//        frame.setSize(1370, 700);
//        frame.setLayout(new BorderLayout());
//        frame.add(road, BorderLayout.CENTER);
//
//        west.setLayout(new GridLayout(4, 1));
//
//        kamyonet.setBackground(Color.ORANGE);
//        arazi.setBackground(Color.PINK);
//        otomobil.setBackground(Color.lightGray);
//        start.setBackground(Color.green);
//        stop.setBackground(Color.red);
//
//        west.add(kamyonet);
//        west.add(arazi);
//        west.add(otomobil);
//
//        south.setLayout(new GridLayout(1, 6));
//
//        south.add(start);
//        start.addActionListener(this);
//
//        south.add(stop);
//        stop.addActionListener(this);
//
//        south.add(restart);   // restart butonu
//        restart.addActionListener(this);
//        restart.setBackground(Color.orange);
//
//
//        south.add(throughput);
//
//        south.add(speedLabel);
//
//        south.add(new JLabel("Hız Artırma: "));
//        south.add(speedSlider);
//
//        west.setLayout(new GridLayout(3, 1));
//
//        west.add(kamyonet);
//        kamyonet.addActionListener(this);
//
//        west.add(arazi);
//        arazi.addActionListener(this);
//
//        west.add(otomobil);
//        otomobil.addActionListener(this);
//
//        frame.add(south, BorderLayout.SOUTH);
//        frame.add(west, BorderLayout.WEST);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//
//        frame.repaint();
//
//        speedSlider.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                int speedValue = speedSlider.getValue();
//                speedLabel.setText("Hız: " + speedValue);
//
//                // Araçların başlangıç hızlarını alarak güncelleyin
//                for (Vehicle car : road.getCars()) {
//                    // Araçların başlangıç hızlarını alarak güncelleyin
//                    car.setSpeed(speedValue);
//                }
//            }
//        });
//    }
//
//    public void restart() {
//        running = false;  // Simülasyonu durdur
//        road.clearCars();  // Araçları temizle
//        road.repaint();    // Paneli tekrar çiz
//        running = true;   // Simülasyonu tekrar başlat
//        Thread t = new Thread(this);
//        t.start();
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent event) {
//
//        int speedValue = speedSlider.getValue();
//        System.out.println(speedValue);
//
//        if (event.getSource().equals(start)) {
//            if (running == false) {
//                running = true;
//                //	road.resetCarCount();
//                startTime = System.currentTimeMillis();
//                Thread t = new Thread(this);
//                t.start();
// 
//
//            }
//        }
//
//        if (event.getSource().equals(restart)) {
//            restart();
//        }
//
//        if (event.getSource().equals(stop)) {
//            running = false;
//        }
//
//        if (event.getSource().equals(kamyonet)) {
//
//
//            Kamyon kamyon = new Kamyon(0, 30, road, 70);
//            road.addCar(kamyon);
//
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 600; y = y + 150) {
//                	kamyon.setX(x);
//                	kamyon.setY(y);
//                    if (road.collision(x, y, kamyon) == false) {
//                        frame.repaint();
//                        return;
//
//                    }
//
//                }
//            }
//        }
//
//        if (event.getSource().equals(arazi)) {
//            AraziAraci arazi = new AraziAraci(0, 30, road, 80);
//            road.addCar(arazi);
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 600; y = y + 150) {
//                	arazi.setX(x);
//                	arazi.setY(y);
//                    if (road.collision(x, y, arazi) == false) {
//                        frame.repaint();
//                        return;
//
//                    }
//
//                }
//            }
//        }
//
//        if (event.getSource().equals(otomobil)) {
//            Otomobil oto = new Otomobil(0, 30, road, 50);
//            road.addCar(oto);
//
//            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
//                for (int y = 40; y < 600; y = y + 150) {
//                	oto.setX(x);
//                	oto.setY(y);
//                    if (road.collision(x, y, oto) == false) {
//                        frame.repaint();
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public void run() {
//        while (running == true) {
//            road.step();
//            road.refillFuelForNextVehicle(); // Refill fuel for the next vehicle in the queue
//            carCount = road.getCarCount();
//            double throughtputCalc = carCount / (1000 * (double) (System.currentTimeMillis()) - startTime);
//            throughput.setText("Throughtput" + throughtputCalc);
//            frame.repaint();
//            try {
//                Thread.sleep(100);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//}

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

    JFrame frame = new JFrame("NESNE PROJE ÖDEVİ - TRAFİK SİMÜLASYONU");

    Road road = new Road();

    JButton start = new JButton("Baslat");
    JButton stop = new JButton("Duraklat");
    JLabel throughput = new JLabel("Verim:0");
    JButton restart = new JButton("Restart");

    Container south = new Container();
    JButton kamyonet = new JButton("Kamyonet");
    JButton arazi = new JButton("Arazi Taşıtı");
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

        kamyonet.setBackground(Color.ORANGE);
        add.setBackground(Color.MAGENTA);
        interest.setBackground(Color.BLUE);
        arazi.setBackground(Color.PINK);
        otomobil.setBackground(Color.lightGray);
        start.setBackground(Color.green);
        stop.setBackground(Color.red);

        west.add(kamyonet);
        west.add(arazi);
        west.add(otomobil);
        west.add(add); 
        west.add(interest);

        south.setLayout(new GridLayout(1, 6));

        south.add(start);
        start.addActionListener(this);

        south.add(stop);
        stop.addActionListener(this);

        south.add(restart);   // restart butonu
        restart.addActionListener(this);
        restart.setBackground(Color.orange);


        south.add(throughput);

        south.add(speedLabel);

        south.add(new JLabel("Hız Artırma: "));
        south.add(speedSlider);

        west.setLayout(new GridLayout(5, 1));
        west.add(add);
        add.addActionListener(this);

        west.add(interest);
        interest.addActionListener(this);

        west.add(kamyonet);
        kamyonet.addActionListener(this);

        west.add(arazi);
        arazi.addActionListener(this);

        west.add(otomobil);
        otomobil.addActionListener(this);

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

                // Araçların başlangıç hızlarını alarak güncelleyin
                for (Vehicle car : road.getCars()) {
                    // Araçların başlangıç hızlarını alarak güncelleyin
                    car.setSpeed(speedValue);
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
 
            }
        }

        if (event.getSource().equals(restart)) {
            restart();
        }

        if (event.getSource().equals(stop)) {
            running = false;
        }
        if (event.getSource().equals(add)){
            serit = serit +update;
        }
        if (event.getSource().equals(interest)){
            serit = serit - update;
        }

        if (event.getSource().equals(kamyonet)) {


            Kamyon kamyon = new Kamyon(0, 30, road, 70);
            road.addCar(kamyon);

            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 600; y = y + 150) {
                	kamyon.setX(x);
                	kamyon.setY(y);
                    if (road.collision(x, y, kamyon) == false) {
                        frame.repaint();
                        return;

                    }

                }
            }
        }

        if (event.getSource().equals(arazi)) {
            AraziAraci arazi = new AraziAraci(0, 30, road, 80);
            road.addCar(arazi);
            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 600; y = y + 150) {
                	arazi.setX(x);
                	arazi.setY(y);
                    if (road.collision(x, y, arazi) == false) {
                        frame.repaint();
                        return;

                    }

                }
            }
        }

        if (event.getSource().equals(otomobil)) {
            Otomobil oto = new Otomobil(0, 30, road, 50);
            road.addCar(oto);

            for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
                for (int y = 40; y < 600; y = y + 150) {
                	oto.setX(x);
                	oto.setY(y);
                    if (road.collision(x, y, oto) == false) {
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



