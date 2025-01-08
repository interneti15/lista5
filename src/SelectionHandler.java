import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class SelectionHandler extends JPanel {
    //MODE mode = MODE.rectangle;
    Zaznaczenie selection = new Zaznaczenie();
    ArrayList<Zaznaczenie> history = new ArrayList<>();
    boolean selecting = false;

    // Ensure correct setup
    public SelectionHandler(MainApplicationWindow mainApplicationWindow) {
        ImageShower imageShower = mainApplicationWindow.getImageShower();

        // Set this panel as non-opaque (transparent background)
        this.setOpaque(false);

        this.setLayout(null); // Avoid conflicts with layout managers
        this.setBounds(0, 0, imageShower.getWidth(), imageShower.getHeight()); // Set size to cover imageShower

        this.setVisible(true);

        // Mouse listeners for selection area
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CheckBounds bounds = new CheckBounds(e.getX(), e.getY(), SelectionHandler.this);
                int startX = bounds.x;
                int startY = bounds.y;

                if (selection.getMode() == MODE.rectangle) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        selecting = true;
                        selection.setStartCoords(startX, startY);
                    }
                } else if (selection.getMode() == MODE.line) {
                    selecting = true;
                    selection.changeLines(startX, startY);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                CheckBounds bounds = new CheckBounds(e.getX(), e.getY(), SelectionHandler.this);
                int currentX = bounds.x;
                int currentY = bounds.y;

                if (selection.getMode() == MODE.rectangle) {
                    if (SwingUtilities.isLeftMouseButton(e) && selecting) {
                        selection.setEndCoords(currentX, currentY);
                        selecting = false;
                    }
                } else if (selection.getMode() == MODE.line) {
                    selection.changeLines(currentX, currentY);
                    selecting = false;
                }

                if (selection.getWidth() > 0 && selection.getHeight() > 0) {
                    history.add(selection.copy());
                }
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selecting) {
                    CheckBounds bounds = new CheckBounds(e.getX(), e.getY(), SelectionHandler.this);
                    int currentX = bounds.x;
                    int currentY = bounds.y;

                    if (selection.getMode() == MODE.rectangle) {
                        selection.setEndCoords(currentX, currentY);
                    } else if (selection.getMode() == MODE.line) {
                        selection.changeLines(currentX, currentY);
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (Main.getjObjectsHandler() != null && Main.getjObjectsHandler().getApplicationMainJFrame() != null) {
            //this.setBounds(0, 0, Main.getjObjectsHandler().getApplicationMainJFrame().getImageShower().getWidth(), Main.getjObjectsHandler().getApplicationMainJFrame().getImageShower().getHeight() + 1);
        }

        if (selection.getMode() == MODE.rectangle) {
            if (selection.getWidth() > 0 && selection.getHeight() > 0) {
                graphics.setColor(Color.CYAN);
                graphics.drawRect(Math.min(selection.getStartX(), selection.getEndX()), Math.min(selection.getStartY(), selection.getEndY()), Math.abs(selection.getEndX() - selection.getStartX()), Math.abs(selection.getEndY() - selection.getStartY()));
            }
        } else if (selection.getMode() == MODE.line) {

            if (!selection.isIni()){
                selection.firstWhenAtLines(this);
            }

            graphics.setColor(Color.CYAN); // Set color for lines
            graphics.drawLine(selection.getStartX(), 0, selection.getStartX(), getHeight()); // First vertical line (from top to bottom)
            graphics.drawLine(selection.getEndX(), 0, selection.getEndX(), getHeight());   // Second vertical line

            // Draw horizontal lines
            graphics.drawLine(0, selection.getStartY(), getWidth(), selection.getStartY()); // First horizontal line (from left to right)
            graphics.drawLine(0, selection.getEndY(), getWidth(), selection.getEndY());
        }
    }
    static class CheckBounds{
        public int x,y;
        CheckBounds(int x, int y, JPanel jPanel){
            this.x = Math.max(0, Math.min(x, jPanel.getWidth() - 1));
            this.y = Math.max(0, Math.min(y, jPanel.getHeight() - 1));
        }
    }

    public void refreshSize(){
        System.out.println("Selection Handler Width: " + this.getWidth() + " Height: " + this.getHeight());
        ImageShower imageShower = Main.getjObjectsHandler().getApplicationMainJFrame().getImageShower();
        this.setBounds(0, 0, imageShower.getWidth(), imageShower.getHeight());
        System.out.println("Selection Handler Width: " + this.getWidth() + " Height: " + this.getHeight());
        MODE temp = selection.getMode();
        this.selection = new Zaznaczenie();
        this.selection.setMode(temp);
        this.repaint();
    }
}

