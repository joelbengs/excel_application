package xl.expr;

public class Comment implements Cell {

    /** The message of the comment, without the leading '#'. */
    private String message;

    public Comment(String message) {
        this.message = message;
    }

    @Override
    public double value(Environment env) {
        return 0; // comment slots return the value 0
    }

    @Override
    public String gridString(Environment env) {
        return message;
    }

    @Override
    public String toString() {
        return "#" + message;
    }
}
