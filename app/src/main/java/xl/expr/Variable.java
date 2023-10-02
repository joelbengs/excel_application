package xl.expr;

import xl.util.XLException;

class Variable extends Expr {

    private Coordinate coordinate;

    public Variable(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String toString(int prec) {
        return coordinate.toString();
    }

    public double value(Environment env) {
        var value = env.value(coordinate);
        if (value.isEmpty()) throw new XLException("Variable " + coordinate + " is undefined");
        return value.get();
    }
}
