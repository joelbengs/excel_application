package xl.gui.menu;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import xl.gui.StatusLabel;
import xl.gui.XL;
import xl.util.XLException;

class SaveMenuItem extends OpenMenuItem {

    public SaveMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Save");
    }

    protected void action(String path) throws FileNotFoundException {
        try {
            var file = new File(path);
            XLPrintStream xlps = new XLPrintStream(path);
            // Fetch a entrySet of the model's content and send to xlps
            xlps.save(xl.getSheet().getRepository().entrySet());
            xl.getSheet().externalNotify();
        } catch (Exception e) {
            throw new XLException("Can not find given path");
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}
