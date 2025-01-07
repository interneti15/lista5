public class JObjectsHandler {
    private MainApplicationWindow ApplicationMainJFrame;

    public MainApplicationWindow getApplicationMainJFrame() {
        return ApplicationMainJFrame;
    }

    JObjectsHandler() {
        System.out.println("JObjectsHandler constructor started");

        System.out.println("Creating MainApplicationWindow");
        this.ApplicationMainJFrame = new MainApplicationWindow("Zadanie 1");

        System.out.println("Setting and adding ImageShower");
        this.ApplicationMainJFrame.setAndAddImageShower(new ImageShower(ApplicationMainJFrame));

        System.out.println("Setting and adding BottomLabels");
        this.ApplicationMainJFrame.setAndAddBottomLabels(new BottomLabels(ApplicationMainJFrame));

        System.out.println("Setting and adding RightLabels");
        this.ApplicationMainJFrame.setAndAddRightLabels(new RightLabels(ApplicationMainJFrame));

        System.out.println("Setting and adding SelectionHandler");
        this.ApplicationMainJFrame.setAndAddSelectionHandler(new SelectionHandler(ApplicationMainJFrame));

        System.out.println("Repainting ApplicationMainJFrame");
        ApplicationMainJFrame.repaint();

        System.out.println("JObjectsHandler constructor finished!");
    }
}
