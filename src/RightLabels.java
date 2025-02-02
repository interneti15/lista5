import javax.swing.*;
import java.awt.*;

public class RightLabels extends JPanel {
    private static final int WIDTH = 250;
    private static final int LABEL_WIDTH = WIDTH - 15; 
    private static final int LABEL_HEIGHT = 150;
    private static final int EMPTY_SPACES = 0;  
    private static final int spaceBetweenLabels = 20; 

    public static int getWIDTH() {
        return WIDTH;
    }

    RightLabels(MainApplicationWindow mainJFrame) {
        this.setBackground(MyColors.MyColor);
        this.setBounds(mainJFrame.getWidth() - WIDTH, 0, WIDTH, mainJFrame.getHeight());
        this.setLayout(null); 

        
        addLabel("  W - wczytaj plik z obrazkiem", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels);
        addLabel("  Z - zapisz zaznaczony fragment obrazka", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 2);
        addLabel("  K - kadrowanie za pomocą kwadratu", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 3);
        addLabel("  L - kadrowanie za pomocą czterech linii", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 4);
        addLabel("  C - powrót do obrazka bez zaznaczenia", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 5);
        addLabel("  H - historia zaznaczeń", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 6);
        addLabel("  Q - wyjście z programu", EMPTY_SPACES * LABEL_HEIGHT + spaceBetweenLabels * 7);

    }

    private void addLabel(String text, int yPosition) {
        JLabel label = new JLabel(text);
        label.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        label.setLocation(0, yPosition);  
        label.setForeground(Color.WHITE); 
        this.add(label);
    }
}
