package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import xl.gui.SelectedCell;
import xl.gui.XL;

class ClearMenuItem extends JMenuItem implements ActionListener {
    private XL xl;
    private SelectedCell selectedCell;

    public ClearMenuItem(XL xl, SelectedCell selectedCell) {
        super("Clear");
        this.xl = xl;
        this.selectedCell = selectedCell;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.xl.getSheet().clearCell(this.selectedCell.getSelectedCoordinate());
    }
}
