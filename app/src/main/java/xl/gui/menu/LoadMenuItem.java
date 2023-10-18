package xl.gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import xl.gui.StatusLabel;
import xl.gui.XL;
import xl.util.XLException;

class LoadMenuItem extends OpenMenuItem {

    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
        //var repository = xl.getSheet().getRepository();
        //var parser = xl.getSheet().getInputParser();
        // call xllbufferreader
        try {
            XLBufferedReader XLBR = new XLBufferedReader(path);
            XLBR.load(xl.getSheet());
            XLBR.close();
        } catch (Exception e) {
            statusLabel.setText("File couldn't be loaded");
        }
        xl.getSheet().externalNotify();
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}
