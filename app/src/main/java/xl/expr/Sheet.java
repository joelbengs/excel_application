package xl.expr;

import java.util.Map;
import java.util.Observable;
import java.util.Optional;
import java.util.TreeMap;
import xl.expr.factories.InputParser;
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
        // map does: Optional<Cell> -> Optional<Double>
        return Optional.ofNullable(this.repository.get(coordinate)).map(cell -> cell.value(this));
    }

    // For the editor, always strings
    @Override
    public Optional<String> stringValue(Coordinate coordinate) {
        return Optional.ofNullable(this.repository.get(coordinate)).map(cell -> cell.toString());
    }

    // For the slot labels, the return type can contain both comment or double
    public Optional<String> gridContent(Coordinate coordinate) {
        //Figure out if comment. If so, return comment as string. Cell instanceoff comment?
        //If not comment, then return double value parsed as a string. Slotlabels will have to do unparsing.
        return Optional.ofNullable(this.repository.get(coordinate)).map(cell -> cell.toString());
    }

    @Override
    public void addToSheet(Coordinate coordinate, String input) {
        Cell newCell = parser.parse(input);
        Cell previousWorking = this.repository.get(coordinate);
        Bomb bomb = new Bomb();
        this.repository.put(coordinate, bomb);
        try {
            // this will throw if there is a circular reference
            // we do not need to save the result,
            // we're just calling it to see if it throws
            newCell.value(this);
        } catch (XLException e) {
            // put back what worked before
            // (if there was something there before)
            if (previousWorking != null) {
                this.repository.put(coordinate, previousWorking);
            }

            throw e;
        }
        // if no exception was thrown, we can put the cell in the sheet
        // as we know it doesn't have a circular reference
        this.repository.put(coordinate, newCell);

        // notify observers that the value of the cell has changed
        this.setChanged();
        this.notifyObservers();
    }

    // Returns a copy of the repository
    @Override
    public Map<Coordinate, Cell> getRepository() {
        return this.repository;
    }

    public InputParser getInputParser() {
        return this.parser;
    }

    public void externalNotify() {
        this.setChanged();
        this.notifyObservers();
        for (Map.Entry<Coordinate, Cell> m : this.repository.entrySet()) {
            System.out.println("Coordinate: " + m.getKey() + " Cell: " + m.getValue());
        }
    }
}
