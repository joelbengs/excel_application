package xl.expr;

import xl.expr.factories.InputParser;

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
                    public double value(Coordinate coordinate) {
                        String name = coordinate.toString();
                        if (name.equals("A3")) return 1;
                        if (name.equals("A2")) return 2;
                        if (name.equals("A1")) return 3;
                        System.out.println(name + " is undefined");
                        return 0;
                    }

                    @Override
                    public void addToSheet(Coordinate coordinate, Cell cell) {
                        // do nothing
                    }
                };
        System.out.println(expr);
        System.out.println(expr.value(env));
    }
}
