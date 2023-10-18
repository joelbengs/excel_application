package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel /* implements Observer */ {

    protected StatusPanel(StatusLabel statusLabel, SelectedCell selectedCell) {
        add(WEST, new CurrentLabel(selectedCell));
        add(CENTER, statusLabel);
        /* selectedCell.addObserver(this); */
    }

    // public void update(Observer o, )
}
