package xl.expr;

public class Comment implements Cell {

    String message;

    public Comment(String message) {
        this.message = message;
    }

    public double value(Environment env) {
        return 0; // comment slots return the value 0
    }

    @Override
    public String toString() {
        return "#" + message;
    }
}
