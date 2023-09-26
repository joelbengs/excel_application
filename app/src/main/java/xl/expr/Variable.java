package xl.expr;

class Variable extends Expr {

    /*
     * `String` needs to be changed to `CellCoordinate` if we decide not to identify
     * variables by name but instead by their coordinate in the spreadsheet.
     */
    private String name;

    Variable(String name) {
        this.name = name;
    }

    public String toString(int prec) {
        return name.toString();
    }

    public double value(Environment env) {
        return env.value(name);
    }
}
