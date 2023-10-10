package xl.gui.menu;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;
import xl.expr.Cell;
import xl.expr.Coordinate;

// Moved to package xl.gui.menu from package xl.util
public class XLPrintStream extends PrintStream {

    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public void save(Set<Entry<Coordinate, Cell>> set) {

        for (Entry<Coordinate, Cell> entry : set) {
            print(entry.getKey());
            print('=');
            println(entry.getValue());
        }
        flush();
        close();
    }
}
