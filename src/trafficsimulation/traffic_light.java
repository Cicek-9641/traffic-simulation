package trafficsimulation;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import trafficsimulation.Road;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.util.List;

public class traffic_light {

    int x;
    int y;
    boolean isDragging = false;

    Image myImage;
    public boolean isGreen;
    
    private List<Vehicle> vehiclesOnRed;

    public traffic_light(int x, int y, Road road) {
        this.x = x;
        this.y = y;

        try {
            myImage = ImageIO.read(new File("red.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        traffic_lightMouseListener mouseListener = new traffic_lightMouseListener(this, road);
        road.addMouseListener(mouseListener);
        road.addMouseMotionListener(mouseListener);
        
        vehiclesOnRed = new ArrayList<>();

    }

    public void setImage(ImageIcon newImageIcon) throws IOException {
        
        isGreen = true;
        myImage = ImageIO.read(new File("green.png"));
    }

    public void setImagee(ImageIcon newImageIcon) {
        try {
            isGreen = false;

            myImage = ImageIO.read(new File("red.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void addVehicleOnRed(Vehicle vehicle) {
        vehiclesOnRed.add(vehicle);
    }

    public List<Vehicle> getVehiclesOnRed() {
        return vehiclesOnRed;
    }

    public void clearVehiclesOnRed() {
        vehiclesOnRed.clear();
    }
    
    public void paintMe(Graphics g) {
        g.drawImage(myImage, x, y, null);
    }

}
