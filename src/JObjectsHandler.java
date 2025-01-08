import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JObjectsHandler {
    private MainApplicationWindow ApplicationMainJFrame;

    private KeyboardHandler keyboardHandler;
    private PopupMenuHandler popupMenuHandler;

    JObjectsHandler() {
        System.out.println("JObjectsHandler constructor started");

        SwingUtilities.invokeLater(() -> {
            System.out.println("Creating MainApplicationWindow");
            this.ApplicationMainJFrame = new MainApplicationWindow("Zadanie 1");

            System.out.println("Setting and adding ImageShower");
            this.ApplicationMainJFrame.setAndAddImageShower(new ImageShower(ApplicationMainJFrame));
            this.ApplicationMainJFrame.getImageShower().repaint();

            System.out.println("Setting and adding RightLabels");
            this.ApplicationMainJFrame.setAndAddRightLabels(new RightLabels(ApplicationMainJFrame));

            System.out.println("Setting and adding SelectionHandler");
            this.ApplicationMainJFrame.setAndAddSelectionHandler(new SelectionHandler(ApplicationMainJFrame));

            System.out.println("Setting and adding BottomLabels");
            this.ApplicationMainJFrame.setAndAddBottomLabels(new BottomLabels(ApplicationMainJFrame));

            System.out.println("Repainting ApplicationMainJFrame");
            this.ApplicationMainJFrame.getContentPane().setComponentZOrder(ApplicationMainJFrame.getSelectionHandler(), 0);
            this.ApplicationMainJFrame.getContentPane().setComponentZOrder(ApplicationMainJFrame.getImageShower(), 2);
            this.ApplicationMainJFrame.getContentPane().revalidate();
            this.ApplicationMainJFrame.getContentPane().repaint();
            this.ApplicationMainJFrame.getSelectionHandler().repaint();
            this.ApplicationMainJFrame.repaint();

            System.out.println("Creating keyHandler");
            this.keyboardHandler = new KeyboardHandler(ApplicationMainJFrame);
            ApplicationMainJFrame.addKeyListener(this.keyboardHandler);

            System.out.println("Creating popupMenuHandler");
            this.popupMenuHandler = new PopupMenuHandler(this.keyboardHandler);
            JPopupMenu popupMenu = this.popupMenuHandler.createPopupMenu();

            System.out.println("Adding mouse listeners");
            enableGlobalPopupMenu(ApplicationMainJFrame, popupMenu);

            System.out.println("JObjectsHandler constructor finished!");
        });
    }


    public MainApplicationWindow getApplicationMainJFrame() {
        return ApplicationMainJFrame;
    }

    public KeyboardHandler getKeyboardHandler() {
        return keyboardHandler;
    }
    public void enableGlobalPopupMenu(JFrame applicationFrame, JPopupMenu popupMenu) {
        // Add a mouse listener to the entire JFrame
        applicationFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        // Add the same mouse listener to all components in the JFrame
        addMouseListenerToAllComponents(applicationFrame, popupMenu);
    }

    private void addMouseListenerToAllComponents(Container container, JPopupMenu popupMenu) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                addMouseListenerToAllComponents((Container) component, popupMenu);
            }

            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });
        }
    }
}
