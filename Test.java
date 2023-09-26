public class CellRepository implements Environment {
    private Map<CellCoordinate, Cell> cellMap = new HashMap<>();

    public value(CellCoordinate coordinate) {
        return cellMap.get(coordinate);
    }
}

class CellCoordinate {
    private int row, col;

    public CellCoordinate(int row, int col) {
        // ...
    }

    @Override
    public boolean equals(Object o) {
        // ...
    }

    @Override
    public int hashCode() {
        return x + 1000 * y;
    }
}

interface Cell {}

class Expr implements Cell {}

class Comment implements Cell {}