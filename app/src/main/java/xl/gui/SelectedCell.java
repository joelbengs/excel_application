package xl.gui;

import java.util.Observable;
import xl.expr.Coordinate;

@SuppressWarnings("deprecation")
public class SelectedCell extends Observable {

    private Coordinate selectedCell;

    public SelectedCell() {
        selectedCell = new Coordinate("A1");
    }

    public Coordinate getSelectedCoordinate() {
        return selectedCell;
    }

    public void setSelectedCoordinate(Coordinate coordinate) {
        selectedCell = coordinate;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return selectedCell.toString();
    }
}
