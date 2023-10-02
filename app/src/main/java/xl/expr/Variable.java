package xl.expr;

class Variable extends Expr {

    private Coordinate coordinate;

    public Variable(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String toString(int prec) {
        return coordinate.toString();
    }

    public double value(Environment env) {
        return env.value(coordinate);
    }
}
