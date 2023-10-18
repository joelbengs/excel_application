package xl.expr;

public interface Cell {
    /**
     * Returns the value of the cell, as evaluated in the given environment.
     *
     * @param env The environment in which to evaluate the cell.
     * @return The value of the cell.
     */
    double value(Environment env);

    /**
     * Returns a string representation of the cell, as shown in the editor. For example: "=(A1+B1)".
     */
    String toString();

    /**
     * Returns a string representation of the cell, as shown in the grid. In most cases this is just
     * a string representation of the cell's value, but it can be something else. For example,
     * comments are shown as its comment string.
     *
     * @param env The environment in which to evaluate the cell.
     * @return The string representation of the cell.
     */
    String gridString(Environment env);
}
