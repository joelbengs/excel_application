package xl.expr;

import xl.util.XLException;

public class Bomb implements Cell {

    @Override
    public double value(Environment env) {
        throw new XLException("Circular reference");
    }

    @Override
    public String gridString(Environment env) {
        return "BOMB";
    }

    @Override
    public String toString() {
        return "BOMB";
    }
}
