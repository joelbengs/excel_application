package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {

    protected StatusPanel(StatusLabel statusLabel, SelectedCell selectedCell) {
        add(WEST, new CurrentLabel(selectedCell));
        add(CENTER, statusLabel);
    }
}
