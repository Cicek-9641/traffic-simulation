package TrafikSimulasyon;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class DragAndDropIcon {
    private JLabel draggableIcon;
    private Road road;
    

    public DragAndDropIcon(Road roadRef) {
        road = roadRef;
        createDraggableIcon();
    }

    private void createDraggableIcon() {
        draggableIcon = new JLabel(new ImageIcon("suruklered.png"));
        draggableIcon.setSize(draggableIcon.getPreferredSize());

        draggableIcon.setTransferHandler(new TransferHandler("icon"));

        draggableIcon.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	JComponent comp = (JComponent) e.getSource();
                TransferHandler handler = comp.getTransferHandler();

                 Point offset = new Point(e.getPoint());

                 handler.exportAsDrag(comp, e, TransferHandler.COPY);

                 e = SwingUtilities.convertMouseEvent(comp, e, comp.getParent());
                int x = e.getX() - offset.x;
                int y = e.getY() - offset.y;

                 comp.setLocation(x, y);
                
            }
            
        });

        draggableIcon.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    Transferable transferable = evt.getTransferable();
                    if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                        evt.acceptDrop(DnDConstants.ACTION_COPY);
                        ImageIcon simge = (ImageIcon) transferable.getTransferData(DataFlavor.imageFlavor);

                        int x = evt.getLocation().x - road.getX();  
                        int y = evt.getLocation().y - road.getY(); 

                        Levha levha = new Levha(simge);
                        levha.setBounds(x, y, simge.getIconWidth(), simge.getIconHeight());

                        road.add(levha);
                        road.updateUI();
                        road.repaint();
                       

                         evt.dropComplete(true);
                    } else {
                        evt.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    evt.rejectDrop();
                }
            }
        });
    }


    public JLabel getDraggableIcon() {
        return draggableIcon;
    }
}
