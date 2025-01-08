import javax.swing.*;
import java.awt.*;

public class MainApplicationWindow extends JFrame {

    private ImageShower imageShower;
    private BottomLabels bottomLabels;
    private RightLabels rightLabels;
    private SelectionHandler selectionHandler;
    private KeyboardHandler keyboardHandler;

    public ImageShower getImageShower() {
        return imageShower;
    }

    public BottomLabels getBottomLabels() {
        return bottomLabels;
    }

    public void setAndAddBottomLabels(BottomLabels bottomLabels) {
        this.add(bottomLabels,JLayeredPane.DEFAULT_LAYER);
        this.bottomLabels = bottomLabels;
    }

    public SelectionHandler getSelectionHandler() {
        return selectionHandler;
    }

    public void setAndAddImageShower(ImageShower imageShower) {
        this.add(imageShower,JLayeredPane.DEFAULT_LAYER);
        this.imageShower = imageShower;
    }

    public void setAndAddRightLabels(RightLabels rightLabels) {
        this.add(rightLabels,JLayeredPane.DEFAULT_LAYER);
        this.rightLabels = rightLabels;
    }

    public void setAndAddSelectionHandler(SelectionHandler selectionHandler){
        this.add(selectionHandler,JLayeredPane.PALETTE_LAYER);
        this.selectionHandler = selectionHandler;
    }

    MainApplicationWindow(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);
        this.setResizable(false);
        this.getContentPane().setBackground(MyColors.MainBackground);
        this.setVisible(true);
    }
}
