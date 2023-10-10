package xl.expr.factories;

import java.io.IOException;
import xl.expr.Cell;
import xl.expr.ExprParser;
import xl.util.XLException;

public class ExprFactory implements CellFactory {

    private ExprParser exprParser;

    public ExprFactory() {
        this.exprParser = new ExprParser();
    }

    @Override
    public Cell build(String contents) {
        try {
            return exprParser.build(contents);
        } catch (IOException e) {
            throw new XLException("Could not read input");
        } catch (XLException e) {
            throw new XLException("Grammar issue in input");
        }
    }
}
