public class JObjectsHandler {
    private MainApplicationWindow ApplicationMainJFrame;
    private KeyboardHandler keyHandler;
    public MainApplicationWindow getApplicationMainJFrame() {
        return ApplicationMainJFrame;
    }

    JObjectsHandler() {
        System.out.println("JObjectsHandler constructor started");

        System.out.println("Creating MainApplicationWindow");
        this.ApplicationMainJFrame = new MainApplicationWindow("Zadanie 1");

        System.out.println("Setting and adding ImageShower");
        this.ApplicationMainJFrame.setAndAddImageShower(new ImageShower(ApplicationMainJFrame));
        this.ApplicationMainJFrame.getImageShower().repaint();

        System.out.println("Setting and adding RightLabels");
        this.ApplicationMainJFrame.setAndAddRightLabels(new RightLabels(ApplicationMainJFrame));

        try {
            Thread.sleep(100);
        }
        catch (Exception e){}
        System.out.println("Setting and adding SelectionHandler");
        this.ApplicationMainJFrame.setAndAddSelectionHandler(new SelectionHandler(ApplicationMainJFrame));

        System.out.println("Setting and adding BottomLabels");
        this.ApplicationMainJFrame.setAndAddBottomLabels(new BottomLabels(ApplicationMainJFrame));

        System.out.println("Repainting ApplicationMainJFrame");
        this.ApplicationMainJFrame.getContentPane().setComponentZOrder(ApplicationMainJFrame.getSelectionHandler(),0);
        this.ApplicationMainJFrame.getContentPane().setComponentZOrder(ApplicationMainJFrame.getImageShower(),2);
        this.ApplicationMainJFrame.getContentPane().revalidate();
        this.ApplicationMainJFrame.getContentPane().repaint();
        this.ApplicationMainJFrame.getSelectionHandler().repaint();
        this.ApplicationMainJFrame.repaint();

        System.out.println("JObjectsHandler constructor finished!");
        System.out.println("Creating keyHandler");
        ApplicationMainJFrame.addKeyListener(new KeyboardHandler(ApplicationMainJFrame));

    }
}
