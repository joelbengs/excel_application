package xl.expr;

import java.util.Map;
import java.util.Optional;
import xl.expr.factories.InputParser;
import xl.gui.SheetPanel;

public class TestExpr {

    public static void main(String[] args) {
        InputParser parser = new InputParser();
        Cell expr = parser.parse("1+2*3");
        System.out.println(expr);
        System.out.println(expr.value(null));
        expr = parser.parse("#this is a comment");

        System.out.println(expr);
        System.out.println(expr.value(null));

        expr = parser.parse("A3+A2*A1");
        Environment env =
                new Environment() {
                    @Override
                    public Optional<Double> value(Coordinate coordinate) {
                        String name = coordinate.toString();
                        if (name.equals("A3")) return Optional.of(1.0);
                        if (name.equals("A2")) return Optional.of(2.0);
                        if (name.equals("A1")) return Optional.of(3.0);
                        System.out.println(name + " is undefined");
                        return Optional.of(0.0);
                    }

                    @Override
                    public void addToSheet(Coordinate coordinate, String input) {
                        // do nothing
                    }

                    @Override
                    public Map<Coordinate, Cell> getRepository() {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "Unimplemented method 'getRepository'");
                    }

                    @Override
                    public Optional<String> stringValue(Coordinate coordinate) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "Unimplemented method 'stringValue'");
                    }

                    @Override
                    public Optional<String> gridContent(Coordinate coordinate) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "Unimplemented method 'gridContent'");
                    }

                    @Override
                    public void clearCell(Coordinate coordinate) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "Unimplemented method 'removeCell'");
                    }

                    @Override
                    public void clearAllCells() {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "Unimplemented method 'clearAllCells'");
                    }

                    @Override
                    public void externalNotify() {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "Unimplemented method 'externalNotify'");
                    }

                    @Override
                    public InputParser getInputParser() {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException(
                                "Unimplemented method 'getInputParser'");
                    }

                    @Override
                    public void addObserver(SheetPanel sheetPanel) {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
                    }
                };
        // System.out.println(expr);
        // System.out.println(expr.value(env));
    }
}
