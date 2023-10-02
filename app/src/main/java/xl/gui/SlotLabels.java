package xl.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import xl.expr.Coordinate;
import xl.expr.Environment;

public class SlotLabels extends GridPanel {

    private List<SlotLabel> labelList;

    public SlotLabels(int rows, int cols) {
        super(rows + 1, cols);
        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY, SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                var coordinate = new Coordinate(ch + String.valueOf(row));
                SlotLabel label = new SlotLabel(coordinate);
                add(label);
                labelList.add(label);
            }
        }
        SlotLabel firstLabel = labelList.get(0);
        firstLabel.setBackground(Color.YELLOW);
    }

    public void update(Environment env) {
        for (SlotLabel slotLabel : labelList) {
            var coordinate = slotLabel.getCoordinate();
            var value = env.value(coordinate);
            if (value.isPresent()) {
                slotLabel.setText(String.valueOf(value.get()));
            } else {
                slotLabel.setText("          ");
            }
        }
    }
}
