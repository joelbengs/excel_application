package xl.expr.factories;

import xl.expr.Cell;
import xl.expr.Comment;

public class CommentFactory implements CellFactory {
    @Override
    public Cell build(String contents) {
        // strip leading '#'
        var text = contents.substring(1);
        return new Comment(text);
    }
}
