import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class KeyboardHandler implements KeyListener {

    private MainApplicationWindow applicationMainJFrame;
    public KeyboardHandler(MainApplicationWindow applicationMainJFrame) {
        this.applicationMainJFrame = applicationMainJFrame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                loadImage();
                break;

            case KeyEvent.VK_Z:
                saveSelectedFragment();
                break;

            case KeyEvent.VK_K:
                cropWithSquare();
                break;

            case KeyEvent.VK_L:
                cropWithLines();
                break;

            case KeyEvent.VK_C:
                resetSelection();
                break;

            case KeyEvent.VK_Q:
                quitProgram();
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void loadImage() {
        // Create a file chooser
        String imagePath;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");

        // Filter for image files (optional)
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp"));

        // Show the open file dialog
        int result = fileChooser.showOpenDialog(null);

        // If a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath(); // Store the selected path
            System.out.println("Image selected: " + imagePath); // Display the selected path
        } else {
            System.out.println("No file selected.");
            return;
        }

        applicationMainJFrame.getImageShower().setImagePathAndRepaint(imagePath);

    }

    private void saveSelectedFragment() {
    }

    private void cropWithSquare() {
    }

    private void cropWithLines() {
    }

    private void resetSelection() {
    }

    private void quitProgram() {
        System.exit(0);
    }

}

