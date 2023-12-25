package trafficsimulation;

import java.awt.Graphics;


public class Vehicle {

    int x;
    int y;
    int width = 0;
    int height = 0;
    int speed = 0;
    //int gas = 0;
    double fuelLevel = 0;  // Başlangıçta aracın benzin miktarı
     
       // Add a reference to the Road object to access gas stations
    Road road;
    
    
 
    public Vehicle(int newX, int newY, Road road) {
        x = newX;
        y = newY;
        this.road = road;
    }
    
    // Method to check and handle low fuel
    public void checkLowFuel() {
        if (fuelLevel <= 33.3) {
            road.goToNearestGasStation(this);
        }
    }
    
    // Add a method to go to the gas station and wait
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


