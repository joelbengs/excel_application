package xl.gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import xl.expr.Coordinate;
import xl.gui.StatusLabel;
import xl.gui.XL;

class LoadMenuItem extends OpenMenuItem {

    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
        String[] equations = path.split("\n");
        for (String equation : equations) {
            String[] parts = equation.split("=");
            String key = parts[0];
            String value = parts[1];
            this.xl
                    .getSheet()
                    .addToSheet(new Coordinate(key), this.xl.getInputParser().parse(value));
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}
