package trafficsimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Road extends JPanel {

    final int LANE_HEIGHT = 150;
    final int ROAD_WIDTH = 1200;
    ArrayList<Vehicle> cars = new ArrayList<>();
    ArrayList<GasStation> gasStations = new ArrayList<>();

    public Road() {
        super();
        // Örnek olarak bir benzin istasyonu ekleyelim
        GasStation gasStation = new GasStation(300, LANE_HEIGHT * 3, this);
        GasStation gasStation2 = new GasStation(800, LANE_HEIGHT * 2, this);
        gasStations.add(gasStation);
        gasStations.add(gasStation2);

    }

    public void addCar(Vehicle v) {
        cars.add(v);
    }

    public void addGasStation(GasStation gasStation) {
        gasStations.add(gasStation);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);

        // Yolları çiz
        for (int a = LANE_HEIGHT; a < LANE_HEIGHT * 4; a = a + LANE_HEIGHT) {
            for (int b = 0; b < getWidth(); b = b + 40) {
                g.fillRect(b, a, 30, 5);
            }
        }

        // Araçları çiz
        for (int a = 0; a < cars.size(); a++) {
            cars.get(a).paintMe(g);
        }

        // Benzin istasyonlarını çiz
        for (int i = 0; i < gasStations.size(); i++) {
            gasStations.get(i).paintMe(g);
        }

    }

    public void step() {
//        for (int a = 0; a < cars.size(); a++) {
//            Vehicle v = cars.get(a);
//            if (collision(v.getX() + v.getSpeed(), v.getY(), v) == false) {
//                v.setX(v.getX() + v.getSpeed());
//                if (v.getX() > ROAD_WIDTH) {
//                    if (collision(0, v.getY(), v) == false) {
//                        v.setX(0);
//                    }
//
//                }
//            } else {// car ahead
//                if ((v.getY() > 40)
//                        && (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)) {
//                    v.setY(v.getY() - LANE_HEIGHT);
//
//                } 
//                else if((v.getY() < 40 + 3 * LANE_HEIGHT)
//                            && (collision(v.getX(), v.getY() + LANE_HEIGHT, v) == false)){
//                    v.setY(v.getY() + LANE_HEIGHT);
//                }
//
//            }
//        }

//        for (int a = 0; a < cars.size(); a++) {
//            Vehicle v = cars.get(a);
//            v.setX(v.getX() + v.getSpeed());
//            v.decreaseFuel();  // Her adımda benzin miktarını azalt
//            if (v.getFuelLevel() <= 33.3) {  // Benzin miktarı 33.3% veya daha azsa
//                goToNearestGasStation(v);
//            }
//            if (v.getX() > ROAD_WIDTH) {
//                v.setX(0);
//            }
//        }

////////////////

        for (int a = 0; a < cars.size(); a++) {
            Vehicle v = cars.get(a);

            if (collision(v.getX() + v.getSpeed(), v.getY(), v) == false) {
                v.setX(v.getX() + v.getSpeed());

                // Check if the vehicle moved beyond the road width
                if (v.getX() > ROAD_WIDTH) {
                    if (collision(0, v.getY(), v) == false) {
                        v.setX(0);
                    }
                }

            } else { // Collision with another vehicle ahead
                if ((v.getY() > 40) && (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)) {
                    v.setY(v.getY() - LANE_HEIGHT);

                } else if ((v.getY() < 40 + 3 * LANE_HEIGHT)
                        && (collision(v.getX(), v.getY() + LANE_HEIGHT, v) == false)) {
                    v.setY(v.getY() + LANE_HEIGHT);
                }
            }

            v.decreaseFuel(); // Decrease fuel at each step
            if (v.getFuelLevel() <= 33.3) {
                goToNearestGasStation(v);
            }

            // Check if the vehicle moved beyond the road width after collision handling
            if (v.getX() > ROAD_WIDTH) {
                v.setX(0);
            }
        }

  



    }

    public boolean collision(int x, int y, Vehicle v) {
        for (int a = 0; a < cars.size(); a++) {
            Vehicle u = cars.get(a);
            if (y == u.getY()) {
                if (u.equals(v) == false) {
                    if (x < u.getX() + u.getWidth()
                            && x + v.getWidth() > u.getX()) {
                        return true;

                    }
                }
            }
        }
        return false;
    }

    // Yeni bir metod ekleyerek en yakın benzin istasyonuna gitme işlemini gerçekleştir
    private void goToNearestGasStation(Vehicle v) {
        GasStation nearestStation = findNearestGasStation(v);
        if (nearestStation != null) {
            synchronized (nearestStation) {
                v.setX(nearestStation.x);
                v.setY(nearestStation.y);
                try {
                    Thread.sleep(3000);  // Bekleme süresini ihtiyacınıza göre ayarlayabilirsiniz
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v.refillFuel();
            }
        }
    }
 





    // Araç için en yakın benzin istasyonunu bulma
    private GasStation findNearestGasStation(Vehicle v) {
        double minDistance = Double.MAX_VALUE;
        GasStation nearestStation = null;
        for (GasStation station : gasStations) {
            double distance = Math.sqrt(Math.pow(v.getX() - station.x, 2) + Math.pow(v.getY() - station.y, 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestStation = station;
            }
        }
        return nearestStation;

    }
    
    
     public void clearCars() {
        cars.clear();
    }
    
}
