import javax.swing.*;
import java.awt.*;

public class RightLabels extends JPanel {
    private static final int WIDTH = 250;
    private static final int LABEL_WIDTH = WIDTH - 15; // Width of each label
    private static final int LABEL_HEIGHT = 150;
    private static final int EMPTY_SPACES = 0;  // Number of empty spaces at the front
    private static final int spaceBetweenLabels = 20; // Space between labels

    public static int getWIDTH() {
        return WIDTH;
    }

    RightLabels(MainApplicationWindow mainJFrame) {
        this.setBackground(Color.BLUE);
        this.setBounds(mainJFrame.getWidth() - WIDTH, 0, WIDTH, mainJFrame.getHeight());
        this.setLayout(null); // No layout manager for manual positioning

        // Adding labels with calculated positions
        addLabel("  W - wczytaj plik z obrazkiem", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels);
        addLabel("  Z - zapisz zaznaczony fragment obrazka", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 2);
        addLabel("  K - kadrowanie za pomocą kwadratu", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 3);
        addLabel("  L - kadrowanie za pomocą czterech linii", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 4);
        addLabel("  C - powrót do obrazka bez zaznaczenia", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 5);
        addLabel("  Q - wyjście z programu", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 6);

    }

    private void addLabel(String text, int yPosition) {
        JLabel label = new JLabel(text);
        label.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        label.setLocation(0, yPosition);  // Positioning the label
        label.setForeground(Color.WHITE); // Set the label text color to white for contrast
        this.add(label);
    }
}
