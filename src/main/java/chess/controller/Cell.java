package chess.controller;

import java.util.List;

import chess.controller.pieces.Piece;

public class Cell {
    private Piece piece;
    private final char columnLetter;
    private final int rowNum;

    private List<Position> movableCells;
    private List<Position> capturableCells;

    public Cell(char columnLetter, int rowNum) {
        this.columnLetter = columnLetter;
        this.rowNum = rowNum;
        this.piece = null;
        movableCells = null;
        capturableCells = null;
    }

    public List<Position> getMovableCells() {
        movableCells = piece.generateMovableCells();
        return movableCells;
    }

    public List<Position> getCapturableCells() {
        capturableCells = piece.generateCapturableCells();
        return capturableCells;
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
}
