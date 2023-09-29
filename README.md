# EDAF60 - grupp 46: XL

+ Joel Bengs (jo5531be-s)
+ Gustaf Jonson Stamfält (gu3505jo-s)
+ Victor Pekkari (vi8011pe-s)
+ Henrik Vester (he5834ve-s)

## Svar på designfrågor

![Model](images/model.png)

+ **A3**: Förklara i bara några få ord vad var och en av klasserna
          `SlotLabel`, `SlotLabels`, `Editor`, `StatusLabel`,
          `CurrentLabel` och `XL` i den utdelade koden gör.

The classes are located in the gui package.

+ A `SlotLabel` displays the contents of one cell in the spreadsheet. `SlotLabel` extends `ColoredLabel`, which in turn extends `JLabel` – a Swing GUI element for displaying strings.

+ `SlotLabels` is the view for all of the cells in the spreadsheet. This class has a field with the corresponding `SlotLabel` for each position in the cell grid.

+ `Editor` extends `JTextField` and as such, it's a textfield that the user can edit text with. It is the users main point of interaction with the application. The purpose of this text field is to enable the user to write values and formulas to be assigned to a cell. The `Editor` view is presented above the cells, next to the status bar.

+ The purpose of the `StatusLabel` class is to present status messages to the user. This label is presented next to a label showing the currently selected cell. They are bundled together in the `StatusPanel` class. The `StatusLabel` class implements `Observer` to be able to react to changes in the application state and update itself accordingly.

+ `CurrentLabel` is a view displaying the currently selected cell. It is bundled together with a `StatusLabel` instance in a `StatusPanel` instance.

+ `XL` is the main class of the application. It initializes the various Model instances and sets up the views. By having a list of currently active spreadsheets, it supports opening several spreadsheets at once.

+ **A4**: Användningsfall: Någon skriver talet 42 i `Editor`, vad
          skall hända innan värdet syns i vyn (dvs vilken väg skall
          värdet gå genom M, V och C)?

1. The editor (part of the View) registers "42" as an input string by the user.
2. The Controller needs to be alerted in some way that the text in the text field has been updated and react to it.
3. The Controller will update the model which the rest of the View will react to, making it update.

The path the value takes is therefore View ➡️ Controller ️➡️ ️️Model ➡️ View.

+ **B2**: En cell kan innehålla antingen en kommentar eller ett
          uttryck hur modellerar vi det i Javakod?

We can implement an interface (or abstract class) for cell contents, which is implemented by two classes: `Expression` and `Comment`. This is dependency inversion. Please note that this representation is used in the model only and is separate from what is displayed in each `SlotLabel`, which preferably could still store its contents to be displayed as a simple `String`.

+ **B4**: Vilka klasser, förutom dem i `expr`-paketet, kommer vi att
          behöva i vår modell?

We need a `Cell` interface/abstract class, that is implemented/subclassed by `Expr` and `Comment`.

We also need a model for all the cells in the sheet. This could preferably be a class implementing the `Environment` interface. This is different from the `SlotsLabels` class which is only responsible for _displaying_ the cells. class.

+ **C1**: När ett uttryck som innehåller en adress beräknas använder
          vi en `Environment` -- varför?

We use the intercace `Environment` in place of its implementation `sheet` in order to achieve dependency inversion, an important SOLID principle.

Interface `Expr` specifies the method `value(Environment): double`, which returnts the value of the expression. The parameter `Environment` is used to retrieve the values of the variables that the expression referes to. E.g. a cell in the spreadsheet can contain the logic `a1 + a2`. Given that the variable have values, `a1 = 3`, `a2 = 5`, the call to `Expr.value(Environment)` should then return `8`.

+ **D1**: Vilka klasser i modellen måste vara kända av vårt GUI?

It needs to have access to some class that can give it a `String` to put in its `SlotLabel`. This can come either from the `toString` method in the `Cell` class or some kind of facade pattern class implemented specifically for this purpose. By doing it this way we achieve a loose coupling between the various parts of our system.

+ **D2**: När vårt GUI hämtar 'värdet' i en cell, för att visa det i
          rutnätet, vilket värde är det vi egentligen vill ha, och
          vilken typ bör det ha?

We want the GUI to be able to access `String` representations of all the various `Cell` types.

+ **E4**: Allmänt: vilket paket bör upptäcka fel?

The model (i.e. the `expr` package).

+ **E5**: Allmänt: vilket paket bör hantera fel, och hur gör vi det?

The Controller, because it has access both to the view (to display for example error messages) and the model. The View will only communicate errors to the user. The model only rejects new cell entries.

+ **F1**: Vilken slags synkronisering (_Flow Synchronization_ eller
          _Observer Synchronization_) vill gruppen använda för
          kommunikationen mellan M och V/C?

We want to imlemement Observer Synchronization.

+ **F2**: Hur håller programet reda vilket som är den aktuella
          cellen? Svaret på denna fråga beror på hur ni
          implementerar er Controller.

It should not be dealt with in the model (i,e, not in the Sheet that implements Environment). Either the view or the controller keeps track of this.

+ **F3**: Hur triggas uppdateringar i ert GUI? Svaret på denna fråga
          beror av hur ni implementerar er Controller.

Through the Observer Synchronization.

## Att köra programmet

För att köra programmet kan man skriva:

~~~{.sh}
./gradlew run
~~~

i projektets rot-katalog.

## Klassdiagram

~~~mermaid
classDiagram
    class Cell {
        +value(Environment): Double
        +toString() : String
    }
    <<Interface>> Cell

    Cell <|.. Expr
    Cell <|.. Comment
    Cell <|.. Bomb

    class Expr{
        + toString() String
        + toString(Int)* String
        + value(Environment)* Double
    }
    <<Abstract>> Expr

    Expr <|.. Num
    Expr <|.. BinaryExpr
    Expr <|.. Variable

    Expr o-- BinaryExpr

    class Num {
        -value : Double
        +Num(Double)
        +value(Environment) Double
        +toString(Int) String
    }

    class BinaryExpr {
        - expr1 : Expr
        - expr2 : Expr
        # precedence1 : Int
        # precedence2 : Int
        #BinaryExpr(Expr, Expr)
        #op(Double, Double)* : Double
        #opString()* : String
        +value(Environment): Double
        +toString(Int) : String
    }
    <<Abstract>> BinaryExpr
    BinaryExpr <|.. Add
    BinaryExpr <|.. Sub
    BinaryExpr <|.. Mul
    BinaryExpr <|.. Div

    class Add {
        +Add(Expr, Expr)
        +op(Double, Double) : Double
        #opString() : String
    }

    class Sub {
        +Sub(Expr, Expr)
        +op(Double, Double) : Double
        #opString() : String
    }

    class Mul {
        +Mul(Expr, Expr)
        +op(Double, Double) : Double
        #opString() : String
    }

    class Div {
        +Div(Expr, Expr)
        +op(Double, Double) : Double
        #opString() : String
    }

    class Variable {
        -name : String
        +Variable(String)
        +toString(Int) : String
        +value(Environment) : Double
    }

    class Comment{
        - text : String
        + Comment(String)
        + value(Environment): Double
        + toString(Environment) : String
    }

    class Bomb{
        +value() : Double
        +toString() : String
    }

    class Environment{
        + value(String) : Double
    }
    <<Interface>> Environment

    class Sheet {
        - map : TreeMap~Coordinate-Cell~
    }

    class Coordinate {
        - row : Int
        - col : Int
        + Coordinate(Int, Int)
        + getRow() : Int
        + getCol() : Int
    }

    Environment <|.. Sheet

    Coordinate -- Sheet

    class ExprParser {
        + build(Reader) : Expr
    }

~~~
