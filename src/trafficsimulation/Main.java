package trafficsimulation;


import trafficsimulation.DosyaIslemleri;



public class Main {

	public static void main(String[] args) {
		
		DosyaIslemleri file = new DosyaIslemleri();
		file.createNewFile("trafikDurumu.txt");
		
		new Traffic();
		
	}
}
 
