package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
This class represents a column in the board
 */
public class Column implements Iterable<Cell> {
    private char columnLetter;
    private List<Cell> cells = new ArrayList<>();

    //Creates a column with 8 cells in it
    public Column(char columnLetter, boolean isWhite) {
        this.columnLetter = columnLetter;
        for (int x = 0; x < 8; x++) {
            cells.add(new Cell(isWhite, columnLetter, x + 1));
            isWhite = !isWhite;
        }
    }



    @Override
    public Iterator<Cell> iterator() {
        return cells.iterator();
    }

    public char getColumnLetter() {
        return columnLetter;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
