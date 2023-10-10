package xl.gui.menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import xl.expr.Cell;
import xl.expr.Coordinate;
import xl.expr.factories.InputParser;
import xl.util.XLException;

// Moved to package xl.gui.menu from package xl.util
public class XLBufferedReader extends BufferedReader {

    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Map<Coordinate, Cell> repository, InputParser parser) {
        try {
            repository.clear();
            while (ready()) {
                String string = readLine();
                int i = string.indexOf('=');
                Coordinate key = new Coordinate(string.substring(0, i).trim());
                Cell value = parser.parse(string.substring(i + 1).trim());
                repository.put(key, value);
            }
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}
