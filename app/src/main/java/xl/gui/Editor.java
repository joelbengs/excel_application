package xl.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import xl.expr.Coordinate;
import xl.expr.Environment;

public class Editor extends JTextField implements Observer {
    private Environment sheet;
    private SelectedCell selectedCell;

    public Editor(Environment sheet, SelectedCell selectedCell) {
        setBackground(Color.WHITE);
        addActionListener(new EnterActionListener());
        this.sheet = sheet;
        this.selectedCell = selectedCell;
        selectedCell.addObserver(this);
    }

    private class EnterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = getText();
            if (!text.isEmpty()) {
                sheet.addToSheet(selectedCell.getSelectedCoordinate(), text);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Coordinate selectedCoordinate = this.selectedCell.getSelectedCoordinate();
        this.setText(sheet.stringValue(selectedCoordinate));
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
