package xl.expr.factories;

import xl.expr.Cell;

public interface CellFactory {

    Cell build(String contents);
}
