# Brikks

A terminal implementation of the block-placing board game Brikks, written in
plain Java with no external dependencies. Blocks fall into a grid, settle on
what is already there, and score points for the rows they complete.

## Gameplay

Each turn the game deals a block: one of five colours in one of several shapes.
You choose a column, the block drops to the lowest free row, and the sheet is
scored. Alongside the grid, a player tracks two resources — **bombs**, for
clearing an awkward placement, and **energy**.

The board is drawn with ANSI escape codes, so blocks appear in their actual
colour in any terminal that supports them.

## Running

Requires a JDK (developed against 21).

```bash
javac -d out main/java/*.java
java -cp out Main
```

You are dropped into a menu with three options: start a game, read the
instructions, or exit.

## Structure

| File | Role |
| --- | --- |
| `Main.java` | entry point, owns the `Scanner` |
| `Menu.java` | menu loop and navigation |
| `Game.java` | turn loop, block generation, shape and colour tables |
| `Sheet.java` | the grid — placement, drop position, scoring |
| `Block.java` | a single block: colour and shape matrix |
| `Player.java` | name, sheet, bombs, energy |
| `Colors.java` | ANSI colour constants |
