# Design Questions

## Overview

### A1: What are the roles of the M, V, and C in our program?

- M: To model the logic related to data of the application.
- V: To present the model to the end user.
- C: To control the view and the model.

### A2: What classes are visible in the figure above?

Most, if not all, of the classes in the GUI package and its subpackage `gui.menu`.

### A3: Explain what each of the classes `SlotLabel`, `SlotLabels`, `Editor`, `StatusLabel`, `CurrentLabel`, and `XL` in the provided code does

The classes are located in the gui package.

- A `SlotLabel` displays the contents of one cell in the spreadsheet. `SlotLabel` extends `ColoredLabel`, which in turn extends `JLabel` – a Swing GUI element for displaying strings.

- `SlotLabels` is the view for all of the cells in the spreadsheet. This class has a field with the corresponding `SlotLabel` for each position in the cell grid.

- `Editor` extends `JTextField` and as such, it's a textfield that the user can edit text with. It is the users main point of interaction with the application. The purpose of this text field is to enable the user to write values and formulas to be assigned to a cell. The `Editor` view is presented above the cells, next to the status bar.

- The purpose of the `StatusLabel` class is to present status messages to the user. This label is presented next to a label showing the currently selected cell. They are bundled together in the `StatusPanel` class. The `StatusLabel` class implements `Observer` to be able to react to changes in the application state and update itself accordingly.

- `CurrentLabel` is a view displaying the currently selected cell. It is bundled together with a `StatusLabel` instance in a `StatusPanel` instance.

- `XL` is the main class of the application. It initializes the various Model instances and sets up the views. By having a list of currently active spreadsheets, it supports opening several spreadsheets at once.

### A4: Use case: someone types in 42 into `Editor` - what should happen for us to be able to see the value in the spreadsheet (which way does this value take through the M, V, and C)?

1. The editor (part of the View) registers "42" as an input string by the user.
2. The Controller needs to be alerted in some way that the text in the text field has been updated and react to it.
3. The Controller will update the model which the rest of the View will react to, making it update.

The path the value takes is therefore View ➡️ Controller ️➡️ ️️Model ➡️ View.

## Model

### B1: How are addresses represented (this has already been decided upon in the provided code)? Can you come up with an alternative representation?

Internally, the cells are represented as a one-dimensional list (more specifically an `ArrayList`), with the zeroeth element being the top-left cell, and the last element the bottom-right cell. To the user, the cells are represented by a letter indicating the column and a number indicating the row, for example 'A1' for the top-left cell.

If one wishes to have a more similar representation internally as the one presented to the user, one could store the cell contents in two dimensions, for example a matrix or a list of lists.

A more 'absurd' representation is a variant of a linked list, where each cell contains references to its four neighbouring nodes. A pro of this implementation would be fast access to neighbouring nodes, and a good basis for implementing algorithms that depend on neighbours. If our sheet were to be the basis for a graph database, this could be used to enable nodes and vectors.

### B2: How can we model the fact that a cell can contain either an expression or a comment?

We could implement an interface (or abstract class) for cell contents, which is implemented by two classes: `Expression` and `Comment`. Please note that this representation is used in the model only and is separate from what is displayed in each `SlotLabel`, which preferably could still store its contents to be displayed as a simple `String`.

### B3: What kind of cell do we get when we enter the value 42 into the editor?

An instance of the `Num` class, which extends the abstract class `Expr`

### B4: What classes are needed for the model, apart from those in the `expr` package?

We need a `Cell` interface/abstract class, that is implemented/subclassed by `Expr` and `Comment`.

We also need a model for all the cells in the sheet. This could preferably be a class implementing the `Environment` interface. This is different from the `SlotsLabels` class which is only responsible for _displaying_ the cells. class.

### B5: What class keeps track of all the cells?

A class implementing the `Environment` interface.

### B6: What data structure is best suited to keep track of the cells?

A list or matrix is the data structure that immediately comes to mind, but this would violate the customer requirement that the memory usage only be dependent on the information actually inputted – by doing it this way we need an instance for each location in the sheet regardless of it being filled in or not.

A simple way of doing it would be to have some kind of `Map` mapping the cell index to each cell. If the cell index is encapsulated in a `CellCoordinate` class the type of the cell store would be `Map<CellCoordinate, Cell>`. HashMap would be suitable for fast access, but a difficulty is to override hashCode(). We had an interesting discussion about the posibility to guarantee a unique mapping of R^2 to R, continous or discreet. With the finite nature of `int`, we are sure that we can't map each unique `CellCoordinate` (`row, col`) to a unique hashCode.

### B7: The assignment above contains the following wording. How do we ensure that we do not use more memory than required?

"The customer has expressed the requirement that the memory usage for the spreadsheet model must not depend on the size of the sheet, but only on the amount of information that has been inputted."

This was covered under our B6-discussion.

## Model: `Environment`

### C1: When an expression containing an address is computed, we use `Environment` -- why?

Interface `Expr` specifies the method `value(Environment): double`, which returnts the value of the expression. The parameter `Environment` is used to retrieve the values of the variables that the expression referes to. E.g. a cell in the spreadsheet can contain the logic `a1 + a2`. Given that the variable have values, `a1 = 3`, `a2 = 5`, the call to `Expr.value(Environment)` should then return `8`.

### C2: Which class should implement `Environment`?

Our proposed `MapEnvironment` class.

Are we allowed to change the signature of `Environment.value(String)`? In that case we would prefer for the method to take one of our own `CellCoordinate` values.

### C3: What practical use do we have from the `Environment` interface, before writing any code?

Because we are forced to not make the concrete implementation and the rest of the program depend on each other too much, by specifying (a very simple) way for them to communicate. This supports high cohesion and low coupling.

### C4: What principle dictates that we use the `Environment` interface?

Dependency Inversion Principle (DIP) – by programming against an interface instead of a concrete implementation.

## Relation between M and C/V

### D1: Which classes in the model does the GUI need to know about?

It needs to have access to some class that can give it a `String` to put in its `SlotLabel`. This can come either from the `toString` method in the `Cell` class or some kind of facade pattern class implemented specifically for this purpose. By doing it this way we achieve a loose coupling between the various parts of our system.

### D2: When our GUI accesses values to write in a `SlotLabel` or `SlotLabels`, what values and what type do we want to get back?

We want the GUI to be able to access `String` representations of all the various `Cell` types.

### D3: When the user clicks in a cell, and our `Editor` gets updated, what value, and what type do we want to get back, to put in the editor?

We want a `String` representation of what is currently in the cell. This `String` needs to be in the right format so that it can be parsed again into a `Cell`.

## Relation between M and C/V: error handling

### E1: Use case: the user enters an invalid formula -- what should happen?

The controller should display a descriptive error message in the status bar.

__What in the program is responsible for enforcing this behaviour?__

This could eiter be enforced directly in the Editor (which is in the View), or in the Controller, or in the Model. A benefit of doing the check in the Model is that we achieve lower coupling - only the model can be sure about what values are valid.

### E2: Use case: the user edits a cell so that division by zero occurs somewhere else in the sheet -- what should happen?

The cell contents should be invalidated (i.e. display something so that the User knows where the error has occured) as well as an error message shown in the status bar.

__What in the program is responsible for enforcing this behaviour?__

This logic should be imlemented in the Model.

### E3: Use case: the user edits a cell so that a circular reference is created -- what should happen?

The circular reference should be detected, all cells affected should halt their updates (to prevent infinite loops adversely affecting performance), and an error message displayed in the status bar.

__What in the program is responsible for enforcing this behaviour?__

This logic should be implemented in the Model.

### E4: Overview: what package is responsible for error detection?

The model (i.e. the `expr` package).

### E5: Overview: what package is responsible for error handling, and how?

The Controller, because it has access both to the view (to display for example error messages) and the model. The View will only communicate errors to the user.

## Control

### F1: What kind of synchronization (Flow Synchronization or Observer Synchronization) do you want to use between the M and the V/C?

We want to imlemement Observer Synchronization.

### F2: How does the system keep track of which cell is currently selected? (This depends on how the `Controller` is implemented)

Through the Observer Synchronization.

### F3: How are GUI updates triggered? (This depends on how the `Controller` is implemented)

Through the Observer Synchronization.

## Miscellaneous

### G1: How are circular references detected?

We add the open source version of Neo4J's graph database as a dependency. We let each cell be a node, and any interdependencies are edges in the graph. Once we have a closed loop in the graph, we have a circular dependencies in the spreadsheet.
