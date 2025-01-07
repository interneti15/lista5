import javax.swing.*;
import java.awt.*;

public class MainApplicationWindow extends JFrame {

    ImageShower imageShower;
    BottomLabels bottomLabels;
    RightLabels rightLabels;
    SelectionHandler selectionHandler;

    public ImageShower getImageShower() {
        return imageShower;
    }

    public BottomLabels getBottomLabels() {
        return bottomLabels;
    }

    public void setAndAddBottomLabels(BottomLabels bottomLabels) {
        this.add(bottomLabels);
        this.bottomLabels = bottomLabels;
    }

    public void setAndAddImageShower(ImageShower imageShower) {
        this.add(imageShower);
        this.imageShower = imageShower;
    }

    public void setAndAddRightLabels(RightLabels rightLabels) {
        this.add(rightLabels);
        this.rightLabels = rightLabels;
    }

    public void setAndAddSelectionHandler(SelectionHandler selectionHandler){
        this.add(selectionHandler);
        this.selectionHandler = selectionHandler;
    }

    MainApplicationWindow(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(MyColors.MainBackground);
        this.setVisible(true);
    }
}
