public class JObjectsHandler {
    private MainApplicationWindow ApplicationMainJFrame;

    JObjectsHandler() {
        this.ApplicationMainJFrame = new MainApplicationWindow("Zadanie 1");
        this.ApplicationMainJFrame.setAndAddImageShower(new ImageShower());
        this.ApplicationMainJFrame.setAndAddBottomLabels(new BottomLabels());
    }
}
