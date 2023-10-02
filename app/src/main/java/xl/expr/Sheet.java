package xl.expr;

import java.util.Map;
import java.util.TreeMap;

public class Sheet implements Environment {
    private Map<Coordinate, Cell> repository;
    private CellFactory cf;

    public Sheet() {
        this.repository = new TreeMap<Coordinate, Cell>();
        cf = new CellFactory();
    }

    @Override
    public double value(Coordinate coordinate) {
        return this.repository.get(coordinate).value(this);
    }
}
