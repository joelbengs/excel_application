package xl.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import xl.expr.Coordinate;
import xl.expr.Environment;

public class SlotLabels extends GridPanel {

    private List<SlotLabel> labelList;
    private SelectedCell selectedCell;

    public SlotLabels(int rows, int cols, SelectedCell selectedCell) {
        super(rows + 1, cols);
        this.selectedCell = selectedCell;

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

                label.addMouseListener(
                        new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                System.out.println("pressed at coordinate " + coordinate);
                                selectedCell.setSelectedCoordinate(coordinate);
                            }
                        });
            }
        }
        SlotLabel firstLabel = labelList.get(0);
        firstLabel.setBackground(Color.YELLOW);
    }

    public void update(Environment env) {
        
        for (SlotLabel slotLabel : labelList) {
            var coordinate = slotLabel.getCoordinate();
            // var value = env.value(coordinate);
            var gridString = env.gridContent(coordinate);
            // if (value.isPresent()) {
            /* if (gridString.isPresent()) {
                // slotLabel.setText(String.valueOf(value.get()));
                slotLabel.setText(gridString.get());
            } else {
                slotLabel.setText("          ");
            } */
            slotLabel.setText(gridString.orElse("            "));

            if (selectedCell.getSelectedCoordinate().equals(coordinate)) {
                slotLabel.setBackground(Color.YELLOW);
            } else {
                slotLabel.setBackground(Color.WHITE);
            }
        }
    }

    /*
     * public void update(Environment env) {
     *
     * for (SlotLabel slotLabel : labelList) {
     * var coordinate = slotLabel.getCoordinate();
     *
     * if (coordinate != selectedCell.getSelectedCell()) {
     * slotLabel.setBackground(Color.WHITE);
     * } else {
     * slotLabel.setBackground(Color.YELLOW);
     * }
     * var value = env.value(coordinate); // får value, vad som står i rutan
     * if (value.isPresent()) {
     * slotLabel.setText(String.valueOf(value.get()));
     * } else {
     * slotLabel.setText("          ");
     * }
     * }
     *
     * }
     */

}
