package xl.gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import xl.gui.StatusLabel;
import xl.gui.XL;

class SaveMenuItem extends OpenMenuItem {

    public SaveMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Save");
    }

    protected void action(String path) throws FileNotFoundException {
        try (XLPrintStream xlps = new XLPrintStream(path)) {
            // Fetch a entrySet of the model's content and send to xlps
            xlps.save(xl.getSheet());
            xl.getSheet().externalNotify();
        } catch (Exception e) {
            statusLabel.setText("File couldn't be saved");
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}
