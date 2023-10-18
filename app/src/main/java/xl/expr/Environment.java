package xl.expr;

import java.util.Map;
import java.util.Optional;
import xl.expr.factories.InputParser;
import xl.gui.SheetPanel;

public interface Environment {

    /**
     * Calculates the value of the cell at the specified coordinate.
     *
     * @param coordinate the coordinate to get the value of
     * @return the value of the cell at the specified coordinate
     */
    Optional<Double> value(Coordinate coordinate);

    /**
     * Returns the string representation of the cell at the specified coordinate.
     *
     * @param coordinate the coordinate to get the string representation of
     * @return the string representation of the cell at the specified coordinate
     */
    Optional<String> stringValue(Coordinate coordinate);

    /**
     * Returns the string representation of the cell at the specified coordinate, but with value of
     * expressions instead of expression strings
     *
     * @param coordinate
     * @return
     */
    Optional<String> gridContent(Coordinate coordinate);

    /**
     * Tries to add a cell to the sheet at the specified coordinate. If the cell contains a circular
     * reference, the sheet is not modified.
     *
     * @param coordinate the coordinate to add the cell at
     * @param cell the cell to add
     * @throws XLException if the cell has a circular reference
     */
    void addToSheet(Coordinate coordinate, String inputString);

    /**
     * Clears the cell at the specified coordinate.
     *
     * @param coordinate the coordinate to clear
     */
    void clearCell(Coordinate coordinate);

    /** Clears all cells in the sheet. */
    void clearAllCells();

    /** Returns the repository of the environment. */
    Map<Coordinate, Cell> getRepository();

    /** Notify all observers that the environment has been modified. */
    void externalNotify();

    /** Returns the input parser that the environment uses to parse input strings. */
    InputParser getInputParser();

    /**
     * Adds an observer to the environment. Enables the observer to be notified when this
     * environment has been modified.
     */
    void addObserver(SheetPanel sheetPanel);
}
