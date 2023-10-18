package xl.expr;

import java.util.Map;
import java.util.Observable;
import java.util.Optional;
import java.util.TreeMap;
import xl.expr.factories.InputParser;
import xl.gui.SheetPanel;
import xl.util.XLException;

@SuppressWarnings("deprecation")
public class Sheet extends Observable implements Environment {
    private Map<Coordinate, Cell> repository;
    private InputParser parser;

    public Sheet() {
        this.repository = new TreeMap<Coordinate, Cell>();
        this.parser = new InputParser();
    }

    @Override
    public Optional<Double> value(Coordinate coordinate) {
        return Optional.ofNullable(this.repository.get(coordinate)).map(cell -> cell.value(this));
    }

    // For the editor, always return gridcontent as String
    @Override
    public Optional<String> stringValue(Coordinate coordinate) {
        return Optional.ofNullable(this.repository.get(coordinate)).map(cell -> cell.toString());
    }

    // For the slot labels, the return type can contain both comment or double
    @Override
    public Optional<String> gridContent(Coordinate coordinate) {
        return Optional.ofNullable(this.repository.get(coordinate))
                .map(cell -> cell.gridString(this));
    }

    @Override
    public void addToSheet(Coordinate coordinate, String input) {
        Cell newCell = parser.parse(input);
        Cell previousWorking = this.repository.get(coordinate);
        Bomb bomb = new Bomb();
        this.repository.put(coordinate, bomb);
        try {
            // This will throw if there is a circular reference or a division by zero.
            // We do not need to save the result, we're just calling it to see if it throws.
            newCell.value(this);
        } catch (XLException e) {
            // put back what worked before
            // (if there was something there before)
            // otherwise just remove the bomb
            if (previousWorking == null) {
                this.repository.remove(coordinate);
            } else {
                this.repository.put(coordinate, previousWorking);
            }

            // rethrow the exception
            throw e;
        }
        // if no exception was thrown, we can put the cell in the sheet
        // as we know it doesn't have a circular reference
        this.repository.put(coordinate, newCell);

        // notify observers that the value of the cell has changed
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void clearCell(Coordinate coordinate) {
        this.repository.remove(coordinate);
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void clearAllCells() {
        this.repository.clear();
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public Map<Coordinate, Cell> getRepository() {
        return this.repository;
    }

    @Override
    public InputParser getInputParser() {
        return this.parser;
    }

    @Override
    public void externalNotify() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void addObserver(SheetPanel sheetPanel) {
        super.addObserver(sheetPanel);
    }
}
