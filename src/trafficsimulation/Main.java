package trafficsimulation;


import trafficsimulation.DosyaIslemleri;



public class Main {

	public static void main(String[] args) {
			DosyaIslemleri file = new DosyaIslemleri();
		file.createNewFile("trafikBilgi.txt");
		file.createNewFile("MTV.txt");
		file.createNewFile("vergi.txt");
 
		new Traffic();
		new MTVEkran();	
		
	}
}
 
