package xl.expr;

import java.util.Map;
import java.util.Optional;

public interface Environment {

    /**
     * Calculates the value of the cell at the specified coordinate.
     *
     * @param coordinate the coordinate to get the value of
     * @return the value of the cell at the specified coordinate
     */
    public Optional<Double> value(Coordinate coordinate);

    /**
     * Tries to add a cell to the sheet at the specified coordinate. If the cell contains a circular
     * reference, the sheet is not modified.
     *
     * @param coordinate the coordinate to add the cell at
     * @param cell the cell to add
     * @throws XLException if the cell has a circular reference
     */
    public void addToSheet(Coordinate coordinate, Cell cell);

    public Map<Coordinate, Cell> getRepository();
}
