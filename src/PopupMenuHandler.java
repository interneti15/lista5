import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupMenuHandler {

    private KeyboardHandler keyboardHandler;

    public PopupMenuHandler(KeyboardHandler keyboardHandler) {
        this.keyboardHandler = keyboardHandler;
    }

    public JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        // Add "Load Image" menu item
        JMenuItem loadImageItem = new JMenuItem("Load Image");
        loadImageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.loadImage();
            }
        });
        popupMenu.add(loadImageItem);

        // Add "Save Selected Fragment" menu item
        JMenuItem saveFragmentItem = new JMenuItem("Save Selected Fragment");
        saveFragmentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.saveSelectedFragment();
            }
        });
        popupMenu.add(saveFragmentItem);

        // Add "Crop with Square" menu item
        JMenuItem cropSquareItem = new JMenuItem("Crop with Square");
        cropSquareItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.cropWithSquare();
            }
        });
        popupMenu.add(cropSquareItem);

        // Add "Crop with Lines" menu item
        JMenuItem cropLinesItem = new JMenuItem("Crop with Lines");
        cropLinesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.cropWithLines();
            }
        });
        popupMenu.add(cropLinesItem);

        // Add "Reset Selection" menu item
        JMenuItem resetSelectionItem = new JMenuItem("Reset Selection");
        resetSelectionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.resetSelection();
            }
        });
        popupMenu.add(resetSelectionItem);

        JMenuItem historyItem = new JMenuItem("Selection History");
        historyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.handleHistory();
            }
        });
        popupMenu.add(historyItem);

        // Add "Quit Program" menu item
        JMenuItem quitProgramItem = new JMenuItem("Quit Program");
        quitProgramItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.quitProgram();
            }
        });
        popupMenu.add(quitProgramItem);

        return popupMenu;
    }
}
