package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class CurrentLabel extends ColoredLabel implements Observer {

    private SelectedCell selectedCell;

    public CurrentLabel(SelectedCell selectedCell) {
        super("A1", Color.WHITE);
        this.selectedCell = selectedCell;
        selectedCell.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Something has updated");
        this.setText(this.selectedCell.toString());
    }
}
