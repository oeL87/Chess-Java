package Model;

import java.util.ArrayList;
import java.util.List;

/*
This class represents a single square on the board
 */
public class Cell {
    private boolean hasPiece = false;
    private boolean isCellWhite;
    private boolean isCellHighlighted = false;
    private boolean isCellMove = false;
    private boolean isCellCapture = false;
    private char columnLetter;
    private int rowNum;
    private Piece piece;

    private List<Cell> nextMoveableCells = new ArrayList<>();
    private List<Cell> nextCapturableCells = new ArrayList<>();
    //Knight moves:
    private Cell upLeft;
    private Cell leftUp;
    private Cell downLeft;
    private Cell leftDown;
    private Cell rightUp;
    private Cell upRight;
    private Cell rightDown;
    private Cell downRight;
    //Castling
    private Cell queenSide;
    private Cell kingSide;

    //Creates an empty cell with nothing in it
    public Cell(boolean isCellWhite, char columnLetter, int rowNum) {
        this.isCellWhite = isCellWhite;
        this.columnLetter = columnLetter;
        this.rowNum = rowNum;
        piece = null;
    }

    //Only for pawns
    public void generatePawnCaptures() {
        if (columnLetter > 'a') {
            nextCapturableCells.add(new Cell(true, Character.toString(columnLetter - 1).charAt(0),
                    rowNum + (piece.isPieceWhite() ? 1 : - 1)));
        }
        if (columnLetter < 'h') {
            nextCapturableCells.add(new Cell(true, Character.toString(columnLetter + 1).charAt(0),
                    rowNum + (piece.isPieceWhite() ? 1 : - 1)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
//        System.out.println(columnLetter + " " + cell.columnLetter);
//        System.out.println(rowNum + " " + cell.rowNum);
        return columnLetter == cell.columnLetter && rowNum == cell.rowNum;
    }

    @Override
    public String toString() {
        return columnLetter + "" + rowNum;
    }

    //GETTERS and SETTERS
    public boolean isHasPiece() {
        return hasPiece;
    }

    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    public boolean isCellWhite() {
        return isCellWhite;
    }

    public void setCellWhite(boolean cellWhite) {
        isCellWhite = cellWhite;
    }

    public char getColumnLetter() {
        return columnLetter;
    }

    public void setColumnLetter(char columnLetter) {
        this.columnLetter = columnLetter;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        hasPiece = piece != null;
//        if (piece == null) {
//            hasPiece = false;
//            nextCapturableCells = null;
//            nextMoveableCells = null;
//        }
    }

    public void setCellHighlighted(boolean isCellHighlighted) {
        this.isCellHighlighted = isCellHighlighted;
    }

    public boolean isCellHighlighted() {
        return isCellHighlighted;
    }

    public Cell getUpLeft() {
        return upLeft;
    }

    public void setUpLeft(Cell upLeft) {
        this.upLeft = upLeft;
    }

    public Cell getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Cell leftUp) {
        this.leftUp = leftUp;
    }

    public Cell getDownLeft() {
        return downLeft;
    }

    public void setDownLeft(Cell downLeft) {
        this.downLeft = downLeft;
    }

    public Cell getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(Cell leftDown) {
        this.leftDown = leftDown;
    }

    public Cell getRightUp() {
        return rightUp;
    }

    public void setRightUp(Cell rightUp) {
        this.rightUp = rightUp;
    }

    public Cell getUpRight() {
        return upRight;
    }

    public void setUpRight(Cell upRight) {
        this.upRight = upRight;
    }

    public Cell getRightDown() {
        return rightDown;
    }

    public void setRightDown(Cell rightDown) {
        this.rightDown = rightDown;
    }

    public Cell getDownRight() {
        return downRight;
    }

    public void setDownRight(Cell downRight) {
        this.downRight = downRight;
    }

    public Cell getQueenSide() {
        return queenSide;
    }

    public void setQueenSide(Cell queenSide) {
        this.queenSide = queenSide;
    }

    public Cell getKingSide() {
        return kingSide;
    }

    public void setKingSide(Cell kingSide) {
        this.kingSide = kingSide;
    }

    public boolean isCellMove() {
        return isCellMove;
    }

    public void setCellMove(boolean isCellMove) {
        this.isCellMove = isCellMove;
    }

    public boolean isCellCapture() {
        return isCellCapture;
    }

    public void setCellCapture(boolean isCellCapture) {
        this.isCellCapture = isCellCapture;
    }

    public List<Cell> getNextMoveableCells() {
        return nextMoveableCells;
    }

    public void setNextMoveableCells(List<Cell> nextMoveableCells) {
        this.nextMoveableCells = nextMoveableCells;
    }

    public List<Cell> getNextCapturableCells() {
        return nextCapturableCells;
    }

    public void setNextCapturableCells(List<Cell> nextCapturableCells) {
        this.nextCapturableCells = nextCapturableCells;
    }
}


