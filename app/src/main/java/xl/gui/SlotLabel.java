package xl.gui;

import java.awt.Color;
import xl.expr.Coordinate;

public class SlotLabel extends ColoredLabel {

    private Coordinate coordinate;

    public SlotLabel(Coordinate coordinate) {
        super("                    ", Color.WHITE, RIGHT);
        this.coordinate = coordinate;

        // this.addMouseListener(new MouseAdapter() {
        //     @Override
        //   public void mouseClicked(MouseEvent e) {
        //         System.out.println("tryckt");
        //         setBackground(Color.YELLOW);
        //         //setSelectedCell(coordinate);
        //   }
        // });

        //     FocusListener focusListener =
        //             new FocusListener() {
        //                 @Override
        //                 public void focusGained(FocusEvent e) {
        //                     System.out.println("tryckt");
        //                     setBackground(Color.YELLOW);
        //                     //setSelectedCell(coordinate);
        //                 }

        //                 @Override
        //                 public void focusLost(FocusEvent e) {
        //                     System.out.println("focus lost");
        //                     // Handle focus lost event (e.g., when the text field loses focus)
        //                     System.out.println("Focus Lost");
        //                     setBackground(Color.WHITE);
        //                 }
        //             };
        //     this.addFocusListener(focusListener);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
