package trafficsimulation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ceza {

    private LocalDateTime zaman;
    private String ihlalTuru;
    private int cezaMiktari;

    public Ceza(String ihlalTuru, int cezaMiktari){
        this.zaman = LocalDateTime.now();
        this.ihlalTuru = ihlalTuru;
        this.cezaMiktari = cezaMiktari;
    }

   
    public void yazdir() {
     
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = zaman.format(formatter);
      
        System.out.println(formattedDateTime + " - " + ihlalTuru + " ihlali için ceza: " + cezaMiktari + " TL");     
        dosyayaYaz(formattedDateTime, ihlalTuru, cezaMiktari);
    }

  
    private void dosyayaYaz(String formattedDateTime, String ihlalTuru, int cezaMiktari) {
        try (FileWriter writer = new FileWriter("ceza_bilgileri.txt", true)) {
           
            writer.write(formattedDateTime + " - " + ihlalTuru + " ihlali için ceza: " + cezaMiktari + " TL\n");
        } catch (IOException e) {        
            e.printStackTrace();
        }
    }
   
    public LocalDateTime getZaman() {
        return zaman;
    }

    public void setZaman(LocalDateTime zaman) {
        this.zaman = zaman;
    }

    public String getIhlalTuru() {
        return ihlalTuru;
    }

    public void setIhlalTuru(String ihlalTuru) {
        this.ihlalTuru = ihlalTuru;
    }

    public int getCezaMiktari() {
        return cezaMiktari;
    }

    public void setCezaMiktari(int cezaMiktari) {
        this.cezaMiktari = cezaMiktari;
    }

    // Ceza işlemlerini uygular
    public void applyPenalty(List<Vehicle> vehiclesOnRed) {
        for (Vehicle car : vehiclesOnRed) {
            car.addPenaltyPoints(cezaMiktari);
            car.setAllowedToPass(false);
        }
        yazdir();
    }
}
