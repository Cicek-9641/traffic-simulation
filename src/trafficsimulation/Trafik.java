package TrafikSimulasyon;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Trafik implements Runnable, ActionListener {
	
	
 	

	JFrame frame = new JFrame("Nesne dersi odev");

	Road road = new Road();
	
	

	altYol ayol = new altYol();
	
	JButton start = new JButton("Baslat");
	JButton stop = new JButton("Duraklat");
	JLabel throughput = new JLabel("Verim:0");

	Container south = new Container();

	JButton semi = new JButton("SEMI");

	JButton suv = new JButton("SUV");
	JButton sports = new JButton("Spor");
	JButton suvup = new JButton("SUV guncelle");

	Container west = new Container();

	boolean running = false;
	
	
	int carCount = 0;
	long startTime = 0;

	Timer timer;
	boolean isRed = true;
	private JTextField slowdownField;
	private JTextField hizArtirField;

	public Trafik() {
   

		slowdownField = new JTextField();
		Dimension fieldSize = new Dimension(100, 30);
		slowdownField.setPreferredSize(fieldSize);

		hizArtirField = new JTextField();
		Dimension fieldSize1 = new Dimension(200, 130);
		hizArtirField.setPreferredSize(fieldSize1);
		
		JButton slowdownButton = new JButton("Hız Azalt");
		JButton hizlanButton = new JButton("Hız Artısı Yap");


 		slowdownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int slowdownAmount = Integer.parseInt(slowdownField.getText());

					Semi semi = new Semi(0, 30);
					road.addCar(semi);
			//		semi.slowDown(slowdownAmount);
			 		 
				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null, "  geçerli bir sayı girin.");
				}
			}
		});
 		
 		hizlanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int hizlanAmount = Integer.parseInt(hizlanButton.getText());

					Semi semi = new Semi(0, 30);
					road.addCar(semi);
					semi.hizlanButton(hizlanAmount);
					 
				} catch (NumberFormatException ex) {

					JOptionPane.showMessageDialog(null, "  geçerli bir sayı girin.");
				}
			}
		});
	 
 		

		west.setLayout(new GridLayout(5, 1));

		JPanel slowdownPanel = new JPanel();
		slowdownPanel.add(slowdownButton);
		slowdownPanel.add(slowdownField, BorderLayout.CENTER);
		
		JPanel hizArtirPanel = new JPanel();
		hizArtirPanel.add(hizlanButton);
		hizArtirPanel.add(hizArtirField, BorderLayout.CENTER);
		
		semi.setBackground(Color.ORANGE);
 		suv.setBackground(Color.PINK);
		sports.setBackground(Color.lightGray);
		start.setBackground(Color.green);
		stop.setBackground(Color.red);

		slowdownButton.setBackground(Color.MAGENTA);
		suvup.setBackground(Color.orange);

		west.add(semi);
		west.add(suv);
		west.add(sports);
		
		west.add(slowdownPanel);
		west.add(suvup);
		west.add(hizArtirPanel);
        ayol.add(semi);

		frame.setSize(1500, 500);
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		//frame.add(ayol, BorderLayout.AFTER_LINE_ENDS);
	 
		south.setLayout(new GridLayout(1, 6));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(throughput);
		
	 

		west.setLayout(new GridLayout(3, 1));
		
		west.add(semi);
		semi.addActionListener(this);
	
		west.add(suv);
		suv.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		
		
		west.add(suvup);
		suvup.addActionListener(this);
      
		 
		frame.add(south, BorderLayout.SOUTH);

		frame.add(west, BorderLayout.WEST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
 
		frame.repaint();
		

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		 
		
		if (event.getSource().equals(start)) {
			if (running == false) {
				running = true;
				road.resetCarCount();
				startTime = System.currentTimeMillis();
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (event.getSource().equals(stop)) {
			running = false;
		}

		if (event.getSource().equals(semi)) {
	
			int slowDownAmount = 2;
			Semi semi = new Semi(0, 30);
			road.addCar(semi);
 
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20)
				for (int y = 40; y < 480; y = y + 120) {
					semi.setX(x);
					semi.setY(y);
					if (road.collision(x, y, semi) == false) {
						frame.repaint();
						return;

					}
				 
				}
		semi.slowDown(slowDownAmount);
		}
 
		if (event.getSource().equals(suv)) {
			SUV suv = new SUV(0, 30);
			road.addCar(suv);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20)
				for (int y = 40; y < 480; y = y + 120) {
					suv.setX(x);
					suv.setY(y);
 					if (road.collision(x, y, suv) == false) {
						frame.repaint();
						return;

					}

				} 
		}

		if (event.getSource().equals(sports)) {
			Sports sports = new Sports(0, 30);
			road.addCar(sports);

			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20)
				for (int y = 40; y < 480; y = y + 120) {
					sports.setX(x);
					sports.setY(y);
					if (road.collision(x, y, sports) == false) {
						frame.repaint();
						return;
					}
				}
		}
	}
	
	@Override
	public void run() {
		while (running == true) {
			road.step();
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
