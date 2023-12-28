package trafficsimulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Road extends JPanel {
	
 
	private ImageIcon redIcon = new ImageIcon("red.png");
	private ImageIcon greenIcon = new ImageIcon("green.png");
	private ImageIcon surukleIcon = new ImageIcon("suruklered.png");
	ArrayList<DragDropLight> dragdrops = new ArrayList<DragDropLight>();
	ArrayList<DragDropLevha> dragdropslevha = new ArrayList<DragDropLevha>();
	ArrayList<DragDropRealTimeLight> dragdropsreallight = new ArrayList<DragDropRealTimeLight>();
	ArrayList<DragDropGreenLight> dragdropsgreenlight = new ArrayList<DragDropGreenLight>();
	
	//y
     ArrayList<GasStation> gasStations = new ArrayList<>();
    // Add a queue to store vehicles waiting at the gas station
    Queue<Vehicle> gasStationQueue = new LinkedList<>();
	//y
	
	private JButton surukleIconn = new JButton(surukleIcon);

	private JButton trafficLightButton = new JButton(redIcon);
	
	private JButton greenLightButton = new JButton(greenIcon);

 
	final int LANE_HEIGHT = 120;
	final int ROAD_WIDTH = 800;
	//final int VERTİCAL_ROAD_WIDTH = 30; dikey yolun genişliği
	
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	int carCount = 0;
	  public List<Vehicle> getCars() {
	        return cars;  
	    }
	private boolean isRedLight = true;

	public void saveCarCountToFile() {
		try {
			BufferedWriter writer = new BufferedWriter(
					new FileWriter("trafikBilgi.txt", true));

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = now.format(formatter);

			writer.write(formattedDateTime + " - " + carCount);
			writer.newLine();
			writer.close();
			System.out.println("Trafikteki toplam araÃ§ sayÄ±sÄ± trafikBilgi.txt dosyasÄ±na kaydediliyor.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Road() {

		super();
		 
		
		
		 DragDropLight dragdrop = new DragDropLight(300, LANE_HEIGHT * 3, this);
		 dragdrops.add(dragdrop);
		 
		 DragDropLevha dragdroplevha = new DragDropLevha(400, LANE_HEIGHT * 3, this);
		 dragdropslevha.add(dragdroplevha);
		 
		 DragDropRealTimeLight dragdropsreal  = new DragDropRealTimeLight(500, LANE_HEIGHT * 3, this);
		 dragdropsreallight.add(dragdropsreal);
		 
		 DragDropGreenLight dragdropsgreen   = new DragDropGreenLight(600, LANE_HEIGHT * 3, this);
		 dragdropsgreenlight.add(dragdropsgreen);
		  
		// Örnek olarak bir benzin istasyonu ekleme
	        GasStation gasStation = new GasStation(700, LANE_HEIGHT * 3, this);
	        GasStation gasStation2 = new GasStation(800, LANE_HEIGHT * 2, this);
	        gasStations.add(gasStation);
	        gasStations.add(gasStation2);
		 
		
	 
	}
	 
	
	public void addCar(Vehicle v) {
		cars.add(v);
	}
	
	public void addDragDropLight(DragDropLight dragdropLight) {
	    dragdrops.add(dragdropLight);
    }
	
     public void addDragDropLevha(DragDropLevha dragdropLevha) {
		dragdropslevha.add(dragdropLevha);
	 }
     public void addDragDropGreenLight(DragDropGreenLight dragdropsgreen) {
    	 dragdropsgreenlight.add(dragdropsgreen);
 	 }
     
     
     public void addGasStation(GasStation gasStation) {
         gasStations.add(gasStation);
     }
     
     
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*g.setColor(Color.YELLOW);
        	for (int c = 0; c < getWidth(); c += VERTICAL_ROAD_WIDTH + 10) {
            	g.fillRect(c, 0, VERTICAL_ROAD_WIDTH, getHeight());
        	}*/ //Dikey yol 
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		for (int a = LANE_HEIGHT; a < 600; a = a + LANE_HEIGHT) {
			for (int b = 0; b < getWidth(); b = b + 40) {
				g.fillRect(b, a, 30, 5);
			}
		}
		for (int a = 0; a < cars.size(); a++) {
			cars.get(a).paintMe(g);
		}
		  
		   for (int i = 0; i < dragdrops.size(); i++) {
			   dragdrops.get(i).paintMe(g);
	       }
		   for (int i = 0; i < dragdropslevha.size(); i++) {
			   dragdropslevha.get(i).paintMe(g);
	       }
		   
		   for (int i = 0; i < dragdropsreallight.size(); i++) {
			   dragdropsreallight.get(i).paintMe(g);
	       }
		   
		   for (int i = 0; i < dragdropsgreenlight.size(); i++) {
			   dragdropsgreenlight.get(i).paintMe(g);
	       }
		   for (int i = 0; i < gasStations.size(); i++) {
	            gasStations.get(i).paintMe(g);
	        }
	}
    
	public void step() {
		for (int a = 0; a < cars.size(); a++) {
			Vehicle v = cars.get(a);
			if ( collision(v.getX() + v.getSpeed(), v.getY(), v) == false) {
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

				} else if ((v.getY() > 40 + 3 * LANE_HEIGHT)
						&& (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)) {
				}
			}
			
		       v.decreaseFuel();
	            v.checkLowFuel(); // Check for low fuel and go to the nearest gas station if needed

	            // v.decreaseFuel(); // Decrease fuel at each step
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
	                gasStationQueue.offer(v); // Add the vehicle to the gas station queue
	                v.decreaseFuel(); // Decrease fuel to trigger refueling
	            }
	        }
	    }
  
	
	// Add a method to refill fuel for a vehicle in the queue
    public void refillFuelForNextVehicle() {
        if (!gasStationQueue.isEmpty()) {
            Vehicle nextVehicle = gasStationQueue.poll();
            synchronized (nextVehicle) {
                nextVehicle.refillFuel();
                nextVehicle.notify(); // Notify the waiting vehicle that refueling is complete
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
	

//    public void clearCars() {
//        cars.clear();
//    }

    public void addToQueue(Vehicle aThis) {
        throw new UnsupportedOperationException("Not supported yet."); 
    
    }
}
