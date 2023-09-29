package xl.expr;

public class Sheet implements Environment {
    private Map<Coordinate, Cell> repository;
    private CellFactory cf;

    public Sheet() {
        this.repository = new TreeMap<Coordinate, Cell>();
        cf = new CellFactory();
    }

    public double value(String name) {}
}
