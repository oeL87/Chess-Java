package Model;

import java.awt.*;

/*
This class represents a Piece on the board
 */
public class Piece {
    private char columnLetter;
    private int rowNum;
    private int movableCells;
    private boolean doubleMove;
    private boolean canCastle;
    private final boolean pieceWhite;
    private final PieceType pieceType;
    private final ChessImage image;

    //Creates a new piece based on type, location and which team it is on. Pulls the corresponding image
    public Piece(PieceType pieceType, char columnLetter, int rowNum, boolean pieceWhite) {
        this.pieceType = pieceType;
        this.columnLetter = columnLetter;
        this.rowNum = rowNum;
        this.pieceWhite = pieceWhite;

        image = new ChessImage("./data/Chess_" +
                (pieceWhite ? "White_" : "Black_") +
                pieceType.getImageName() + ".png");

        doubleMove = pieceType == PieceType.PAWN;
        canCastle = pieceType == PieceType.KING || pieceType == PieceType.ROOK;
        movableCells = pieceType == PieceType.KING ? 8 : 0;
    }

    //GETTERS and SETTERS
    public Image getImage() {
        return image.getImage();
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

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isPieceWhite() {
        return pieceWhite;
    }

    public boolean isDoubleMove() {
        return doubleMove;
    }

    public void setDoubleMove(boolean doubleMove) {
        this.doubleMove = doubleMove;
    }

    public void setCanCastle(boolean canCastle) {
        this.canCastle = canCastle;
    }

    public boolean canCastle() {
        return canCastle;
    }
}
