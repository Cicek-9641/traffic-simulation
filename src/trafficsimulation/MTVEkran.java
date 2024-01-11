package trafficsimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MTVEkran extends JFrame {

	 JTextField plakaField;
	    JLabel vergiLabel;

	    ArrayList<Vergi> vergiBilgileri;

	    public MTVEkran() {
	        plakaField = new JTextField("Plakayı Girin");
	        add(plakaField);

	        vergiLabel = new JLabel("Ödenmesi gereken MTV tutarı: ");
	        add(vergiLabel);

	        JButton hesaplaButton = new JButton("MTV Sorgula");
	        hesaplaButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                calculateTaxAndDisplay();
	            }
	        });
	        add(hesaplaButton);

	        loadTaxInformationFromFile("vergi.txt");

	        setSize(300, 200);
	        setLayout(new FlowLayout());
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);
	    }

	    private void calculateTaxAndDisplay() {
	        String plaka = plakaField.getText().trim().toUpperCase();

	        for (Vergi bilgi : vergiBilgileri) {
	            if (bilgi.getPlaka().equals(plaka)) {
	                double vergiMiktari = bilgi.getVergiMiktari();
	                vergiLabel.setText("Vergi Miktarı: " + vergiMiktari + " TL");
	                return;
	            }
	        }

	        vergiLabel.setText("Araba bulunamadı.");
	    }

	    private void loadTaxInformationFromFile(String dosyaAdi) {
	        vergiBilgileri = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if (parts.length == 4) {
	                    String plaka = parts[1].trim().toUpperCase();
	                    double vergiMiktari = Double.parseDouble(parts[3].trim());
	                    Vergi bilgi = new Vergi(plaka, vergiMiktari);
	                    vergiBilgileri.add(bilgi);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    
}

 