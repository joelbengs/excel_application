package xl.gui.menu;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;
import xl.expr.Environment;

// Moved to package xl.gui.menu from package xl.util
public class XLPrintStream extends PrintStream {

    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public void save(Environment env) {
        Set<Map.Entry<String, String>> set = env.getEntrySet();
        for (Map.Entry<String, String> entry : set) {
            print(entry.getKey());
            print('=');
            println(entry.getValue());
        }
        flush();
        close();
    }
}
