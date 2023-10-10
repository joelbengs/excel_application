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
        if (!this.repository.containsKey(coordinate)) {
            return Optional.empty();
        }
        return Optional.of(this.repository.get(coordinate).value(this));
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

    @Override
    public Map<Coordinate, Cell> getRepository() {
        return repository;
    }
}
