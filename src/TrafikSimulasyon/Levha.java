package TrafikSimulasyon;

	import javax.swing.*;
	import java.awt.*;

	public class Levha extends JPanel {
	    private ImageIcon icon;

	    public Levha(ImageIcon icon) {
	        this.icon = icon;
	        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
	        setBorder(BorderFactory.createLineBorder(Color.RED)); 
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (icon != null) {
	            g.drawImage(icon.getImage(), 0, 0, this);
	        }
	    }
	}
