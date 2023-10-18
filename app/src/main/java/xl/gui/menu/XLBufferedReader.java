package xl.gui.menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import xl.expr.Environment;
import xl.util.XLException;

// Moved to package xl.gui.menu from package xl.util
public class XLBufferedReader extends BufferedReader {

    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Environment env) {
        try {
            env.clearAllCells();
            while (ready()) {
                String string = readLine();
                int i = string.indexOf('=');
                String key = string.substring(0, i).trim();
                String value = string.substring(i + 1).trim();
                env.loadToSheet(key,value);
            }
            env.checkValidity();
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}
