import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ImageShower extends JPanel {
    private String imagePath = "C:\\Users\\inter\\Downloads\\image.jpg";
    private Image imageObject;
    private int currentMouseX;
    private int currentMouseY;
    private double scaleFactor = 1;

    ImageShower(MainApplicationWindow mainJFrame) {

        this.setBackground(Color.BLACK);
        this.setBounds(0, 0, 1, 1);
        this.setVisible(true);
        this.findScale();
        this.refreshImage();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                currentMouseX = e.getX();
                currentMouseY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentMouseX = e.getX();
                currentMouseY = e.getY();
            }
        });

        //repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (imageObject == null) {
            return;
        }
        //this.findScale();
        //this.setBounds(0, 0, imageObject.getWidth(null) * scaleFactor, imageObject.getHeight(null) * scaleFactor);
        graphics.drawImage(imageObject, 0, 0, getWidth(), getHeight(), this);
    }

    public int getCurrentMouseX() {
        return currentMouseX;
    }

    public int getCurrentMouseY() {
        return currentMouseY;
    }

    void findScale() {
        double potentialScaleHeight = 1;
        double potentialScaleWidth = 1;

        if (Main.getjObjectsHandler() == null) {
            return;
        }

        int maxWidth = Main.getjObjectsHandler().getApplicationMainJFrame().getWidth() - RightLabels.getWIDTH();
        int maxHeight = Main.getjObjectsHandler().getApplicationMainJFrame().getHeight() - BottomLabels.getHEIGHT();

        if (imageObject.getHeight(null) < maxHeight && imageObject.getWidth(null) < maxWidth) { // Calculate scale based on height
            while (imageObject.getHeight(null) * potentialScaleHeight < maxHeight) {
                potentialScaleHeight++;
            }

            // Calculate scale based on width
            while (imageObject.getWidth(null) * potentialScaleWidth < maxWidth) {
                potentialScaleWidth++;
            }

            this.scaleFactor = Math.min(potentialScaleHeight - 1, potentialScaleWidth - 1);
        } else {
            double i = 2;
            double j = 2;

            while (imageObject.getWidth(null) * potentialScaleWidth > maxWidth){
                potentialScaleWidth = 1/i;
            }

            while (imageObject.getHeight(null) * potentialScaleHeight > maxHeight){
                potentialScaleHeight = 1/j;
            }

            this.scaleFactor = Math.min(potentialScaleHeight, potentialScaleWidth);
        }
    }

    private void refreshImage() {
        try {
            this.imageObject = new ImageIcon(imagePath).getImage();
            this.findScale();
            System.out.println("Setting bounds: x=0, y=0, width=" + (imageObject.getWidth(null) * scaleFactor) +
                    ", height=" + (imageObject.getHeight(null) * scaleFactor) +
                    ", scaleFactor=" + scaleFactor);
            this.setBounds(0, 0, (int) (imageObject.getWidth(null) * scaleFactor), (int) (imageObject.getHeight(null) * scaleFactor));
        } catch (Exception e) {
            this.imageObject = null;
        }
    }

    public void setImagePathAndRepaint(String path) {
        this.imagePath = path;
        this.refreshImage();
        this.repaint();
        Main.getjObjectsHandler().getApplicationMainJFrame().getSelectionHandler().refreshSize();
    }
}
