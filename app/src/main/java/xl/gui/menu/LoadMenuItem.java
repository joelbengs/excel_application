package xl.gui.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import xl.expr.Coordinate;
import xl.gui.StatusLabel;
import xl.gui.XL;

class LoadMenuItem extends OpenMenuItem {

    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        var reader = new Scanner(file);

        while (reader.hasNextLine()) {
            var equation = reader.nextLine();

            String[] parts = equation.split("=");
            String key = parts[0];
            String value = parts[1];
            this.xl
                    .getSheet()
                    .addToSheet(new Coordinate(key), this.xl.getInputParser().parse(value));
        }

        reader.close();
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}
