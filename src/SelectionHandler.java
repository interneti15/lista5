import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class SelectionHandler extends JPanel {
    private Zaznaczenie selection = new Zaznaczenie();
    private ArrayList<Zaznaczenie> history = new ArrayList<>();
    boolean selecting = false;
    public ClosestLine closestLine = null;

    public SelectionHandler(MainApplicationWindow mainApplicationWindow) {
        ImageShower imageShower = mainApplicationWindow.getImageShower();


        this.setOpaque(false);

        this.setLayout(null);
        this.setBounds(0, 0, imageShower.getWidth(), imageShower.getHeight());

        this.setVisible(true);


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

                    if (!selection.isIni()){
                        selection.firstWhenAtLines(Main.getjObjectsHandler().getApplicationMainJFrame().getSelectionHandler());
                    }

                    selecting = true;
                    closestLine = selection.findClosest(startX, startY);
                    selection.changeLines(startX, startY,closestLine);
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
                    selection.changeLines(currentX, currentY,closestLine);
                    selecting = false;
                }

                if (selection.getWidth() > 0 && selection.getHeight() > 0) {
                    selection.revalidate();
                    selection = new Zaznaczenie(selection);
                    history.add(selection.copy());

                }
                selection.revalidate();
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
                        selection.changeLines(currentX, currentY,closestLine);
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (selection.getMode() == MODE.rectangle) {
            if (selection.getWidth() > 0 && selection.getHeight() > 0) {
                graphics.setColor(Color.CYAN);
                graphics.drawRect(Math.min(selection.getStartX(), selection.getEndX()), Math.min(selection.getStartY(), selection.getEndY()), Math.abs(selection.getEndX() - selection.getStartX()), Math.abs(selection.getEndY() - selection.getStartY()));
            }
        } else if (selection.getMode() == MODE.line) {

            if (!selection.isIni()){
                return;
            }

            graphics.setColor(Color.CYAN);
            graphics.drawLine(selection.getStartX(), 0, selection.getStartX(), getHeight());
            graphics.drawLine(selection.getEndX(), 0, selection.getEndX(), getHeight());


            graphics.drawLine(0, selection.getStartY(), getWidth(), selection.getStartY());
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

    public Zaznaczenie getSelection() {
        return selection;
    }

    public void setSelection(Zaznaczenie selection) {
        this.selection = selection;
    }

    public void switchSelectionMode(){

        if (this.selection.getMode() == MODE.rectangle){
            this.selection.setMode(MODE.line);
        }
        else {
            this.selection.setMode(MODE.rectangle);
        }

        if (Main.getjObjectsHandler().getApplicationMainJFrame().getImageShower().getImageObject() != null){
            this.selection = new Zaznaczenie(this.selection);
            this.history.add(selection.copy());
        }

        this.repaint();
    }

    public ArrayList<Zaznaczenie> getHistory() {
        return history;
    }
}

