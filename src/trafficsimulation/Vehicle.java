package TrafikSimulasyon;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Vehicle {
	
	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed = 0;
	  public Rectangle getBounds() {
	        return new Rectangle(x, y, width, height);
	    }
	public Vehicle(int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	
	public void paintMe(Graphics g) {
	
	}

    
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}


	public int getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}


	public void setX(int newx) {
		// TODO Auto-generated method stub
		x=newx;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public void setY(int newy) {
		// TODO Auto-generated method stub
		y=newy;
	}
	 public int getWidth() {
		 return width;
	 }
}
 
 