package xl.expr;

public class Coordinate implements Comparable<Coordinate> {
    private int row;
    private int col;

    public Coordinate(String name) {
        this.row = getRow(name);
        this.col = getCol(name);
    }

    private static int getRow(String name) {
        return name.charAt(0) - 'A' + 1;
    }

    private static int getCol(String name) {
        return Integer.parseInt(name.substring(1));
    }

    @Override
    public String toString() {
        return String.valueOf((char) ('A' + row - 1)) + col;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Coordinate)) return false;

        Coordinate otherCoordinate = (Coordinate) other;
        return row == otherCoordinate.row && col == otherCoordinate.col;
    }

    @Override
    public int compareTo(Coordinate o) {
        return toString().compareTo(o.toString());
    }
}
