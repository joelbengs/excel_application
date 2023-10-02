package xl.expr;

import xl.util.XLException;

public class Bomb implements Cell {

    public double value(Environment env) {
        throw new XLException("Circular reference");
    }

    public String toString() {
        return "BOMB";
    }
}
