package xl.expr;

public class Coordinate implements Comparable<Coordinate> {
    private int row;
    private int col;

    public Coordinate(String name) {
        this.row = getRow(name);
        this.col = getCol(name);
    }

    private static int getRow(String name) {
        // extract just the letters from the name
        var letters = name.replaceAll("\\d.*", "");
        // reverse the letters to make the last letter the least significant digit
        var reversedLetters = new StringBuilder(letters).reverse().toString();
        // consider each letter as a base-26 digit
        int value = 0;
        for (int i = 0; i < reversedLetters.length(); i++) {
            var letterValue = (reversedLetters.charAt(i) - 'A' + 1);
            value += letterValue * Math.pow(26, i);
        }
        return value;
    }

    private static int getCol(String name) {
        return Integer.parseInt(name.replaceAll("[^0-9]", ""));
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
