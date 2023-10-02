package xl.gui;

import java.awt.Color;
import xl.expr.Coordinate;

public class SlotLabel extends ColoredLabel {

    private Coordinate coordinate;

    public SlotLabel(Coordinate coordinate) {
        super("                    ", Color.WHITE, RIGHT);
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
