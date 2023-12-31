package trafficsimulation;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Vehicle {

    int x;
    int y;
    int width = 0;
    int height = 0;
    int speed = 0;
    double fuelLevel = 0;  // Başlangıçta aracın benzin miktarı

    // Add a reference to the Road object to access gas stations
    Road road;

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Vehicle(int newx, int newy, Road road) {
        x = newx;
        y = newy;
        this.road = road;

    }

    public void checkLowFuel() {
        if (fuelLevel <= 33.3) {
            road.goToNearestGasStation(this);
        }
    }

    public void goToGasStationAndWait(Road road) {
        road.addToQueue(this); // Add the vehicle to the gas station queue
        synchronized (this) {
            try {
                wait(); // Wait until the vehicle gets refueled
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
 public void paintMe(Graphics g) {
         //araç çizimi
    }

    public int getX() {
        return x;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int newx) {
        x = newx;
    }

    public int getY() {
        return y;
    }
     public void setY(int newy) {
        y = newy;
    }

    public int getWidth() {
        return width;
    }
    
    public double getFuelLevel() {
        return fuelLevel;
    }

    public void decreaseFuel() {
        // Her adımda benzin miktarını azalt
        fuelLevel -= 0.5;  // Bu değeri ihtiyacınıza göre ayarlayabilirsiniz
    }

    public void refillFuel() {
        // Benzin istasyonuna gidip benzin dolumu yap
        fuelLevel = 100.0;  // Bu değeri ihtiyacınıza göre ayarlayabilirsiniz
    }
}
