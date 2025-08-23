package chess.controller;

import java.awt.Point;

import chess.controller.pieces.Piece;

public class Cell {
    private Piece piece;
    private final char columnLetter;
    private final int rowNum;

    public Cell(char columnLetter, int rowNum) {
        this.columnLetter = columnLetter;
        this.rowNum = rowNum;
        this.piece = null;
    }

    @Override
    public String toString() {
        return columnLetter + "" + rowNum;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public char getColumnLetter() {
        return columnLetter;
    }

    public int getRowNum() {
        return rowNum;
    }

    public Point getPosition() {
        return new Point((char)(columnLetter - 'a'), rowNum - 1);
    }

}
