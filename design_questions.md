# Design Questions

## Overview

### A1: What are the roles of the M, V, and C in our program?

- M: To model the logic related to data of the application.
- V: To present the model to the end user.
- C: To control the view and the model.

### A2: What classes are visible in the figure above?

Most, if not all, of the classes in the GUI package and its subpackage `gui.menu`.

### A3: Explain what each of the classes `SlotLabel`, `SlotLabels`, `Editor`, `StatusLabel`, `CurrentLabel`, and `XL` in the provided code does

### A4: Use case: someone types in 42 into `Editor` - what should happen for us to be able to see the value in the spreadsheet (which way does this value take through the M, V, and C)?

## Model

### B1: How are addresses represented (this has already been decided upon in the provided code)? Can you come up with an alternative representation?

### B2: How can we model the fact that a cell can contain either an expression or a comment?

### B3: What kind of cell do we get when we enter the value 42 into the editor?

### B4: What classes are needed for the model, apart from those in the `expr` package?

### B5: What class keeps track of all the cells?

### B6: What data structure is best suited to keep track of the cells?

### B7: The assignment above contains the following wording. How do we ensure that we do not use more memory than required?

"The customer has expressed the requirement that the memory usage for the spreadsheet model must not depend on the size of the sheet, but only on the amount of information that has been inputted."

## Model: `Environment`

### C1: When an expression containing an address is computed, we use `Environment` -- why?

### C2: Which class should implement `Environment`?

### C3: What practical use do we have from the `Environment` interface, before writing any code?

### C4: What principle dictates that we use the `Environment` interface?

## Relation between M and C/V

### D1: Which classes in the model does or GUI need to know about?

### D2: When our GUI accesses values to write in a `SlotLabel` or `SlotLabels`, what values and what type do we want to get back?

### D3: When the user clicks in a cell, and our `Editor` gets updated, what value, and what type do we want to get back, to put in the editor?

## Relation between M and C/V: error handling

### E1: Use case: the user enters an invalid formula -- what should happen?

### E2: Use case: the user edits a cell so that division by zero occurs somewhere else in the sheet -- what should happen?

### E3: Use case: the user edits a cell so that a circular reference is created -- what should happen?

### E4: Overview: what package is responsible for error detection?

### E5: Overview: what package is responsible for error handling, and how?

## Control

### F1: What kind of synchronization (Flow Synchronization or Observer Synchronization) do you want to use between the M and the V/C?

### F2: How does the system keep track of which cell is currently selected? (This depends on how the `Controller` is implemented)

### F3: How are GUI updates triggered? (This depends on how the `Controller` is implemented)

## Miscellaneous

### G1: How are circular references detected?
