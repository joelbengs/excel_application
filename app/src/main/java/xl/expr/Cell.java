package xl.expr;

public interface Cell {
    double value(Environment env);

    String toString();

    String toString(int prec);
}
