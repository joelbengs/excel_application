package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

import xl.expr.Environment;

public class SheetPanel extends BorderPanel {

    SlotLabels slotLabels;

    public SheetPanel(int rows, int columns) {
        this.slotLabels = new SlotLabels(rows, columns);
        add(WEST, new RowLabels(rows));
        add(CENTER, slotLabels);
    }

    public void update(Environment env) {
        slotLabels.update(env);
    }
}
