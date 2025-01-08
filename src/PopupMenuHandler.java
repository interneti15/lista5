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

        
        JMenuItem loadImageItem = new JMenuItem("Load Image");
        loadImageItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.loadImage();
            }
        });
        popupMenu.add(loadImageItem);

        
        JMenuItem saveFragmentItem = new JMenuItem("Save Selected Fragment");
        saveFragmentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.saveSelectedFragment();
            }
        });
        popupMenu.add(saveFragmentItem);

        
        JMenuItem cropSquareItem = new JMenuItem("Crop with Square");
        cropSquareItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.cropWithSquare();
            }
        });
        popupMenu.add(cropSquareItem);

        
        JMenuItem cropLinesItem = new JMenuItem("Crop with Lines");
        cropLinesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyboardHandler.cropWithLines();
            }
        });
        popupMenu.add(cropLinesItem);

        
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
