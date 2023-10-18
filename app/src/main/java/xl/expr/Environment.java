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
     * Returns the string representation of the cell at the specified coordinate.
     *
     * @param coordinate the coordinate to get the string representation of
     * @return the string representation of the cell at the specified coordinate
     */
    public Optional<String> stringValue(Coordinate coordinate);

    /**
     * Returns the string representation of the cell at the specified coordinate, but with value of
     * expressions instead of expression strings
     *
     * @param coordinate
     * @return
     */
    public Optional<String> gridContent(Coordinate coordinate);

    /**
     * Tries to add a cell to the sheet at the specified coordinate. If the cell contains a circular
     * reference, the sheet is not modified.
     *
     * @param coordinate the coordinate to add the cell at
     * @param cell the cell to add
     * @throws XLException if the cell has a circular reference
     */
    public void addToSheet(Coordinate coordinate, String inputString);

    public Map<Coordinate, Cell> getRepository();
}
