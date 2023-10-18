package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import javax.swing.JFrame;
import javax.swing.JPanel;
import xl.expr.Environment;
import xl.expr.Sheet;
import xl.gui.menu.XLMenuBar;

public class XL extends JFrame {

    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;
    private Environment sheet;

    // Constructor used by NewMenuItem.java when creating a new instance of the
    // application from the menu bar, for the purpose of using
    // the global list and the global counter for all applications
    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();
        this.sheet = new Sheet();
        SelectedCell selectedCell = new SelectedCell();
        JPanel statusPanel = new StatusPanel(statusLabel, selectedCell);
        SheetPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, sheet, selectedCell);
        Editor editor = new Editor(sheet, selectedCell, statusLabel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel, selectedCell));
        this.sheet.addObserver(sheetPanel);
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    // Used by LoadMenuItem.java to rename an application instance by the name of the loaded file
    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public Environment getSheet() {
        return this.sheet;
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
}
