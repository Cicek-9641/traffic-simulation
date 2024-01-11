package trafficsimulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Road extends JPanel {

    final int LANE_HEIGHT = 150;
    final int ROAD_WIDTH = 1200;

    private ImageIcon redIcon = new ImageIcon("red.png");
    private ImageIcon greenIcon = new ImageIcon("green.png");
 
    ArrayList<Levha> dragdropslevha = new ArrayList<Levha>();
    ArrayList<traffic_light> dragdropsreallight = new ArrayList<traffic_light>();

    ArrayList<GasStation> gasStations = new ArrayList<>();
    Queue<Vehicle> gasStationQueue = new LinkedList<>();

    ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
   
 

    int carCount = 0;

public List<Vehicle> getCarsOnLane(int lightX, int lightY) {
    List<Vehicle> carsOnLane = new ArrayList<>();

    int laneIndex = lightY / LANE_HEIGHT;

    for (Vehicle car : cars) {
        if (car.getY() / LANE_HEIGHT == laneIndex) {
            carsOnLane.add(car);
        }
    }

    return carsOnLane;
}

    private boolean isRedLight = true;

    public void saveCarCountToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("trafikBilgi.txt", true));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            writer.write(formattedDateTime + " - " + carCount);
            writer.newLine();
            writer.close();
            System.out.println("Trafikteki toplam arac sayisi trafikBilgi.txt dosyasina kaydediliyor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void turkaydet(String tip)  {
  	  
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("MTV.txt", true));
//           
//            writer.write(tip+vehicle.getPlaka());
            for (Vehicle vehicle : getCars()) {
                writer.write(tip+","+vehicle.getPlaka() +"," + vehicle.getSpeed());
                
                writer.newLine();
             }
            //writer.newLine();
            writer.close();
            System.out.println("Trafikteki arac turu mtv.txt dosyasina kaydediliyor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
			dosyayaYeniSutunEkle("MTV.txt", "vergi.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       // dosyayaYeniSutunEkle("MTV.txt","MTV.txt");
    }
 
    public Road() {
       
        super();
  
        Levha dragdroplevha = new Levha(300, LANE_HEIGHT * 3, this);
        dragdropslevha.add(dragdroplevha);

        traffic_light dragdropsreal = new traffic_light(500, LANE_HEIGHT * 3, this);
        dragdropsreallight.add(dragdropsreal);

        GasStation gasStation = new GasStation(700, LANE_HEIGHT * 3, this);
        GasStation gasStation2 = new GasStation(800, LANE_HEIGHT * 2, this);
        gasStations.add(gasStation);
        gasStations.add(gasStation2);

    }

    public void addCar(Vehicle v)  {
        cars.add(v);

         
        System.out.println("Eklenen araç türü: " + v.getType());
        turkaydet(v.getType());
 
    }

    public void addDragDropLevha(Levha dragdropLevha) {
        dragdropslevha.add(dragdropLevha);
    }

    public void addGasStation(GasStation gasStation) {
        gasStations.add(gasStation);
    }
  
    public void paintComponent(Graphics g) {
    
        super.paintComponent(g);
      
     
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        
      
        
        //YOL CIZIM
        for (int a = LANE_HEIGHT; a < 600; a = a + LANE_HEIGHT) {
            for (int b = 0; b < getWidth(); b = b + 40) {
                g.fillRect(b, a, 30, 5);
            }
        }

        for (int a = 0; a < cars.size(); a++) {
            cars.get(a).paintMe(g);
        }

        for (int i = 0; i < dragdropslevha.size(); i++) {
            dragdropslevha.get(i).paintMe(g);
        }

        for (int i = 0; i < dragdropsreallight.size(); i++) {
            dragdropsreallight.get(i).paintMe(g);
        }

        for (int i = 0; i < gasStations.size(); i++) {
            gasStations.get(i).paintMe(g);
        }
        double averageSpeed = calculateAverageSpeed();
       
        
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2F);  
        g.setFont(newFont);

       
        
        g.setColor(Color.BLACK);
        g.drawString("Ortalama Hız: " + averageSpeed, 1000, 30);

     
        g.setFont(currentFont);
    }

    public void step() {

        for (int a = 0; a < cars.size(); a++) {
            Vehicle v = cars.get(a);

            if (collision(v.getX() + v.getSpeed(), v.getY(), v) == false) {
                v.setX(v.getX() + v.getSpeed());

                if (v.getX() > ROAD_WIDTH) {
                    if (collision(0, v.getY(), v) == false) {
                        v.setX(0);
                        carCount++;
                        saveCarCountToFile();
                       
                    }
                }
            } else { 
                if ((v.getY() > 40) && (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)) {
                    v.setY(v.getY() - LANE_HEIGHT);

                } else if ((v.getY() < 40 + 3 * LANE_HEIGHT)
                        && (collision(v.getX(), v.getY() + LANE_HEIGHT, v) == false)) {
                    v.setY(v.getY() + LANE_HEIGHT);
                }
            }

            v.decreaseFuel();
            v.checkLowFuel();  

             if (v.getFuelLevel() <= 33.3) { 
                goToNearestGasStation(v);

            }

             if (v.getX() > ROAD_WIDTH) {
                v.setX(0);
            }
        }
        double averageSpeed = calculateAverageSpeed();
        System.out.println("Trafikteki araçların hız ortalaması: " + averageSpeed);
    }

    public boolean collision(int x, int y, Vehicle v) {
        for (int a = 0; a < cars.size(); a++) {
            Vehicle u = cars.get(a);
            if (y == u.getY()) {
                if (u.equals(v) == false) {
                    if (x < u.getX() + u.getWidth() && x + v.getWidth() > u.getX()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getCarCount() {
        return carCount;
    }

    public void resetCarCount() {
        carCount = 0;
    }

    public void goToNearestGasStation(Vehicle v) {
        GasStation nearestStation = findNearestGasStation(v);
        if (nearestStation != null) {
            synchronized (nearestStation) {
                v.setX(nearestStation.x);
                v.setY(nearestStation.y);
                gasStationQueue.offer(v);  
                v.decreaseFuel();  
            }
        }
    }

     public void refillFuelForNextVehicle() {

        if (!gasStationQueue.isEmpty()) {
            Vehicle nextVehicle = gasStationQueue.poll();
            synchronized (nextVehicle) {
                nextVehicle.refillFuel();
                nextVehicle.notify();
                
            }
        }
    }

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

    public void addToQueue(Vehicle vehicle) {
        synchronized (gasStationQueue) {
            gasStationQueue.offer(vehicle);  
        }
    }



    public ArrayList<Vehicle> getCars() {
        return cars;
    }
    

    
    public  void dosyayaYeniSutunEkle(String kaynakDosya, String hedefDosya) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(kaynakDosya));
        BufferedWriter writer = new BufferedWriter(new FileWriter(hedefDosya));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] satirBilgileri = line.split(",");
            String tur = satirBilgileri[0];
            String plaka = satirBilgileri[1];
            int hiz = Integer.parseInt(satirBilgileri[2]);

            double fiyat = 0;
            if (tur.equals("Kamyon")) {
                fiyat = 5000;
            } else if (tur.equals("Arazi")) {
                fiyat = 6000;
            } else if (tur.equals("Otomobil")) {
                fiyat = 4000;
            }

            String yeniSatir = tur + "," + plaka + "," + hiz + "," + fiyat;

            writer.write(yeniSatir);
            writer.newLine();
        }

        reader.close();
        writer.close();

        System.out.println("Yeni sütunlar başarıyla eklendi.");
    }
    public double calculateAverageSpeed() {
        if (cars.isEmpty()) {
            return 0.0;
        }

        double totalSpeed = 0.0;

        for (Vehicle car : cars) {
            totalSpeed += car.getSpeed();
        }

        return totalSpeed / cars.size();
    }


}
 
 

 
