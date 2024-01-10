package trafficsimulation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Vehicle {

    int x;
    int y;
    int width = 0;
    int height = 0;
    int speed = 0;
    double fuelLevel = 0;  // Başlangıçta aracın benzin miktarı
    
    private int cezaPuani = 0; 
    private boolean allowedToPass = true;  // Geçiş iznini saklamak için değişken
    
    // Yeni eklenen değişken
    Image fuelGaugeImage;
    
    // Araçların başlangıç hızını saklamak için bir değişken ekleyin
    private int initialSpeed;

    // Add a reference to the Road object to access gas stations
    Road road;

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public Vehicle(int newx, int newy, Road road,int speed) {
        x = newx;
        y = newy;
        this.road = road;
         this.initialSpeed = speed;
        this.speed = speed; // Başlangıç hızını ayarlayın
  

    }
    
    // Başlangıç hızını döndüren bir metot ekleyin
    public int getInitialSpeed() {
        return initialSpeed;
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

  public void setSpeed(int newSpeed) {
    speed = newSpeed;
}
  
  
  
// Kırmızı ışıkta geçen araçlara ceza puanı ekleyen metot
    public void addPenaltyPoints(int cezaMiktari) {
        cezaPuani += cezaMiktari;

        // Örneğin: Başka bir sınıfta veya listeye ceza bilgilerini kaydetmek gibi
        System.out.println("Ceza puanı eklendi: " + cezaMiktari + " - Toplam Ceza Puanı: " + cezaPuani);
    }

    // Araçların yeşil ışıkta geçip geçemeyeceğini belirleyen metot
    public void setAllowedToPass(boolean allowed) {
        allowedToPass = allowed;

        // Burada aracın geçiş iznini set etmek için kodu ekleyebilirsiniz
        System.out.println("Geçiş izni güncellendi: " + allowedToPass);
    }
}

