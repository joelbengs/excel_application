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
        var repository = xl.getSheet().getRepository();
        var parser = xl.getSheet().getInputParser();
        // call xllbufferreader
        try {
            XLBufferedReader XLBR = new XLBufferedReader(path);
            XLBR.load(repository, parser);
            XLBR.close();
        } catch (Exception e) {
            throw new XLException("Can not find given path");
        }
        // call externalnotify
        xl.getSheet().externalNotify();
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }

    /* The previous solution had loadMenuItem call addToSheet in Sheet for each new input.
    Now loadMenuItem sends the repository and the parser to XLBR, which then mutates the repository.
    More coupling, previously both the repo and the parser could stay in sheet.
    The signature for load in XLBR used to not require a parser.
    We also had to add a getRepository, a getInputParser and a ExternalNotify in Sheet.
    However the previous solution woulde have needed to use a clearAll in sheet */
    /*

    * protected void action(String path) throws FileNotFoundException {
    * File file = new File(path);
    * if (!file.exists()) {
    * throw new FileNotFoundException();
    * }
    * var reader = new Scanner(file);
    *
    * while (reader.hasNextLine()) {
    * var equation = reader.nextLine();
    *
    * String[] parts = equation.split("=");
    * String key = parts[0];
    * String value = parts[1];
    * this.xl.getSheet().addToSheet(new Coordinate(key), value);
    * }
    *
    * reader.close();
    * }
    */
}
