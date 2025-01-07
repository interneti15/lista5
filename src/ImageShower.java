import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ImageShower extends JPanel {
    private final String imagePath = "C:\\Users\\inter\\Downloads\\image.jpg";
    private Image imageObject;
    private int scaleFactor = 1;

    private int currentMouseX;
    private int currentMouseY;

    public int getCurrentMouseX() {
        return currentMouseX;
    }

    public int getCurrentMouseY() {
        return currentMouseY;
    }

    ImageShower(MainApplicationWindow mainJFrame) {
        this.refreshImage();
        this.setBackground(Color.BLACK);
        this.setBounds(0, 0, 1, 1);
        this.setVisible(true);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                currentMouseX = e.getX();
                currentMouseY = e.getY();
            }
        });

        repaint();
    }

    void refreshImage() {
        try {
            this.imageObject = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            this.imageObject = null;
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (imageObject == null) {
            return;
        }
        this.findScale();
        this.setBounds(0, 0, imageObject.getWidth(null) * scaleFactor, imageObject.getHeight(null) * scaleFactor);
        graphics.drawImage(imageObject, 0, 0, getWidth(), getHeight(), this);
    }

    void findScale() {
        int potentialScaleHeight = 1;
        int potentialScaleWidth = 1;

        // Calculate scale based on height
        while (imageObject.getHeight(null) * potentialScaleHeight < Main.getjObjectsHandler().getApplicationMainJFrame().getHeight() - BottomLabels.getHEIGHT()) {
            potentialScaleHeight++;
        }

        // Calculate scale based on width
        while (imageObject.getWidth(null) * potentialScaleWidth < Main.getjObjectsHandler().getApplicationMainJFrame().getWidth() - RightLabels.getWIDTH()) {
            potentialScaleWidth++;
        }

        scaleFactor = Math.min(potentialScaleHeight - 1, potentialScaleWidth - 1);
    }
}
