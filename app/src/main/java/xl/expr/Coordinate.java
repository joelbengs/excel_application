package xl.expr;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate(String name) {
        this.row = getRow(name);
        this.col = getCol(name);
    }

    private int getRow(String name) {
        return name.charAt(0) - 'A' + 1;
    }

    private int getCol(String name) {
        return Integer.parseInt(String.valueOf(name.charAt(1)));
    }
}
