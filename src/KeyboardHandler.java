import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class KeyboardHandler implements KeyListener {

    private MainApplicationWindow applicationMainJFrame;
    public KeyboardHandler(MainApplicationWindow applicationMainJFrame) {
        this.applicationMainJFrame = applicationMainJFrame;
    }

    private boolean popupOpen = false;
    private JFrame popupMenu = null;
    private JList<Zaznaczenie> currentSelectionList = null;

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

            case KeyEvent.VK_H:
                handleHistory();
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

    public void loadImage() {
        
        String imagePath;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");

        
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image Files", "jpg", "png", "gif", "bmp", "jpeg", "tiff", "webp", "svg"));

        
        int result = fileChooser.showOpenDialog(null);

        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath(); 

            String fileExtension = getFileExtension(imagePath);
            if (!fileExtension.equalsIgnoreCase("jpg") &&
                    !fileExtension.equalsIgnoreCase("png") &&
                    !fileExtension.equalsIgnoreCase("gif") &&
                    !fileExtension.equalsIgnoreCase("bmp") &&
                    !fileExtension.equalsIgnoreCase("jpeg") &&
                    !fileExtension.equalsIgnoreCase("tiff") &&
                    !fileExtension.equalsIgnoreCase("webp") &&
                    !fileExtension.equalsIgnoreCase("svg")) {

                JOptionPane.showMessageDialog(applicationMainJFrame, "Wrong file type selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            System.out.println("Image selected: " + imagePath); 
        } else {
            System.out.println("No file selected.");
            return;
        }

        applicationMainJFrame.getImageShower().setImagePathAndRepaint(imagePath);

    }

    public void saveSelectedFragment() {
        SelectionHandler selectionHandler = applicationMainJFrame.getSelectionHandler();
        ImageShower imageShower = applicationMainJFrame.getImageShower();

        if (!selectionHandler.getSelection().isIni()) {
            JOptionPane.showMessageDialog(applicationMainJFrame, "No selection to save.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int startX = (int) (selectionHandler.getSelection().getStartX() / imageShower.getScaleFactor());
        int startY = (int) (selectionHandler.getSelection().getStartY() / imageShower.getScaleFactor());
        int endX = (int) (selectionHandler.getSelection().getEndX() / imageShower.getScaleFactor());
        int endY = (int) (selectionHandler.getSelection().getEndY() / imageShower.getScaleFactor());
        Image image = imageShower.getImageObject();
        
        
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        BufferedImage croppedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        
        Graphics g = croppedImage.getGraphics();
        g.drawImage(image, 0, 0, width, height, startX, startY, endX, endY, null);
        g.dispose();

        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Cropped Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));

        
        int userSelection = fileChooser.showSaveDialog(applicationMainJFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            
            if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
            }

            try {
                ImageIO.write(croppedImage, "png", fileToSave);
                JOptionPane.showMessageDialog(applicationMainJFrame, "Image saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(applicationMainJFrame, "Error saving image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void cropWithSquare() {
        SelectionHandler selectionHandler = applicationMainJFrame.getSelectionHandler();

        if (selectionHandler.getSelection().getMode() == MODE.rectangle){
            return;
        }

        selectionHandler.switchSelectionMode();
    }

    public void cropWithLines() {
        SelectionHandler selectionHandler = applicationMainJFrame.getSelectionHandler();

        if (selectionHandler.getSelection().getMode() == MODE.line){
            return;
        }

        selectionHandler.switchSelectionMode();
    }

    public void resetSelection() {
        SelectionHandler selectionHandler = applicationMainJFrame.getSelectionHandler();
        MODE currentMode = selectionHandler.getSelection().getMode();
        selectionHandler.setSelection(new Zaznaczenie());
        selectionHandler.getSelection().setMode(currentMode);
        
        selectionHandler.repaint();
    }

    public void quitProgram() {
        SelectionHandler selectionHandler = applicationMainJFrame.getSelectionHandler();
        if (selectionHandler.getSelection().isIni()) {
            int result = JOptionPane.showConfirmDialog(
                applicationMainJFrame,
                "You have an active selection. Are you sure you want to quit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (result != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        System.exit(0);
    }

    public void handleHistory() {
        ArrayList<Zaznaczenie> history = applicationMainJFrame.getSelectionHandler().getHistory();

        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(applicationMainJFrame, "No history available.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DefaultListModel<Zaznaczenie> listModel = new DefaultListModel<>();
        history.forEach(listModel::addElement);

        createPopupHandler(listModel);
    }

    private void createPopupHandler(DefaultListModel<Zaznaczenie> listModel) {
        JList<Zaznaczenie> selectionList = new JList<>(listModel);
        selectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Zaznaczenie zaznaczenie) {
                    value = String.format("StartX: %d, StartY: %d, Width: %d, Height: %d",
                            zaznaczenie.getStartX(), zaznaczenie.getStartY(),
                            zaznaczenie.getWidth(), zaznaczenie.getHeight());
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        Zaznaczenie currentSelection = applicationMainJFrame.getSelectionHandler().getSelection();
        
        selectCurrent(selectionList, currentSelection);
        

        selectionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (selectionList.getSelectedIndex() != -1) {
                    Zaznaczenie selected = selectionList.getSelectedValue();
                    applicationMainJFrame.getSelectionHandler().setSelection(selected.copy());
                    applicationMainJFrame.getSelectionHandler().repaint();
                }
            }
        });
        selectionList.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_X) {
                    int selectedIndex = currentSelectionList.getSelectedIndex();
                    if (selectedIndex == -1){
                        JOptionPane.showMessageDialog(applicationMainJFrame, "No selection available.", "Info", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    currentSelectionList.clearSelection();
                    applicationMainJFrame.getSelectionHandler().getHistory().remove(selectedIndex);

                    DefaultListModel<Zaznaczenie> listModel = new DefaultListModel<>();
                    applicationMainJFrame.getSelectionHandler().getHistory().forEach(listModel::addElement);
                    currentSelectionList.setModel(listModel);
                    resetSelection();
                    currentSelectionList.revalidate(); 
                    currentSelectionList.repaint(); 


                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
            }
        });

        
        JFrame popupFrame = new JFrame("Selection History");
        this.popupMenu = popupFrame;
        this.currentSelectionList = selectionList;
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupFrame.add(new JScrollPane(selectionList));
        popupFrame.setSize(300, 200);
        popupFrame.setVisible(true);
    }

    void selectCurrent(JList<Zaznaczenie> selectionList, Zaznaczenie currentSelection) {
        int currentId = currentSelection.getId();
        ListModel<Zaznaczenie> model = selectionList.getModel();

        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).getId() == currentId) {
                selectionList.setSelectedIndex(i);
                break;
            }
        }
    }

    public static String getFileExtension(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return ""; 
        }

        int lastDotIndex = filePath.lastIndexOf('.');
        int lastSeparatorIndex = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));

        
        if (lastDotIndex > lastSeparatorIndex && lastDotIndex != -1) {
            return filePath.substring(lastDotIndex + 1);
        }

        return ""; 
    }

    public void refreshHistoryJlist (){

        if (currentSelectionList == null) {
            return;
        }

        DefaultListModel<Zaznaczenie> listModel = new DefaultListModel<>();
        applicationMainJFrame.getSelectionHandler().getHistory().forEach(listModel::addElement);
        currentSelectionList.setModel(listModel);
        selectCurrent(currentSelectionList, applicationMainJFrame.getSelectionHandler().getSelection());
        currentSelectionList.revalidate();
        currentSelectionList.repaint();
    }

}

