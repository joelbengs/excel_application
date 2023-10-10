package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

import java.util.Observable;
import java.util.Observer;
import xl.expr.Environment;

@SuppressWarnings("deprecation")
public class SheetPanel extends BorderPanel implements Observer {

    SlotLabels slotLabels;
    Environment environment;
    private SelectedCell selectedCell;

    public SheetPanel(int rows, int columns, Environment environment, SelectedCell selectedCell) {
        this.selectedCell = selectedCell;
        this.slotLabels = new SlotLabels(rows, columns, selectedCell);

        this.selectedCell.addObserver(this);

        this.environment = environment;
        add(WEST, new RowLabels(rows));
        add(CENTER, slotLabels);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.slotLabels.update(this.environment);
    }
}
