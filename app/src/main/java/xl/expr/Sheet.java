package xl.expr;

import java.util.Map;
import java.util.Observable;
import java.util.Optional;
import java.util.Set;
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

        try {
            /* DETECT CIRCULAR REFERENCES */
            // temporarily replace the new cell by a bomb,
            // which throws when evaluated.
            // this detects circular references
            Bomb bomb = new Bomb();
            this.repository.put(coordinate, bomb);
            // We do not need to save the result, we're just calling it to see if it throws.
            newCell.value(this);

            /* DETECT OTHER EXCEPTIONS */
            // for each cell in the sheet, check if it throws when
            // this cell is added
            // for example, division by zero

            // put the cell in the sheet
            this.repository.put(coordinate, newCell);
            // check if any cell in the sheet throws when this
            // new cell has been added
            for (Cell cell : this.repository.values()) {
                cell.value(this);
            }

        } catch (XLException e) {
            System.out.println("We have an exception: " + e.getMessage());
            // put back what worked before
            // (if there was something there before)
            // otherwise just remove the bomb
            if (previousWorking == null) {
                System.out.println("Removing the current cell");
                this.repository.remove(coordinate);
            } else {
                System.out.println(
                        "Removing the current cell and putting back " + previousWorking.toString());
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
        Cell previousWorking = this.repository.get(coordinate);
        try {
            this.repository.remove(coordinate);
            for (Cell cell : this.repository.values()) {
                cell.value(this);
            }
        } catch (XLException e) {
            System.out.println("We have an exception: " + e.getMessage());
            if (previousWorking != null) {
                System.out.println("Putting back " + previousWorking.toString());
                this.repository.put(coordinate, previousWorking);
            }
            throw e;
        }
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
    public void loadToSheet(String key, String input) {
        Coordinate coordinate = new Coordinate(key);
        Cell cell = parser.parse(input);
        this.repository.put(coordinate, cell);
    }

    public void checkValidity() {
        try {
            for (Cell cell : this.repository.values()) {
                cell.value(this);
            }
        } catch (XLException exception) {
            System.out.println("We have an exception: " + exception.getMessage());
            repository.clear();
            throw exception;
        }
    }

    @Override
    public Set<Map.Entry<String, String>> getEntrySet() {
        var map = new TreeMap<String, String>();
        for (Coordinate key : this.repository.keySet()){
            map.put(key.toString(), repository.get(key).toString());
        }
        return map.entrySet();
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



