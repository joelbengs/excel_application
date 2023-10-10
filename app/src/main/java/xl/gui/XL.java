package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import javax.swing.JFrame;
import javax.swing.JPanel;
import xl.expr.Coordinate;
import xl.expr.Sheet;
import xl.gui.menu.XLMenuBar;

public class XL extends JFrame {

    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;
    private Sheet sheet;

    // Constructure used by NewMenuItem.java when creating a new instance of the
    // application from the menu bar, for the purpose of using
    // the global list and the global counter for all applications
    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    @SuppressWarnings("deprecation")
    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();

        this.sheet = new Sheet();

        // this is a part of the model
        SelectedCell selectedCell = new SelectedCell();
        JPanel statusPanel = new StatusPanel(statusLabel, selectedCell);
        SheetPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, sheet, selectedCell);

        sheet.addObserver(sheetPanel);

        sheet.addToSheet(new Coordinate("A1"), "11");

        Editor editor = new Editor(sheet, selectedCell, statusLabel);
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // Used by LoadMenuItem.java to rename an application instance by the name of the loaded file
    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public Sheet getSheet() {
        return this.sheet;
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
}
