import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ImageShower extends JPanel {
    private String imagePath = "";
    private Image imageObject;
    private int currentMouseX;
    private int currentMouseY;
    private double scaleFactor = 1;
    private MainApplicationWindow mainJFrame;

    ImageShower(MainApplicationWindow mainJFrame) {
        this.mainJFrame = mainJFrame;
        this.setBackground(Color.BLACK);
        this.setBounds(0, 0, 1, 1);
        this.setVisible(true);
        this.refreshImage();
        //this.findScale();

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

    private void findScale() {

        int maxWidth = this.mainJFrame.getWidth() - RightLabels.getWIDTH();
        int maxHeight = this.mainJFrame.getHeight() - BottomLabels.getHEIGHT() - 25;

        int imageWidth = imageObject.getWidth(null);
        int imageHeight = imageObject.getHeight(null);

        if (imageWidth == -1 || imageHeight == -1) {
            return; // Avoid null pointer or invalid image dimensions
        }

        if (imageWidth < maxWidth && imageHeight < maxHeight) {
            // Scale up
            this.scaleFactor = Math.min(maxWidth / (double) imageWidth, maxHeight / (double) imageHeight);
        } else {
            // Scale down
            this.scaleFactor = Math.min((double) maxWidth / imageWidth, (double) maxHeight / imageHeight);
        }
    }


    private void refreshImage() {
        try {
            if (imagePath == null || imagePath.isEmpty()) {
                return;
            }
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
        this.mainJFrame.getSelectionHandler().getHistory().clear();
        this.refreshImage();
        this.repaint();
        this.mainJFrame.getSelectionHandler().refreshSize();
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public Image getImageObject() {
        return imageObject;
    }
}
