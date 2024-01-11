package trafficsimulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DosyaIslemleri {
	public void createNewFile(String path) {
		
	    File file = new File(path);
	    try {
	        FileWriter fw = new FileWriter(file, true);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
