package xl.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JTextField;
import xl.expr.Coordinate;
import xl.expr.Environment;
import xl.util.XLException;

@SuppressWarnings("deprecation")
public class Editor extends JTextField implements Observer {
    private Environment sheet;
    private SelectedCell selectedCell;
    private StatusLabel statusLabel;

    public Editor(Environment sheet, SelectedCell selectedCell, StatusLabel statusLabel) {
        setBackground(Color.WHITE);
        this.statusLabel = statusLabel;
        addActionListener(
                (ActionEvent e) -> {
                    String text = getText();
                    if (!text.isEmpty()) {
                        try {
                            sheet.addToSheet(selectedCell.getSelectedCoordinate(), text);
                        } catch (XLException exception) {
                            this.statusLabel.setText(exception.getMessage());
                        }
                    }
                });
        this.sheet = sheet;
        this.selectedCell = selectedCell;
        selectedCell.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Coordinate selectedCoordinate = this.selectedCell.getSelectedCoordinate();
        // The value from the model is Optional<String>. Empty string is default
        String value = sheet.stringValue(selectedCoordinate).orElse("");
        this.setText(value);
    }
}
