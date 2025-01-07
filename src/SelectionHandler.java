import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectionHandler extends JPanel {
    MODE mode = MODE.rectangle;
    Zaznaczenie selection = new Zaznaczenie();
    boolean selecting = false; // Flag to check if selection is in progress

    SelectionHandler(MainApplicationWindow mainApplicationWindow) {
        ImageShower imageShower = mainApplicationWindow.getImageShower();
        /*this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Record the starting position of the selection using coordinates from ImageShower
                if (SwingUtilities.isLeftMouseButton(e)) {
                    selecting = true;
                    int startX = imageShower.getCurrentMouseX();
                    int startY = imageShower.getCurrentMouseY();
                    selection.setStartX(startX);
                    selection.setStartY(startY);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Finalize the selection using coordinates from ImageShower
                if (SwingUtilities.isLeftMouseButton(e) && selecting) {
                    int currentX = imageShower.getCurrentMouseX();
                    int currentY = imageShower.getCurrentMouseY();
                    selection.setWidth(Math.abs(currentX - selection.getStartX()));
                    selection.setHeight(Math.abs(currentY - selection.getStartY()));
                    selecting = false;
                    repaint(); // Repaint to show the final selection
                }
            }
        });

        // Add MouseMotionListener to handle dragging and updating selection
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selecting) {
                    // Update the selection dimensions using coordinates from ImageShower
                    int currentX = imageShower.getCurrentMouseX();
                    int currentY = imageShower.getCurrentMouseY();
                    selection.setWidth(Math.abs(currentX - selection.getStartX()));
                    selection.setHeight(Math.abs(currentY - selection.getStartY()));
                    repaint(); // Repaint during dragging to show the selection
                }
            }
        });*/
    }

    /*@Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (selection.getWidth() > 0 && selection.getHeight() > 0) {
            // Draw the selection rectangle if it's been created
            graphics.setColor(Color.RED); // You can choose any color
            graphics.drawRect(selection.getStartX(), selection.getStartY(),
                    selection.getWidth(), selection.getHeight());
        }
    }*/
}

enum MODE {
    rectangle,
    line
}
