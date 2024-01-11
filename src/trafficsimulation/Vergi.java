package trafficsimulation;

class Vergi {
    private String plaka;
    private double vergiMiktari;

    public Vergi(String plaka, double vergiMiktari) {
        this.plaka = plaka;
        this.vergiMiktari = vergiMiktari;
    }

    public String getPlaka() {
        return plaka;
    }

    public double getVergiMiktari() {
        return vergiMiktari;
    }
}