package trafficsimulation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ceza {

    private LocalDateTime zaman;
    private String ihlalTuru;
    private int cezaMiktari;

    public Ceza(String ihlalTuru, int cezaMiktari){
        this.zaman = LocalDateTime.now();
        this.ihlalTuru = ihlalTuru;
        this.cezaMiktari = cezaMiktari;
    }

    // Ceza bilgilerini ekrana yazdırır ve dosyaya kaydeder
    public void yazdir() {
        // Tarih ve saat bilgisini belirli bir formata göre biçimlendirir
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = zaman.format(formatter);

        // Ekrana ceza bilgilerini yazdırır
        System.out.println(formattedDateTime + " - " + ihlalTuru + " ihlali için ceza: " + cezaMiktari + " TL");
        // Dosyaya ceza bilgilerini ekler
        dosyayaYaz(formattedDateTime, ihlalTuru, cezaMiktari);
    }

    // Dosyaya ceza bilgilerini ekler
    private void dosyayaYaz(String formattedDateTime, String ihlalTuru, int cezaMiktari) {
        try (FileWriter writer = new FileWriter("ceza_bilgileri.txt", true)) {
            // Dosyaya tarih, ihlal türü ve ceza miktarını ekler
            writer.write(formattedDateTime + " - " + ihlalTuru + " ihlali için ceza: " + cezaMiktari + " TL\n");
        } catch (IOException e) {
            // Dosya işlemleri sırasında oluşan hataları ekrana yazdırır
            e.printStackTrace();
        }
    }

    // Getter ve setter metotları
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
}
