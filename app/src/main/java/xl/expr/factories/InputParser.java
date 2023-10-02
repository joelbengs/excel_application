package xl.expr.factories;

import xl.expr.Cell;

public class InputParser {

    private ExprFactory exprFactory;
    private CommentFactory commentFactory;

    public InputParser() {
        this.exprFactory = new ExprFactory();
        this.commentFactory = new CommentFactory();
    }

    public Cell parse(String contents) {
        if (contents.charAt(0) == '#') {
            return parseAsComment(contents);
        } else {
            return parseAsExpr(contents);
        }
    }

    private Cell parseAsComment(String contents) {
        return this.commentFactory.build(contents);
    }

    private Cell parseAsExpr(String contents) {
        return this.exprFactory.build(contents);
    }
}
