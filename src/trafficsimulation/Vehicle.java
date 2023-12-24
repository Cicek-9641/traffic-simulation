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
     
    
    public Vehicle(int newX, int newY) {
        x = newX;
        y = newY;
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


