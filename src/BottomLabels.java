import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class BottomLabels extends JPanel {
    private static final int HEIGHT = 50;
    private static final int LABEL_WIDTH = 100; // Width of each label
    private static final int EMPTY_SPACES = 0;  // Number of empty spaces at the front
    private JLabel xLabel;  // Label to display X coordinate
    private JLabel yLabel;  // Label to display Y coordinate
    private JLabel colorLabel; // Color

    BottomLabels(MainApplicationWindow mainJFrame) {
        ImageShower imageShower = mainJFrame.getImageShower();
        if (imageShower == null) {
            for (int i = 0; i < 100; i++) {
                System.out.println("111111");
            }
        }
        this.setBackground(MyColors.MyColor);
        this.setBounds(0, (mainJFrame.getHeight() - 50 - 25), mainJFrame.getWidth(), HEIGHT);
        this.setLayout(null); // No layout manager for manual positioning
        //this.setPreferredSize(new Dimension(mainJFrame.getWidth(), HEIGHT));

        // Create labels for X and Y coordinates
        xLabel = new JLabel("X: 0");
        yLabel = new JLabel("Y: 0");
        colorLabel = new JLabel("Color:");


        // Set the font size and color for the labels
        Font labelFont = new Font("Arial", Font.BOLD, 16); // Font size 16, bold
        xLabel.setFont(labelFont);
        yLabel.setFont(labelFont);
        colorLabel.setFont(labelFont);
        xLabel.setForeground(Color.WHITE);  // Set text color to white
        yLabel.setForeground(Color.WHITE);
        colorLabel.setForeground(Color.WHITE);  // Set text color to white
        // Set text color to white

        // Position the labels
        xLabel.setBounds(EMPTY_SPACES * LABEL_WIDTH, 10, LABEL_WIDTH, 30);
        yLabel.setBounds(LABEL_WIDTH + 10, 10, LABEL_WIDTH, 30);
        colorLabel.setBounds(LABEL_WIDTH + 10 + LABEL_WIDTH + 10, 10, LABEL_WIDTH*3, 30);

        this.add(xLabel);
        this.add(yLabel);
        this.add(colorLabel);

        // Mouse motion listener to update the labels with the mouse coordinates
        mainJFrame.getSelectionHandler().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                updateBottomLabelsText(x, y, mainJFrame);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                updateBottomLabelsText(x, y, mainJFrame);
            }
        });

        this.revalidate();
        this.repaint();
    }

    private void updateBottomLabelsText(int x, int y, MainApplicationWindow mainJFrame) {

        SelectionHandler.CheckBounds checkBounds = new SelectionHandler.CheckBounds(x, y, mainJFrame.getSelectionHandler());

        xLabel.setText("X: " + checkBounds.x);
        yLabel.setText("Y: " + checkBounds.y);
        ImageShower imageShower = mainJFrame.getImageShower();
        Image image = imageShower.getImageObject();

        if (image != null && checkBounds.x / imageShower.getScaleFactor() >= 0 && checkBounds.y / imageShower.getScaleFactor() >= 0 &&
            checkBounds.x / imageShower.getScaleFactor() < image.getWidth(null) && checkBounds.y / imageShower.getScaleFactor() < image.getHeight(null)) {

            BufferedImage bufferedImage = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            int rgb = bufferedImage.getRGB((int) (checkBounds.x / imageShower.getScaleFactor()), (int) (checkBounds.y / imageShower.getScaleFactor()));
            int red = (rgb >> 16) & 0xFF;
            int green = (rgb >> 8) & 0xFF;
            int blue = rgb & 0xFF;

            String redString = String.format("%3d", red).replace(" ", "_");
            String greenString = String.format("%3d", green).replace(" ", "_");
            String blueString = String.format("%3d", blue).replace(" ", "_");
            colorLabel.setText(String.format("RGB: %s, %s, %s, %s", redString, greenString, blueString, MyColors.nameColor(new Color(red, green, blue))));
        } else {
            colorLabel.setText("RGB: N/A");
        }
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}


