package trafficsimulation;


import javax.swing.JFrame;

import trafficsimulation.DosyaIslemleri;



public class Main {

	public static void main(String[] args) {
		
		DosyaIslemleri file = new DosyaIslemleri();
		file.createNewFile("trafikBilgi.txt");
		new Traffic();
		 new yeni();		
	}
}
 
