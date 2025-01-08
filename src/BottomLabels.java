import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class BottomLabels extends JPanel {
    private static final int HEIGHT = 50;
    private static final int LABEL_WIDTH = 100; // Width of each label
    private static final int EMPTY_SPACES = 0;  // Number of empty spaces at the front
    private JLabel xLabel;  // Label to display X coordinate
    private JLabel yLabel;  // Label to display Y coordinate

    BottomLabels(MainApplicationWindow mainJFrame) {
        ImageShower imageShower = mainJFrame.getImageShower();
        if (imageShower == null) {
            for (int i = 0; i < 100; i++) {
                System.out.println("111111");
            }
        }
        this.setBackground(Color.BLUE);
        this.setBounds(0, (mainJFrame.getHeight() - 50 - 25), mainJFrame.getWidth(), HEIGHT);
        this.setLayout(null); // No layout manager for manual positioning
        //this.setPreferredSize(new Dimension(mainJFrame.getWidth(), HEIGHT));

        // Create labels for X and Y coordinates
        xLabel = new JLabel("X: 0");
        yLabel = new JLabel("Y: 0");

        // Set the font size and color for the labels
        Font labelFont = new Font("Arial", Font.BOLD, 16); // Font size 16, bold
        xLabel.setFont(labelFont);
        yLabel.setFont(labelFont);
        xLabel.setForeground(Color.WHITE);  // Set text color to white
        yLabel.setForeground(Color.WHITE);  // Set text color to white

        // Position the labels
        xLabel.setBounds(EMPTY_SPACES * LABEL_WIDTH, 10, LABEL_WIDTH, 30);
        yLabel.setBounds(EMPTY_SPACES * LABEL_WIDTH + LABEL_WIDTH + 10, 10, LABEL_WIDTH, 30);

        this.add(xLabel);
        this.add(yLabel);

        // Mouse motion listener to update the labels with the mouse coordinates
        mainJFrame.getSelectionHandler().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                SelectionHandler.CheckBounds checkBounds = new SelectionHandler.CheckBounds(x, y, mainJFrame.getSelectionHandler());

                xLabel.setText("X: " + checkBounds.x);
                yLabel.setText("Y: " + checkBounds.y);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                SelectionHandler.CheckBounds checkBounds = new SelectionHandler.CheckBounds(x, y, mainJFrame.getSelectionHandler());

                xLabel.setText("X: " + checkBounds.x);
                yLabel.setText("Y: " + checkBounds.y);
            }
        });

        this.revalidate();
        this.repaint();
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}


