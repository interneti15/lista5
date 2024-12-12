import javax.swing.*;
import java.awt.*;

public class MainApplicationWindow extends JFrame {

    ImageShower imageShower;
    BottomLabels bottomLabels;

    public void setAndAddBottomLabels(BottomLabels bottomLabels) {
        this.add(bottomLabels, BorderLayout.SOUTH);
        this.bottomLabels = bottomLabels;
    }

    public void setAndAddImageShower(ImageShower imageShower) {
        this.add(imageShower, BorderLayout.NORTH);
        this.imageShower = imageShower;
    }

    MainApplicationWindow(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setLayout(new BorderLayout());
        //this.setResizable(false);
        //this.setLayout(null);
        this.getContentPane().setBackground(MyColors.MainBackground);
        this.setVisible(true);
    }
}
